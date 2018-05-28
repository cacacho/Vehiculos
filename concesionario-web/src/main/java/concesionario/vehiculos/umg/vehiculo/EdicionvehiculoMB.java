package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
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
@ManagedBean(name = "edicionvehiculoMB")
@ViewScoped
public class EdicionvehiculoMB implements Serializable {

    private static final Logger log = Logger.getLogger(EdicionvehiculoMB.class);

    @EJB
    private VehiculoBeanLocal vehiculosBean;

    private Integer idvehiculo;
    private CvVehiculo vehiculo;

    public EdicionvehiculoMB() {
    }

    public void cargarDatos() {
        vehiculo = vehiculosBean.findVehiculo(idvehiculo);
    }

    public void cancelarEdicion() {
        JsfUtil.redirectTo("/vehiculos/detalle.xhtml?faces-redirect=true&idProveedor=" + idvehiculo);
    }

    public void actualizarVehiculo() {
        CvVehiculo vehi = new CvVehiculo();
        vehi = vehiculosBean.updateVehiculo(vehiculo);
        if (vehi.getIdVehiculo() != null) {

            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    /*Metodos getters y setters*/
    public Integer getIdvehiculo() {
        return idvehiculo;
    }

    public void setIdvehiculo(Integer idvehiculo) {
        this.idvehiculo = idvehiculo;
    }

    public CvVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(CvVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
