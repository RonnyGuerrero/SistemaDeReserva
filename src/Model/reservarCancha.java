package Model;

import java.util.Date;

import Model.oConstantes.estadoReserva;

public class reservarCancha {
    private String idReserva;
    private String dia;
    private String hora;
    private Date fecha;
    private String motivo;
    private estadoReserva estado;
    private Cancha cancha;
    private Usuario usuario;

    // Getters y Setters
    public String getIdReserva() { return idReserva; }
    public void setIdReserva(String idReserva) { this.idReserva = idReserva; }
    
    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }
    
    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }
    
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    
    public estadoReserva getEstado() { return estado; }
    public void setEstado(estadoReserva estado) { this.estado = estado; }
    
    public Cancha getCancha() { return cancha; }
    public void setCancha(Cancha cancha) { this.cancha = cancha; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    // Métodos de negocio
    public boolean confirmarReserva() {
        if (this.estado == estadoReserva.PENDIENTE) {
            this.estado = estadoReserva.APROBADA;
            return true;
        }
        return false;
    }
}