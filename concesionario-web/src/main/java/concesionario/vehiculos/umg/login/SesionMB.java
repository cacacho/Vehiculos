package concesionario.vehiculos.umg.login;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 *
 * @author raul
 */
@ManagedBean(name = "sesionMB")
@SessionScoped
public class SesionMB implements Serializable {

    private static final Logger log = Logger.getLogger(SesionMB.class);

    public SesionMB() {
    }

    private void createSession() throws SecurityException {
        String usuario = null;
        try {

//            String email = empleado.getEmail();
//            String nombreUsuario = empleado.getNombreCompleto();
//            String puesto = empleado.getPuesto();
//            Integer idRhDependencia = Integer.parseInt(empleado.getIdDependencia());
//            String nombreRhDependencia = empleado.getDependencia();

            //sesion = new Sesion(nip, usuario, email, nombreUsuario, puesto, idRhDependencia, nombreRhDependencia, idDrDependencia, nombreDrDependencia, emailSql, nombreUsuarioSql, puestoSql, idRhDependenciaSql, nombreRhDependenciaSql, nipSql);

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//            sesion.setIp(request.getRemoteAddr());
//            sesion.setRequestUri(request.getRequestURI());

            //log.info(String.format("INICIANDO SESIÓN: %s", sesion.toString()));

        } catch (NumberFormatException e) {
            SecurityException se = new SecurityException(String.format("Error al intentar convertir a entero el idRhDependencia de usuario %s", usuario));
            log.fatal(se.getMessage());
            log.fatal(e.getMessage());
            throw se;
        }

    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/login.xhtml?faces-redirect=true";
    }

    public String home() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/menu/menu.xhtml?faces-redirect=true";
    }

}
