package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
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
@ManagedBean(name = "edicionServicioOficialMB")
@ViewScoped
public class EdicionServicioOficialMB implements Serializable {

    private static final Logger log = Logger.getLogger(EdicionServicioOficialMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private Integer idServicio;
    private CvServicioOficial servicio;

    public EdicionServicioOficialMB() {
    }

    public void cargarDatos() {
        servicio = concesionarioBean.findServicioOficial(idServicio);

    }

    public void cancelarEdicion() {
        JsfUtil.redirectTo("/concesionario/servicioOficial/detalle.xhtml?faces-redirect=true&idServicio=" + idServicio);
    }

    public void actualizarServicio() {
        CvServicioOficial of = new CvServicioOficial();
        of = concesionarioBean.updateServicioOficial(servicio);
        if (of.getIdServicioOficial() != null) {
 
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    /*Metodos getters y setters*/
    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public CvServicioOficial getServicio() {
        return servicio;
    }

    public void setServicio(CvServicioOficial servicio) {
        this.servicio = servicio;
    }

}
