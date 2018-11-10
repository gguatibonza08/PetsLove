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

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.EstablecimientoPojo;

public class estilistaAdapter extends RecyclerView.Adapter<estilistaAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<EstablecimientoPojo> estilistas;
    private View.OnClickListener listener;

    public estilistaAdapter(Context context, ArrayList<EstablecimientoPojo> estilistas) {
        this.context = context;
        this.estilistas = estilistas;
    }

    @NonNull
    @Override
    public estilistaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estilista, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull estilistaAdapter.ViewHolder holder, int position) {
        try{
            holder.nombreEstilista.setText(estilistas.get(position).getNombre());
            holder.direccionEstilista.setText(estilistas.get(position).getDireccion());
            holder.telefonoEstilista.setText(estilistas.get(position).getTelefono());
            Picasso.get().load(estilistas.get(position).getFotografias().get(0).getUrl()).into(holder.fotoEstilista);

        }catch (Exception e){
            Log.e("Error","Error en estilista");
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
        return estilistas.size();
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
        ImageView fotoEstilista;
        TextView nombreEstilista;
        TextView direccionEstilista;
        TextView telefonoEstilista;
        TextView descripcionEstilista;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoEstilista = itemView.findViewById(R.id.fotoEstilista);
            nombreEstilista = itemView.findViewById(R.id.nombreEstilista);
            direccionEstilista = itemView.findViewById(R.id.direccionEstilista);
            telefonoEstilista = itemView.findViewById(R.id.telefonoEstilista);
            descripcionEstilista = itemView.findViewById(R.id.descripcionEstilista);
        }
    }
}