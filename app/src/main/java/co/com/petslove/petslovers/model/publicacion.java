package co.com.petslove.petslovers.model;

public class publicacion {
    String nombreUsuario;
    String fotoUsuario;
    String foto;
    String likes;
    String fechaPublicacion;

    public publicacion() {
    }

    public publicacion(String nombreUsuario, String fotoUsuario, String foto, String likes, String fechaPublicacion) {
        this.nombreUsuario = nombreUsuario;
        this.fotoUsuario = fotoUsuario;
        this.foto = foto;
        this.likes = likes;
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}
