package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface VehiculoBeanLocal {

    List<CvVehiculo> ListaVehiculos();

    List<CvVehiculo> ListaVehiculosByIdConcesionario(Integer idConcesionario);

    CvVehiculo saveVehiculo(CvVehiculo CvVehiculo);

    CvVehiculo findVehiculo(Integer idVehiculo);

    CvVehiculo updateVehiculo(CvVehiculo vehiculo);

    /*Extra vehiculo*/
    CvExtraVehiculo findExtraVehiculo(Integer idExtraVehiculo);

    CvExtraVehiculo updateExtraVehiculoByIdVehiculo(CvExtraVehiculo idExtraVehiculo);

    List<CvExtraVehiculo> listExtraVehiculoByIdVehiculoByIdVehiculo(Integer idVehiculo);

    CvExtraVehiculo saveExtraVehiculo(CvExtraVehiculo extraVehiculo);

}
