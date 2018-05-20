package concesionario.vehiculos.umg.login;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;

/**
 *
 * @author raul
 */
@ManagedBean(name = "loginMB")
@ViewScoped
public class LoginMB implements Serializable {

    private static final Logger log = Logger.getLogger(LoginMB.class);

    private String usuario;
    private String password;

    public LoginMB() {
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().redirect("menu/menu.xhtml");
    }

    /*Metodos Getters y setters*/
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
