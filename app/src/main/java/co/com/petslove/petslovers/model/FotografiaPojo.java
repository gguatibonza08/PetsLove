package co.com.petslove.petslovers.model;

import java.util.HashSet;
import java.util.Set;

public class FotografiaPojo {
	private Long idFotografia;
	private String url;
	private Set<EstablecimientoPojo> establecimientos = new HashSet<>();
	private Set<AnimalPojo> animales = new HashSet<>();
	
	public FotografiaPojo() {
		super();
	}
	public Long getIdFotografia() {
		return idFotografia;
	}
	public void setIdFotografia(Long idFotografia) {
		this.idFotografia = idFotografia;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<EstablecimientoPojo> getEstablecimientos() {
		return establecimientos;
	}
	public void setEstablecimientos(Set<EstablecimientoPojo> establecimientos) {
		this.establecimientos = establecimientos;
	}
	public Set<AnimalPojo> getAnimales() {
		return animales;
	}
	public void setAnimales(Set<AnimalPojo> animales) {
		this.animales = animales;
	}
	
	
}
