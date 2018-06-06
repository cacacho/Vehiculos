package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionarioProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvMarca;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoColaborador;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoPago;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoVehiculo;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CatalogoBeanLocal {

    List<CvTipoColaborador> listAllTipoColaborador();

    List<CvProveedor> listAllProveedor();

    List<CvConcesionario> listAllConcesionarios();

    List<CvTipoVehiculo> listAllTipoVehiculo();

    List<CvMarca> listAllMarcaVehiculo();

    List<CvTipoPago> listAllTipoPago();

    CvConcesionarioProveedor findConcesionarioProveedor(Integer idConcesionario);
}
