package co.com.petslove.petslovers.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AnimalPojo {
    private BigInteger idAnimal;

    private String ciudad;
    private String departamento;
    private String descripcion;
    private String direccion;
    private String foto;
    private String status;
    private List<String> fotografias = new ArrayList<>();
    private String raza;
    private String tipo;
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

    public List<String> getFotografias() {
        return fotografias;
    }

    public void setFotografias(List<String> fotografias) {
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
