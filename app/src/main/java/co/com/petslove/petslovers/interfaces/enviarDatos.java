package co.com.petslove.petslovers.interfaces;

import co.com.petslove.petslovers.model.Busqueda;
import co.com.petslove.petslovers.model.EstablecimientoPojo;
import co.com.petslove.petslovers.model.PublicacionPojo;
import co.com.petslove.petslovers.model.TransaccionPojo;

public interface enviarDatos {

    void EnviarDetalle(TransaccionPojo datos);

    void EnviarPublicacion(PublicacionPojo publicacion);

    void EnviarEstablecimiento(EstablecimientoPojo establecimiento);

    void EnviarBusqueda(Busqueda busqueda);

    void addPublicacion();
}
