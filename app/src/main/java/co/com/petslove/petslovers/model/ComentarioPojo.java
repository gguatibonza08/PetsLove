package com.Servicios.kevin.pojos;

import java.math.BigInteger;

public class ComentarioPojo {
	private BigInteger comentarioId;
	private BigInteger usuario;
	private String contenido;
	private String fechaComentario;
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
