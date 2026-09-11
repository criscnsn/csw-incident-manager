package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;
import services.usecases.exceptions.IncidenciaNoEncontradaException;



public class BuscarIncidenciaPorId {
//    búsqueda + manejo de "no encontrado"
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;
    public BuscarIncidenciaPorId(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;
    }

    public Incidencia ejecutar(String id){
        if(id == null || id.isBlank()) {
            throw new IncidenciaNoEncontradaException("ID vacia o nula");
        }
        String idAux = id.replaceAll("[ \\s]", "");
        /**
         * El Operador elvis estaba al reves, mandaba errores si se ponia un id correcto
         * */
        Incidencia incidencia = validarId(idAux) ? incidenciaRepository.findById(idAux) : null;
        if(incidencia == null){
            throw new IncidenciaNoEncontradaException("ID no encontrado " + id);
        }
        return incidencia;
    }
    private static boolean validarId(String id){
        return id.matches(  "^INC-\\d{3,}?$");
    }
}
