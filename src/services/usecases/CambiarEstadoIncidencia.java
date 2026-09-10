package services.usecases;

import models.EstadoIncidencia;
import models.Incidencia;
import repository.IIncidenciaRepository;

public class CambiarEstadoIncidencia {
//    validación de transición de estados
    private final IIncidenciaRepository<Incidencia> incidenciaRepository;

    public CambiarEstadoIncidencia(IIncidenciaRepository<Incidencia> incidenciaRepository){
        this.incidenciaRepository = incidenciaRepository;

    }

    // Este es el método que ejecuta la acción

    public Incidencia ejecutar(String id, EstadoIncidencia nuevoEstado) {

        // 1. Buscamos la incidencia en la base de datos (repositorio)

        Incidencia incidencia = incidenciaRepository.findById(id);
        
        // 2. Si no existe, devolvemos null (o podríamos lanzar un error)

        if (incidencia == null) {
            System.out.println("Error: No se encontró la incidencia con ID " + id);
            return null;
        }

        // 3. Validación de estado (Lógica de negocio)
        // Verificamos si el estado al que queremos pasar es válido según la lógica

        EstadoIncidencia estadoActual = incidencia.getEstadoActual();
        if (estadoActual == EstadoIncidencia.RESUELTA) {
            System.out.println("Error: La incidencia ya está resuelta y no puede cambiar de estado.");
            return null;
        }
        if (estadoActual == nuevoEstado) {
            System.out.println("La incidencia ya se encuentra en el estado: " + nuevoEstado);
            return incidencia; 
        }

        // 4. Cambiamos el estado (usamos el método que ya existe)
        // NOTA: Como setEstadoActual() avanza solo al siguiente estado, 
        // lo ideal es llamarlo para que la incidencia se actualice

        incidencia.setEstadoActual(nuevoEstado);

        // 5. Guardamos/Actualizamos en el repositorio

        return incidenciaRepository.save(incidencia);
    }

}
