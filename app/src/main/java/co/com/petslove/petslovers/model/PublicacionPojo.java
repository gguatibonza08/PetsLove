package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class PublicacionPojo implements Serializable, Parcelable {
    @SerializedName("publicacionId")
    @Expose
    private BigInteger publicacionId;
    @SerializedName("horaPublicacion")
    @Expose
    private String horaPublicacion;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("usuario")
    @Expose
    private BigInteger usuario;
    @SerializedName("fotoUsuario")
    @Expose
    private String fotoUsuario;
    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("comentarios")
    @Expose
    private List<ComentarioPojo> comentarios;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("correoUsuario")
    @Expose
    private String correoUsuario;
    public PublicacionPojo() {

    }

    protected PublicacionPojo(Parcel in) {
        descripcion = in.readString();
        fotoUsuario = in.readString();
        nombreUsuario = in.readString();
        comentarios = in.createTypedArrayList(ComentarioPojo.CREATOR);
        foto = in.readString();
        if (in.readByte() == 0) {
            likes = null;
        } else {
            likes = in.readInt();
        }
    }

    public static final Creator<PublicacionPojo> CREATOR = new Creator<PublicacionPojo>() {
        @Override
        public PublicacionPojo createFromParcel(Parcel in) {
            return new PublicacionPojo(in);
        }

        @Override
        public PublicacionPojo[] newArray(int size) {
            return new PublicacionPojo[size];
        }
    };

    public BigInteger getPublicacionId() {
        return publicacionId;
    }

    public void setPublicacionId(BigInteger publicacionId) {
        this.publicacionId = publicacionId;
    }

    public String getHoraPublicacion() {
        return horaPublicacion;
    }

    public void setHoraPublicacion(String horaPublicacion) {
        this.horaPublicacion = horaPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
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

    public List<ComentarioPojo> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioPojo> comentarios) {
        this.comentarios = comentarios;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(descripcion);
        dest.writeString(fotoUsuario);
        dest.writeString(nombreUsuario);
        dest.writeTypedList(comentarios);
        dest.writeString(foto);
        if (likes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likes);
        }
    }
}
