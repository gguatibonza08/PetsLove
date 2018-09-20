package co.com.petslove.petslovers;

import android.content.Intent;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
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

    public static final int SIGN_IN_CODE = 777;

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account == null) {

        } else {

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
        } else {
            Snackbar.make(loginLayout, getString(R.string.errorSesion), Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.googleLoginActivity:
                signIn();
                break;
            case R.id.registroLoginActivity:
                logOut();
                break;

            case R.id.ingresarLoginActivity:
                revocar();
                break;
        }

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
