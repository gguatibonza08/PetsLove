package co.com.petslove.petslovers.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.telephony.PhoneNumberUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.TransaccionPojo;


public class AnimalDetail extends Fragment {

    private OnFragmentInteractionListener mListener;
    private ImageView fotoMascota, fotoUsuario, chatear;
    private CardView next, previous;
    private TextView nombreUsuario, descripcion, raza, precio, ciudad;
    private RatingBar calificicacion;
    private List<String> fotos;
    private int contador = 1;

    public AnimalDetail() {
    }

    public static AnimalDetail newInstance() {
        AnimalDetail fragment = new AnimalDetail();
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
        View view = inflater.inflate(R.layout.fragment_animal_detail, container, false);
        Bundle objetoDetalles = getArguments();
        fotoMascota = view.findViewById(R.id.imagenMascota);
        next = view.findViewById(R.id.nextButton);
        previous = view.findViewById(R.id.previousButton);
        fotoUsuario = view.findViewById(R.id.img_perfilUsuario);
        chatear = view.findViewById(R.id.contactar);
        nombreUsuario = view.findViewById(R.id.tv_nombreUsuario);
        descripcion = view.findViewById(R.id.tv_descripcionMascota);


        raza = view.findViewById(R.id.razaAnimal);
        precio = view.findViewById(R.id.precioDet);
        ciudad = view.findViewById(R.id.ciudadDetalle);
        calificicacion = view.findViewById(R.id.calificacionUsuario);
        try {
            final TransaccionPojo trans = (TransaccionPojo) objetoDetalles.getSerializable("detalle");
            fotos = trans.getFotografias();
            fotoMascota.setImageBitmap(decode64(trans.getFoto().getBytes()));
            fotoUsuario.setImageBitmap(decode64(trans.getFotoUsuario().getBytes()));
            nombreUsuario.setText(trans.getNombreUsuario());
            descripcion.setText(trans.getDescripcion());
            raza.setText(trans.getRaza());
            if (trans.getPrecio() != null) {
                precio.setText(trans.getPrecio().toString());
            } else {
                precio.setText("ADOPCIÓN");
            }
            ciudad.setText(trans.getCiudad());
            calificicacion.setRating(trans.getCalificacionUsuario());

            chatear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent _intencion = new Intent("android.intent.action.MAIN");
                    _intencion.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.Conversation"));
                    _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("57" + "3223734997") + "@s.whatsapp.net");
                    startActivity(_intencion);
                }
            });

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contador++;
                    Log.e("++", "onClick: adherir");
                    cambiarFoto();
                }
            });
            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("--", "onClick: restar");
                    contador--;
                    cambiarFoto();
                }
            });

        } catch (Exception e) {
            Log.e("error", e.getMessage());
        }
        return view;
    }

    private void cambiarFoto() {
        if (contador < 0) {
            contador = fotos.size() - 1;
        }
        if (contador == fotos.size()) {
            contador = 0;
        }
        fotoMascota.setImageBitmap(decode64(fotos.get(contador).getBytes()));
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
