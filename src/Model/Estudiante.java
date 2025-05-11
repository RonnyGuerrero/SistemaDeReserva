package Model;

import java.util.List;

public class Estudiante extends Usuario {
    private String programa;
    private String semestre;
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
    public String getPrograma() { return programa; }
    public void setPrograma(String programa) { this.programa = programa; }
    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }
    public String getFacultad() { return facultad; }
    public void setFacultad(String facultad) { this.facultad = facultad; }
}