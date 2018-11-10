package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Usuario implements Serializable, Parcelable {
    @SerializedName("idUsuario")
    @Expose
    private int idUsuario;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("contraseña")
    @Expose
    private String contraseña;
    @SerializedName("calificacion")
    @Expose
    private int calificacion;
    @SerializedName("ventas")
    @Expose
    private int ventas;
    @SerializedName("perfil")
    @Expose
    private String perfil;


    public Usuario(int idUsuario, String nombre, String correo, String contraseña, int calificacion, int ventas, String perfil) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.calificacion = calificacion;
        this.ventas = ventas;
        this.perfil = perfil;
    }

    public Usuario() {

    }

    protected Usuario(Parcel in) {
        idUsuario = in.readInt();
        nombre = in.readString();
        correo = in.readString();
        contraseña = in.readString();
        calificacion = in.readInt();
        ventas = in.readInt();
        perfil = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idUsuario);
        dest.writeString(nombre);
        dest.writeString(correo);
        dest.writeString(contraseña);
        dest.writeInt(calificacion);
        dest.writeInt(ventas);
        dest.writeString(perfil);
    }
}