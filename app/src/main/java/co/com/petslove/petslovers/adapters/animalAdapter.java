package co.com.petslove.petslovers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.TransaccionPojo;

public class animalAdapter extends RecyclerView.Adapter<animalAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<TransaccionPojo> animales;
    private View.OnClickListener listener;

    @NonNull
    @Override
    public animalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull animalAdapter.ViewHolder holder, int position) {
        Picasso.get().load(animales.get(position).getFotografias().get(0)).into(holder.foto);
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
