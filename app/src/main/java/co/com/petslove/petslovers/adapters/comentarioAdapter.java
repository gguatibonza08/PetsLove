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
import java.util.List;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.ComentarioPojo;

public class comentarioAdapter extends RecyclerView.Adapter<comentarioAdapter.ViewHolder> implements View.OnClickListener {


    private Context context;
    private List<ComentarioPojo> listaComentarios;
    private View.OnClickListener listener;

    public comentarioAdapter(Context context, List<ComentarioPojo> listaComentarios) {
        this.context = context;
        this.listaComentarios = listaComentarios;
    }

    @NonNull
    @Override
    public comentarioAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull comentarioAdapter.ViewHolder holder, int position) {
        holder.nombreUsuario.setText(listaComentarios.get(position).getNombreUsuario());
        holder.perfilUsuario.setImageBitmap(decode64(listaComentarios.get(position).getFotoUsuario().getBytes()));
        holder.perfilUsuario.setImageBitmap(decode64(listaComentarios.get(position).getFotoUsuario().getBytes()));
        holder.fechaComentario.setText(listaComentarios.get(position).getFechaComentario());
        holder.contenido.setText(listaComentarios.get(position).getContenido());

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
        return listaComentarios.size();
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
        ImageView perfilUsuario;
        TextView nombreUsuario;
        TextView fechaComentario;
        TextView contenido;

        public ViewHolder(View itemView) {
            super(itemView);
            perfilUsuario = itemView.findViewById(R.id.fotoUsuarioComentario);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuarioComentario);
            fechaComentario = itemView.findViewById(R.id.fechaComentario);
            contenido = itemView.findViewById(R.id.textoComentario);
        }
    }
}
