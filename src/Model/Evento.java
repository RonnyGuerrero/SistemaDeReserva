package Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Evento implements Serializable {
    private String idEvento;
    private String nombreEvento;
    private Date fechaInicio;
    private String FechaCreada;

    public String getNombreuUsuario() {
        return nombreuUsuario;
    }

    public void setNombreuUsuario(String nombreuUsuario) {
        this.nombreuUsuario = nombreuUsuario;
    }
    private String nombreuUsuario;
   
    
    public String getFechaCreada() {
        return FechaCreada;
    }

    public void setFechaCreada(String FechaCreada) {
        this.FechaCreada = FechaCreada;
    }
    private String descripcion;
    private String participantes;
    private Usuario organizador;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    private String estado;
    
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
    
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public Usuario getOrganizador() { return organizador; }
    public void setOrganizador(Usuario organizador) { this.organizador = organizador; }
}