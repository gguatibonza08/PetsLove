package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.publicacionAdapter;
import co.com.petslove.petslovers.addPublicacion;
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.PublicacionPojo;
import co.com.petslove.petslovers.model.respuestaPublicacion;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class RedSocial extends Fragment {

    private RecyclerView redSocial;
    Activity activity;
    private OnFragmentInteractionListener mListener;
    private ArrayList<PublicacionPojo> publicaciones;
    private FloatingActionButton addPublicaciones;
    private enviarDatos envio;

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
        addPublicaciones = view.findViewById(R.id.floatingActionButton);
        addPublicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), addPublicacion.class));
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

    /**
     * @Author Kevin Joel Olarte
     * 7/11/2018
     */
    public void consultarPublicaciones() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://"+getString( R.string.ip )+":8080/consultarPublicaciones")
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
                    respuestaPublicacion respuesta = new Gson().fromJson(rta, respuestaPublicacion.class);
                    publicaciones = new ArrayList<>();

                    for (PublicacionPojo iter : respuesta.getObjectRest()) {
                        PublicacionPojo pub = new PublicacionPojo();
                        pub.setFoto(iter.getFoto());
                        pub.setComentarios(iter.getComentarios());
                        pub.setDescripcion(iter.getDescripcion());
                        pub.setFotoUsuario(iter.getFotoUsuario());
                        pub.setHoraPublicacion(iter.getHoraPublicacion());
                        pub.setLikes(iter.getLikes());
                        pub.setNombreUsuario(iter.getNombreUsuario());
                        pub.setPublicacionId(iter.getPublicacionId());
                        pub.setUsuario(iter.getUsuario());
                        publicaciones.add(pub);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            publicacionAdapter publicacionAdapter = new publicacionAdapter(getContext(), publicaciones);
                            redSocial.setLayoutManager(new LinearLayoutManager(getContext()));
                            redSocial.setAdapter(publicacionAdapter);
                            publicacionAdapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    envio.EnviarPublicacion(publicaciones.get(redSocial.getChildAdapterPosition(v)));
                                }
                            });
                        }
                    });
                }
            }
        });
    }
}
