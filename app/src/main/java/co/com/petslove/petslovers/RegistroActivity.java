package co.com.petslove.petslovers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import co.com.petslove.petslovers.model.rtaWS.RespuestaRest;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton foto;
    private boolean logueado;
    private Button registrar;
    private ImageView prueba;
    private EditText nombre, apellido, contrasena, contrasena2, correo;
    private String name, lastName, password, code, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        referenciar();
    }

    private void referenciar() {
        correo = findViewById(R.id.correoRegistro);
        foto = findViewById(R.id.fotoRegistro);
        prueba = findViewById(R.id.prueba);
        nombre = findViewById(R.id.nombreRegistro);
        apellido = findViewById(R.id.apellidoRegistro);
        contrasena = findViewById(R.id.contrasenaRegistro);
        contrasena2 = findViewById(R.id.contrasena2Registro);
        registrar = findViewById(R.id.registrar);
        foto.setOnClickListener(this);
        registrar.setOnClickListener(this);
    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la aplicacion"), 10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                Uri imageUri = data.getData();
                prueba.setImageURI(imageUri);
                Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                code = Base64.encodeToString(b, Base64.DEFAULT);
                registrar.setEnabled(true);

                /*byte[] decodedBytes = Base64.decode(encode.getBytes(), Base64.DEFAULT);
                Bitmap bn = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
                bn.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                prueba.setImageBitmap(bn);*/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registrar:
                if (contrasena.getText().toString().equals(contrasena2.getText().toString())) {
                    password = contrasena.getText().toString();
                    name = nombre.getText().toString();
                    lastName = apellido.getText().toString();
                    email = correo.getText().toString();
                    //enviarRegistro();
                    crearPreferencias(name + " " + lastName, email, code);
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Snackbar.make(v, "La contraseña no coinciden", Snackbar.LENGTH_LONG).show();
                }


                break;
            case R.id.fotoRegistro:
                cargarImagen();
                break;
        }
    }

    private void enviarRegistro() {

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("nombre", name + " " + lastName)
                .add("correo", email)
                .add("password", password)
                .add("perfil", code)
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.0.19:8080/guardarUsuario")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String rta = response.body().string(); //{"success":1,"message":"Has hecho el reporte existosamente."}
                    Gson gson = new Gson();
                    RespuestaRest respuestaRest = new RespuestaRest();
                    respuestaRest = gson.fromJson(rta, RespuestaRest.class);
                    final RespuestaRest finalRespuestaRest = respuestaRest;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalRespuestaRest.getCodigoRespuesta() == 1) {
                                crearPreferencias(name + " " + lastName, email, code);
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }
                    });
                }
            }
        });
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
}
