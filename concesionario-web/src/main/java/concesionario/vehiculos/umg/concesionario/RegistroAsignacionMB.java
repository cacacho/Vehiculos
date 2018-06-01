package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTraspasoVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
import concesionario.vehiculos.umg.login.LoginMB;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "registroAsignacionMB")
@ViewScoped
public class RegistroAsignacionMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroAsignacionMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBeanlocal;
    @EJB
    private VehiculoBeanLocal vehiculosBean;

    private List<CvVehiculo> listaVehiculos;
    private List<CvVehiculo> selectedListaVehiculos;
    private CvTraspasoVehiculo asignacionVehiculo;
    private List<CvConcesionario> listaConcesionarios;
    private CvConcesionario selectedConcesionario;

    public RegistroAsignacionMB() {
        asignacionVehiculo = new CvTraspasoVehiculo();
    }

    @PostConstruct
    void initData() {
        listaConcesionarios = concesionarioBeanlocal.ListaConcesionarios();
        listaVehiculos = vehiculosBean.ListaVehiculos();
    }

    public void cancelarRegistro() {
        JsfUtil.redirectTo("/concesionario/asignacion/lista.xhtml");
    }

    public void guardarAsignacion() {
        if (selectedListaVehiculos == null || selectedListaVehiculos.isEmpty()) {
            JsfUtil.addErrorMessage("Debe de selecionar al menos un vehículo");
            return;
        }

        if (selectedConcesionario == null) {
            JsfUtil.addErrorMessage("Debe de seleciona un concesionario");
            return;
        }

        for (CvVehiculo ve : selectedListaVehiculos) {
            CvTraspasoVehiculo tras = new CvTraspasoVehiculo();
            tras = concesionarioBeanlocal.findAsignacionByIdVehiculo(ve.getIdVehiculo());
            if (tras == null) {
                asignacionVehiculo.setIdConcesionarioActual(selectedConcesionario);
                asignacionVehiculo.setUsuarioCreacion(LoginMB.usuario);
                asignacionVehiculo.setIdVehiculo(ve);
                tras = concesionarioBeanlocal.saveAsignacion(asignacionVehiculo);
                if (tras.getIdVehiculo() != null) {
                    JsfUtil.addSuccessMessage("Registro agregado correctamente");
                    actualizaConceVehiculo(ve.getIdVehiculo());
                } else {
                    JsfUtil.addErrorMessage("Sucedio un error inesperado");
                }
            } else {
                tras.setIdConcesionarioActual(selectedConcesionario);
                tras.setIdConcesionarioNuevo(selectedConcesionario);
                tras.setIdVehiculo(ve);
                tras = concesionarioBeanlocal.cambiarConcesionario(tras);

                if (tras.getIdVehiculo() != null) {
                    actualizaConceVehiculo(ve.getIdVehiculo());
                } else {
                    JsfUtil.addErrorMessage("Sucedio un error inesperado");
                }
            }
        }
    }

    public void limpiarPagina() {
        asignacionVehiculo = null;
    }

    public void actualizaConceVehiculo(Integer idVehiculo) {
        CvVehiculo veh = new CvVehiculo();
        veh = vehiculosBean.asignarConcesionarioVehiculo(idVehiculo, selectedConcesionario);
    }

    /*Metodos getters y setters*/
    public CvTraspasoVehiculo getAsignacionVehiculo() {
        return asignacionVehiculo;
    }

    public void setAsignacionVehiculo(CvTraspasoVehiculo asignacionVehiculo) {
        this.asignacionVehiculo = asignacionVehiculo;
    }

    public List<CvConcesionario> getListaConcesionarios() {
        return listaConcesionarios;
    }

    public void setListaConcesionarios(List<CvConcesionario> listaConcesionarios) {
        this.listaConcesionarios = listaConcesionarios;
    }

    public List<CvVehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(List<CvVehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public List<CvVehiculo> getSelectedListaVehiculos() {
        return selectedListaVehiculos;
    }

    public void setSelectedListaVehiculos(List<CvVehiculo> selectedListaVehiculos) {
        this.selectedListaVehiculos = selectedListaVehiculos;
    }

    public CvConcesionario getSelectedConcesionario() {
        return selectedConcesionario;
    }

    public void setSelectedConcesionario(CvConcesionario selectedConcesionario) {
        this.selectedConcesionario = selectedConcesionario;
    }

}
