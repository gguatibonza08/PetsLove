package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tipo implements Serializable,Parcelable {

    @SerializedName("idTipo")
    @Expose
    private int idTipo;
    @SerializedName("raza")
    @Expose
    private String raza;
    @SerializedName("nombreTipo")
    @Expose
    private String nombreTipo;

    @SerializedName("urlTipo")
    @Expose
    private String urlTipo;

    public Tipo() {

    }

    public Tipo(int idTipo, String raza, String nombreTipo, String urlTipo) {
        this.idTipo = idTipo;
        this.raza = raza;
        this.nombreTipo = nombreTipo;
        this.urlTipo = urlTipo;
    }

    protected Tipo(Parcel in) {
        idTipo = in.readInt();
        raza = in.readString();
        nombreTipo = in.readString();
        urlTipo = in.readString();
    }

    public static final Creator<Tipo> CREATOR = new Creator<Tipo>() {
        @Override
        public Tipo createFromParcel(Parcel in) {
            return new Tipo(in);
        }

        @Override
        public Tipo[] newArray(int size) {
            return new Tipo[size];
        }
    };

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
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
        dest.writeInt(idTipo);
        dest.writeString(raza);
        dest.writeString(nombreTipo);
        dest.writeString(urlTipo);
    }
}
