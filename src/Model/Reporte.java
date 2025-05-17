package Model;

import Model.oConstantes.tipoReporte;
import java.util.Date;

public class Reporte {
    private String idReporte;
    private Date fechaGeneracion;
    private tipoReporte tipo;
    private String contenido;

    // Getters y Setters
    public String getIdReporte() { return idReporte; }
    public void setIdReporte(String idReporte) { this.idReporte = idReporte; }
    
    public Date getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(Date fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
    
    public tipoReporte getTipo() { return tipo; }
    public void setTipo(tipoReporte tipo) { this.tipo = tipo; }
    
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    // Métodos de negocio
    public void generarPDF() {
        // Lógica para generar PDF
    }
    
    public boolean enviarPorCorreo(String destinatario) {
        // Lógica para enviar por correo
        return true;
    }
}