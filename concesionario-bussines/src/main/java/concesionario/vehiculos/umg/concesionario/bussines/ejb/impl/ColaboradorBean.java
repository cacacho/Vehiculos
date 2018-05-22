package concesionario.vehiculos.umg.concesionario.bussines.ejb.impl;

import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import java.util.List;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import concesionario.vehiculos.umg.concesionario.api.ejb.EmpleadoBeanLocal;

/**
 *
 * @author DELL
 */
@Singleton
public class ColaboradorBean implements EmpleadoBeanLocal {

    private static final Logger log = Logger.getLogger(EmpleadoBeanLocal.class);

    @PersistenceContext(unitName = "ConceVehiculosPU")
    EntityManager em;

    @Override
    public List<CvColaborador> ListaColaboradores() {

        List<CvColaborador> lst = em.createQuery("SELECT col FROM CvColaborador col WHERE col.activo = true", CvColaborador.class)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return new Response(ResponseStatus.ERROR_NO_DATA, String.format("No se encontró información relacionada con el puesto"));
        }
        // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
        return lst;
    }

    @Override
    public List<CvColaborador> ListaColaboradores(String nombre) {
        if (nombre == null) {
            //return new Response(ResponseStatus.ERROR_PARAMS, "Debe de proporcionar un puesto existente");
        }

        List<CvColaborador> lst = em.createQuery("SELECT col FROM CvColaborador col WHERE col.nombres like :nombre ", CvColaborador.class)
                .setParameter("nombre", nombre)
                .getResultList();

        if (lst == null || lst.isEmpty()) {
            //return new Response(ResponseStatus.ERROR_NO_DATA, String.format("No se encontró información relacionada con el puesto"));
        }
       // return new Response(lst.get(0), ResponseStatus.OK_QUERY);
       return lst;
    }

}
