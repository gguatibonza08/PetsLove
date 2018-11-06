package com.Servicios.kevin.pojos;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.Servicios.kevin.entities.Comentario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class PublicacionPojo {
	private BigInteger	publicacionId;
	private Date horaPublicacion;
	private String descripcion;
	private BigInteger usuario;
	private String fotoUsuario;
	private String nombreUsuario;
	@JsonIgnore
	private List<Comentario> comentarios;
	private String foto;
	private Integer likes;
	
	public PublicacionPojo() {
		
	}
	
	public BigInteger getPublicacionId() {
		return publicacionId;
	}
	public void setPublicacionId(BigInteger publicacionId) {
		this.publicacionId = publicacionId;
	}
	public Date getHoraPublicacion() {
		return horaPublicacion;
	}
	public void setHoraPublicacion(Date horaPublicacion) {
		this.horaPublicacion = horaPublicacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigInteger getUsuario() {
		return usuario;
	}
	public void setUsuario(BigInteger usuario) {
		this.usuario = usuario;
	}
	public String getFotoUsuario() {
		return fotoUsuario;
	}
	public void setFotoUsuario(String fotoUsuario) {
		this.fotoUsuario = fotoUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	
	
	
	
}
