package co.com.petslove.petslovers.fragments;

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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.alimentoAdapter;
import co.com.petslove.petslovers.model.EstablecimientoPojo;
import co.com.petslove.petslovers.utilidades.EstablecimientosEnum;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Alimento extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ArrayList<EstablecimientoPojo> listAlimentos;
    private RecyclerView alimentos;

    public Alimento() {
        // Required empty public constructor
    }

    public static Alimento newInstance(String param1, String param2) {
        Alimento fragment = new Alimento();
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

        View view = inflater.inflate(R.layout.fragment_alimento, container, false);
        alimentos = view.findViewById(R.id.listAlimentos);
        consultarAlimento();
        return view;
    }

    private void consultarAlimento() {
        listAlimentos = new ArrayList<>();
        alimentoAdapter adapter = new alimentoAdapter(getContext(), listAlimentos);
        alimentos.setLayoutManager(new LinearLayoutManager(getContext()));
        alimentos.setAdapter(adapter);

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
        RequestBody formBody = new FormBody.Builder().add("tipo", EstablecimientosEnum.ALIMENTO.getNombre()).build();

        Request request = new Request.Builder()
                .url("http://" + getString(R.string.ip) + ":8080/establecimientosTipo").post(formBody)
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
                    Log.i("exito_estilista", "rta " + rta);
                    Gson gson = new Gson();
                    Type listType = new
                            TypeToken<ArrayList<EstablecimientoPojo>>() {
                            }.getType();
                    final ArrayList<EstablecimientoPojo> establecimientos = new Gson().fromJson(rta, listType);


                    for (EstablecimientoPojo iter : establecimientos) {
                        Log.i("iter", iter.getDireccion());
                        listAlimentos.add(iter);
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            alimentoAdapter adapter = new alimentoAdapter(getContext(), listAlimentos);
                            alimentos.setLayoutManager(new LinearLayoutManager(getContext()));
                            alimentos.setAdapter(adapter);
                        }
                    });


                }
            }


        });


    }
}
