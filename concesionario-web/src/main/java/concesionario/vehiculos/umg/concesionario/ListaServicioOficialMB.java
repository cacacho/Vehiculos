package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
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
@ManagedBean(name = "listaServicioOficialMB")
@ViewScoped
public class ListaServicioOficialMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private List<CvServicioOficial> listaServicioOficial;
    private CvServicioOficial selectedServicioOficial;

    public ListaServicioOficialMB() {
    }

    public void cargarDatos() {
        listaServicioOficial = concesionarioBean.ListaServiciosOficiales();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/servicioOficial/registro.xhtml");
    }

    public String verDetalle() {
        return "detalle.xhtml?faces-redirect=true&idConcesionario=" + selectedServicioOficial.getIdServicioOficial();
    }

    /*Metodos getters y setters*/
    public List<CvServicioOficial> getListaServicioOficial() {
        return listaServicioOficial;
    }

    public void setListaServicioOficial(List<CvServicioOficial> listaServicioOficial) {
        this.listaServicioOficial = listaServicioOficial;
    }

    public CvServicioOficial getSelectedServicioOficial() {
        return selectedServicioOficial;
    }

    public void setSelectedServicioOficial(CvServicioOficial selectedServicioOficial) {
        this.selectedServicioOficial = selectedServicioOficial;
    }

}
