package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import concesionario.vehiculos.umg.concesionario.api.entity.CvUsuarios;
import javax.ejb.Local;

/**
 *
 * @author DELL
 */
@Local
public interface LoginBeanLocal {

    CvUsuarios verificarUsuario(String usuario, String password);

    CvUsuarios saveUsuario(CvColaborador colaborador);

    String findUsuario(String usuario);

}
