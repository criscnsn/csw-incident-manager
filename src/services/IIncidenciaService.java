package services;

import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;

import java.util.List;

public interface IIncidenciaService {
    // Dani
    Incidencia registrarIncidencia(
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad
    );

    // Cris
    List<Incidencia> listarIncidencias();

    // Gael
    Incidencia buscarIncidenciaPorIdentificador(String identificadorIncidencia);

    // Diego
    Incidencia cambiarEstadoIncidencia(
            String identificadorIncidencia,
            EstadoIncidencia nuevoEstado
    );
}
