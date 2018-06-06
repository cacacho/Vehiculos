package concesionario.vehiculos.umg.venta;

import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvPedido;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "listaPedidoMB")
@ViewScoped
public class ListaPedidoMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaPedidoMB.class);

    @EJB
    private VentaBeanLocal ventaBeanLocal;

    private List<CvPedido> listPedido;
    private CvPedido pedido;

    public ListaPedidoMB() {
    }

    public void cargarDatos() {
        listPedido = ventaBeanLocal.ListaPedidos();
    }
    
     public String verDetalle() {
        return "detalle.xhtml?faces-redirect=true&idPedido=" + pedido.getIdPedido();
    }

    /*Metodos getters y setters*/
    public List<CvPedido> getListPedido() {
        return listPedido;
    }

    public void setListPedido(List<CvPedido> listPedido) {
        this.listPedido = listPedido;
    }

    public CvPedido getPedido() {
        return pedido;
    }

    public void setPedido(CvPedido pedido) {
        this.pedido = pedido;
    }
    
    

}
