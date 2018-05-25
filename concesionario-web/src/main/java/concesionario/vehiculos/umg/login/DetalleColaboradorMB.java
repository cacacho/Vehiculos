package concesionario.vehiculos.umg.login;

import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.LoginBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import concesionario.vehiculos.umg.concesionario.api.entity.CvUsuarios;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "detalleColaboradorMB")
@ViewScoped
public class DetalleColaboradorMB implements Serializable {

    private static final Logger log = Logger.getLogger(DetalleColaboradorMB.class);

    @EJB
    private EmpleadoBeanLocal colaboradorBeanLocal;
    @EJB
    private LoginBeanLocal loginBeanLocal;

    private Integer idColaborador;
    private CvColaborador colaborador;
    private CvUsuarios usuario;

    public DetalleColaboradorMB() {
    }

    public void cargarDatos() {
        colaborador = colaboradorBeanLocal.findColaborador(idColaborador);
        usuario = loginBeanLocal.findUsuarioByIdColaborador(idColaborador);

    }

    /*Metodos getters y setters*/
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

    public CvUsuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(CvUsuarios usuario) {
        this.usuario = usuario;
    }

}
