package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
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
public class ConcesionarioBean implements ConcesionarioBeanLocal {

    private static final Logger log = Logger.getLogger(ConcesionarioBeanLocal.class);

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

    @Override
    public List<CvConcesionario> ListaConcesionarios() {
        List<CvConcesionario> lst = em.createQuery("SELECT conce FROM CvConcesionario conce WHERE conce.activo = true", CvConcesionario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return ("No se encontró información relacionada con el puesto");
        }

        return lst;
    }

    @Override
    public CvConcesionario saveConcesionario(CvConcesionario concesionario) {
        try {
            concesionario.setFechaCreacion(new Date());
            concesionario.setActivo(true);

            em.persist(concesionario);
            em.flush();
            return (concesionario);
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
    public CvConcesionario findConcesionario(Integer idConcesionario) {
        List<CvConcesionario> lst = em.createQuery("SELECT conce FROM CvConcesionario conce WHERE conce.idConcesionario =:idConcesionario and conce.activo = true", CvConcesionario.class)
                .setParameter("idConcesionario", idConcesionario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvConcesionario updateConcesionario(CvConcesionario concesionario) {
        if (concesionario == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvConcesionario toUpdate = em.find(CvConcesionario.class, concesionario.getIdConcesionario());

//            toUpdate.setPrimerNombre(colaborador.getPrimerNombre());
//            toUpdate.setSegundoNombre(colaborador.getSegundoNombre());
//            toUpdate.setPrimerApellido(colaborador.getPrimerApellido());
//            toUpdate.setSegundoApellido(colaborador.getSegundoApellido());
//            toUpdate.setTelefono(colaborador.getTelefono());
//            toUpdate.setDireccion(colaborador.getDireccion());

            if (concesionario.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return concesionario;
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

    @Override
    public List<CvServicioOficial> ListaServiciosOficiales() {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.activo = true", CvServicioOficial.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return ("No se encontró información relacionada con el puesto");
        }

        return lst;
    }

    @Override
    public CvServicioOficial saveServicioOficial(CvServicioOficial servicioOficial) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CvServicioOficial findServicioOficial(Integer idServicioOficial) {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.idServicioOficial =:idServicioOficial and oficial.activo = true", CvServicioOficial.class)
                .setParameter("idServicioOficial", idServicioOficial)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvServicioOficial updateServicioOficial(CvServicioOficial servicioOficial) {
        if (servicioOficial == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvServicioOficial toUpdate = em.find(CvServicioOficial.class, servicioOficial.getIdServicioOficial());

//            toUpdate.setPrimerNombre(colaborador.getPrimerNombre());
//            toUpdate.setSegundoNombre(colaborador.getSegundoNombre());
//            toUpdate.setPrimerApellido(colaborador.getPrimerApellido());
//            toUpdate.setSegundoApellido(colaborador.getSegundoApellido());
//            toUpdate.setTelefono(colaborador.getTelefono());
//            toUpdate.setDireccion(colaborador.getDireccion());

            if (servicioOficial.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return servicioOficial;
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

    @Override
    public CvServicioOficial findServicioOficialByIdConcesionario(Integer idConcesionario) {
        List<CvServicioOficial> lst = em.createQuery("SELECT oficial FROM CvServicioOficial oficial WHERE oficial.idConcesionario.idConcesionario =:idConcesionario and oficial.activo = true", CvServicioOficial.class)
                .setParameter("idConcesionario", idConcesionario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvProveedor saveProveedor(CvProveedor proveedor) {
              try {
            proveedor.setFechaCreacion(new Date());
            proveedor.setActivo(true);

            em.persist(proveedor);
            em.flush();
            return (proveedor);
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

}
