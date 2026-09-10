package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;
import services.usecases.exceptions.IncidenciaNoEncontradaException;

import static services.usecases.Validaciones.validarId;


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
        Incidencia incidencia = Validaciones.validarId(idAux) ? null : incidenciaRepository.findById(idAux);
        if(incidencia == null){
            throw new IncidenciaNoEncontradaException("ID no encontrado " + id);
        }
        return incidencia;
    }



    static void main() {
        String id = "INC-0045";
        BuscarIncidenciaPorId buscarIncidenciaPorId = new BuscarIncidenciaPorId(null);
        Incidencia inc;
        try {
            inc = buscarIncidenciaPorId.ejecutar(id);
            System.out.println(inc);
        } catch (IncidenciaNoEncontradaException e){
            System.out.println(e.getMessage());
        }

    }
}
