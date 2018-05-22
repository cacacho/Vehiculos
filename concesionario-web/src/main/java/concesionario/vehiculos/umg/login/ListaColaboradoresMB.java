package concesionario.vehiculos.umg.login;

import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.utilidades.JsfUtil;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "listaColaboradoresMB")
@ViewScoped
public class ListaColaboradoresMB implements Serializable {

    private static final Logger log = Logger.getLogger(ListaColaboradoresMB.class);

    @EJB
    private EmpleadoBeanLocal colaboradorBeanLocal;

    private List<CvColaborador> listColaboradores;

    public ListaColaboradoresMB() {
    }

    public void cargarDatos() {
        listColaboradores = colaboradorBeanLocal.ListaColaboradores();
    }

    public void linkRegistro() {
        JsfUtil.redirectTo("/colaborador/registro.xhtml");
    }

    /*Metodos getters y setters*/
    public List<CvColaborador> getListColaboradores() {
        return listColaboradores;
    }

    public void setListColaboradores(List<CvColaborador> listColaboradores) {
        this.listColaboradores = listColaboradores;
    }

}
