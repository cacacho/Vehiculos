package concesionario.vehiculos.umg.vehiculo;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvMarca;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
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
@ManagedBean(name = "registroVehiculoMB")
@ViewScoped
public class RegistroVehiculoMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroVehiculoMB.class);

    @EJB
    private VehiculoBeanLocal vehiculosBean;
    @EJB
    private CatalogoBeanLocal catalogoBean;

    private CvVehiculo vehiculo;
    private boolean mostrarAgregarExtra;
    private CvExtraVehiculo extraVehiculo;
    private List<CvExtraVehiculo> listExtraVehiculo;
    private List<CvExtraVehiculo> selectedListExtraVehiculo;
    private CvExtraVehiculo selectedExtra;
    private boolean mostrarAgregarTipo;
    private CvTipoVehiculo tipoVehiculo;
    private List<CvTipoVehiculo> listTipoVehiculo;
    private boolean mostrarAgregarMarca;
    private List<CvMarca> listMarcaVehiculo;
    private CvMarca marcaVehiculo;

    public RegistroVehiculoMB() {
        extraVehiculo = new CvExtraVehiculo();
        tipoVehiculo = new CvTipoVehiculo();
        marcaVehiculo = new CvMarca();
    }

    @PostConstruct
    public void cargar() {
        listaExtraVehiculo();
        listaMarcas();
        listaTipo();
    }

    public void visualizarAgregarExtra() {
        mostrarAgregarExtra = true;
    }

    public void visualizarAgregarTipo() {
        mostrarAgregarTipo = true;
    }

    public void visualizarAgregarMarca() {
        mostrarAgregarMarca = true;
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
        mostrarAgregarMarca = false;
    }

    public void guardarExtraVehiculo() {
        CvExtraVehiculo extra = new CvExtraVehiculo();
        extra = vehiculosBean.saveExtraVehiculo(extraVehiculo);
        if (extra.getIdExtraVehiculo() != null) {
            mostrarAgregarExtra = false;
            listaExtraVehiculo();
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
            listaMarcas();
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarTipo = false;
            JsfUtil.addErrorMessage("Sucedio un error inesperado");
        }
    }

    public void guardarMarca() {
        CvMarca mar = new CvMarca();
        mar = vehiculosBean.saveMarcaVehiculo(marcaVehiculo);
        if (mar.getIdMarca() != null) {
            mostrarAgregarMarca = false;
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
        } else {
            mostrarAgregarMarca = false;
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

    public void listaExtraVehiculo() {
        listExtraVehiculo = vehiculosBean.listExtraVehiculo();
    }

    public void listaMarcas() {
        listMarcaVehiculo = catalogoBean.listAllMarcaVehiculo();
    }

    public void listaTipo() {
        listTipoVehiculo = catalogoBean.listAllTipoVehiculo();
    }

    public String guardarVehiculo() {
        if (selectedListExtraVehiculo == null || selectedListExtraVehiculo.isEmpty()) {
            JsfUtil.addErrorMessage("Debe de seleccionar un extra");
        }
        //else {
//            Integer anio = TimeUtil.now().getYear() + 1900;
//            anioAcuerdoActualizacionSelected = anio;
//            List<PlazaWrapper> plazasEnEstadoCorrecto = new ArrayList();
//            for (PlazaWrapper pl : selectedPlazas) {
//                if (pl.getPlazaDto().getIdSituacionApartaPlaza().equals(EstadosApartaPlaza.EN_ESTUDIO_CLASAL.getValue())
//                        && pl.getPlazaDto().getActivo() == true) {
//                    plazasEnEstadoCorrecto.add(pl);
//                }
//            }
//
//            if (!plazasEnEstadoCorrecto.isEmpty()) {
//                selectedPlazas.clear();
//                selectedPlazas.addAll(plazasEnEstadoCorrecto);
//                ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//                listPlazasAnular = new ArrayList<>();
//                listPlazasAnular.addAll(selectedPlazas);
//                context.getSessionMap().put("LISTA_ANULAR_PLAZAS", listPlazasAnular);
//                RequestContext.getCurrentInstance().execute("PF('plazasAnuladas').show()");
//            } else {
//                JsfUtil.addErrorMessage("No seleccionó ninguna plaza que se encuentre en proceso: EN ESTUDIO DE SUPRESIÓN, por lo que no puede anular");
//            }
//        }
        return "";
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

    public List<CvExtraVehiculo> getSelectedListExtraVehiculo() {
        return selectedListExtraVehiculo;
    }

    public void setSelectedListExtraVehiculo(List<CvExtraVehiculo> selectedListExtraVehiculo) {
        this.selectedListExtraVehiculo = selectedListExtraVehiculo;
    }

}
