package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.publicacionAdapter;
import co.com.petslove.petslovers.model.PublicacionPojo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RedSocial extends Fragment {

    private RecyclerView redSocial;
    private OnFragmentInteractionListener mListener;
    private ArrayList<PublicacionPojo> publicaciones;
    private FloatingActionButton addPublicacion;

    public RedSocial() {

    }

    public static RedSocial newInstance() {
        RedSocial fragment = new RedSocial();
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
        View view = inflater.inflate(R.layout.fragment_red_social, container, false);
        redSocial = view.findViewById(R.id.listaPublicacions);
        addPublicacion = view.findViewById(R.id.floatingActionButton);
        addPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "me falta agregar la otra activity para esto", Toast.LENGTH_SHORT).show();
            }
        });

        consultarPublicaciones();

        return view;
    }

    private void referenciar() {
        publicacionAdapter publicacionAdapter = new publicacionAdapter(getContext(), publicaciones);
        redSocial.setLayoutManager(new LinearLayoutManager(getContext()));
        redSocial.setAdapter(publicacionAdapter);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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

    /**
     * @Author Kevin Joel Olarte
     * 7/11/2018
     */
    public void consultarPublicaciones() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/consultarPublicaciones")
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
                    Log.i("exito", "rta " + rta);
                    Gson gson = new Gson();
                    Type listType = new
                            TypeToken<ArrayList<PublicacionPojo>>() {
                            }.getType();
                    final ArrayList<PublicacionPojo> respuesta = new Gson().fromJson(rta, listType);
                    publicaciones = new ArrayList<>();
                    for (PublicacionPojo iter : respuesta) {
                        publicaciones.add(iter);
                        Log.i("servicio", iter.getDescripcion());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //referenciar();  ->prueba así, si funciona entonces descomenta esta parte y borra lo de abajo para hacer prueba por si algo, la igual prueba enviando al adapter publicaciones y respuesta para ver si se puede ahorrar lo dl for anterior
                            publicacionAdapter publicacionAdapter = new publicacionAdapter(getContext(), respuesta);
                            redSocial.setLayoutManager(new LinearLayoutManager(getContext()));
                            redSocial.setAdapter(publicacionAdapter);
                        }
                    });
                }

            }
        });
    }
}
