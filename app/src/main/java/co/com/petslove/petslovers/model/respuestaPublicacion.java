package co.com.petslove.petslovers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class respuestaPublicacion {

    @SerializedName("codigoRespuesta")
    @Expose
    private Integer codigoRespuesta;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("idRest")
    @Expose
    private Object idRest;
    @SerializedName("objectRest")
    @Expose
    private List<PublicacionPojo> objectRest = null;
    @SerializedName("list")
    @Expose
    private Object list;

    public Integer getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(Integer codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getIdRest() {
        return idRest;
    }

    public void setIdRest(Object idRest) {
        this.idRest = idRest;
    }

    public List<PublicacionPojo> getObjectRest() {
        return objectRest;
    }

    public void setObjectRest(List<PublicacionPojo> objectRest) {
        this.objectRest = objectRest;
    }

    public Object getList() {
        return list;
    }

    public void setList(Object list) {
        this.list = list;
    }

}
