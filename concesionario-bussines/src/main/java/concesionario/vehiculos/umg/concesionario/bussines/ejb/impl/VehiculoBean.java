package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.VehiculoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvDetalleExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvExtraVehiculo;
import concesionario.vehiculos.umg.concesionario.api.entity.CvMarca;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoVehiculo;
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

    private static final Logger log = Logger.getLogger(VehiculoBean.class);

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
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.activo = true order by vehi.fechaCreacion desc", CvVehiculo.class)
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
    public CvVehiculo saveVehiculo(CvVehiculo CvVehiculo) {
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
    public CvVehiculo findVehiculo(Integer idVehiculo) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.idVehiculo =:idVehiculo and vehi.activo = true", CvVehiculo.class)
                .setParameter("idVehiculo", idVehiculo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvVehiculo updateVehiculo(CvVehiculo vehiculo) {
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

            toUpdate.setBastidor(vehiculo.getBastidor());
            toUpdate.setMatricula(vehiculo.getMatricula());
            toUpdate.setModelo(vehiculo.getModelo());
            toUpdate.setMotor(vehiculo.getMotor());
            toUpdate.setColor(vehiculo.getColor());
            toUpdate.setPrecio(vehiculo.getPrecio());
            toUpdate.setStock(vehiculo.getStock());

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

    @Override
    public CvExtraVehiculo findExtraVehiculo(Integer idExtraVehiculo) {
        List<CvExtraVehiculo> lst = em.createQuery("SELECT extra FROM CvExtraVehiculo extra WHERE extra.idExtraVehiculo =:idExtraVehiculo and extra.activo = true", CvExtraVehiculo.class)
                .setParameter("idExtraVehiculo", idExtraVehiculo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvExtraVehiculo updateExtraVehiculoByIdVehiculo(CvExtraVehiculo idExtraVehiculo) {
        if (idExtraVehiculo == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvExtraVehiculo toUpdate = em.find(CvExtraVehiculo.class, idExtraVehiculo.getIdExtraVehiculo());

            toUpdate.setDescripcion(idExtraVehiculo.getDescripcion());
            toUpdate.setPrecio(idExtraVehiculo.getPrecio());

            if (idExtraVehiculo.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return idExtraVehiculo;
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
    public List<CvExtraVehiculo> listExtraVehiculoByIdVehiculoByIdVehiculo(Integer idVehiculo) {
        List<CvExtraVehiculo> lst = em.createQuery("SELECT extra FROM CvExtraVehiculo extra WHERE extra.cvDetalleExtraVehiculoList.idVehiculo.idVehiculo =:idVehiculo and extra.activo = true", CvExtraVehiculo.class)
                .setParameter("idVehiculo", idVehiculo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvExtraVehiculo saveExtraVehiculo(CvExtraVehiculo extraVehiculo) {
        try {
            extraVehiculo.setFechaCreacion(new Date());
            extraVehiculo.setActivo(true);

            em.persist(extraVehiculo);
            em.flush();
            return (extraVehiculo);
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
    public CvTipoVehiculo findTipoVehiculo(Integer idTipoVehiculo) {
        List<CvTipoVehiculo> lst = em.createQuery("SELECT tipo FROM CvTipoVehiculo tipo WHERE tipo.idTipoVehiculo =:idTipoVehiculo and tipo.activo = true", CvTipoVehiculo.class)
                .setParameter("idTipoVehiculo", idTipoVehiculo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvTipoVehiculo updateExtraVehiculoByIdVehiculo(CvTipoVehiculo tipo) {
        if (tipo == null) {
            context.setRollbackOnly();
            return null;
        }
//        if (sesion == null) {
//            context.setRollbackOnly();
//           
//        }
        try {
            CvTipoVehiculo toUpdate = em.find(CvTipoVehiculo.class, tipo.getIdTipoVehiculo());

            toUpdate.setDescripcionTipo(tipo.getDescripcionTipo());

            if (tipo.getActivo() == Boolean.FALSE) {
                toUpdate.setFechaEliminacion(new Date());
                toUpdate.setActivo(false);
            }

            em.merge(toUpdate);

            return tipo;
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
    public List<CvTipoVehiculo> listTipoVehiculoByIdVehiculoByIdVehiculo(Integer idVehiculo) {
        List<CvTipoVehiculo> lst = em.createQuery("SELECT tipo FROM CvTipoVehiculo tipo WHERE tipo.cvVehiculoList.idVehiculo =:idVehiculo and tipo.activo = true", CvTipoVehiculo.class)
                .setParameter("idVehiculo", idVehiculo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvTipoVehiculo saveTipoVehiculo(CvTipoVehiculo tipo) {
        try {
            tipo.setFechaCreacion(new Date());
            tipo.setActivo(true);

            em.persist(tipo);
            em.flush();
            return (tipo);
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
    public CvMarca saveMarcaVehiculo(CvMarca marca) {
        try {
            marca.setFechaCreacion(new Date());
            marca.setActivo(true);

            em.persist(marca);
            em.flush();
            return (marca);
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
    public List<CvVehiculo> ListaVehiculosByBastido(String bastidor) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.bastidor like :bastidor", CvVehiculo.class)
                .setParameter("bastidor", "%" + bastidor + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvExtraVehiculo> listExtraVehiculo() {
        List<CvExtraVehiculo> lst = em.createQuery("SELECT extra FROM CvExtraVehiculo extra WHERE extra.activo = true", CvExtraVehiculo.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvDetalleExtraVehiculo saveDetalleExtra(CvDetalleExtraVehiculo detalleExtra) {
        try {
            detalleExtra.setFechaCreacion(new Date());
            detalleExtra.setActivo(true);

            em.persist(detalleExtra);
            em.flush();
            return (detalleExtra);
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
    public List<CvVehiculo> ListaVehiculosByPlaca(String placa) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.matricula like :placa", CvVehiculo.class)
                .setParameter("placa", "%" + placa + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvVehiculo> ListaVehiculosByMarca(String marca) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.idMarca.nombre like :marca", CvVehiculo.class)
                .setParameter("marca", "%" + marca + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvVehiculo> ListaVehiculosByBastidorAndPlacaAndMarca(String bastido, String placa, String marca) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.bastidor like :marca AND vehi.matricula like :marca and vehi.idMarca.nombre like :marca", CvVehiculo.class)
                .setParameter("bastido", "%" + bastido + "%")
                .setParameter("placa", "%" + placa + "%")
                .setParameter("marca", "%" + marca + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvVehiculo> ListaVehiculosByBastidorAndPlaca(String bastidor, String placa) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.bastidor like :bastidor and vehi.matricula like :placa", CvVehiculo.class)
                .setParameter("bastidor", "%" + bastidor + "%")
                .setParameter("placa", "%" + placa + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvVehiculo> ListaVehiculosByBastidorAndMarca(String bastidor, String marca) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.bastidor like :bastido and  vehi.idMarca.nombre like :marca", CvVehiculo.class)
                .setParameter("bastidor", "%" + bastidor + "%")
                .setParameter("marca", "%" + marca + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public List<CvVehiculo> ListaVehiculosByPlacaAndMarca(String placa, String marca) {
        List<CvVehiculo> lst = em.createQuery("SELECT vehi FROM CvVehiculo vehi WHERE vehi.matricula like :placa and vehi.idMarca.nombre like :marca", CvVehiculo.class)
                .setParameter("placa", "%" + placa + "%")
                .setParameter("marca", "%" + marca + "%")
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvVehiculo asignarConcesionarioVehiculo(Integer idVehiculo, CvConcesionario concesionario) {
        if (idVehiculo == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            CvVehiculo toUpdate = em.find(CvVehiculo.class, idVehiculo);

            toUpdate.setIdConcesionario(concesionario);
            em.merge(toUpdate);

            return toUpdate;
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
    public CvVehiculo actualizarStockVehiculo(Integer idVehiculo, Integer cantidad) {
        if (idVehiculo == null) {
            context.setRollbackOnly();
            return null;
        }

        try {
            CvVehiculo toUpdate = em.find(CvVehiculo.class, idVehiculo);

            toUpdate.setStock(cantidad);
            em.merge(toUpdate);

            return toUpdate;
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
    public CvVehiculo findVehiculoByBastido(String bastidor) {
        List<CvVehiculo> lst = em.createQuery("SELECT tipo FROM CvVehiculo tipo WHERE tipo.bastidor =:bastidor ", CvVehiculo.class)
                .setParameter("bastidor", bastidor)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvVehiculo findVehiculoByPlaca(String matricula) {
        List<CvVehiculo> lst = em.createQuery("SELECT tipo FROM CvVehiculo tipo WHERE tipo.matricula =:matricula ", CvVehiculo.class)
                .setParameter("matricula", matricula)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvTipoVehiculo findTipoVehiculoByNombre(String descripcionTipo) {
        List<CvTipoVehiculo> lst = em.createQuery("SELECT tipo FROM CvTipoVehiculo tipo WHERE tipo.descripcionTipo =:descripcionTipo and tipo.activo = true", CvTipoVehiculo.class)
                .setParameter("descripcionTipo", descripcionTipo)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvMarca findMarcaVehiculoByMarca(String nombre) {
         List<CvMarca> lst = em.createQuery("SELECT tipo FROM CvMarca tipo WHERE tipo.nombre =:nombre  and tipo.activo = true", CvMarca.class)
                .setParameter("nombre", nombre)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

}
