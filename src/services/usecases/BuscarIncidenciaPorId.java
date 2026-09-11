package services.usecases;

import models.Incidencia;
import repository.IIncidenciaRepository;
import services.usecases.exceptions.IncidenciaNoEncontradaException;



public class BuscarIncidenciaPorId {
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public BuscarIncidenciaPorId(final IIncidenciaRepository<Incidencia> incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public Incidencia ejecutar(final String identificador) {
        if (identificador == null || identificador.isBlank()) {
            throw new IncidenciaNoEncontradaException("Identificador vacío o nulo.");
        }

        final String idNormalizado = identificador.replaceAll("[ \\s]", "");
        final Incidencia incidencia = validarId(idNormalizado)
                ? this.incidenciaRepository.findById(idNormalizado)
                : null;

        if (incidencia == null) {
            throw new IncidenciaNoEncontradaException("No se encontró la incidencia con identificador: " + identificador);
        }

        return incidencia;
    }

    private static boolean validarId(final String identificador) {
        return identificador.matches("^INC-\\d{3,}?$");
    }
}
