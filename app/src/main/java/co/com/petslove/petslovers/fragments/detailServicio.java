package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.EstablecimientoPojo;

public class detailServicio extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ImageView foto;
    private CardView next, before;
    private TextView nombre, descripcion, telefono, direccion, correo;
    private RatingBar calificacion;
    private List<String> fotos = new ArrayList<>();
    private int contador = 1;

    public detailServicio() {
        // Required empty public constructor
    }

    public static detailServicio newInstance() {
        detailServicio fragment = new detailServicio();
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
        View view = inflater.inflate(R.layout.fragment_detail_servicio, container, false);
        Bundle objetoDetalles = getArguments();
        foto = view.findViewById(R.id.imagenEstblecimiento);
        next = view.findViewById(R.id.nextEstablecimiento);
        before = view.findViewById(R.id.previousEstablecimiento);
        nombre = view.findViewById(R.id.nombreEstablecimiento);
        calificacion = view.findViewById(R.id.calificacionEstablecimiento);
        descripcion = view.findViewById(R.id.tv_descripcionEstableciento);
        direccion = view.findViewById(R.id.direccionEstablecimiento);
        telefono = view.findViewById(R.id.telefonoEstablecimiento);
        correo = view.findViewById(R.id.correoEstablecimiento);

        try {
            EstablecimientoPojo trans = (EstablecimientoPojo) objetoDetalles.getSerializable("detalle");
            fotos = trans.getFotografias();
            foto.setImageBitmap(decode64(trans.getFotografias().get(0).getBytes()));
            nombre.setText(trans.getNombre());
            calificacion.setRating(trans.getCalificacion());
            descripcion.setText(trans.getDescripcion());
            direccion.setText(trans.getDireccion());
            telefono.setText(trans.getTelefono());
            correo.setText(trans.getCorreo());

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contador++;
                    Log.e("++", "onClick: adherir");
                    cambiarFoto();
                }
            });
            before.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("--", "onClick: restar");
                    contador--;
                    cambiarFoto();
                }
            });


        } catch (Exception e) {
            Log.e("errror", e.getMessage());
        }


        return view;
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

    private void cambiarFoto() {
        if (contador < 0) {
            contador = fotos.size() - 1;
        }
        if (contador == fotos.size()) {
            contador = 0;
        }
        foto.setImageBitmap(decode64(fotos.get(contador).getBytes()));
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

}
