package Model;

import Model.oConstantes.tipoReporte;
import java.io.File;
import java.util.Date;

public class Reporte {
    private String idReporte;
    private Date fechaGeneracion;
    private tipoReporte tipo;

    public File generarPDF() {
        // Lógica para generar PDF
        return null;
    }

    public boolean enviarPorCorreo(String destinatario) {
        // Lógica para enviar por correo
        return true;
    }

    // Getters y Setters
    public String getIdReporte() { return idReporte; }
    public void setIdReporte(String idReporte) { this.idReporte = idReporte; }
    public Date getFechaGeneracion() { return fechaGeneracion; }
    public void setFechaGeneracion(Date fechaGeneracion) { this.fechaGeneracion = fechaGeneracion; }
    public tipoReporte getTipo() { return tipo; }
    public void setTipo(tipoReporte tipo) { this.tipo = tipo; }
}