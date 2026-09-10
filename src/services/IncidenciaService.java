package services;

import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;
import services.usecases.BuscarIncidenciaPorId;
import services.usecases.CambiarEstadoIncidencia;
import services.usecases.ListarIncidencias;
import services.usecases.RegistrarIncidencia;

import java.util.List;

//Clase encargada de gestionar las acciones del sistema
public class IncidenciaService implements IIncidenciaService{
    private final BuscarIncidenciaPorId buscarIncidenciaPorId;
    private final CambiarEstadoIncidencia cambiarEstadoIncidencia;
    private final ListarIncidencias listarIncidencias;
    private final RegistrarIncidencia registrarIncidencia;

    public IncidenciaService(
            BuscarIncidenciaPorId buscarIncidenciaPorId,
            CambiarEstadoIncidencia cambiarEstadoIncidencia,
            ListarIncidencias listarIncidencias,
            RegistrarIncidencia registrarIncidencia
    ){
        this.buscarIncidenciaPorId = buscarIncidenciaPorId;
        this.cambiarEstadoIncidencia = cambiarEstadoIncidencia;
        this.listarIncidencias = listarIncidencias;
        this.registrarIncidencia = registrarIncidencia;
    }


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
        return buscarIncidenciaPorId.ejecutar(identificadorIncidencia);
    }

    @Override
    public Incidencia cambiarEstadoIncidencia(String identificadorIncidencia, EstadoIncidencia nuevoEstado) {
        return null;
    }
}
