package co.com.petslove.petslovers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class addPublicacion extends AppCompatActivity {

    private String codeFoto;
    private ImageView foto;
    private EditText contenido;
    private Button publicar;
    private String correo, descripcionFoto;

    final int COD_SELECCIONA = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_publicacion);
        foto = findViewById(R.id.fotoPublicacionAdd);
        contenido = findViewById(R.id.descripcionPublicacion);
        publicar = findViewById(R.id.publicar);
        cargarImagen2();
        SharedPreferences preferences = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        correo = preferences.getString("correo", "uno");
        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicar();
            }
        });

    }

    private void publicar() {
        //conexion
        descripcionFoto = contenido.getText().toString(); //se envia esto
        //codefoto
        //correo -> este es el correo del usuario logueado que por ende es el correo de quien publica

    }

    private void cargarImagen2() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), COD_SELECCIONA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case COD_SELECCIONA:
                    Uri imageUri = data.getData();
                    foto.setImageURI(imageUri);
                    try {
                        procesar(imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }


        }
    }

    private void procesar(Uri imageUri) throws IOException {
        Log.e("code", "LLEGO A DECODIFICAR");
        Bitmap bm = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        codeFoto = Base64.encodeToString(b, Base64.DEFAULT);
        Log.e("code", codeFoto);
    }


    private Bitmap decode64(byte[] bytes) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] decodedBytes = Base64.decode(bytes, Base64.DEFAULT);
            Bitmap bn = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            bn.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return bn;
        } catch (Exception e) {
            Log.e("Error", "Campo foto vacio");
            return null;
        }

    }

    /**
     * @Author Kevin Joel Olarte
     */
    public void consultarPublicaciones() {
        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("descripcion", descripcionFoto).add("foto", codeFoto)
                .add("correoUsuario", "getUsuario").build();

        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/consultaAdopciones")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String rta = response.body().string();
                    Log.e("rta", rta);
                    RespuestaRest respuesta = new Gson().fromJson(rta, RespuestaRest.class);
                    Log.i("respuesta", respuesta.getMensaje());
                    Log.i("status", Integer.toString(respuesta.getCodigoRespuesta()));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                }
            }
        });
    }
}
