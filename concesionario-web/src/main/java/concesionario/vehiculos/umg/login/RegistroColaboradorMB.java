package concesionario.vehiculos.umg.login;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoColaborador;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "registroColaboradorMB")
@ViewScoped
public class RegistroColaboradorMB implements Serializable {

    private static final Logger log = Logger.getLogger(RegistroColaboradorMB.class);

    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;
    @EJB
    EmpleadoBeanLocal colaboradorBeanLocal;

    private List<CvTipoColaborador> listTipoColaboradores;
    private CvTipoColaborador tipoColaboradorSelected;
    private CvColaborador colaborador;
    private List<CvConcesionario> listconcesionario;

    public RegistroColaboradorMB() {
        colaborador = new CvColaborador();
    }

    @PostConstruct
    void initData() {
        listTipoColaboradores = catalogoBeanLocal.listAllTipoColaborador();
        listconcesionario = catalogoBeanLocal.listAllConcesionarios();
    }

    public void saveColaborador() {
        colaborador.setUsuarioCreacion(LoginMB.usuario);
        colaboradorBeanLocal.saveColaborador(colaborador);
        JsfUtil.addSuccessMessage("Colaborador creado con exito");
        JsfUtil.redirectTo("/colaborador/lista.xhtml");
    }

    public void cancelarRegistro() {
        JsfUtil.redirectTo("/colaborador/lista.xhtml");
    }

    /*Metodos getters y setters*/
    public List<CvTipoColaborador> getListTipoColaboradores() {
        return listTipoColaboradores;
    }

    public void setListTipoColaboradores(List<CvTipoColaborador> listTipoColaboradores) {
        this.listTipoColaboradores = listTipoColaboradores;
    }

    public CvTipoColaborador getTipoColaboradorSelected() {
        return tipoColaboradorSelected;
    }

    public void setTipoColaboradorSelected(CvTipoColaborador tipoColaboradorSelected) {
        this.tipoColaboradorSelected = tipoColaboradorSelected;
    }

    public CvColaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(CvColaborador colaborador) {
        this.colaborador = colaborador;
    }

    public List<CvConcesionario> getListconcesionario() {
        return listconcesionario;
    }

    public void setListconcesionario(List<CvConcesionario> listconcesionario) {
        this.listconcesionario = listconcesionario;
    }

}
