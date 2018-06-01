package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
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
@ManagedBean(name = "listaVehiculoMB")
@ViewScoped
public class ListaVehiculoMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaVehiculoMB.class);

    @EJB
    private VehiculoBeanLocal vehiculosBean;

    private List<CvVehiculo> listaVehiculos;
    private CvVehiculo selectedVehiculo;

    public ListaVehiculoMB() {
    }

    public void cargarDatos() {
        listaVehiculos = vehiculosBean.ListaVehiculos();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/vehiculos/registro.xhtml");
    }

    public String verDetalle() {
        return "detalle.xhtml?faces-redirect=true&idVehiculo=" + selectedVehiculo.getIdVehiculo()+"&idRegresar=0";
    }

    /*Metodos getters y setters*/
    public List<CvVehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<CvVehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public CvVehiculo getSelectedVehiculo() {
        return selectedVehiculo;
    }

    public void setSelectedVehiculo(CvVehiculo selectedVehiculo) {
        this.selectedVehiculo = selectedVehiculo;
    }

}
