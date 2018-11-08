package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.veterinariaAdapter;
import co.com.petslove.petslovers.model.EstablecimientoPojo;


public class Veterinaria extends Fragment {

    private RecyclerView veterinarias;
    private ArrayList<EstablecimientoPojo> ListVeterinarias;
    private OnFragmentInteractionListener mListener;

    public Veterinaria() {
        // Required empty public constructor
    }


    public static Veterinaria newInstance(String param1, String param2) {
        Veterinaria fragment = new Veterinaria();
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
        View view = inflater.inflate(R.layout.fragment_veterinaria, container, false);
        veterinarias = view.findViewById(R.id.listVeterinarias);
        consultar();
        return view;
    }

    private void consultar() {

        //conexión webservices
        ListVeterinarias = new ArrayList<>();
        veterinariaAdapter adapter = new veterinariaAdapter(getContext(), ListVeterinarias);
        veterinarias.setLayoutManager(new LinearLayoutManager(getContext()));
        veterinarias.setAdapter(adapter);


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
