package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.publicacionAdapter;
import co.com.petslove.petslovers.model.PublicacionPojo;


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
        addPublicacion = view.findViewById(R.id.addPublicacion);

        addPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "me falta agregar la otra activity para esto", Toast.LENGTH_SHORT).show();
            }
        });
        //en este metodo agregar el llamado a web services
        referenciar();

        return view;
    }

    private void referenciar() {

        //configurar conexion a web servies

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
}
