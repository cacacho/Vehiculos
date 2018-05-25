package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
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
@ManagedBean(name = "listaConcesionarioMB")
@ViewScoped
public class ListaConcesionarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;

    private List<CvConcesionario> listaConcesionarios;
    private CvConcesionario selectedConcesionario;

    public ListaConcesionarioMB() {
    }

    public void cargarDatos() {
        listaConcesionarios = concesionarioBean.ListaConcesionarios();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/concesionario/registro.xhtml");
    }

    public String verDetalle() {
        return "detalle.xhtml?faces-redirect=true&idConcesionario=" + selectedConcesionario.getIdConcesionario();
    }

    /*Metodos getters y setters*/
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
