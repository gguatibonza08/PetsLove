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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import co.com.petslove.petslovers.R;

public class fotoAdapter extends RecyclerView.Adapter<fotoAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<String> fotos;
    private View.OnClickListener listener;

    public fotoAdapter(Context context, ArrayList<String> fotos) {
        this.context = context;
        this.fotos = fotos;
    }

    @NonNull
    @Override
    public fotoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foto_item, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull fotoAdapter.ViewHolder holder, int position) {
        try {
            holder.foto.setImageBitmap(decode64(fotos.get(position).getBytes()));

        } catch (Exception e) {
            Log.e("Error Alimento", "Se peto en la parte Alimento");
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
        return fotos.size();
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

        public ViewHolder(View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.addFoto);

        }
    }
}
