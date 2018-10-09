package co.com.petslove.petslovers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    private Button registro, ingreso;
    private RelativeLayout loginLayout;
    private SignInButton google;
    private EditText correoUsuario, contrasenaUsuario;
    private GoogleApiClient googleApiClient;
    private boolean logueado = false;
    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        logueado = preferences.getBoolean("logeado", false);
        // GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (logueado == true) {
            goMain();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referencias();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void referencias() {
        registro = findViewById(R.id.registroLoginActivity);
        ingreso = findViewById(R.id.ingresarLoginActivity);
        loginLayout = findViewById(R.id.layoutLogin);
        google = findViewById(R.id.googleLoginActivity);
        google.setSize(SignInButton.SIZE_WIDE);
        google.setColorScheme(SignInButton.COLOR_DARK);
        correoUsuario = findViewById(R.id.correoLoginActivity);
        contrasenaUsuario = findViewById(R.id.contrasenaLoginActivity);
        google.setOnClickListener(this);
        registro.setOnClickListener(this);
        ingreso.setOnClickListener(this);
    }

    private void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, SIGN_IN_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult resultado = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(resultado);
        }
    }

    private void handleSignInResult(GoogleSignInResult resultado) {
        if (resultado.isSuccess()) {
            GoogleSignInAccount account = resultado.getSignInAccount();
            crearPreferencias(account.getDisplayName(), account.getEmail(), account.getPhotoUrl().toString());
            goMain();
        } else {
            Snackbar.make(loginLayout, getString(R.string.errorSesion), Snackbar.LENGTH_LONG).show();
        }
    }

    private void goMain() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void crearPreferencias(String nombre, String correo, String foto) {
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        logueado = true;
        editor.putBoolean("logeado", logueado);
        editor.putString("correo", correo);
        editor.putString("nombre", nombre);
        editor.putString("fotoperfil", foto);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.googleLoginActivity:
                signIn();
                break;

            case R.id.registroLoginActivity:
                Intent intent = new Intent(this, RegistroActivity.class);
                startActivity(intent);
                //logOut();
                break;

            case R.id.ingresarLoginActivity:
                //revocar();
                loginManual();
                break;
        }
    }

    private void loginManual() {
        String correoUs = correoUsuario.getText().toString();
        String contrasenaUs = contrasenaUsuario.getText().toString();
        //mientras conecto el web services
        crearPreferencias(contrasenaUs, correoUs, "");
        goMain();
       /* OkHttpClient cliente = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("password", contrasenaUs)
                .add("correo", correoUs)
                .build();
        Request request = new Request.Builder()
                .url(getString(R.string.loginManual))
                .post(formBody)
                .build();

        cliente.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Snackbar.make(loginLayout, "Fallo la conexión", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String rta = response.body().string();
                    Gson gson = new Gson();
                    RespuestaRest respuesta = gson.fromJson(rta, RespuestaRest.class);
                    Usuario user = respuesta.getUser();


                    if (respuesta.isConfirmacion()) {
                        crearPreferencias(user.getNombre(), user.getCorreo(), "");
                        goMain();
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(loginLayout, getString(R.string.novalido), Snackbar.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });*/

    }

    private void revocar() {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "revocar", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "no se pudo revocar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void logOut() {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    Toast.makeText(getApplicationContext(), "cerrar sesión", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "no se pudo cerrar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
}
