package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import co.com.petslove.petslovers.model.Tipo;
import co.com.petslove.petslovers.model.Transaccion;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class informacionPublicacion extends Fragment {

    private Activity activity;
    private ArrayList<Tipo> tipoDeMascota = new ArrayList<>();
    private Spinner tipo, raza;
    private EditText precio, descripcion;
    private CheckBox adopcion;
    private FloatingActionButton next;
    private List<String> tipos, razas;
    private Transaccion transaccion;
    private ArrayAdapter<String> tipoAdapter, razaAdapter;
    private OnFragmentInteractionListener mListener;
    private enviarPublicacion enviar;

    public informacionPublicacion() {
    }


    public static informacionPublicacion newInstance() {
        informacionPublicacion fragment = new informacionPublicacion();
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
        View view = inflater.inflate(R.layout.fragment_informacion_publicacion, container, false);
        try {
            Bundle objetoDetalles = getArguments();
            transaccion = (Transaccion) objetoDetalles.getSerializable("transaccion");
            tipo = view.findViewById(R.id.sTipo);
            raza = view.findViewById(R.id.sRaza);
            precio = view.findViewById(R.id.precioIdx);
            descripcion = view.findViewById(R.id.eDescripcion);
            adopcion = view.findViewById(R.id.checkBox);
            next = view.findViewById(R.id.nextFV);
            Consulta();
            adopcion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        transaccion.setAdopta(true);
                        precio.setEnabled(false);
                    } else {
                        transaccion.setAdopta(false);
                        precio.setEnabled(true);
                    }
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    siguiente();
                }
            });

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        return view;
    }

    private void siguiente() {
        transaccion.setDescripcion(descripcion.getText().toString());
        if (transaccion.isAdopta()) {
            transaccion.setPrecio("");
        } else {
            transaccion.setPrecio(precio.getText().toString());
        }
        enviar.EnviarInformacion(transaccion);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    public void Consulta() {
        tipos = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/buscarPorTipos")
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
                            TypeToken<ArrayList<Tipo>>() {
                            }.getType();
                    final ArrayList<Tipo> Tipos = new Gson().fromJson(rta, listType);
                    tipos.add(0, "");
                    for (Tipo iter : Tipos) {
                        tipoDeMascota.add(iter);
                        if (!tipos.contains(iter.getNombreTipo()))
                            tipos.add(iter.getNombreTipo());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tipoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, tipos);
                            tipoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            tipo.setAdapter(tipoAdapter);
                            tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    transaccion.setTipo(tipos.get(position));
                                    asignarRazas(position);
                                    raza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            transaccion.setRaza(razas.get(position));
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

    private void asignarRazas(int position) {
        razas = new ArrayList<>();
        razas.add(0, "");
        for (Tipo iter : tipoDeMascota) {
            if (iter.getNombreTipo().equals(tipos.get(position))) {
                if (!razas.contains(iter.getRaza())) {
                    razas.add(iter.getRaza());
                }
            }
        }
        razaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, razas);
        razaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raza.setAdapter(razaAdapter);

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
