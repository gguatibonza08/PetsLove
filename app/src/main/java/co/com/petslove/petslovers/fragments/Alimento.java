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
import co.com.petslove.petslovers.adapters.alimentoAdapter;
import co.com.petslove.petslovers.model.EstablecimientoPojo;

public class Alimento extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ArrayList<EstablecimientoPojo> ListAlimentos;
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alimento, container, false);
        alimentos = view.findViewById(R.id.listAlimentos);
        consultarAlimento();
        return view;
    }

    private void consultarAlimento() {

        alimentoAdapter adapter = new alimentoAdapter(getContext(), ListAlimentos);
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
}
