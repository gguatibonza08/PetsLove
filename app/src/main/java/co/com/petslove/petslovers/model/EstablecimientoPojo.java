
package co.com.petslove.petslovers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: KevinOlarte
 */
public class EstablecimientoPojo {
    @SerializedName("idEstablecimiento")
    @Expose
    private BigInteger idEstablecimiento;
    @SerializedName("calificacion")
    @Expose
    private int calificacion;
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("nombre")
    @Expose
    private String nombre;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("telefono")
    @Expose
    private int telefono;
    @SerializedName("tipo")
    @Expose
    private String tipo;
    @SerializedName("usuario")
    @Expose
    private BigInteger usuario;
    @SerializedName("fotografias")
    @Expose
    private Set<FotografiaPojo> fotografias;

    public EstablecimientoPojo() {
        this.fotografias = new HashSet<>();
    }

    public BigInteger getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(BigInteger idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correoo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigInteger getUsuario() {
        return usuario;
    }

    public void setUsuario(BigInteger usuario) {
        this.usuario = usuario;
    }

    public Set<FotografiaPojo> getFotografias() {
        return fotografias;
    }

    public void setFotografias(Set<FotografiaPojo> fotografias) {
        this.fotografias = fotografias;
    }


}
