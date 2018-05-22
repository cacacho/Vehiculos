package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.ejb.CatalogoBeanLocal;
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
            //return ("No se encontró información relacionada con el puesto");
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

}
