package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
import concesionario.vehiculos.umg.concesionario.api.entity.CvConcesionario;
import concesionario.vehiculos.umg.concesionario.api.entity.CvProveedor;
import concesionario.vehiculos.umg.concesionario.api.entity.CvTipoColaborador;
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
public class CatalogoBean implements CatalogoBeanLocal {

    private static final Logger log = Logger.getLogger(CatalogoBean.class);

    @PersistenceContext(unitName = "ConceVehiculosPU")
    EntityManager em;

    @Override
    public List<CvTipoColaborador> listAllTipoColaborador() {
        List<CvTipoColaborador> lst = em.createQuery("SELECT col FROM CvTipoColaborador col WHERE col.activo = true", CvTipoColaborador.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

    @Override
    public List<CvProveedor> listAllProveedor() {
        List<CvProveedor> lst = em.createQuery("SELECT pro FROM CvProveedor pro WHERE pro.activo = true", CvProveedor.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

    @Override
    public List<CvConcesionario> listAllConcesionarios() {
        List<CvConcesionario> lst = em.createQuery("SELECT conce FROM CvConcesionario conce WHERE conce.activo = true", CvConcesionario.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            return null;
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

}
