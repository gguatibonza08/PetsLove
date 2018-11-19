package co.com.petslove.petslovers.interfaces;

import co.com.petslove.petslovers.model.Transaccion;
import co.com.petslove.petslovers.model.TransaccionPojo;

public interface enviarPublicacion {

    void EnviarFotos(Transaccion transaccion);

    void EnviarInformacion(Transaccion transaccion);

    void EnviarUbicacion(Transaccion transaccion, TransaccionPojo animal);

}
