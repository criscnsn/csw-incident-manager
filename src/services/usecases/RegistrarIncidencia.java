package services.usecases;

import models.Incidencia;
import models.PrioridadIncidencia;
import services.usecases.exceptions.DatosIncidenciaInvalidosException;
import storage.IIncidenciaStorage;

public class RegistrarIncidencia {

    private final IIncidenciaStorage incidenciaStorage;

    public RegistrarIncidencia(
            final IIncidenciaStorage incidenciaStorage
    ) {
        this.incidenciaStorage = incidenciaStorage;
    }

    public Incidencia ejecutar(
            final String equipoAfectado,
            final String ubicacionArea,
            final String descripcionFalla,
            final PrioridadIncidencia prioridad
    ) {
        validarDatos(
                equipoAfectado,
                ubicacionArea,
                descripcionFalla,
                prioridad
        );

        final String identificador = generarIdentificador();

        final Incidencia incidencia = new Incidencia(
                identificador,
                equipoAfectado,
                ubicacionArea,
                descripcionFalla,
                prioridad
        );

        return this.incidenciaStorage.save(incidencia);
    }

    private void validarDatos(
            final String equipoAfectado,
            final String ubicacionArea,
            final String descripcionFalla,
            final PrioridadIncidencia prioridad
    ) {
        if (equipoAfectado == null || equipoAfectado.isBlank()) {
            throw new DatosIncidenciaInvalidosException(
                    "El equipo afectado no puede estar vacío"
            );
        }

        if (ubicacionArea == null || ubicacionArea.isBlank()) {
            throw new DatosIncidenciaInvalidosException(
                    "La ubicación o área no puede estar vacía"
            );
        }

        if (descripcionFalla == null || descripcionFalla.isBlank()) {
            throw new DatosIncidenciaInvalidosException(
                    "La descripción de la falla no puede estar vacía"
            );
        }

        if (prioridad == null) {
            throw new DatosIncidenciaInvalidosException(
                    "La prioridad no puede ser nula"
            );
        }
    }

    private String generarIdentificador() {
        // Los identificadores siguen el formato INC-001, INC-002, etc.
        int numeroIncidencia = this.incidenciaStorage.listAll().size() + 1;

        String identificador = String.format(
                "INC-%03d",
                numeroIncidencia
        );

        // Evita registrar dos incidencias con el mismo identificador.
        while (this.incidenciaStorage.existsById(identificador)) {
            numeroIncidencia++;

            identificador = String.format(
                    "INC-%03d",
                    numeroIncidencia
            );
        }
        return identificador;
    }

}