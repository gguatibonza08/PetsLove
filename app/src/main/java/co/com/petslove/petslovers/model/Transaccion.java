package co.com.petslove.petslovers.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transaccion implements Serializable {

    private String raza;
    private String tipo;
    private String descripcion;
    private String precio;
    private boolean adopta;
    private List<String> fotos = new ArrayList<>();

    public Transaccion(String raza, String tipo, String descripcion, String precio, boolean adopta, List<String> fotos) {
        this.raza = raza;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.adopta = adopta;
        this.fotos = fotos;
    }

    public Transaccion() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public boolean isAdopta() {
        return adopta;
    }

    public void setAdopta(boolean adopta) {
        this.adopta = adopta;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }
}
