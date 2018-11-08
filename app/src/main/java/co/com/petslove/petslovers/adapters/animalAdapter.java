package co.com.petslove.petslovers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.TransaccionPojo;

public class animalAdapter extends RecyclerView.Adapter<animalAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<TransaccionPojo> animales;
    private View.OnClickListener listener;

    public animalAdapter(Context context, ArrayList<TransaccionPojo> animales) {
        this.context = context;
        this.animales = animales;
    }

    @NonNull
    @Override
    public animalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull animalAdapter.ViewHolder holder, int position) {

        holder.foto.setImageBitmap(decode64(animales.get(position).getFoto().getBytes()));
        holder.nombreUsuario.setText(animales.get(position).getNombreUsuario());
        holder.calificacion.setRating(animales.get(position).getCalificacionUsuario());
        holder.ciudad.setText(animales.get(position).getCiudad());

        if (animales.get(position).getPrecio() == 0) {
            holder.precio.setText("ADOPCIÓN");
        } else {
            holder.precio.setText(animales.get(position).getPrecio().toString());
        }
        holder.fotoUsuario.setImageBitmap(decode64(animales.get(position).getFotoUsuario().getBytes()));
    }

    private Bitmap decode64(byte[] bytes) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] decodedBytes = Base64.decode(bytes, Base64.DEFAULT);
        Bitmap bn = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        bn.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        return bn;
    }

    @Override
    public int getItemCount() {
        return animales.size();
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

        ImageView foto;
        TextView nombreUsuario;
        TextView ciudad;
        TextView precio;
        RatingBar calificacion;
        ImageView fotoUsuario;

        public ViewHolder(View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.img_animal);
            nombreUsuario = itemView.findViewById(R.id.tv_nombre);
            ciudad = itemView.findViewById(R.id.city);
            precio = itemView.findViewById(R.id.tv_precio);
            calificacion = itemView.findViewById(R.id.ratingBar);
            fotoUsuario = itemView.findViewById(R.id.img_perfil);
        }
    }
}
