package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Busqueda implements Serializable, Parcelable {
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("raza")
    @Expose
    private String raza;
    @SerializedName("departamento")
    @Expose
    private String departamento;
    @SerializedName("ciudad")
    @Expose
    private String ciudad;
    @SerializedName("nprecioMax")
    @Expose
    private String precioMax;
    @SerializedName("precioMin")
    @Expose
    private String precioMin;

    @SerializedName("adopta")
    @Expose
    private boolean adopta;

    @SerializedName("urlTipo")
    @Expose
    private String urlTipo;

    public Busqueda(String tipo, String raza, String departamento, String ciudad, String precioMax, String precioMin) {
        this.tipo = tipo;
        this.raza = raza;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.precioMax = precioMax;
        this.precioMin = precioMin;
    }

    public Busqueda() {

    }

    public Busqueda(String tipo, String urlTipo) {
        this.tipo = tipo;
        this.urlTipo = urlTipo;
    }

    protected Busqueda(Parcel in) {
        tipo = in.readString();
        raza = in.readString();
        departamento = in.readString();
        ciudad = in.readString();
        precioMax = in.readString();
        precioMin = in.readString();
        adopta = in.readByte() != 0;
        urlTipo = in.readString();
    }

    public static final Creator<Busqueda> CREATOR = new Creator<Busqueda>() {
        @Override
        public Busqueda createFromParcel(Parcel in) {
            return new Busqueda(in);
        }

        @Override
        public Busqueda[] newArray(int size) {
            return new Busqueda[size];
        }
    };

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

    public String getPrecioMax() {
        return precioMax;
    }

    public void setPrecioMax(String precioMax) {
        this.precioMax = precioMax;
    }

    public String getPrecioMin() {
        return precioMin;
    }

    public void setPrecioMin(String precioMin) {
        this.precioMin = precioMin;
    }

    public boolean isAdopta() {
        return adopta;
    }

    public void setAdopta(boolean adopta) {
        this.adopta = adopta;
    }

    public String getUrlTipo() {
        return urlTipo;
    }

    public void setUrlTipo(String urlTipo) {
        this.urlTipo = urlTipo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tipo);
        dest.writeString(raza);
        dest.writeString(departamento);
        dest.writeString(ciudad);
        dest.writeString(precioMax);
        dest.writeString(precioMin);
        dest.writeByte((byte) (adopta ? 1 : 0));
        dest.writeString(urlTipo);
    }
}
