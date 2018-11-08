package co.com.petslove.petslovers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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
        holder.nombrePaseador.setText(paseadores.get(position).getNombre());
        holder.direccionPaseador.setText(paseadores.get(position).getDireccion());
        holder.telefonoPaseador.setText(paseadores.get(position).getTelefono());
        Picasso.get().load(paseadores.get(position).getFotografias().get(0).getUrl()).into(holder.fotoPaseador);

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
