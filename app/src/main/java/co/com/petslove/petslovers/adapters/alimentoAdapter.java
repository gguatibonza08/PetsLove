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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.EstablecimientoPojo;

public class alimentoAdapter extends RecyclerView.Adapter<alimentoAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<EstablecimientoPojo> alimentos;
    private View.OnClickListener listener;

    public alimentoAdapter(Context context, ArrayList<EstablecimientoPojo> alimentos) {
        this.context = context;
        this.alimentos = alimentos;
    }

    @NonNull
    @Override
    public alimentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estilista, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nombreAlimento.setText(alimentos.get(position).getNombre());
        holder.direccionAlimento.setText(alimentos.get(position).getDireccion());
        holder.telefonoAlimento.setText(alimentos.get(position).getTelefono());
        holder.fotoAlimento.setImageBitmap(decode64(alimentos.get(position).getFotografias().get(0).getUrl().getBytes()));


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
        return alimentos.size();
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
        ImageView fotoAlimento;
        TextView nombreAlimento;
        TextView direccionAlimento;
        TextView telefonoAlimento;
        TextView descripcionAlimento;

        public ViewHolder(View itemView) {
            super(itemView);
            fotoAlimento = itemView.findViewById(R.id.fotoAlimento);
            nombreAlimento = itemView.findViewById(R.id.nombreAlimento);
            direccionAlimento = itemView.findViewById(R.id.direccionAlimento);
            telefonoAlimento = itemView.findViewById(R.id.telefonoAlimento);
            descripcionAlimento = itemView.findViewById(R.id.descripcionAlimento);
        }
    }
}
