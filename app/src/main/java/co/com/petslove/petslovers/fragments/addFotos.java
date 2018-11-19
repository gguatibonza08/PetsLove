package co.com.petslove.petslovers.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.fotoAdapter;
import co.com.petslove.petslovers.interfaces.enviarPublicacion;
import co.com.petslove.petslovers.model.Transaccion;

public class addFotos extends Fragment {

    private Transaccion transaccion;
    private OnFragmentInteractionListener mListener;
    private ImageView fotoGrande;
    private ArrayList<String> fotos = new ArrayList<>();
    private RecyclerView listFotos;
    private Button next, addPhoto;
    private enviarPublicacion enviar;
    private Activity activity;
    private int contador = 0;


    public addFotos() {
        // Required empty public constructor
    }

    public static addFotos newInstance() {
        addFotos fragment = new addFotos();
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
        View view = inflater.inflate(R.layout.fragment_add_fotos, container, false);
        fotoGrande = view.findViewById(R.id.fotoAnimalId);
        listFotos = view.findViewById(R.id.listaFotos);
        next = view.findViewById(R.id.Next);
        transaccion = new Transaccion();
        addPhoto = view.findViewById(R.id.seleccion_botonF);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transaccion.setFotos(fotos);
                enviar.EnviarFotos(transaccion);
            }
        });


        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarFoto();

            }
        });

        return view;
    }

    private void cargarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            switch (requestCode) {
                case 100:
                    Uri imageUri = data.getData();
                    fotoGrande.setImageURI(imageUri);
                    contador++;
                    if (contador != 0) {
                        next.setVisibility(View.VISIBLE);
                    }
                    try {
                        procesar(imageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    private void procesar(Uri imageUri) throws IOException {

        Bitmap bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String code = Base64.encodeToString(b, Base64.DEFAULT);
        Log.e("code", code);
        fotos.add(code);
        addRecyler();
    }

    private void addRecyler() {
        fotoAdapter adapter = new fotoAdapter(getContext(), fotos);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        listFotos.setLayoutManager(horizontalLayoutManager);
        listFotos.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fotoGrande.setImageBitmap(decode64(fotos.get(listFotos.getChildAdapterPosition(v)).getBytes()));
            }
        });
    }


    private Bitmap decode64(byte[] bytes) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] decodedBytes = Base64.decode(bytes, Base64.DEFAULT);
            Bitmap bn = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
            bn.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            return bn;
        } catch (Exception e) {
            Log.e("Error", "Campo foto vacio");
            return null;
        }

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
            enviar = (enviarPublicacion) this.activity;
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
