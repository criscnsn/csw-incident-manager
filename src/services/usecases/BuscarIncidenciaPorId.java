package services.usecases;

import models.Incidencia;
import services.usecases.exceptions.IncidenciaNoEncontradaException;
import storage.IIncidenciaStorage;

public class BuscarIncidenciaPorId {
    private final IIncidenciaStorage incidenciaStorage;

    public BuscarIncidenciaPorId(final IIncidenciaStorage incidenciaStorage) {
        this.incidenciaStorage = incidenciaStorage;
    }

    public Incidencia ejecutar(final String identificador) {
        if (identificador == null || identificador.isBlank()) {
            throw new IncidenciaNoEncontradaException("Identificador vacío o nulo.");
        }

        final String idNormalizado = identificador.replaceAll("[ \\s]", "");
        final Incidencia incidencia = validarId(idNormalizado)
                ? this.incidenciaStorage.findById(idNormalizado)
                : null;

        if (incidencia == null) {
            throw new IncidenciaNoEncontradaException("No se encontró la incidencia con identificador: " + identificador);
        }

        return incidencia;
    }

    private static boolean validarId(final String identificador) {
        return identificador.matches("^INC-\\d{3}?$");
    }
}
