package co.com.petslove.petslovers.model;

import java.math.BigInteger;

public class RespuestaRest {
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
}