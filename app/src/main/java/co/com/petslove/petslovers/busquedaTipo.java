package co.com.petslove.petslovers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.com.petslove.petslovers.adapters.animalAdapter;
import co.com.petslove.petslovers.interfaces.enviarDatos;
import co.com.petslove.petslovers.model.TransaccionPojo;


public class busquedaTipo extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Activity activity;
    private enviarDatos envio;
    private RecyclerView animales;
    private animalAdapter adapter;
    private ArrayList<TransaccionPojo> listAnimales;
    private String tipo;

    public busquedaTipo() {
        // Required empty public constructor
    }

    public static busquedaTipo newInstance() {
        busquedaTipo fragment = new busquedaTipo();
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
        View view = inflater.inflate(R.layout.fragment_busqueda_tipo, container, false);
        animales = view.findViewById(R.id.listTipoAnimal);
        try {
            Bundle objetoDetalles = getArguments();
            tipo = objetoDetalles.getString("detalle");
            consultarTipo(tipo);


        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return view;
    }

    private void consultarTipo(String tipo) {
        //aqui la conexión al web services, es casi lo mismo que el de traer todas las publicaciones solo que esta vez van filtradaspor el tipo


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


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
