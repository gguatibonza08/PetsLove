package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class ComentarioPojo implements Serializable, Parcelable {
    @SerializedName("comentarioId")
    @Expose
    private BigInteger comentarioId;
    @SerializedName("usuario")
    @Expose
    private BigInteger usuario;
    @SerializedName("contenido")
    @Expose
    private String contenido;
    @SerializedName("fechaComentario")
    @Expose
    private String fechaComentario;
    @SerializedName("publicacion")
    @Expose
    private BigInteger publicacion;
    @SerializedName("fotoUsuario")
    @Expose
    private String fotoUsuario;
    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;

    public ComentarioPojo() {

    }

    protected ComentarioPojo(Parcel in) {
        contenido = in.readString();
        fechaComentario = in.readString();
        fotoUsuario = in.readString();
        nombreUsuario = in.readString();
    }

    public static final Creator<ComentarioPojo> CREATOR = new Creator<ComentarioPojo>() {
        @Override
        public ComentarioPojo createFromParcel(Parcel in) {
            return new ComentarioPojo(in);
        }

        @Override
        public ComentarioPojo[] newArray(int size) {
            return new ComentarioPojo[size];
        }
    };

    public BigInteger getComentarioId() {
        return comentarioId;
    }

    public void setComentarioId(BigInteger comentarioId) {
        this.comentarioId = comentarioId;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public BigInteger getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(BigInteger publicacion) {
        this.publicacion = publicacion;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contenido);
        dest.writeString(fechaComentario);
        dest.writeString(fotoUsuario);
        dest.writeString(nombreUsuario);
    }
}
