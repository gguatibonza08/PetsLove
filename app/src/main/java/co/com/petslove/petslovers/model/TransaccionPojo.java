package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolarte on 3/03/18.
 */
public class TransaccionPojo implements Serializable, Parcelable {
    @SerializedName("usuario")
    @Expose
    private BigInteger usuario;

    //datos nuevo para pojo
    @SerializedName("nombreUsuario")
    @Expose
    private String nombreUsuario;
    @SerializedName("fotoUsuario")
    @Expose
    private String fotoUsuario;
    @SerializedName("calificacionUsuario")
    @Expose
    private int calificacionUsuario;

    //hasta aquí
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("raza")
    @Expose
    private String raza;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("ubicacion")
    @Expose
    private String ubicacion;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("activo")
    @Expose
    private boolean activo;
    @SerializedName("precio")
    @Expose
    private Integer precio;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("fotografias")
    @Expose
    private List<String> fotografias;


    public TransaccionPojo(String nombreUsuario, String fotoUsuario, int calificacionUsuario, String ciudad, String direccion, Integer precio, String foto, ArrayList<String> fotos) {
        this.nombreUsuario = nombreUsuario;
        this.fotoUsuario = fotoUsuario;
        this.calificacionUsuario = calificacionUsuario;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.precio = precio;
        this.foto = foto;
        this.raza = "prueba2";
        this.descripcion = "aosfas osbfas ifsvaf asiufva";
        this.fotografias = fotos;
        Log.e("fotosize", this.fotografias.size() + "");
    }

    public TransaccionPojo() {

    }

    protected TransaccionPojo(Parcel in) {
        nombreUsuario = in.readString();
        fotoUsuario = in.readString();
        calificacionUsuario = in.readInt();
        tipo = in.readString();
        raza = in.readString();
        descripcion = in.readString();
        departamento = in.readString();
        ciudad = in.readString();
        direccion = in.readString();
        ubicacion = in.readString();
        status = in.readString();
        activo = in.readByte() != 0;
        if (in.readByte() == 0) {
            precio = null;
        } else {
            precio = in.readInt();
        }
        foto = in.readString();
        fotografias = in.createStringArrayList();
    }

    public static final Creator<TransaccionPojo> CREATOR = new Creator<TransaccionPojo>() {
        @Override
        public TransaccionPojo createFromParcel(Parcel in) {
            return new TransaccionPojo(in);
        }

        @Override
        public TransaccionPojo[] newArray(int size) {
            return new TransaccionPojo[size];
        }
    };

    public int getCalificacionUsuario() {
        return calificacionUsuario;
    }

    public void setCalificacionUsuario(int calificacionUsuario) {
        this.calificacionUsuario = calificacionUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<String> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<String> fotografias) {
        this.fotografias = fotografias;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombreUsuario);
        dest.writeString(fotoUsuario);
        dest.writeInt(calificacionUsuario);
        dest.writeString(tipo);
        dest.writeString(raza);
        dest.writeString(descripcion);
        dest.writeString(departamento);
        dest.writeString(ciudad);
        dest.writeString(direccion);
        dest.writeString(ubicacion);
        dest.writeString(status);
        dest.writeByte((byte) (activo ? 1 : 0));
        if (precio == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(precio);
        }
        dest.writeString(foto);
        dest.writeStringList(fotografias);
    }
}
