package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "detalleConcesionarioMB")
@ViewScoped
public class DetalleConcesionarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private Integer idConcesionario;
    private CvConcesionario concesionario;
    private boolean mostrarAgregarServicio;
    private boolean mostrarAgregarVehiculo;
    private boolean mostrarAgregarProveedor;

    public DetalleConcesionarioMB() {
    }

    public void cargarDatos() {
        concesionario = concesionarioBean.findConcesionario(idConcesionario);
    }

    public void visualizarAgregarServicio() {
        mostrarAgregarServicio = true;
    }

    public void cancelarAgregarServicio() {
        mostrarAgregarServicio = false;
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

    public boolean isMostrarAgregarServicio() {
        return mostrarAgregarServicio;
    }

    public void setMostrarAgregarServicio(boolean mostrarAgregarServicio) {
        this.mostrarAgregarServicio = mostrarAgregarServicio;
    }

    public boolean isMostrarAgregarVehiculo() {
        return mostrarAgregarVehiculo;
    }

    public void setMostrarAgregarVehiculo(boolean mostrarAgregarVehiculo) {
        this.mostrarAgregarVehiculo = mostrarAgregarVehiculo;
    }

    public boolean isMostrarAgregarProveedor() {
        return mostrarAgregarProveedor;
    }

    public void setMostrarAgregarProveedor(boolean mostrarAgregarProveedor) {
        this.mostrarAgregarProveedor = mostrarAgregarProveedor;
    }

}
