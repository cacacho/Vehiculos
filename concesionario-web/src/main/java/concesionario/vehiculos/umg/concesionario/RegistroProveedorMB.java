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
@ManagedBean(name = "RegistroProveedorMB")
@ViewScoped
public class RegistroProveedorMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroProveedorMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBeanlocal;

    private CvProveedor proveedor;

    public RegistroProveedorMB() {
    }

    public void saveServicioOficial() {
        CvProveedor prove = new CvProveedor();

        prove = concesionarioBeanlocal.saveProveedor(proveedor);
        if (prove.getIdProveedor() != null) {
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
             JsfUtil.redirectTo("/proveedor/lista.xhtml");
        } else {
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    public void cancelarRegistro() {
        JsfUtil.redirectTo("/proveedor/lista.xhtml");
    }

    /*Metodos getters y setters*/
    public CvProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(CvProveedor proveedor) {
        this.proveedor = proveedor;
    }

}
