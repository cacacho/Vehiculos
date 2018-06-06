package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvCliente;
import concesionario.vehiculos.umg.concesionario.api.entity.CvPedido;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoPedido;
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

    /*Clientes*/
    CvCliente findCliente(String nit);

    CvCliente saveCliente(CvCliente cliente);

    /*Pedidos*/
    CvPedido savePedido(CvPedido pedido);

    CvTipoPedido findTipoPedido(Integer idTipoPedido);

    CvPedido findPedido(Integer idPedido);

    List<CvPedido> ListaPedidos();

}
