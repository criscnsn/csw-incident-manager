package services;

import java.util.List;
import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;
import services.usecases.BuscarIncidenciaPorId;
import services.usecases.CambiarEstadoIncidencia;
import services.usecases.ListarIncidencias;
import services.usecases.RegistrarIncidencia;
import storage.IIncidenciaStorage;

public class IncidenciaService implements IIncidenciaService {
    private final BuscarIncidenciaPorId buscarIncidenciaPorId;
    private final CambiarEstadoIncidencia cambiarEstadoIncidencia;
    private final ListarIncidencias listarIncidencias;
    private final RegistrarIncidencia registrarIncidencia;

    public IncidenciaService(final IIncidenciaStorage storage) {
        this.buscarIncidenciaPorId = new BuscarIncidenciaPorId(storage);
        this.cambiarEstadoIncidencia = new CambiarEstadoIncidencia(storage);
        this.listarIncidencias = new ListarIncidencias(storage);
        this.registrarIncidencia = new RegistrarIncidencia(storage);
    }

    @Override
    public Incidencia registrarIncidencia(
            final String equipoAfectado,
            final String ubicacionArea,
            final String descripcionFalla,
            final PrioridadIncidencia prioridad
    ) {
        return this.registrarIncidencia.ejecutar(
                equipoAfectado,
                ubicacionArea,
                descripcionFalla,
                prioridad
        );
    }

    @Override
    public List<Incidencia> listarIncidencias() {
        return this.listarIncidencias.ejecutar();
    }

    @Override
    public Incidencia buscarIncidenciaPorIdentificador(final String identificadorIncidencia) {
        return this.buscarIncidenciaPorId.ejecutar(identificadorIncidencia);
    }

    @Override
    public Incidencia cambiarEstadoIncidencia(final String identificadorIncidencia, final EstadoIncidencia nuevoEstado) {
        final Incidencia incidenciaEncontrada = this.buscarIncidenciaPorId.ejecutar(identificadorIncidencia);
        return this.cambiarEstadoIncidencia.ejecutar(incidenciaEncontrada, nuevoEstado);
    }
}
