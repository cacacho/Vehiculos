package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "listaProveedorMB")
@ViewScoped
public class ListaProveedorMB {

    private static final Logger log = Logger.getLogger(ListaConcesionarioMB.class);

    @EJB
    private CatalogoBeanLocal catalogoBean;

    private List<CvProveedor> listaProveedores;
    private CvProveedor selectedProveedor;

    public ListaProveedorMB() {
    }

    public void cargarDatos() {
        listaProveedores = catalogoBean.listAllProveedor();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/proveedor/registro.xhtml");
    }

    public String verDetalle() {
        return "detalle.xhtml?faces-redirect=true&idProveedor=" + selectedProveedor.getIdProveedor();
    }

    /*Metodos getters y setters*/
    public List<CvProveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<CvProveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public CvProveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(CvProveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

}
