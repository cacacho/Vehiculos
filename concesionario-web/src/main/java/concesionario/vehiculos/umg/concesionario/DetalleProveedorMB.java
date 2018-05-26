package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "detalleProveedorMB")
@ViewScoped
public class DetalleProveedorMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleProveedorMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private Integer idProveedor;
    private CvProveedor proveedor;

    public DetalleProveedorMB() {
    }

    public void cargarDatos() {
        proveedor = concesionarioBean.findProveedor(idProveedor);
    }

    public void cancelarEdicion() {
        JsfUtil.redirectTo("/proveedor/detalle.xhtml?faces-redirect=true&idProveedor=" + idProveedor);
    }

    /*Metdos getters y setters*/
    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }

    public CvProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(CvProveedor proveedor) {
        this.proveedor = proveedor;
    }

}
