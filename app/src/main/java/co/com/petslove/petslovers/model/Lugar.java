package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Lugar implements Serializable,Parcelable {
    @Expose
    @SerializedName("idLugar")
    private int idLugar;

    @Expose
    @SerializedName("ciudad")
    private String ciudad;
    @Expose
    @SerializedName("departamento")
    private String departamento;


    public Lugar() {

    }


    protected Lugar(Parcel in) {
        idLugar = in.readInt();
        ciudad = in.readString();
        departamento = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idLugar);
        dest.writeString(ciudad);
        dest.writeString(departamento);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Lugar> CREATOR = new Creator<Lugar>() {
        @Override
        public Lugar createFromParcel(Parcel in) {
            return new Lugar(in);
        }

        @Override
        public Lugar[] newArray(int size) {
            return new Lugar[size];
        }
    };

    public int getIdLugar() {
        return idLugar;
    }

    public void setIdLugar(int idLugar) {
        this.idLugar = idLugar;
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
}