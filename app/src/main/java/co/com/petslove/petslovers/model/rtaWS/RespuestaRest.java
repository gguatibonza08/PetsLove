package co.com.petslove.petslovers.model.rtaWS;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.math.BigInteger;

import co.com.petslove.petslovers.model.Usuario;

public class RespuestaRest implements Serializable, Parcelable {
    private int codigoRespuesta;
    private String mensaje;
    private BigInteger idRest;
    private boolean confirmacion;
    private Usuario objectRest;


    public RespuestaRest(int codigoRespuesta, String mensaje) {
        super();
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
    }

    public RespuestaRest(int codigoRespuesta, String mensaje, BigInteger idRest) {
        super();
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.idRest = idRest;
    }

    public RespuestaRest(int codigoRespuesta, String mensaje, BigInteger idRest, boolean confirmacion, Usuario user) {
        this.codigoRespuesta = codigoRespuesta;
        this.mensaje = mensaje;
        this.idRest = idRest;
        this.confirmacion = confirmacion;
        this.objectRest = user;
    }

    public RespuestaRest() {

    }

    protected RespuestaRest(Parcel in) {
        codigoRespuesta = in.readInt();
        mensaje = in.readString();
        confirmacion = in.readByte() != 0;
    }

    public static final Creator<RespuestaRest> CREATOR = new Creator<RespuestaRest>() {
        @Override
        public RespuestaRest createFromParcel(Parcel in) {
            return new RespuestaRest(in);
        }

        @Override
        public RespuestaRest[] newArray(int size) {
            return new RespuestaRest[size];
        }
    };

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public BigInteger getIdRest() {
        return idRest;
    }

    public void setIdRest(BigInteger idRest) {
        this.idRest = idRest;
    }

    public boolean isConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public Usuario getUser() {
        return objectRest;
    }

    public void setUser(Usuario user) {
        this.objectRest = user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(codigoRespuesta);
        dest.writeString(mensaje);
        dest.writeByte((byte) (confirmacion ? 1 : 0));
    }
}