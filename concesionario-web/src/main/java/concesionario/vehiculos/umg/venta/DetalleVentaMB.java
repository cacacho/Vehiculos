package concesionario.vehiculos.umg.venta;

import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVenta;
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
@ManagedBean(name = "detalleVentaMB")
@ViewScoped
public class DetalleVentaMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleVentaMB.class);

    @EJB
    private VentaBeanLocal ventaBeanLocal;

    private CvVenta venta;
    private Integer idVenta;
    private List<CvVenta> listVentas;

    public DetalleVentaMB() {
    }

    public void cargarDatos() {
        venta = ventaBeanLocal.findVenta(idVenta);
    }

    /*Metodos getters y setters*/
    public CvVenta getVenta() {
        return venta;
    }

    public void setVenta(CvVenta venta) {
        this.venta = venta;
    }

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public List<CvVenta> getListVentas() {
        return listVentas;
    }

    public void setListVentas(List<CvVenta> listVentas) {
        this.listVentas = listVentas;
    }

}
