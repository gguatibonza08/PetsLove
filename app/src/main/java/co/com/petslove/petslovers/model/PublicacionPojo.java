package co.com.petslove.petslovers.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class PublicacionPojo implements Serializable {
	@SerializedName("publicacionId")
	@Expose
	private BigInteger	publicacionId;
	@SerializedName("horaPublicacion")
	@Expose
	private Date horaPublicacion;
	@SerializedName("descripcion")
	@Expose
	private String descripcion;
	@SerializedName("usuario")
	@Expose
	private BigInteger usuario;
	@SerializedName("fotoUsuario")
	@Expose
	private String fotoUsuario;
	@SerializedName("nombreUsuario")
	@Expose
	private String nombreUsuario;
	@SerializedName("comentarios")
	@Expose
	private List<ComentarioPojo> comentarios;
	@SerializedName("foto")
	@Expose
	private String foto;
	@SerializedName("likes")
	@Expose
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
	public List<ComentarioPojo> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<ComentarioPojo> comentarios) {
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