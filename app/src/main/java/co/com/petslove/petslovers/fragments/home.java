package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.animalAdapter;
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.TransaccionPojo;

public class home extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;
    private RecyclerView animales;
    private Button compra, adoptar;
    Activity activity;
    private ArrayList<TransaccionPojo> ListAnimales;
    private enviarDatos envio;

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        animales = view.findViewById(R.id.listaAnimales);
        compra = view.findViewById(R.id.buttoncompra);
        adoptar = view.findViewById(R.id.buttonadoptar);
        //refererenciar();
        return view;
    }

    private void refererenciar() {
        ListAnimales = new ArrayList<>();
        animalAdapter adapter = new animalAdapter(getContext(), ListAnimales);
        animales.setLayoutManager(new LinearLayoutManager(getContext()));
        animales.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envio.EnviarDetalle(ListAnimales.get(animales.getChildAdapterPosition(v)));
            }
        });

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
                traerPublicacion("comprar");
                break;
            case R.id.buttonadoptar:
                traerPublicacion("adoptar");
                break;
        }
    }

    private void traerPublicacion(String comprar) {
        //acá agregar la conexión al webservice

        ListAnimales = new ArrayList<>();
        animalAdapter adapter = new animalAdapter(getContext(), ListAnimales);
        animales.setLayoutManager(new LinearLayoutManager(getContext()));
        animales.setAdapter(adapter);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
