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
            PrioridadIncidencia prioridad
    ) {
        this.identificador = identificador;
        this.equipoAfectado = equipoAfectado;
        this.ubicacionArea = ubicacionArea;
        this.descripcionFalla = descripcionFalla;
        this.prioridad = prioridad;
        this.estadoActual = EstadoIncidencia.PENDIENTE;
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

    //El estado cicla entre Pendiente → En proceso → Resuelta
    public void changeState(){
        if(estadoActual == EstadoIncidencia.PENDIENTE){
            estadoActual = EstadoIncidencia.EN_PROCESO;
        }else if(estadoActual == EstadoIncidencia.EN_PROCESO){
            estadoActual = EstadoIncidencia.RESUELTA;
        }
    }
}