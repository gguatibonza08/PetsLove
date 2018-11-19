package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
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
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.Busqueda;
import co.com.petslove.petslovers.model.Lugar;
import co.com.petslove.petslovers.model.Tipo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class search extends Fragment {
    private OnFragmentInteractionListener mListener;
    private Spinner tipo, raza, departamento, ciudad;
    private EditText precioMax, precioMin;
    private CheckBox adopcion;
    private CardView button;
    private Busqueda busqueda;
    private enviarDatos envio;
    private Activity activity;
    private ArrayList<Tipo> tipoDeMascota = new ArrayList<>();
    private ArrayList<Lugar> lugarMascota = new ArrayList<>();
    private List<String> tipos, razas, departamentos, ciudades;
    private ArrayAdapter<String> tipoAdapter, razaAdapter, departamentoAdapter, ciudadAdapter;

    public search() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static search newInstance() {
        search fragment = new search();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        busqueda = new Busqueda();
        tipo = view.findViewById(R.id.sStipo);
        raza = view.findViewById(R.id.sSraza);
        departamento = view.findViewById(R.id.sSdepartamento);
        ciudad = view.findViewById(R.id.sSciudad);
        precioMax = view.findViewById(R.id.precioMax);
        precioMin = view.findViewById(R.id.precioMin);
        adopcion = view.findViewById(R.id.checkAdopcion);
        button = view.findViewById(R.id.buscarButton);
        obtenerFiltros();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarMascota();
            }
        });


        adopcion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    precioMax.setEnabled(false);
                    busqueda.setAdopta(true);
                    precioMin.setEnabled(false);
                } else {
                    precioMax.setEnabled(true);
                    busqueda.setAdopta(false);
                    precioMin.setEnabled(true);
                }
            }
        });

        return view;
    }

    private void buscarMascota() {
        busqueda.setPrecioMax(precioMax.getText().toString());
        busqueda.setPrecioMin(precioMin.getText().toString());
        envio.EnviarBusqueda(busqueda);
    }

    private void obtenerFiltros() {
        Consulta();
        busquedaUbicaciones();
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
            envio = (enviarDatos) this.activity;
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
                                    busqueda.setTipo(tipos.get(position));
                                    asignarRazas(position);
                                    raza.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            busqueda.setRaza(razas.get(position));
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
                                    busqueda.setDepartamento(departamentos.get(position));
                                    asignarCiudades(position);
                                    ciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            busqueda.setCiudad(ciudades.get(position));
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

}
