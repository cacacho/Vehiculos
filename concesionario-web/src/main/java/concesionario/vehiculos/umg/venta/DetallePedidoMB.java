package concesionario.vehiculos.umg.venta;

import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvPedido;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "detallePedidoMB")
@ViewScoped
public class DetallePedidoMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleVentaMB.class);

    @EJB
    private VentaBeanLocal ventaBeanLocal;

    private CvPedido pedido;
    private Integer idPedido;

    public DetallePedidoMB() {
    }

    public void cargarDatos() {
        pedido = ventaBeanLocal.findPedido(idPedido);
    }

    /*Metodos getters y setters*/
    public CvPedido getPedido() {
        return pedido;
    }

    public void setPedido(CvPedido pedido) {
        this.pedido = pedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }
    
    

}
