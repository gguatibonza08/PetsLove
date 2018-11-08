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
import co.com.petslove.petslovers.adapters.estilistaAdapter;
import co.com.petslove.petslovers.model.EstablecimientoPojo;


public class Estilista extends Fragment {

    private OnFragmentInteractionListener mListener;
    private RecyclerView estilistas;
    private ArrayList<EstablecimientoPojo> ListEstilistas;

    public Estilista() {
        // Required empty public constructor
    }


    public static Estilista newInstance(String param1, String param2) {
        Estilista fragment = new Estilista();
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
        View view = inflater.inflate(R.layout.fragment_estilista, container, false);
        estilistas = view.findViewById(R.id.ListEstilistas);
        consultarEstilistas();
        return view;
    }

    private void consultarEstilistas() {
        estilistaAdapter adapter = new estilistaAdapter(getContext(), ListEstilistas);
        estilistas.setLayoutManager(new LinearLayoutManager(getContext()));
        estilistas.setAdapter(adapter);
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
