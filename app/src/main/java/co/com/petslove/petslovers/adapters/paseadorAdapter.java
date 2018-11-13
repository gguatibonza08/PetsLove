package co.com.petslove.petslovers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.EstablecimientoPojo;

public class paseadorAdapter extends RecyclerView.Adapter<paseadorAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private ArrayList<EstablecimientoPojo> paseadores;
    private View.OnClickListener listener;

    public paseadorAdapter(Context context, ArrayList<EstablecimientoPojo> paseadores) {
        this.context = context;
        this.paseadores = paseadores;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paseador, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            holder.nombrePaseador.setText(paseadores.get(position).getNombre());
            holder.direccionPaseador.setText(paseadores.get(position).getDireccion());
            holder.telefonoPaseador.setText(paseadores.get(position).getTelefono() + "");
            holder.descripcionPaseador.setText(paseadores.get(position).getDescripcion());
            holder.fotoPaseador.setImageBitmap(decode64(paseadores.get(position).getFotografias().get(0).getBytes()));

        } catch (Exception e) {
            Log.e("Erro", e.getMessage());
        }
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

    @Override
    public int getItemCount() {
        return paseadores.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoPaseador;
        TextView nombrePaseador;
        TextView direccionPaseador;
        TextView telefonoPaseador;
        TextView descripcionPaseador;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoPaseador = itemView.findViewById(R.id.fotoPaseador);
            nombrePaseador = itemView.findViewById(R.id.nombrePaseador);
            direccionPaseador = itemView.findViewById(R.id.direccionPaseador);
            telefonoPaseador = itemView.findViewById(R.id.telefonoPaseador);
            descripcionPaseador = itemView.findViewById(R.id.descripcionPaseador);
        }
    }
}
