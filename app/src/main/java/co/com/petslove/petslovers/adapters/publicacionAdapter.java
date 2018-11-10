package co.com.petslove.petslovers.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import co.com.petslove.petslovers.R;
import co.com.petslove.petslovers.model.PublicacionPojo;

public class publicacionAdapter extends RecyclerView.Adapter<publicacionAdapter.ViewHolder> implements View.OnClickListener {

    private Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private Context context;
    private List<PublicacionPojo> listaPublicaciones;
    private View.OnClickListener listener;


    public publicacionAdapter(Context context, List<PublicacionPojo> listaPublicaciones) {
        this.context = context;
        this.listaPublicaciones = listaPublicaciones;
    }

    @NonNull
    @Override
    public publicacionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publicacion, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull publicacionAdapter.ViewHolder holder, int position) {

        try {
            holder.nombreUsuario.setText(listaPublicaciones.get(position).getNombreUsuario());
            holder.perfilUsuario.setImageBitmap(decode64(listaPublicaciones.get(position).getFotoUsuario().getBytes()));
            String fecha = formatter.format(listaPublicaciones.get(position).getHoraPublicacion());
            holder.fechaPublicacion.setText("Publicado el: " + fecha);
            holder.contenido.setText(listaPublicaciones.get(position).getDescripcion());
            holder.fotoPublicacion.setImageBitmap(decode64(listaPublicaciones.get(position).getFoto().getBytes()));

            holder.cantidadLikes.setText(listaPublicaciones.get(position).getLikes() + " me Encorazonan.");
            holder.cantidadComentarios.setText(listaPublicaciones.get(position).getComentarios().size() + " comentarios");
            if (listaPublicaciones.get(position).getComentarios().size() > 0) {
                Log.e("adapter", "mayor a 0");
                holder.comentarios.setVisibility(View.VISIBLE);
                comentarioAdapter comentarioAdapter = new comentarioAdapter(context, listaPublicaciones.get(position).getComentarios());
                holder.comentarios.setLayoutManager(new LinearLayoutManager(context));
                holder.comentarios.setAdapter(comentarioAdapter);
            }
            holder.like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "agregar un like", Toast.LENGTH_SHORT).show();
                }
            });
            holder.comentar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "agregar un comentario", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("Error Publicacion", "Se peto en la parte de Red Social");
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
        return listaPublicaciones.size();
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
        TextView fechaPublicacion;
        TextView contenido;
        ImageView fotoPublicacion;
        TextView cantidadLikes;
        TextView cantidadComentarios;
        RecyclerView comentarios;
        ImageView like;
        ImageView comentar;


        public ViewHolder(View itemView) {
            super(itemView);
            perfilUsuario = itemView.findViewById(R.id.fotoUsuarioPublicacion);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuarioPublicacion);
            fechaPublicacion = itemView.findViewById(R.id.fechaPublicaion);
            fotoPublicacion = itemView.findViewById(R.id.fotoPublicacion);
            cantidadLikes = itemView.findViewById(R.id.CantidadLikePublicacion);
            cantidadComentarios = itemView.findViewById(R.id.cantidaComentariosPublicacion);
            like = itemView.findViewById(R.id.likePublicacion);
            comentar = itemView.findViewById(R.id.comentarPublicacion);
            contenido = itemView.findViewById(R.id.contenidoPublicacion);
            comentarios = itemView.findViewById(R.id.itemPublicacion_comentarios);

        }
    }
}
