package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.paseadorAdapter;
import co.com.petslove.petslovers.model.EstablecimientoPojo;


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
        referenciar();
        return view;
    }

    private void referenciar() {
        //conexion a web services
        listPaseadores = new ArrayList<>();
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
}
