package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
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
@ManagedBean(name = "edicionConcesionarioMB")
@ViewScoped
public class EdicionConcesionarioMB implements Serializable {

    private static final Logger log = Logger.getLogger(EdicionConcesionarioMB.class);

    @EJB
    private ConcesionarioBeanLocal concesionarioBean;
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;

    private Integer idConcesionario;
    private CvConcesionario concesionario;
    private List<CvProveedor> listProveedor;

    public EdicionConcesionarioMB() {
    }

    public void cargarDatos() {
        listProveedor = catalogoBeanLocal.listAllProveedor();
        concesionario = concesionarioBean.findConcesionario(idConcesionario);
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

    public List<CvProveedor> getListProveedor() {
        return listProveedor;
    }

    public void setListProveedor(List<CvProveedor> listProveedor) {
        this.listProveedor = listProveedor;
    }

}
