package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface VehiculoBeanLocal {

    List<CvVehiculo> ListaVehiculos();
    
    List<CvVehiculo> ListaVehiculosByIdConcesionario(Integer idConcesionario);

    CvVehiculo saveConcesionario(CvVehiculo CvVehiculo);

    CvVehiculo findConcesionario(Integer idVehiculo);

    CvVehiculo updateConcesionario(CvVehiculo vehiculo);

}
