package co.com.petslove.petslovers.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FotografiaPojo implements Serializable,Parcelable {
	private Long idFotografia;
	private String url;
	private List<EstablecimientoPojo> establecimientos = new ArrayList<>();
	private List<AnimalPojo> animales = new ArrayList<>();
	
	public FotografiaPojo() {
		super();
	}

	protected FotografiaPojo(Parcel in) {
		if (in.readByte() == 0) {
			idFotografia = null;
		} else {
			idFotografia = in.readLong();
		}
		url = in.readString();
		establecimientos = in.createTypedArrayList(EstablecimientoPojo.CREATOR);
		animales = in.createTypedArrayList(AnimalPojo.CREATOR);
	}

	public static final Creator<FotografiaPojo> CREATOR = new Creator<FotografiaPojo>() {
		@Override
		public FotografiaPojo createFromParcel(Parcel in) {
			return new FotografiaPojo(in);
		}

		@Override
		public FotografiaPojo[] newArray(int size) {
			return new FotografiaPojo[size];
		}
	};

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


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (idFotografia == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeLong(idFotografia);
		}
		dest.writeString(url);
		dest.writeTypedList(establecimientos);
		dest.writeTypedList(animales);
	}
}
