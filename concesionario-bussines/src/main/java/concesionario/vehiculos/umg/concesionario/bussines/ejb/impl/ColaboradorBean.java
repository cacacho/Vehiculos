package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.LoginBeanLocal;
import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author DELL
 */
@Singleton
public class ColaboradorBean implements EmpleadoBeanLocal {

    private static final Logger log = Logger.getLogger(EmpleadoBeanLocal.class);

    @PersistenceContext(unitName = "ConceVehiculosPU")
    EntityManager em;

    @Resource
    private EJBContext context;

    @EJB
    LoginBeanLocal loginBeanLocal;

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

    @Override
    public List<CvColaborador> ListaColaboradores() {

        List<CvColaborador> lst = em.createQuery("SELECT col FROM CvColaborador col WHERE col.activo = true", CvColaborador.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return ("No se encontró información relacionada con el puesto");
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

    @Override
    public List<CvColaborador> ListaColaboradores(String nombre) {
        if (nombre == null) {
            //return new Response(ResponseStatus.ERROR_PARAMS, "Debe de proporcionar un puesto existente");
        }

        List<CvColaborador> lst = em.createQuery("SELECT col FROM CvColaborador col WHERE col.primerNombre like :nombre ", CvColaborador.class)
                .setParameter("nombre", nombre)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return new Response(ResponseStatus.ERROR_NO_DATA, String.format("No se encontró información relacionada con el puesto"));
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

    @Override
    public CvColaborador saveColaborador(CvColaborador colaborador) {
        try {
            colaborador.setFechaCreacion(new Date());
            colaborador.setActivo(true);

            em.persist(colaborador);
            em.flush();

            loginBeanLocal.saveUsuario(colaborador);
            return (colaborador);
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
    public CvColaborador findColaborador(Integer idColaborador) {
        if (idColaborador == null) {
            return null;
        }

        List<CvColaborador> lst = em.createQuery("SELECT col FROM CvColaborador col WHERE col.idColaborador =:idColaborador ", CvColaborador.class)
                .setParameter("idColaborador", idColaborador)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst.get(0);
    }

    @Override
    public CvColaborador updateColaborador(CvColaborador colaborador) {
        if (colaborador == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvColaborador toUpdate = em.find(CvColaborador.class, colaborador.getIdColaborador());

            toUpdate.setPrimerNombre(colaborador.getPrimerNombre());
            toUpdate.setSegundoNombre(colaborador.getSegundoNombre());
            toUpdate.setPrimerApellido(colaborador.getPrimerApellido());
            toUpdate.setSegundoApellido(colaborador.getSegundoApellido());
            toUpdate.setTelefono(colaborador.getTelefono());
            toUpdate.setDireccion(colaborador.getDireccion());

            if (colaborador.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return colaborador;
        } catch (ConstraintViolationException ex) {
            String validationError = getConstraintViolationExceptionAsString(ex);
            log.error(validationError);
            context.setRollbackOnly();
            return null;
        } catch (Exception ex) {
            processException(ex);
            return null;
        }
    }

}
