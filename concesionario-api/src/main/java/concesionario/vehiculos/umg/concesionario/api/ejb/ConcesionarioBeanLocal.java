package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionarioProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTraspasoVehiculo;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ConcesionarioBeanLocal {

    List<CvConcesionario> ListaConcesionarios();

    CvConcesionario saveConcesionario(CvConcesionario concesionario);

    CvConcesionario findConcesionario(Integer idConcesionario);
    
    CvConcesionario findConcesionarioByNombre(String nombre);

    CvConcesionario updateConcesionario(CvConcesionario concesionario);

    /*Servicio oficial*/
    List<CvServicioOficial> ListaServiciosOficiales();

    List<CvServicioOficial> ListaServiciosOficialesByIdConcesionario(Integer idConcesionario);

    CvServicioOficial saveServicioOficial(CvServicioOficial servicioOficial);

    CvServicioOficial findServicioOficial(Integer idServicioOficial);
    
    CvServicioOficial findServicioOficialByNombre(String nombre);

    CvServicioOficial findServicioOficialByIdConcesionario(Integer idConcesionario);

    CvServicioOficial updateServicioOficial(CvServicioOficial servicioOficial);

    /*Registro de proveedores*/
    CvProveedor saveProveedor(CvProveedor proveedor);

    List<CvProveedor> listaProveedoresByIdConcesionario(Integer idConcesionario);

    CvProveedor findProveedor(Integer idProveedor);

    CvProveedor findProveedorByNombre(String nombre);

    CvProveedor updateProveedor(CvProveedor proveedor);

    CvConcesionarioProveedor AsignarProveedorConcesionario(CvConcesionarioProveedor concesionarioProveedor);

    CvConcesionarioProveedor findProveedorConcesionario(Integer idProveedor, Integer idConcesionario);

    /*Asignacion vehiculo*/
    List<CvTraspasoVehiculo> listAsignacionVehiculo();

    CvTraspasoVehiculo saveAsignacion(CvTraspasoVehiculo asginacion);

    CvTraspasoVehiculo cambiarConcesionario(CvTraspasoVehiculo asignacion);

    CvTraspasoVehiculo findAsignacionByIdConcesionario(Integer idConcesionario);

    CvTraspasoVehiculo findAsignacionByIdVehiculo(Integer idVehiculo);
}
