package Model;

import java.util.List;

public class Profesor extends Usuario {
    private String profesion;
    private String programa;
    private String facultad;

    public reservarCancha solicitarReserva(Cancha cancha, Fecha fecha) {
        // Lógica para solicitar reserva
        return new reservarCancha();
    }

    public List<reservarCancha> consultarReservas() {
        // Lógica para consultar reservas
        return null;
    }

    public boolean cancelarReserva(reservarCancha reserva) {
        // Lógica para cancelar reserva
        return true;
    }

    // Getters y Setters
    public String getProfesion() { return profesion; }
    public void setProfesion(String profesion) { this.profesion = profesion; }
    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }
    public String getFacultad() { return facultad; }
    public void setFacultad(String facultad) { this.facultad = facultad; }
}