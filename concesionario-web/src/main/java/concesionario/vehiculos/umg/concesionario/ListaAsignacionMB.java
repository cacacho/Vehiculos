package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTraspasoVehiculo;
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
@ManagedBean(name = "listaAsignacionMB")
@ViewScoped
public class ListaAsignacionMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaAsignacionMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private List<CvTraspasoVehiculo> listAsignacionVehiculo;
    private CvTraspasoVehiculo selectedAsignacionVehiculo;

    public ListaAsignacionMB() {
    }

    public void cargarDatos() {
        listAsignacionVehiculo = concesionarioBean.listAsignacionVehiculo();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/concesionario/asignacion/registro.xhtml");
    }

    public String verDetalle() {
        return "detalle.xhtml?faces-redirect=true&idAsignacion=" + selectedAsignacionVehiculo.getIdTraspasoVehiculo();
    }

    /*Metodos getters y setters*/
    public List<CvTraspasoVehiculo> getListAsignacionVehiculo() {
        return listAsignacionVehiculo;
    }

    public void setListAsignacionVehiculo(List<CvTraspasoVehiculo> listAsignacionVehiculo) {
        this.listAsignacionVehiculo = listAsignacionVehiculo;
    }

    public CvTraspasoVehiculo getSelectedAsignacionVehiculo() {
        return selectedAsignacionVehiculo;
    }

    public void setSelectedAsignacionVehiculo(CvTraspasoVehiculo selectedAsignacionVehiculo) {
        this.selectedAsignacionVehiculo = selectedAsignacionVehiculo;
    }

}
