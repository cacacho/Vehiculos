package concesionario.vehiculos.umg.venta;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
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
@ManagedBean(name = "registroVentaMB")
@ViewScoped
public class RegistroVentaMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroVentaMB.class);

    @EJB
    private VentaBeanLocal ventaBeanLocal;
    @EJB
    private VehiculoBeanLocal vehiculoBeanLocal;

    private CvVehiculo vehiculo;
    private List<CvVehiculo> listVehiculo;

    public RegistroVentaMB() {
    }

    public void completeVehiculo(String bastidor) {
        listVehiculo = vehiculoBeanLocal.ListaVehiculosByBastido(bastidor);
    }

    public void seleccionaVehiculo() {
        for (CvVehiculo vehi : listVehiculo) {
            vehiculo = vehi;
            break;
        }
    }

    /*Metodos getters y setters*/
    public CvVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(CvVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

}
