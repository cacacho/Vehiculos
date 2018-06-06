package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.VentaBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvCliente;
import concesionario.vehiculos.umg.concesionario.api.entity.CvPedido;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoPedido;
import concesionario.vehiculos.umg.concesionario.api.entity.CvVenta;
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
public class VentaBean implements VentaBeanLocal {

    private static final Logger log = Logger.getLogger(VentaBean.class);

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
    public CvVenta saveVentas(CvVenta venta) {
        try {
            venta.setFechaCreacion(new Date());
            venta.setActivo(true);

            em.persist(venta);
            em.flush();
            return (venta);
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
    public List<CvVenta> ListaVentas() {
        List<CvVenta> lst = em.createQuery("SELECT venta FROM CvVenta venta WHERE venta.activo = true order by venta.fechaCreacion desc", CvVenta.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvVenta findVenta(Integer idVenta) {
        List<CvVenta> lst = em.createQuery("SELECT venta FROM CvVenta venta WHERE venta.idVenta =:idVenta and venta.activo = true", CvVenta.class)
                .setParameter("idVenta", idVenta)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvCliente findCliente(String nit) {
        List<CvCliente> lst = em.createQuery("SELECT client FROM CvCliente client WHERE client.nit =:nit and client.activo = true", CvCliente.class)
                .setParameter("nit", nit)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public CvCliente saveCliente(CvCliente cliente) {
        try {
            cliente.setFechaCreacion(new Date());
            cliente.setActivo(true);

            em.persist(cliente);
            em.flush();
            return (cliente);
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
    public CvPedido savePedido(CvPedido pedido) {
        try {
            pedido.setFechaCreacion(new Date());
            pedido.setActivo(true);

            em.persist(pedido);
            em.flush();
            return (pedido);
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
    public CvTipoPedido findTipoPedido(Integer idTipoPedido) {
        List<CvTipoPedido> lst = em.createQuery("SELECT tipo FROM CvTipoPedido tipo WHERE tipo.idTipoPedido =:idTipoPedido and tipo.activo = true", CvTipoPedido.class)
                .setParameter("idTipoPedido", idTipoPedido)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

    @Override
    public List<CvPedido> ListaPedidos() {
       List<CvPedido> lst = em.createQuery("SELECT pedido FROM CvPedido pedido WHERE  pedido.activo = true order by pedido.fechaCreacion", CvPedido.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst;
    }

    @Override
    public CvPedido findPedido(Integer idPedido) {
          List<CvPedido> lst = em.createQuery("SELECT pedido FROM CvPedido pedido WHERE pedido.idPedido =:idPedido and pedido.activo = true", CvPedido.class)
                .setParameter("idPedido", idPedido)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }

        return lst.get(0);
    }

}
