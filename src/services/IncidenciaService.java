package services;

import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;

import java.util.List;

public class IncidenciaService implements IIncidenciaService{
    @Override
    public Incidencia registrarIncidencia(String equipoAfectado, String ubicacionArea, String descripcionFalla, PrioridadIncidencia prioridad) {
        return null;
    }

    @Override
    public List<Incidencia> listarIncidencias() {
        return List.of();
    }

    @Override
    public Incidencia buscarIncidenciaPorIdentificador(String identificadorIncidencia) {
        return null;
    }

    @Override
    public Incidencia cambiarEstadoIncidencia(String identificadorIncidencia, EstadoIncidencia nuevoEstado) {
        return null;
    }
}
