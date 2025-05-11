package Model;

import java.util.Date;
import java.util.List;

public class Evento {
    private String idEvento;
    private String nombreEvento;
    private Date fecha;
    private String descripcion;
    private int participantesEstimados;
    private Cancha cancha;
    private List<Usuario> participantes;

    public boolean crearEvento() {
        // Lógica para crear evento
        return true;
    }

    public boolean modificarEvento() {
        // Lógica para modificar evento
        return true;
    }

    public boolean cancelarEvento() {
        // Lógica para cancelar evento
        return true;
    }

    public boolean invitarParticipantes(List<Usuario> invitados) {
        // Lógica para invitar participantes
        return true;
    }

    // Getters y Setters
    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }
    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getParticipantesEstimados() { return participantesEstimados; }
    public void setParticipantesEstimados(int participantesEstimados) { this.participantesEstimados = participantesEstimados; }
    public Cancha getCancha() { return cancha; }
    public void setCancha(Cancha cancha) { this.cancha = cancha; }
    public List<Usuario> getParticipantes() { return participantes; }
    public void setParticipantes(List<Usuario> participantes) { this.participantes = participantes; }
}