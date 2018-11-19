package co.com.petslove.petslovers;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import co.com.petslove.petslovers.fragments.addFotos;
import co.com.petslove.petslovers.fragments.informacionPublicacion;
import co.com.petslove.petslovers.fragments.ubicacionPublicacion;
import co.com.petslove.petslovers.interfaces.enviarPublicacion;
import co.com.petslove.petslovers.model.Transaccion;
import co.com.petslove.petslovers.model.TransaccionPojo;
import co.com.petslove.petslovers.model.rtaWS.RespuestaRest;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddVentaAdopcion extends AppCompatActivity implements addFotos.OnFragmentInteractionListener, informacionPublicacion.OnFragmentInteractionListener, ubicacionPublicacion.OnFragmentInteractionListener, enviarPublicacion {

    private informacionPublicacion informacion;
    private ubicacionPublicacion ubiacion;
    private addFotos foto;
    private RespuestaRest respuestaRest;
    String animalID = "";
    String precio = "";
    boolean adopta = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_venta_adopcion);
        foto = new addFotos();
        getSupportFragmentManager().beginTransaction().add(R.id.addPublicacion, foto).commit();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void EnviarFotos(Transaccion transaccion) {
        Log.e("prueba", transaccion.getFotos().size() + "");
        informacion = new informacionPublicacion();
        Bundle detalleEnvio = new Bundle();
        detalleEnvio.putSerializable("transaccion", transaccion);
        informacion.setArguments(detalleEnvio);
        getSupportFragmentManager().beginTransaction().replace(R.id.addPublicacion, informacion).addToBackStack(null).commit();


    }

    @Override
    public void EnviarInformacion(Transaccion transaccion) {
        ubiacion = new ubicacionPublicacion();
        Log.e("prueba", transaccion.getDescripcion());
        Bundle detalleEnvio = new Bundle();
        detalleEnvio.putSerializable("transaccion", transaccion);
        ubiacion.setArguments(detalleEnvio);
        getSupportFragmentManager().beginTransaction().replace(R.id.addPublicacion, ubiacion).addToBackStack(null).commit();
    }

    @Override
    public void EnviarUbicacion(Transaccion transaccion, TransaccionPojo animal) {
        adopta = transaccion.isAdopta();
        precio = transaccion.getPrecio();
        if (adopta) {
            crearAdopcion(animal);
        } else {

            crearVenta(animal, transaccion);
        }

    }

    public void crearAdopcion(TransaccionPojo transaccionPojo) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("tipo", transaccionPojo.getTipo())
                .add("raza", transaccionPojo.getRaza())
                .add("foto", transaccionPojo.getFoto())
                .add("descripcion", transaccionPojo.getDescripcion())
                .add("direccion", transaccionPojo.getDireccion())
                .add("ciudad", transaccionPojo.getCiudad())
                .add("fotografias", "asfasfasf")
                .add("foto", "tqwatasga")
                .add("status", "")
                .add("ubicacion", "")
                .add("nombreUsuario", "kevin")
                .add("departamento", transaccionPojo.getDepartamento())
                .build();

        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/guardarAdopcion")
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
                    Log.i("exito", "rta " + rta);
                    respuestaRest = new RespuestaRest();
                    respuestaRest = gson.fromJson(rta, RespuestaRest.class);
                    Log.i("exito", "object " + respuestaRest.getMensaje());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Toast.makeText(MainActivity.this, finalP.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }
        });


    }

    public void crearVenta(TransaccionPojo transaccionPojo, Transaccion transaccion) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("tipo", transaccionPojo.getTipo())
                .add("raza", transaccionPojo.getRaza())
                .add("foto", transaccionPojo.getFoto())
                .add("descripcion", transaccionPojo.getDescripcion())
                .add("direccion", transaccionPojo.getDireccion())
                .add("ciudad", transaccionPojo.getCiudad())
                .add("fotografias", "asfasfasf")
                .add("foto", transaccionPojo.getFoto())
                .add("status", "")
                .add("ubicacion", transaccionPojo.getUbicacion())
                .add("nombreUsuario", "kevin")
                .add("precio", transaccion.getPrecio())
                .add("departamento", transaccionPojo.getDepartamento())
                .build();

        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/guardarVenta")
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
                    Log.i("exito", "rta " + rta);
                    respuestaRest = new RespuestaRest();
                    respuestaRest = gson.fromJson(rta, RespuestaRest.class);
                    Log.i("exito", "object " + respuestaRest.getMensaje());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Toast.makeText(MainActivity.this, finalP.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });


                }
            }
        });


    }


}
