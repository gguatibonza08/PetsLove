package co.com.petslove.petslovers.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FotografiaPojo {
	private Long idFotografia;
	private String url;
	private List<EstablecimientoPojo> establecimientos = new ArrayList<>();
	private List<AnimalPojo> animales = new ArrayList<>();
	
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
	public List<EstablecimientoPojo> getEstablecimientos() {
		return establecimientos;
	}
	public void setEstablecimientos(List<EstablecimientoPojo> establecimientos) {
		this.establecimientos = establecimientos;
	}
	public List<AnimalPojo> getAnimales() {
		return animales;
	}
	public void setAnimales(List<AnimalPojo> animales) {
		this.animales = animales;
	}
	
	
}
