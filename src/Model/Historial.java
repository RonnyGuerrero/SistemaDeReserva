package Model;

import java.util.List;

public class Historial {
    private String idHistorial;
    private String idUsuario;
    private List<reservarCancha> reservas;

    public List<reservarCancha> consultarHistorial() {
        return reservas;
    }

    public Reporte generarReporte() {
        // Lógica para generar reporte
        return new Reporte();
    }

    // Getters y Setters
    public String getIdHistorial() { return idHistorial; }
    public void setIdHistorial(String idHistorial) { this.idHistorial = idHistorial; }
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
    public List<reservarCancha> getReservas() { return reservas; }
    public void setReservas(List<reservarCancha> reservas) { this.reservas = reservas; }
}