package concesionario.vehiculos.umg.concesionario;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.login.LoginMB;
import concesionario.vehiculos.umg.utilidades.JsfUtil;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 *
 * @author DELL
 */
@ManagedBean(name = "registroConcesionarioMB")
@ViewScoped
public class RegistroConcesionarioMB implements Serializable {
    
    private static final Logger log = Logger.getLogger(RegistroConcesionarioMB.class);
    
    @EJB
    private ConcesionarioBeanLocal concesionarioBeanlocal;
    @EJB
    private CatalogoBeanLocal catalogoBeanLocal;
    
    private CvConcesionario concesionario;
    private List<CvProveedor> listProveedor;
    private CvProveedor proveedor;
    
    public RegistroConcesionarioMB() {
        concesionario = new CvConcesionario();
        proveedor = new CvProveedor();
    }
    
    @PostConstruct
    void initData() {
        listProveedor = catalogoBeanLocal.listAllProveedor();
    }
    
    public void showDialogProveedor() {
        RequestContext.getCurrentInstance().execute("PF('proveedor').show()");
    }
    
    public void guadarProveedor() {
        proveedor.setUsuarioCreacion(LoginMB.usuario);
        concesionarioBeanlocal.saveProveedor(proveedor);
        JsfUtil.addSuccessMessage("Proveedor guardado exitosamente");
    }
    
    public void guardarConcesionario() {
        concesionario.setUsuarioCreacion(LoginMB.usuario);
        concesionarioBeanlocal.saveConcesionario(concesionario);
        JsfUtil.addSuccessMessage("Concesionario de vehículos guardado exitosamente");
        JsfUtil.redirectTo("/concesionario/lista.xhtml");
    }
    
    public void limpiarDialog() {
        proveedor = new CvProveedor();
    }
    
    public void cancelarRegistro() {
        JsfUtil.redirectTo("/concesionario/lista.xhtml");
    }

    /*Metodos getters y setters*/
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
    
    public CvProveedor getProveedor() {
        return proveedor;
    }
    
    public void setProveedor(CvProveedor proveedor) {
        this.proveedor = proveedor;
    }
    
}
