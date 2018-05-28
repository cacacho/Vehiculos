package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "detalleVehiculoMB")
@ViewScoped
public class DetalleVehiculoMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleVehiculoMB.class);

    @EJB
    private VehiculoBeanLocal vehiculosBean;

    private Integer idvehiculo;
    private CvVehiculo vehiculo;

    public DetalleVehiculoMB() {
    }

    public void cargarDatos() {
        vehiculo = vehiculosBean.findVehiculo(idvehiculo);
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
