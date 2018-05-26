package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoColaborador;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface CatalogoBeanLocal {

    List<CvTipoColaborador> listAllTipoColaborador();

    List<CvProveedor> listAllProveedor();
    
     List<CvConcesionario> listAllConcesionarios();

}
