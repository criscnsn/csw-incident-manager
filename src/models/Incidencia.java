package models;

public class Incidencia {
    private final String identificador;
    private final String equipoAfectado;
    private final String ubicacionArea;
    private final String descripcionFalla;
    private final PrioridadIncidencia prioridad;
    private EstadoIncidencia estadoActual;

    public Incidencia(
            String identificador,
            String equipoAfectado,
            String ubicacionArea,
            String descripcionFalla,
            PrioridadIncidencia prioridad,
            EstadoIncidencia estadoActual
    ) {
        this.identificador = identificador;
        this.equipoAfectado = equipoAfectado;
        this.ubicacionArea = ubicacionArea;
        this.descripcionFalla = descripcionFalla;
        this.prioridad = prioridad;
        this.estadoActual = estadoActual;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getEquipoAfectado() {
        return equipoAfectado;
    }

    public String getUbicacionArea() {
        return ubicacionArea;
    }

    public String getDescripcionFalla() {
        return descripcionFalla;
    }

    public PrioridadIncidencia getPrioridad() {
        return prioridad;
    }

    public EstadoIncidencia getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoIncidencia nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }
}