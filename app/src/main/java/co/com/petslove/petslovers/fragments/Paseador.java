package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.paseadorAdapter;
import co.com.petslove.petslovers.model.EstablecimientoPojo;
import co.com.petslove.petslovers.utilidades.EstablecimientosEnum;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.RequestBody;
import okhttp3.Response;


public class Paseador extends Fragment {

    private RecyclerView paseadores;
    private ArrayList<EstablecimientoPojo> listPaseadores = new ArrayList<>();
    private OnFragmentInteractionListener mListener;


    public Paseador() {
        // Required empty public constructor
    }


    public static Paseador newInstance(String param1, String param2) {
        Paseador fragment = new Paseador();
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
        View view = inflater.inflate(R.layout.fragment_paseador, container, false);
        paseadores = view.findViewById(R.id.listaPaseadores);
       ConsultaEstablecimientos();
        return view;
    }

    private void referenciar() {
        paseadorAdapter adapter = new paseadorAdapter(getContext(), listPaseadores);
        paseadores.setLayoutManager(new GridLayoutManager(getContext(), 2));
        paseadores.setAdapter(adapter);
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
    public void ConsultaEstablecimientos() {

        OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder().add("tipo",EstablecimientosEnum.PASEADOR.getNombre()).build();

        Request request = new Request.Builder()
                .url("http://"+getString( R.string.ip )+":8080/establecimientosTipo").post(formBody)
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
                    Log.i("exito_paseador", "rta " + rta);
                    Gson gson = new Gson();
                    Type listType = new
                            TypeToken<ArrayList<EstablecimientoPojo>>() {
                            }.getType();
                    final ArrayList<EstablecimientoPojo> establecimientos = new Gson().fromJson(rta, listType);


                    for(EstablecimientoPojo iter: establecimientos){
                        Log.i("iter",iter.getDireccion());
                        listPaseadores.add(iter);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            paseadorAdapter adapter = new paseadorAdapter(getContext(), listPaseadores);
                            paseadores.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            paseadores.setAdapter(adapter);
                        }
                    });


                }
            }


        });


    }
}
