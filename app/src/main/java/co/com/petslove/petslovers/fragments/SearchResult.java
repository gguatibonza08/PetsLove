package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.animalAdapter;
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.Busqueda;
import co.com.petslove.petslovers.model.TransaccionPojo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class SearchResult extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView ResultList;
    private Busqueda busqueda;
    private animalAdapter adapter;
    private TextView texto;
    private Activity activity;
    private enviarDatos envio;
    private ArrayList<TransaccionPojo> listAnimales;

    public SearchResult() {
        // Required empty public constructor
    }

    public static SearchResult newInstance(String param1, String param2) {
        SearchResult fragment = new SearchResult();
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
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);
        texto = view.findViewById(R.id.titulotexto);
        ResultList = view.findViewById(R.id.listaSearchResult);
        try {
            Bundle objetoDetalles = getArguments();
            busqueda = (Busqueda) objetoDetalles.getSerializable("detalle");
            hacerBusqueda(busqueda);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return view;
    }

    private void hacerBusqueda(Busqueda busqueda) {
        if (busqueda.isAdopta()) {
            Log.e("llegue adopata", "adopatar");
            buscarAdopcion();
        } else {
            Log.e("llegue adopta", "venta");
            buscarVenta();
        }
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

    private void buscarVenta() {
        OkHttpClient client = new OkHttpClient();
// reconfigurar en cambios de red
        RequestBody formBody = new FormBody.Builder()
                .add("tipo", busqueda.getTipo())
                .add("raza", busqueda.getRaza())
                .add("departamento", busqueda.getDepartamento())
                .add("ciudad", busqueda.getCiudad())
                .add("precioMax", busqueda.getPrecioMax())
                .add("precioMin", busqueda.getPrecioMin())
                .add("adopcion", busqueda.isAdopta() + "")
                .build();
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/busquedaPorCriteriosV").post(formBody)
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
                    Type listType = new TypeToken<ArrayList<TransaccionPojo>>() {
                    }.getType();
                    listAnimales = new Gson().fromJson(rta, listType);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (listAnimales.size() == 0) {
                                texto.setText("Ningún Resultado obtenido.");
                            }
                            adapter = new animalAdapter(getContext(), listAnimales);
                            ResultList.setLayoutManager(new LinearLayoutManager(getContext()));
                            ResultList.setAdapter(adapter);
                            adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    envio.EnviarDetalle(listAnimales.get(ResultList.getChildAdapterPosition(v)));
                                }
                            });
                        }
                    });
                }
            }


        });
    }


    private void buscarAdopcion() {
        OkHttpClient client = new OkHttpClient();
        listAnimales = new ArrayList<>();
// reconfigurar en cambios de red
        RequestBody formBody = new FormBody.Builder()
                .add("tipo", busqueda.getTipo())
                .add("raza", busqueda.getRaza())
                .add("departamento", busqueda.getDepartamento())
                .add("ciudad", busqueda.getCiudad())
                .add("adopcion", busqueda.isAdopta() + "")
                .build();
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/busquedaPorCriterios").post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("adopcion", "valimos verga");
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    if (response.isSuccessful()) {
                        String rta = response.body().string();
                        Log.e("rta", rta);
                        Type listType = new TypeToken<ArrayList<TransaccionPojo>>() {
                        }.getType();
                        listAnimales = new Gson().fromJson(rta, listType);
                        Log.e("rta", listAnimales.size() + "");
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (listAnimales.size() == 0) {
                                    texto.setText("Ningún Resultado obtenido.");
                                }
                                adapter = new animalAdapter(getContext(), listAnimales);
                                ResultList.setLayoutManager(new LinearLayoutManager(getContext()));
                                ResultList.setAdapter(adapter);
                                adapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        envio.EnviarDetalle(listAnimales.get(ResultList.getChildAdapterPosition(v)));
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });
    }


}
