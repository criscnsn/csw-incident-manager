package services;

import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;

import java.util.List;

public interface IIncidenciaService {
    Incidencia registrarIncidencia(
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad
    );

    List<Incidencia> listarIncidencias();

    Incidencia buscarIncidenciaPorIdentificador(String identificadorIncidencia);

    Incidencia cambiarEstadoIncidencia(
            String identificadorIncidencia,
            EstadoIncidencia nuevoEstado
    );
}
