package concesionario.vehiculos.umg.concesionario.api.ejb;

import concesionario.vehiculos.umg.concesionario.api.entity.CvColaborador;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface EmpleadoBeanLocal {

    List<CvColaborador> ListaColaboradores();

    List<CvColaborador> ListaColaboradores(String nombre);

    CvColaborador saveColaborador(CvColaborador colaborador);

    CvColaborador findColaborador(Integer idColaborador);

    CvColaborador updateColaborador(CvColaborador colaborador);

}
