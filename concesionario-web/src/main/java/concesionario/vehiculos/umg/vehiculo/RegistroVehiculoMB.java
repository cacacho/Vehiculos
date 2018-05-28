package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvMarca;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoVehiculo;
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
    private boolean mostrarAgregarTipo;
    private CvTipoVehiculo tipoVehiculo;
    private List<CvTipoVehiculo> listTipoVehiculo;
    private boolean mostrarAgregarMarca;
    private List<CvMarca> listMarcaVehiculo;
    private CvMarca marcaVehiculo;

    public RegistroVehiculoMB() {
    }

    public void visualizarAgregarExtra() {
        mostrarAgregarExtra = true;
    }

    public void visualizarAgregarTipo() {
        mostrarAgregarTipo = true;
    }

    public void visualizarAgregarMarca() {
        mostrarAgregarTipo = true;
    }

    public String verDetalleExtra() {
        return "/vehiculos/extra/detalle.xhtml?faces-redirect=true&idServicio=" + selectedExtra.getIdExtraVehiculo();
    }

    public String verDetalleTipo() {
        return "/vehiculos/extra/detalle.xhtml?faces-redirect=true&idServicio=" + selectedExtra.getIdExtraVehiculo();
    }

    public String verDetalleMarca() {
        return "/vehiculos/extra/detalle.xhtml?faces-redirect=true&idServicio=" + selectedExtra.getIdExtraVehiculo();
    }

    public void cancelarAgregarExtra() {
        mostrarAgregarExtra = false;
    }

    public void cancelarAgregarTipo() {
        mostrarAgregarTipo = false;
    }

    public void cancelarAgregarMarca() {
        mostrarAgregarTipo = false;
    }

    public void guardarExtraVehiculo() {
        CvExtraVehiculo extra = new CvExtraVehiculo();
        extra = vehiculosBean.saveExtraVehiculo(extraVehiculo);
        if (extra.getIdExtraVehiculo() != null) {
            mostrarAgregarExtra = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarExtra = false;
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    public void guardarTipo() {
        CvTipoVehiculo tipo = new CvTipoVehiculo();
        tipo = vehiculosBean.saveTipoVehiculo(tipoVehiculo);
        if (tipo.getIdTipoVehiculo() != null) {
            mostrarAgregarTipo = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarTipo = false;
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    public void guardarMarca() {
        CvTipoVehiculo tipo = new CvTipoVehiculo();
        tipo = vehiculosBean.saveTipoVehiculo(tipoVehiculo);
        if (tipo.getIdTipoVehiculo() != null) {
            mostrarAgregarTipo = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarTipo = false;
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    public void eliminarExtra(CvExtraVehiculo extra) {
        vehiculosBean.updateExtraVehiculoByIdVehiculo(extra);
        JsfUtil.addSuccessMessage("Registro eliminado correctamente");
    }

    public void editarExtra(CvExtraVehiculo extra) {
        vehiculosBean.updateExtraVehiculoByIdVehiculo(extra);
        JsfUtil.addSuccessMessage("Registro actualizado correctamente");
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

    public boolean isMostrarAgregarTipo() {
        return mostrarAgregarTipo;
    }

    public void setMostrarAgregarTipo(boolean mostrarAgregarTipo) {
        this.mostrarAgregarTipo = mostrarAgregarTipo;
    }

    public CvTipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(CvTipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public List<CvTipoVehiculo> getListTipoVehiculo() {
        return listTipoVehiculo;
    }

    public void setListTipoVehiculo(List<CvTipoVehiculo> listTipoVehiculo) {
        this.listTipoVehiculo = listTipoVehiculo;
    }

    public boolean isMostrarAgregarMarca() {
        return mostrarAgregarMarca;
    }

    public void setMostrarAgregarMarca(boolean mostrarAgregarMarca) {
        this.mostrarAgregarMarca = mostrarAgregarMarca;
    }

    public List<CvMarca> getListMarcaVehiculo() {
        return listMarcaVehiculo;
    }

    public void setListMarcaVehiculo(List<CvMarca> listMarcaVehiculo) {
        this.listMarcaVehiculo = listMarcaVehiculo;
    }

    public CvMarca getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public void setMarcaVehiculo(CvMarca marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }

}
