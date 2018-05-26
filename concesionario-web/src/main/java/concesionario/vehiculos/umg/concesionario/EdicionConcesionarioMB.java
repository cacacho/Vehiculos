package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
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
@ManagedBean(name = "edicionConcesionarioMB")
@ViewScoped
public class EdicionConcesionarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(EdicionConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private Integer idConcesionario;
    private CvConcesionario concesionario;

    public EdicionConcesionarioMB() {
    }

    public void cargarDatos() {
        concesionario = concesionarioBean.findConcesionario(idConcesionario);
    }

    public void cancelarEdicion() {
        JsfUtil.redirectTo("/concesionario/detalle.xhtml?faces-redirect=true&idConcesionario=" + idConcesionario);
    }

    public void actualizarConcesionario() {
        CvConcesionario conce = new CvConcesionario();
        conce = concesionarioBean.updateConcesionario(concesionario);

        if (conce.getIdConcesionario() != null) {
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    /*Metodos getters y setters*/
    public Integer getIdConcesionario() {
        return idConcesionario;
    }

    public void setIdConcesionario(Integer idConcesionario) {
        this.idConcesionario = idConcesionario;
    }

    public CvConcesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(CvConcesionario concesionario) {
        this.concesionario = concesionario;
    }

}
