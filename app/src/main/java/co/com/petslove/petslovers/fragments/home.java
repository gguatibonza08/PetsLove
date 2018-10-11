package co.com.petslove.petslovers.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import co.com.petslove.petslovers.R;

import static android.content.Context.MODE_PRIVATE;

public class home extends Fragment {
    private OnFragmentInteractionListener mListener;
    private ImageView prueba;
    private String url;

    public home() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static home newInstance(String param1, String param2) {
        home fragment = new home();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        prueba = view.findViewById(R.id.fotoprueba);
        SharedPreferences preferences = getActivity().getSharedPreferences("credenciales", MODE_PRIVATE);
        url = preferences.getString("fotoperfil", "");
        Log.e("prueba", url);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] decodedBytes = Base64.decode(url.getBytes(), Base64.DEFAULT);
        Bitmap bn = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        bn.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        prueba.setImageBitmap(bn);
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
}
