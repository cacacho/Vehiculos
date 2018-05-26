package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
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
@ManagedBean(name = "detalleConcesionarioMB")
@ViewScoped
public class DetalleConcesionarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;

    private Integer idConcesionario;
    private CvConcesionario concesionario;
    private boolean mostrarAgregarServicio;
    private boolean mostrarAgregarVehiculo;
    private boolean mostrarAgregarProveedor;
    private List<CvServicioOficial> listaServicioOficial;
    private CvServicioOficial servicioOficial;
    private CvServicioOficial selectedServicioOficial;
    private List<CvProveedor> listaProveedores;
    private CvProveedor proveedor;
    private CvProveedor selectedProveedor;
    private List<CvVehiculo> listaVehiculo;
    private CvVehiculo vehiculo;
    private CvVehiculo selectedVehiculo;

    public DetalleConcesionarioMB() {
        servicioOficial = new CvServicioOficial();
        proveedor = new CvProveedor();
    }

    public void cargarDatos() {
        concesionario = concesionarioBean.findConcesionario(idConcesionario);
        listaServicioOficial = concesionarioBean.ListaServiciosOficialesByIdConcesionario(idConcesionario);
        listaProveedores = catalogoBeanLocal.listAllProveedor();
    }

    public void visualizarAgregarServicio() {
        mostrarAgregarServicio = true;
    }

    public void visualizarAgregarProveedor() {
        mostrarAgregarProveedor = true;
    }

    public void visualizarAgregarVehiculo() {
        mostrarAgregarVehiculo = true;
    }

    public void cancelarAgregarServicio() {
        mostrarAgregarServicio = false;
    }

    public void cancelarAgregarProveedor() {
        mostrarAgregarProveedor = false;
    }

    public void cancelarAgregarVehiculo() {
        mostrarAgregarVehiculo = false;
    }

    public String verDetalle() {
        return "/concecionario/servicioOficial/detalle.xhtml?faces-redirect=true&idServicio=" + selectedServicioOficial.getIdServicioOficial();
    }

    public String verDetalleProveedor() {
        return "/proveedor/detalle.xhtml?faces-redirect=true&idProveedor=" + selectedProveedor.getIdProveedor();
    }

    public String verDetalleVehiculo() {
        return "/vehiculos/detalle.xhtml?faces-redirect=true&idVehiculo=" + selectedVehiculo.getIdVehiculo();
    }

    public void guardarServicioOficial() {
        CvServicioOficial of = new CvServicioOficial();
        servicioOficial.setIdConcesionario(concesionario);
        of = concesionarioBean.saveServicioOficial(servicioOficial);
        if (of.getIdServicioOficial() != null) {
            mostrarAgregarServicio = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarServicio = false;
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    public void guardarProveedor() {
        CvProveedor prove = new CvProveedor();
        prove = concesionarioBean.saveProveedor(proveedor);
        if (prove.getIdProveedor() != null) {
            mostrarAgregarProveedor = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarServicio = false;
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    public void guardarVehiculo() {
        CvProveedor prove = new CvProveedor();
        prove = concesionarioBean.saveProveedor(proveedor);
        if (prove.getIdProveedor() != null) {
            mostrarAgregarProveedor = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarServicio = false;
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
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

    public List<CvServicioOficial> getListaServicioOficial() {
        return listaServicioOficial;
    }

    public void setListaServicioOficial(List<CvServicioOficial> listaServicioOficial) {
        this.listaServicioOficial = listaServicioOficial;
    }

    public CvServicioOficial getServicioOficial() {
        return servicioOficial;
    }

    public void setServicioOficial(CvServicioOficial servicioOficial) {
        this.servicioOficial = servicioOficial;
    }

    public CvServicioOficial getSelectedServicioOficial() {
        return selectedServicioOficial;
    }

    public void setSelectedServicioOficial(CvServicioOficial selectedServicioOficial) {
        this.selectedServicioOficial = selectedServicioOficial;
    }

    public List<CvProveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<CvProveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public CvProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(CvProveedor proveedor) {
        this.proveedor = proveedor;
    }

    public CvProveedor getSelectedProveedor() {
        return selectedProveedor;
    }

    public void setSelectedProveedor(CvProveedor selectedProveedor) {
        this.selectedProveedor = selectedProveedor;
    }

    public List<CvVehiculo> getListaVehiculo() {
        return listaVehiculo;
    }

    public void setListaVehiculo(List<CvVehiculo> listaVehiculo) {
        this.listaVehiculo = listaVehiculo;
    }

    public CvVehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(CvVehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public CvVehiculo getSelectedVehiculo() {
        return selectedVehiculo;
    }

    public void setSelectedVehiculo(CvVehiculo selectedVehiculo) {
        this.selectedVehiculo = selectedVehiculo;
    }

}
