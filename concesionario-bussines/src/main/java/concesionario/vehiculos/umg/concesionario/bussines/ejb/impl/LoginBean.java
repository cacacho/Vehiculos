package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.LoginBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvUsuarios;
import javax.ejb.Singleton;

/**
 *
 * @author DELL
 */
@Singleton
public class LoginBean implements LoginBeanLocal {

    @Override
    public CvUsuarios verificarUsuario(String usuario, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CvUsuarios saveUsuario(CvUsuarios usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
