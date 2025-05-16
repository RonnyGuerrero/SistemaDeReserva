package Model;

import java.util.Date;
import java.util.List;

public class Evento {
    private String idEvento;
    private String nombreEvento;
    private Date fechaInicio;
    private Date fechaCierre;
    private String descripcion;
    private String participantes;
    private Usuario organizador;
    
    public String getParticipantes() {
        return participantes;}

    public void setParticipantes(String participantes) {
        this.participantes = participantes;}
    

    // Getters y Setters
    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }
    
    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }
    
    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fecha) { this.fechaInicio = fecha; }
    
    public Date getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(Date fecha) { this.fechaCierre = fecha; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Usuario getOrganizador() { return organizador; }
    public void setOrganizador(Usuario organizador) { this.organizador = organizador; }
}