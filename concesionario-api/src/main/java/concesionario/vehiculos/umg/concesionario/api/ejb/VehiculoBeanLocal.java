package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvDetalleExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvMarca;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoVehiculo;
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

    CvVehiculo asignarConcesionarioVehiculo(Integer idVehiculo, CvConcesionario concesionario);

    List<CvVehiculo> ListaVehiculosByBastido(String bastidor);

    List<CvVehiculo> ListaVehiculosByPlaca(String placa);

    List<CvVehiculo> ListaVehiculosByMarca(String marca);

    List<CvVehiculo> ListaVehiculosByBastidorAndPlacaAndMarca(String bastido, String placa, String marca);

    List<CvVehiculo> ListaVehiculosByBastidorAndPlaca(String bastidor, String placa);

    List<CvVehiculo> ListaVehiculosByBastidorAndMarca(String bastidor, String marca);

    List<CvVehiculo> ListaVehiculosByPlacaAndMarca(String placa, String marca);
    
    CvVehiculo actualizarStockVehiculo(Integer idVehiculo, Integer cantidad);

    /*Extra vehiculo*/
    CvExtraVehiculo findExtraVehiculo(Integer idExtraVehiculo);

    CvExtraVehiculo updateExtraVehiculoByIdVehiculo(CvExtraVehiculo idExtraVehiculo);

    List<CvExtraVehiculo> listExtraVehiculoByIdVehiculoByIdVehiculo(Integer idVehiculo);

    CvExtraVehiculo saveExtraVehiculo(CvExtraVehiculo extraVehiculo);

    List<CvExtraVehiculo> listExtraVehiculo();

    /*Tipo vehiculo*/
    CvTipoVehiculo findTipoVehiculo(Integer idTipoVehiculo);

    CvTipoVehiculo updateExtraVehiculoByIdVehiculo(CvTipoVehiculo tipo);

    List<CvTipoVehiculo> listTipoVehiculoByIdVehiculoByIdVehiculo(Integer idVehiculo);

    CvTipoVehiculo saveTipoVehiculo(CvTipoVehiculo tipo);

    /*Marca del vehiculo*/
    CvMarca saveMarcaVehiculo(CvMarca marca);

    /*Detalle extra*/
    CvDetalleExtraVehiculo saveDetalleExtra(CvDetalleExtraVehiculo detalleExtra);

}
