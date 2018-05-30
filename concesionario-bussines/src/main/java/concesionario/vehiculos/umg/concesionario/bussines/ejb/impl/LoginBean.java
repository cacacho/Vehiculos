package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.LoginBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import concesionario.vehiculos.umg.concesionario.api.entity.CvUsuarios;
import java.security.MessageDigest;
import java.text.Normalizer;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJBContext;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;

/**
 *
 * @author DELL
 */
@Singleton
public class LoginBean implements LoginBeanLocal {

    private static final Logger log = Logger.getLogger(LoginBean.class);

    @PersistenceContext(unitName = "ConceVehiculosPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    private void processException(Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    private String getConstraintViolationExceptionAsString(ConstraintViolationException ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("Error de validación:\n");
        for (ConstraintViolation c : ex.getConstraintViolations()) {
            sb.append(String.format("[bean: %s; field: %s; message: %s; value: %s]",
                    c.getRootBeanClass().getName(),
                    c.getPropertyPath().toString(),
                    c.getMessage(), c.getInvalidValue()));
        }
        return sb.toString();
    }

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

    public byte[] cifra(String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    public String descifra(byte[] cifrado) throws Exception {
        final Cipher aes = obtieneCipher(false);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
        final String frase = "ABCDEFGHJKMNPRSTUXZabcdefghjkmnprstuxz123456789";
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }

    @Override
    public CvUsuarios verificarUsuario(String usuario, String password) {
        List<CvUsuarios> lst = em.createQuery("SELECT usuario FROM CvUsuarios usuario WHERE usuario.usuario =:usuario and usuario.password =:password ", CvUsuarios.class)
                .setParameter("usuario", usuario)
                .setParameter("password", password)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst.get(0);
    }

    @Override
    public CvUsuarios saveUsuario(CvColaborador colaborador) {
        try {
            CvUsuarios usuarios = new CvUsuarios();

            String caracteresMayuscula = "ABCDEFGHJKMNPRSTUXZ";
            String caracteresMinuscula = "abcdefghjkmnprstuxz";
            String caracteresNumeros = "123456789";

            StringBuilder saltMayuscula = new StringBuilder();
            StringBuilder saltMiniscula = new StringBuilder();
            StringBuilder saltNumeros = new StringBuilder();
            String salt = null;

            Random rndMayuscula = new Random();
            while (saltMayuscula.length() < 4) {
                int index = (int) (rndMayuscula.nextFloat() * caracteresMayuscula.length());
                saltMayuscula.append(caracteresMayuscula.charAt(index));
            }

            Random rndMiniscula = new Random();
            while (saltMiniscula.length() < 4) {
                int index = (int) (rndMiniscula.nextFloat() * caracteresMinuscula.length());
                saltMiniscula.append(caracteresMinuscula.charAt(index));
            }

            Random rndNumeros = new Random();
            while (saltNumeros.length() < 4) {
                int index = (int) (rndNumeros.nextFloat() * caracteresNumeros.length());
                saltNumeros.append(caracteresNumeros.charAt(index));
            }

            salt = saltMayuscula.toString() + saltMiniscula.toString() + saltNumeros.toString();

            cifra(salt);
            usuarios.setPassword(salt);
            usuarios.setFechaCreacion(new Date());
            usuarios.setActivo(true);
            usuarios.setIdColaborador(colaborador);

            String usuariosYaRegistrados = null;
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

            if (usuariosYaRegistrados == null) {
                usuarios.setUsuario(usuario);
                em.persist(usuarios);
                em.flush();
                //bitacora.info("REGISTRO DE UNA AUSENCIA DE AUDIENCIA", "RD_AUSENCIA_AUDIENCIA", ausenciaAudiencia.getIdAusenciaAudiencia(), sesion);
                return (usuarios);
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
                if (usuariosYaRegistrados == null) {
                    usuarios.setUsuario(usuario);
                    em.persist(usuarios);
                    em.flush();
                    return (usuarios);
                    //bitacora.info("REGISTRO DE UNA AUSENCIA DE AUDIENCIA", "RD_AUSENCIA_AUDIENCIA", ausenciaAudiencia.getIdAusenciaAudiencia(), sesion);
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
                    if (usuariosYaRegistrados == null) {
                        usuarios.setUsuario(usuario);
                        em.persist(usuarios);
                        em.flush();
                        return (usuarios);
                        //bitacora.info("REGISTRO DE UNA AUSENCIA DE AUDIENCIA", "RD_AUSENCIA_AUDIENCIA", ausenciaAudiencia.getIdAusenciaAudiencia(), sesion);
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
                        if (usuariosYaRegistrados == null) {
                            usuarios.setUsuario(usuario);
                            em.persist(usuarios);
                            em.flush();
                            return (usuarios);
                            //bitacora.info("REGISTRO DE UNA AUSENCIA DE AUDIENCIA", "RD_AUSENCIA_AUDIENCIA", ausenciaAudiencia.getIdAusenciaAudiencia(), sesion);
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
                            if (usuariosYaRegistrados == null) {
                                usuarios.setUsuario(usuario);
                                em.persist(usuarios);
                                em.flush();
                                return (usuarios);
                                //bitacora.info("REGISTRO DE UNA AUSENCIA DE AUDIENCIA", "RD_AUSENCIA_AUDIENCIA", ausenciaAudiencia.getIdAusenciaAudiencia(), sesion);
                            } else {
                                usuariosYaRegistrados = usuariosYaRegistrados + ", " + usuario + ".";
                                return null;
                            }
                        }
                    }
                }
            }

        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            context.setRollbackOnly();
            return null;
        }

    }

    @Override
    public String findUsuario(String usuario) {
        List<CvUsuarios> lst = em.createQuery("SELECT usu FROM CvUsuarios usu WHERE usu.usuario =:usuario ", CvUsuarios.class)
                .setParameter("usuario", usuario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst.get(0).getUsuario();
    }

    @Override
    public CvUsuarios findUsuarioByIdColaborador(Integer idColaborador) {
        List<CvUsuarios> lst = em.createQuery("SELECT usu FROM CvUsuarios usu WHERE usu.idColaborador.idColaborador =:idColaborador ", CvUsuarios.class)
                .setParameter("idColaborador", idColaborador)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst.get(0);
    }

}
