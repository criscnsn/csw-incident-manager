package services;

import models.EstadoIncidencia;
import models.Incidencia;
import models.PrioridadIncidencia;
import repository.IIncidenciaRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class IncidenciaService implements IIncidenciaService {

    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public IncidenciaService(IIncidenciaRepository<Incidencia> incidenciaRepository) {
        this.incidenciaRepository = incidenciaRepository;
    }

    // --- PARTE DE DANI: Registrar incidencia ---
    @Override
    public Incidencia registrarIncidencia(
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad
    ) {
        validarCamposObligatorios(equipoAfectado, ubicacionArea, descripcionFalla, prioridad);

        final String nuevoIdentificador = generarSiguienteIdentificador();
        final Incidencia nuevaIncidencia = new Incidencia(
                nuevoIdentificador,
                equipoAfectado.trim(),
                ubicacionArea.trim(),
                descripcionFalla.trim(),
                prioridad,
                EstadoIncidencia.PENDIENTE
        );

        return incidenciaRepository.save(nuevaIncidencia);
    }

    // --- PARTE DE CRIS: Listar incidencias ---
    @Override
    public List<Incidencia> listarIncidencias() {
        return incidenciaRepository.ListAll();
    }

    // --- PARTE DE GAEL: Buscar incidencia ---
    @Override
    public Incidencia buscarIncidenciaPorIdentificador(String identificadorIncidencia) {
        if (identificadorIncidencia == null || identificadorIncidencia.trim().isEmpty()) {
            throw new IllegalArgumentException("El identificador de búsqueda no puede estar vacío.");
        }

        final Incidencia incidenciaEncontrada = incidenciaRepository.findById(identificadorIncidencia.trim());
        if (incidenciaEncontrada == null) {
            throw new NoSuchElementException("No existe una incidencia con ID: " + identificadorIncidencia);
        }

        return incidenciaEncontrada;
    }

    // --- PARTE DE DIEGO: Cambiar estado ---
    @Override
    public Incidencia cambiarEstadoIncidencia(
            String identificadorIncidencia,
            EstadoIncidencia nuevoEstado
    ) {
        final Incidencia incidenciaExistente = buscarIncidenciaPorIdentificador(identificadorIncidencia);

        if (nuevoEstado == null) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo.");
        }

        validarTransicionDeEstado(incidenciaExistente.getEstadoActual(), nuevoEstado);
        incidenciaExistente.setEstadoActual(nuevoEstado);
        incidenciaRepository.save(incidenciaExistente);

        return incidenciaExistente;
    }

    // --- MÉTODOS AUXILIARES ---

    private void validarCamposObligatorios(
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad
    ) {
        if (equipoAfectado == null || equipoAfectado.trim().isEmpty()) {
            throw new IllegalArgumentException("El equipo o elemento es obligatorio.");
        }
        if (ubicacionArea == null || ubicacionArea.trim().isEmpty()) {
            throw new IllegalArgumentException("La ubicación es obligatoria.");
        }
        if (descripcionFalla == null || descripcionFalla.trim().isEmpty()) {
            throw new IllegalArgumentException("La descripción de la falla es obligatoria.");
        }
        if (prioridad == null) {
            throw new IllegalArgumentException("La prioridad seleccionada no es válida.");
        }
    }

    private String generarSiguienteIdentificador() {
        final int totalActual = incidenciaRepository.ListAll().size();
        return String.format("INC-%03d", totalActual + 1);
    }

    // Regla de negocio requerida: Pendiente -> En proceso -> Resuelta
    private void validarTransicionDeEstado(
            EstadoIncidencia estadoActual,
            EstadoIncidencia nuevoEstado
    ) {
        final boolean esTransicionValida = switch (estadoActual) {
            case PENDIENTE -> nuevoEstado == EstadoIncidencia.EN_PROCESO;
            case EN_PROCESO -> nuevoEstado == EstadoIncidencia.RESUELTA;
            case RESUELTA -> false;
        };

        if (!esTransicionValida) {
            throw new IllegalStateException(
                    "Transición de estado inválida. Flujo permitido: Pendiente -> En proceso -> Resuelta."
            );
        }
    }
}