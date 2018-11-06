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
        holder.nombreUsuario.setText(listaComentarios.get(position).getUsuario()+"");
        Picasso.get().load(listaComentarios.get(position).getUsuario()+"").into(holder.perfilUsuario);
        holder.fechaComentario.setText(listaComentarios.get(position).getFechaComentario());
        holder.contenido.setText(listaComentarios.get(position).getContenido());
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
            contenido = itemView.findViewById(R.id.contenidoPublicacion);
        }
    }
}
