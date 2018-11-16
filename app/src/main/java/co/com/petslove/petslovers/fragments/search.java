package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import co.com.petslove.petslovers.R;

public class search extends Fragment implements AdapterView.OnItemClickListener {
    private OnFragmentInteractionListener mListener;
    private Spinner tipo, raza, departamento, ciudad;
    private EditText precioMax, precioMin;
    private CheckBox adopcion;
    private CardView button;
    private List<String> tipos, razas, departamentos, ciudades;
    private ArrayAdapter<String> tipoAdapter, razaAdapter, departamentoAdapter, ciudadAdapter;
    private String tipoTx, razaTx, departamentoTx, ciudadTx, precioMinTx, precioMaxTx;


    public search() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static search newInstance() {
        search fragment = new search();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        tipo = view.findViewById(R.id.sStipo);
        raza = view.findViewById(R.id.sSraza);
        departamento = view.findViewById(R.id.sSdepartamento);
        ciudad = view.findViewById(R.id.sSciudad);
        precioMax = view.findViewById(R.id.precioMax);
        precioMin = view.findViewById(R.id.precioMin);
        adopcion = view.findViewById(R.id.checkAdopcion);
        button = view.findViewById(R.id.buscarButton);

        obtenerFiltros();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarMascota();
            }
        });

        return view;
    }

    private void buscarMascota() {
        if (adopcion.isActivated()) {
            precioMinTx = "";
            precioMaxTx = "";
        } else {
            precioMaxTx = precioMax.getText().toString();
            precioMinTx = precioMin.getText().toString();
        }
        //aca en esta parte se llama el webs ervices y se envian los siguientes parametros:


        /*
           tipoTx,
           razaTx,
           departamentoTx,
           ciudadTx,
           precioMinTx,
           precioMaxTx

         */


    }

    private void obtenerFiltros() {
        //webServices para traer todos los filtros


        //aqui se agregaría la repuestas de ese webs ervices
        tipos = new ArrayList<>();
        ciudades = new ArrayList<>();
        departamentos = new ArrayList<>();
        razas = new ArrayList<>();

        tipoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, tipos);
        ciudadAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, ciudades);
        departamentoAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, departamentos);
        razaAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, razas);

        tipo.setAdapter(tipoAdapter);
        raza.setAdapter(razaAdapter);
        departamento.setAdapter(departamentoAdapter);
        ciudad.setAdapter(ciudadAdapter);


        tipo.setOnItemClickListener(this);
        raza.setOnItemClickListener(this);
        departamento.setOnItemClickListener(this);
        ciudad.setOnItemClickListener(this);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (view.getId()) {
            case R.id.sStipo:
                tipoTx = tipos.get(position);
                break;
            case R.id.sSdepartamento:
                departamentoTx = departamentos.get(position);
                break;
            case R.id.sSciudad:
                ciudadTx = ciudades.get(position);
                break;
            case R.id.sSraza:
                razaTx = razas.get(position);
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
