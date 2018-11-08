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

public class veterinariaAdapter extends RecyclerView.Adapter<veterinariaAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<EstablecimientoPojo> veterinarias;
    private View.OnClickListener listener;

    public veterinariaAdapter(Context context, ArrayList<EstablecimientoPojo> veterinarias) {
        this.context = context;
        this.veterinarias = veterinarias;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_veterinaria, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreVeterinaria.setText(veterinarias.get(position).getNombre());
        holder.direccionVeterinaria.setText(veterinarias.get(position).getDireccion());
        holder.telefonoVeterinaria.setText(veterinarias.get(position).getTelefono());
        Picasso.get().load(veterinarias.get(position).getFotografias().get(0).getUrl()).into(holder.fotoVeterinaria);

    }

    @Override
    public int getItemCount() {
        return veterinarias.size();
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
        ImageView fotoVeterinaria;
        TextView nombreVeterinaria;
        TextView direccionVeterinaria;
        TextView telefonoVeterinaria;
        TextView descripcionVeterinaria;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoVeterinaria = itemView.findViewById(R.id.fotoVeterinaria);
            nombreVeterinaria = itemView.findViewById(R.id.nombreVeterinaria);
            direccionVeterinaria = itemView.findViewById(R.id.direccionVeterinaria);
            telefonoVeterinaria = itemView.findViewById(R.id.telefonoVeterinaria);
            descripcionVeterinaria = itemView.findViewById(R.id.descripcionVeterinaria);
        }
    }
}
