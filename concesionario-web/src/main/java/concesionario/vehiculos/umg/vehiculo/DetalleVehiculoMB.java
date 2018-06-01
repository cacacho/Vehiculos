package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTraspasoVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
import concesionario.vehiculos.umg.login.LoginMB;
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
@ManagedBean(name = "detalleVehiculoMB")
@ViewScoped
public class DetalleVehiculoMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleVehiculoMB.class);

    @EJB
    private VehiculoBeanLocal vehiculosBean;
    @EJB
    private ConcesionarioBeanLocal concesionarioBeanlocal;

    private Integer idvehiculo;
    private CvVehiculo vehiculo;
    private List<CvConcesionario> listaConcesionarios;
    private CvConcesionario selectedConcesionario;
    private Integer idRegresar;
    private CvTraspasoVehiculo asignacionVehiculo;

    public DetalleVehiculoMB() {
        asignacionVehiculo = new CvTraspasoVehiculo();
    }

    public void cargarDatos() {
        if (idRegresar == 1) {
            JsfUtil.addSuccessMessage("Vehículo registrado exitosamente");
        }

        vehiculo = vehiculosBean.findVehiculo(idvehiculo);
    }

    public void regresar() {
        JsfUtil.redirectTo("/vehiculos/lista.xhtml");
    }

    public void guardarAsignacion() {
        if (selectedConcesionario == null) {
            JsfUtil.addErrorMessage("Debe de seleciona un concesionario");
            return;
        }

        CvTraspasoVehiculo tras = new CvTraspasoVehiculo();
        tras = concesionarioBeanlocal.findAsignacionByIdVehiculo(vehiculo.getIdVehiculo());
        if (tras == null) {
            asignacionVehiculo.setIdConcesionarioActual(selectedConcesionario);
            asignacionVehiculo.setUsuarioCreacion(LoginMB.usuario);
            asignacionVehiculo.setIdVehiculo(vehiculo);
            tras = concesionarioBeanlocal.saveAsignacion(asignacionVehiculo);
            if (tras.getIdVehiculo() != null) {
                JsfUtil.addSuccessMessage("Registro agregado correctamente");
                actualizaConceVehiculo(vehiculo.getIdVehiculo());
            } else {
                JsfUtil.addErrorMessage("Sucedio un error inesperado");
            }
        } else {
            tras.setIdConcesionarioActual(selectedConcesionario);
            tras.setIdConcesionarioNuevo(selectedConcesionario);
            tras.setIdVehiculo(vehiculo);
            tras = concesionarioBeanlocal.cambiarConcesionario(tras);

            if (tras.getIdVehiculo() != null) {
                actualizaConceVehiculo(vehiculo.getIdVehiculo());
            } else {
                JsfUtil.addErrorMessage("Sucedio un error inesperado");
            }
        }
    }

    public void actualizaConceVehiculo(Integer idVehiculo) {
        CvVehiculo veh = new CvVehiculo();
        veh = vehiculosBean.asignarConcesionarioVehiculo(idVehiculo, selectedConcesionario);
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

    public List<CvConcesionario> getListaConcesionarios() {
        return listaConcesionarios;
    }

    public void setListaConcesionarios(List<CvConcesionario> listaConcesionarios) {
        this.listaConcesionarios = listaConcesionarios;
    }

    public CvConcesionario getSelectedConcesionario() {
        return selectedConcesionario;
    }

    public void setSelectedConcesionario(CvConcesionario selectedConcesionario) {
        this.selectedConcesionario = selectedConcesionario;
    }

    public Integer getIdRegresar() {
        return idRegresar;
    }

    public void setIdRegresar(Integer idRegresar) {
        this.idRegresar = idRegresar;
    }

}
