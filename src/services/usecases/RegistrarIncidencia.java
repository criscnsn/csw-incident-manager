package services.usecases;

import models.Incidencia;
import models.PrioridadIncidencia;
import repository.IIncidenciaRepository;
import services.usecases.exceptions.DatosIncidenciaInvalidosException;

public class RegistrarIncidencia {

    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public RegistrarIncidencia(
            IIncidenciaRepository<Incidencia> incidenciaRepository
    ) {
        this.incidenciaRepository = incidenciaRepository;
    }

    public Incidencia ejecutar(
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad
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

        return incidenciaRepository.save(incidencia);
    }

    private void validarDatos(
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad
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
        int numeroIncidencia = incidenciaRepository.ListAll().size() + 1;

        String identificador = String.format(
                "INC-%03d",
                numeroIncidencia
        );

        // Evita registrar dos incidencias con el mismo identificador.
        while (incidenciaRepository.existsById(identificador)) {
            numeroIncidencia++;

            identificador = String.format(
                    "INC-%03d",
                    numeroIncidencia
            );
        }
        return identificador;
    }

}