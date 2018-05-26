package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "detalleServicioOficialMB")
@ViewScoped
public class DetalleServicioOficialMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleServicioOficialMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private CvServicioOficial servicio;
    private Integer idServicio;

    public DetalleServicioOficialMB() {
    }

    public void cargarDatos() {
        servicio = concesionarioBean.findServicioOficial(idServicio);
    }

    /*Metodos getters y setters*/
    public CvServicioOficial getServicio() {
        return servicio;
    }

    public void setServicio(CvServicioOficial servicio) {
        this.servicio = servicio;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

}
