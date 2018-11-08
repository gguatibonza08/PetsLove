package co.com.petslove.petslovers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.Set;


public class AnimalPojo {
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


}
