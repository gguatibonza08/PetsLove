package co.com.petslove.petslovers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;

public class ComentarioPojo implements Serializable {
    @SerializedName("comentarioId")
    @Expose
	private BigInteger comentarioId;
    @SerializedName("usuario")
    @Expose
	private BigInteger usuario;
    @SerializedName("contenido")
    @Expose
	private String contenido;
    @SerializedName("fechaComentario")
    @Expose
	private String fechaComentario;
    @SerializedName("publicacion")
    @Expose
	private BigInteger publicacion;
	
	public ComentarioPojo() {
		
	}

	public BigInteger getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(BigInteger comentarioId) {
		this.comentarioId = comentarioId;
	}

	public BigInteger getUsuario() {
		return usuario;
	}

	public void setUsuario(BigInteger usuario) {
		this.usuario = usuario;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(String fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public BigInteger getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(BigInteger publicacion) {
		this.publicacion = publicacion;
	}

}
