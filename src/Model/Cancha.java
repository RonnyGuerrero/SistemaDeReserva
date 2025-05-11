package Model;

import java.util.List;

public class Cancha {
    private String idCancha;
    private String nombre;
    private String tipoCancha;
    private int capacidad;
    private boolean disponibilidad;
    private Horario horario;

    public boolean verDisponibilidad(Fecha fecha) {
        // Lógica para ver disponibilidad
        return true;
    }

    public boolean reservarCancha(Fecha fecha, Usuario usuario) {
        // Lógica para reservar cancha
        return true;
    }

    public boolean liberarCancha(reservarCancha reserva) {
        // Lógica para liberar cancha
        return true;
    }

    public List<Horario> obtenerHorariosDisponibles(Fecha fecha) {
        // Lógica para obtener horarios disponibles
        return null;
    }

    // Getters y Setters
    public String getIdCancha() { return idCancha; }
    public void setIdCancha(String idCancha) { this.idCancha = idCancha; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipoCancha() { return tipoCancha; }
    public void setTipoCancha(String tipoCancha) { this.tipoCancha = tipoCancha; }
    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }
    public boolean isDisponibilidad() { return disponibilidad; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }
    public Horario getHorario() { return horario; }
    public void setHorario(Horario horario) { this.horario = horario; }
}