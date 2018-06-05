package concesionario.vehiculos.umg.venta;

import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVenta;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
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
@ManagedBean(name = "listaVentaMB")
@ViewScoped
public class ListaVentaMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaVentaMB.class);

    @EJB
    private VentaBeanLocal ventaBeanLocal;

    private CvVenta venta;
    private List<CvVenta> listVentas;

    public ListaVentaMB() {
    }

    public void cargarDatos() {
        listVentas = ventaBeanLocal.ListaVentas();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/ventas/registro.xhtml");
    }

    public String verDetalle() {
        return "/ventas/detalle.xhtml?faces-redirect=true&idVenta=" + venta.getIdVenta();
    }


    /*Metodos getters y setters*/
    public CvVenta getVenta() {
        return venta;
    }

    public void setVenta(CvVenta venta) {
        this.venta = venta;
    }

    public List<CvVenta> getListVentas() {
        return listVentas;
    }

    public void setListVentas(List<CvVenta> listVentas) {
        this.listVentas = listVentas;
    }

}
