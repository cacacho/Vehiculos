package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.LoginBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import concesionario.vehiculos.umg.concesionario.api.entity.CvUsuarios;
import java.text.Normalizer;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@Singleton
public class LoginBean implements LoginBeanLocal {

    private static final Logger log = Logger.getLogger(EmpleadoBeanLocal.class);

    @PersistenceContext(unitName = "ConceVehiculosPU")
    EntityManager em;

    public static String remplazoAcentos(String palabra) {
        palabra = Normalizer.normalize(palabra, Normalizer.Form.NFD);
        palabra = palabra.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return palabra;
    }

    String remplazoN(String string) {
        string = string.replace('ñ', '\001');
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        string = string.replace('\001', 'ñ');
        return string;
    }

    @Override
    public CvUsuarios verificarUsuario(String usuario, String password) {
        List<CvUsuarios> lst = em.createQuery("SELECT usuario FROM CvUsuarios usuario WHERE usuario.usuario =:usuario and usuario.password =:usuario ", CvUsuarios.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return new Response(ResponseStatus.ERROR_NO_DATA, String.format("No se encontró información relacionada con el puesto"));
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst.get(0);
    }

    @Override
    public CvUsuarios saveUsuario(CvUsuarios usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String generarUsuario(CvColaborador colaborador) {
        String usuariosYaRegistrados;
        String usuario;

        // Crea el usuario según las 5 reglas del documento: Política Oficial de uso del Correo Institucional.            
        // Regla 1: N1(1) + A1
        usuario = colaborador.getPrimerNombre().substring(0, 1) + colaborador.getPrimerApellido();
        usuario = usuario.toLowerCase();
        usuario = remplazoAcentos(usuario.toLowerCase());
        usuario = remplazoN(usuario);
        usuario = usuario.replace("\\s", "");
        // Verifica que el usuario creado por la regla 1 no haya sido registrado con anterioridad.
        usuariosYaRegistrados = findUsuario(usuario);
        if (usuariosYaRegistrados != null) {
            return (usuariosYaRegistrados);
        } else {
            usuariosYaRegistrados = usuario;

            // Regla 2: N2(1) + A1
            usuario = colaborador.getSegundoNombre().substring(0, 1) + colaborador.getPrimerApellido();
            usuario = usuario.toLowerCase();
            usuario = remplazoAcentos(usuario.toLowerCase());
            usuario = remplazoN(usuario);
            usuario = usuario.replace("\\s", "");
            // Verifica que el usuario creado por la regla 2 no haya sido registrado con anterioridad.
            usuariosYaRegistrados = findUsuario(usuario);
            if (usuariosYaRegistrados != null) {
                return (usuariosYaRegistrados);
            } else {
                usuariosYaRegistrados = usuariosYaRegistrados + ", " + usuario;

                // Regla 3: N1(1) + N2(1) + A1
                usuario = colaborador.getPrimerNombre().substring(0, 1) + colaborador.getSegundoNombre().substring(0, 1)
                        + colaborador.getPrimerApellido();
                usuario = usuario.toLowerCase();
                usuario = remplazoAcentos(usuario.toLowerCase());
                usuario = remplazoN(usuario);
                usuario = usuario.replace("\\s", "");
                // Verifica que el usuario creado por la regla 3 no haya sido registrado con anterioridad.
                usuariosYaRegistrados = findUsuario(usuario);
                if (usuariosYaRegistrados != null) {
                    return (usuariosYaRegistrados);
                } else {
                    usuariosYaRegistrados = usuariosYaRegistrados + ", " + usuario;

                    // Regla 4: N1(1) + A1 + A2(1)
                    usuario = colaborador.getPrimerNombre().substring(0, 1) + colaborador.getPrimerApellido()
                            + colaborador.getSegundoApellido().substring(0, 1);
                    usuario = usuario.toLowerCase();
                    usuario = remplazoAcentos(usuario.toLowerCase());
                    usuario = remplazoN(usuario);
                    usuario = usuario.replace("\\s", "");
                    // Verifica que el usuario creado por la regla 4 no haya sido registrado con anterioridad.
                    usuariosYaRegistrados = findUsuario(usuario);
                    if (usuariosYaRegistrados != null) {
                        return (usuariosYaRegistrados);
                    } else {
                        usuariosYaRegistrados = usuariosYaRegistrados + ", " + usuario;

                        // Regla 5: N1(1) + N2(1) + A1 + A2(1)
                        usuario = colaborador.getPrimerNombre().substring(0, 1) + colaborador.getSegundoNombre().substring(0, 1)
                                + colaborador.getPrimerApellido() + colaborador.getSegundoApellido().substring(0, 1);
                        usuario = usuario.toLowerCase();
                        usuario = remplazoAcentos(usuario.toLowerCase());
                        usuario = remplazoN(usuario);
                        usuario = usuario.replace("\\s", "");
                        // Verifica que el usuario creado por la regla 5 no haya sido registrado con anterioridad.
                        usuariosYaRegistrados = findUsuario(usuario);
                        if (usuariosYaRegistrados != null) {
                            return (usuariosYaRegistrados);
                        } else {
                            usuariosYaRegistrados = usuariosYaRegistrados + ", " + usuario + ".";
                            return ("No es posible generar un usuario que no haya sido registrado con anterioridad. Debe ingresarlo de forma manual.");
                        }
                    }
                }
            }
        }

    }

    @Override
    public String findUsuario(String usuario) {
        List<CvUsuarios> lst = em.createQuery("SELECT usu FROM CvUsuarios usu WHERE usu.usuario =:usuario ", CvUsuarios.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return("No se encontró el usuario");
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst.get(0).getUsuario();
    }

}
