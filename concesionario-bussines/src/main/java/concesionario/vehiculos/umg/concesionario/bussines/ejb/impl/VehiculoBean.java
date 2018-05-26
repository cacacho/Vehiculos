package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.ConcesionarioBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvServicioOficial;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVehiculo;
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
public class VehiculoBean implements VehiculoBeanLocal {

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
    public List<CvVehiculo> ListaVehiculos() {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.activo = true", CvVehiculo.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvVehiculo> ListaVehiculosByIdConcesionario(Integer idConcesionario) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.idConcesionario.idConcesionario =:idConcesionario and vehi.activo = true", CvVehiculo.class)
                .setParameter("idConcesionario", idConcesionario)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvVehiculo saveConcesionario(CvVehiculo CvVehiculo) {
        try {
            CvVehiculo.setFechaCreacion(new Date());
            CvVehiculo.setActivo(true);

            em.persist(CvVehiculo);
            em.flush();
            return (CvVehiculo);
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
    public CvVehiculo findConcesionario(Integer idVehiculo) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.idConcesionario.idConcesionario =:idConcesionario and vehi.activo = true", CvVehiculo.class)
                .setParameter("idConcesionario", idVehiculo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvVehiculo updateConcesionario(CvVehiculo vehiculo) {
        if (vehiculo == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvVehiculo toUpdate = em.find(CvVehiculo.class, vehiculo.getIdVehiculo());

//            toUpdate.setNombre(vehiculo.get);
//            toUpdate.setDireccion(servicioOficial.getDireccion());
//            toUpdate.setTelefono(servicioOficial.getTelefono());
//            toUpdate.setCorreoElectronico(servicioOficial.getCorreoElectronico());
            if (vehiculo.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return vehiculo;
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
