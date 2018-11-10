package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;


public class AnimalPojo implements Serializable, Parcelable {
    @SerializedName("idAnimal")
    @Expose
    private BigInteger idAnimal;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("fotografias ")
    @Expose
    private Set<FotografiaPojo> fotografias;
    @SerializedName("raza")
    @Expose
    private String raza;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("ubicacion")
    @Expose
    private String ubicacion;

    public AnimalPojo() {
        super();
    }

    protected AnimalPojo(Parcel in) {
        ciudad = in.readString();
        departamento = in.readString();
        descripcion = in.readString();
        direccion = in.readString();
        foto = in.readString();
        status = in.readString();
        raza = in.readString();
        tipo = in.readString();
        ubicacion = in.readString();
    }

    public static final Creator<AnimalPojo> CREATOR = new Creator<AnimalPojo>() {
        @Override
        public AnimalPojo createFromParcel(Parcel in) {
            return new AnimalPojo(in);
        }

        @Override
        public AnimalPojo[] newArray(int size) {
            return new AnimalPojo[size];
        }
    };

    public BigInteger getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(BigInteger idAnimal) {
        this.idAnimal = idAnimal;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<FotografiaPojo> getFotografias() {
        return fotografias;
    }

    public void setFotografias(Set<FotografiaPojo> fotografias) {
        this.fotografias = fotografias;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ciudad);
        dest.writeString(departamento);
        dest.writeString(descripcion);
        dest.writeString(direccion);
        dest.writeString(foto);
        dest.writeString(status);
        dest.writeString(raza);
        dest.writeString(tipo);
        dest.writeString(ubicacion);
    }
}
