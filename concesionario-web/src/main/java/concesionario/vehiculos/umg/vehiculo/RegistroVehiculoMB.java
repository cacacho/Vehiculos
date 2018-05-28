package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
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
@ManagedBean(name = "registroVehiculoMB")
@ViewScoped
public class RegistroVehiculoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroVehiculoMB.class);

    @EJB
    private VehiculoBeanLocal vehiculosBean;

    private CvVehiculo vehiculo;
    private boolean mostrarAgregarExtra;
    private CvExtraVehiculo extraVehiculo;
    private List<CvExtraVehiculo> listExtraVehiculo;
    private CvExtraVehiculo selectedExtra;

    public RegistroVehiculoMB() {
    }

    public void visualizarAgregarExtra() {
        mostrarAgregarExtra = true;
    }

    public String verDetalleExtra() {
        return "/vehiculos/extra/detalle.xhtml?faces-redirect=true&idServicio=" + selectedExtra.getIdExtraVehiculo();
    }

    public void cancelarAgregarExtra() {
        mostrarAgregarExtra = false;
    }

    public void guardarExtraVehiculo() {
        CvExtraVehiculo extra = new CvExtraVehiculo();
        extra = vehiculosBean.saveExtraVehiculo(extraVehiculo);
        if (extra.getIdExtraVehiculo() != null) {
            mostrarAgregarExtra = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarExtra = false;
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    /*Metedos getters y setters*/
    public CvVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(CvVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public boolean isMostrarAgregarExtra() {
        return mostrarAgregarExtra;
    }

    public void setMostrarAgregarExtra(boolean mostrarAgregarExtra) {
        this.mostrarAgregarExtra = mostrarAgregarExtra;
    }

    public CvExtraVehiculo getExtraVehiculo() {
        return extraVehiculo;
    }

    public void setExtraVehiculo(CvExtraVehiculo extraVehiculo) {
        this.extraVehiculo = extraVehiculo;
    }

    public List<CvExtraVehiculo> getListExtraVehiculo() {
        return listExtraVehiculo;
    }

    public void setListExtraVehiculo(List<CvExtraVehiculo> listExtraVehiculo) {
        this.listExtraVehiculo = listExtraVehiculo;
    }

    public CvExtraVehiculo getSelectedExtra() {
        return selectedExtra;
    }

    public void setSelectedExtra(CvExtraVehiculo selectedExtra) {
        this.selectedExtra = selectedExtra;
    }

}
