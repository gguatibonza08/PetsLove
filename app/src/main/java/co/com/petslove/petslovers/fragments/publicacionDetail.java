package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.adapters.comentarioAdapter;
import co.com.petslove.petslovers.model.PublicacionPojo;


public class publicacionDetail extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ImageView fotoUsuario, fotoPrincipal, likePublicacion, comentarPublicacion;
    private TextView nombreUsuario, cantidaLike, cantidadComentario, contenido, fechaPublicacion;
    private RecyclerView comentarios;

    public publicacionDetail() {
        // Required empty public constructor
    }


    public static publicacionDetail newInstance() {
        publicacionDetail fragment = new publicacionDetail();
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
        Bundle objetoDetalles = getArguments();
        View view = inflater.inflate(R.layout.fragment_publicacion_detail, container, false);
        fotoUsuario = view.findViewById(R.id.fotoUsuarioDetailPublicacion);
        fotoPrincipal = view.findViewById(R.id.fotoDetailPublicacion);
        likePublicacion = view.findViewById(R.id.likeDetailPublicacion);
        comentarPublicacion = view.findViewById(R.id.comentarDetailPublicacion);
        nombreUsuario = view.findViewById(R.id.nombreUsuarioDetailPublicacion);
        cantidaLike = view.findViewById(R.id.CantidadLikePublicacion);
        cantidadComentario = view.findViewById(R.id.cantidaComentariosDetailPublicacion);
        contenido = view.findViewById(R.id.contenidoDetailPublicacion);
        fechaPublicacion = view.findViewById(R.id.fechaDetailPublicaion);
        comentarios = view.findViewById(R.id.itemDetailPublicacion_comentarios);

        try {
            PublicacionPojo trans = (PublicacionPojo) objetoDetalles.getSerializable("detalle");
            fotoUsuario.setImageBitmap(decode64(trans.getFotoUsuario().getBytes()));
            fotoPrincipal.setImageBitmap(decode64(trans.getFoto().getBytes()));
            nombreUsuario.setText(trans.getNombreUsuario());

            cantidadComentario.setText(trans.getComentarios().size() + " Comentarios");
            contenido.setText(trans.getDescripcion());
            // cantidaLike.setText(trans.getLikes().toString() + " me Encorazonan.");

            fechaPublicacion.setText(trans.getHoraPublicacion());
            comentarioAdapter adapter = new comentarioAdapter(getContext(), trans.getComentarios());
            comentarios.setVisibility(View.VISIBLE);
            comentarios.setLayoutManager(new LinearLayoutManager(getContext()));
            comentarios.setAdapter(adapter);
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        return view;
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
