package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.interfaces.enviarPublicacion;
import co.com.petslove.petslovers.model.Lugar;
import co.com.petslove.petslovers.model.Transaccion;
import co.com.petslove.petslovers.model.TransaccionPojo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ubicacionPublicacion extends Fragment {

    private Activity activity;
    private Spinner departamento, ciudad;
    private ArrayList<Lugar> lugarMascota = new ArrayList<>();
    private List<String> departamentos, ciudades;
    private ArrayAdapter<String> departamentoAdapter, ciudadAdapter;
    private enviarPublicacion enviar;
    private EditText direccion;
    private Button finalizar;
    private TransaccionPojo trans;
    private Transaccion transaccion;
    private OnFragmentInteractionListener mListener;

    public ubicacionPublicacion() {
        // Required empty public constructor
    }

    public static ubicacionPublicacion newInstance() {
        ubicacionPublicacion fragment = new ubicacionPublicacion();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ubicacion_publicacion, container, false);
        try {
            Bundle objetoDetalles = getArguments();
            transaccion = (Transaccion) objetoDetalles.getSerializable("transaccion");
            trans = new TransaccionPojo();
            departamento = view.findViewById(R.id.sDepartamento);
            ciudad = view.findViewById(R.id.sCiudad);
            direccion = view.findViewById(R.id.eDireccion);
            finalizar = view.findViewById(R.id.Finalizar);
            busquedaUbicaciones();

            finalizar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    terminar();
                }
            });


        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return view;
    }

    private void terminar() {
        trans.setDireccion(direccion.getText().toString());
        trans.setTipo(transaccion.getTipo());
        trans.setDescripcion(transaccion.getDescripcion());
        trans.setFotografias(transaccion.getFotos());
        trans.setFoto(transaccion.getFotos().get(0));
        trans.setRaza(transaccion.getRaza());
        enviar.EnviarUbicacion(transaccion, trans);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            this.activity = (Activity) context;
            enviar = (enviarPublicacion) this.activity;
        }
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void asignarCiudades(int position) {
        ciudades = new ArrayList<>();
        ciudades.add(0, "");
        for (Lugar iter : lugarMascota) {
            if (iter.getDepartamento().equals(departamentos.get(position))) {
                if (!ciudades.contains(iter.getCiudad())) {
                    ciudades.add(iter.getCiudad());
                }
            }
        }
        ciudadAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ciudades);
        ciudadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ciudad.setAdapter(ciudadAdapter);
    }

    public void busquedaUbicaciones() {
        departamentos = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/lugares")
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
                    Type listType = new
                            TypeToken<ArrayList<Lugar>>() {
                            }.getType();
                    final ArrayList<Lugar> places = new Gson().fromJson(rta, listType);
                    departamentos.add(0, "");

                    for (Lugar iter : places) {
                        lugarMascota.add(iter);
                        if (!departamentos.contains(iter.getDepartamento()))
                            departamentos.add(iter.getDepartamento());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            departamentoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departamentos);
                            departamentoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            departamento.setAdapter(departamentoAdapter);
                            departamento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    trans.setDepartamento(departamentos.get(position));
                                    asignarCiudades(position);
                                    ciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            trans.setCiudad(ciudades.get(position));
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> parent) {

                                        }
                                    });
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });


                        }
                    });


                }
            }


        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
