package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvVenta;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface VentaBeanLocal {

    CvVenta saveVentas(CvVenta venta);

    List<CvVenta> ListaVentas();

    CvVenta findVenta(Integer idVenta);
}
