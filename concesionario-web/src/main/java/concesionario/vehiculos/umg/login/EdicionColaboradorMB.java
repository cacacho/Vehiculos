package concesionario.vehiculos.umg.login;

import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "edicionColaboradorMB")
@ViewScoped
public class EdicionColaboradorMB implements Serializable {

    private static final Logger log = Logger.getLogger(EdicionColaboradorMB.class);

    @EJB
    private EmpleadoBeanLocal colaboradorBeanLocal;

    private Integer idColaborador;
    private CvColaborador colaborador;

    public EdicionColaboradorMB() {
    }

    public void cargarDatos() {
        colaborador = colaboradorBeanLocal.findColaborador(idColaborador);
    }

    public void cancelarEdicion() {
        JsfUtil.redirectTo("/colaborador/detalle.xhtml?faces-redirect=true&idColaborador=" + idColaborador);
    }

    public void actualizarColaborador() {
        

    }

    /*Metodos Getters y Setters*/
    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public CvColaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(CvColaborador colaborador) {
        this.colaborador = colaborador;
    }

}
