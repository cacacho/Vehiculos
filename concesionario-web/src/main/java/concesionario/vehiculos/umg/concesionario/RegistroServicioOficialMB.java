package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
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
@ManagedBean(name = "registroServicioOficialMB")
@ViewScoped
public class RegistroServicioOficialMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBeanlocal;
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;

    private List<CvServicioOficial> listaServicioOficial;
    private CvServicioOficial servicioOficial;
    private List<CvConcesionario> listaConcesionarios;
    private CvConcesionario selectedConcesionario;

    public RegistroServicioOficialMB() {
    }

    @PostConstruct
    void initData() {
        listaConcesionarios = catalogoBeanLocal.listAllConcesionarios();
    }

    public void saveServicioOficial() {
        CvServicioOficial of = new CvServicioOficial();
        servicioOficial.setIdConcesionario(selectedConcesionario);
        servicioOficial.setUsuarioCreacion(LoginMB.usuario);
        of = concesionarioBeanlocal.saveServicioOficial(servicioOficial);
        if (of.getIdServicioOficial() != null) {
            JsfUtil.addSuccessMessage("Registro agregado correctamente");
            JsfUtil.redirectTo("/concesionario/servicioOficial/lista.xhtml");
        } else {
            JsfUtil.addSuccessMessage("Sucedio un error inesperado");
        }
    }

    public void cancelarRegistro() {
        JsfUtil.redirectTo("/concesionario/servicioOficial/lista.xhtml");
    }

    /*Metodos getters y setters*/
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

}
