package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface ConcesionarioBeanLocal {

    List<CvConcesionario> ListaConcesionarios();

    CvConcesionario saveConcesionario(CvConcesionario concesionario);

    CvConcesionario findConcesionario(Integer idConcesionario);

    CvConcesionario updateConcesionario(CvConcesionario concesionario);

    /*Servicio oficial*/
    List<CvServicioOficial> ListaServiciosOficiales();

    List<CvServicioOficial> ListaServiciosOficialesByIdConcesionario(Integer idConcesionario);

    CvServicioOficial saveServicioOficial(CvServicioOficial servicioOficial);

    CvServicioOficial findServicioOficial(Integer idServicioOficial);

    CvServicioOficial findServicioOficialByIdConcesionario(Integer idConcesionario);

    CvServicioOficial updateServicioOficial(CvServicioOficial servicioOficial);

    /*Registro de proveedores*/
    CvProveedor saveProveedor(CvProveedor proveedor);

    List<CvProveedor> listaProveedoresByIdConcesionario(Integer idConcesionario);
}
