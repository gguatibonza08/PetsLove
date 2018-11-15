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
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.animalAdapter;
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.TransaccionPojo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class home extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;
    private RecyclerView animales;
    private Button compra, adoptar;
    Activity activity;
    private ArrayList<TransaccionPojo> listAnimales;
    private enviarDatos envio;
    private animalAdapter adapter;
    private String complemento = "consultaAdopciones";

    public home() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        animales = view.findViewById(R.id.listaAnimales);
        compra = view.findViewById(R.id.buttoncompra);
        adoptar = view.findViewById(R.id.buttonadoptar);
        compra.setOnClickListener(this);
        adoptar.setOnClickListener(this);
        consultarPublicaciones();

        return view;
    }

    private void refererenciar() {
        listAnimales = new ArrayList<>();
        animalAdapter adapter = new animalAdapter(getContext(), listAnimales);
        animales.setLayoutManager(new LinearLayoutManager(getContext()));
        animales.setAdapter(adapter);


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttoncompra:
                complemento = "consultaVentas";
                compra.setEnabled(false);
                adoptar.setEnabled(true);
                consultarPublicaciones();
                break;
            case R.id.buttonadoptar:
                complemento = "consultaAdopciones";
                compra.setEnabled(true);
                adoptar.setEnabled(false);
                consultarPublicaciones();
                break;
        }
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void consultarPublicaciones() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/" + complemento)
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
                    Type listType = new TypeToken<ArrayList<TransaccionPojo>>() {
                    }.getType();
                    listAnimales = new Gson().fromJson(rta, listType);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new animalAdapter(getContext(), listAnimales);
                            animales.setLayoutManager(new LinearLayoutManager(getContext()));
                            animales.setAdapter(adapter);
                            adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    envio.EnviarDetalle(listAnimales.get(animales.getChildAdapterPosition(v)));
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
