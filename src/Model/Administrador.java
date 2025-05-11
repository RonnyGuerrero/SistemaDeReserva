package Model;

import Model.oConstantes.nivelAcceso;
import java.util.List;

public class Administrador extends Usuario {
    private String idAdmin;
    private String contrasena;
    private nivelAcceso nivel;

    public List<reservarCancha> verReservas() {
        // Lógica para ver reservas
        return null;
    }

    public boolean modificarReservas(reservarCancha reserva) {
        // Lógica para modificar reservas
        return true;
    }

    public boolean aprobarSolicitud(reservarCancha reserva) {
        // Lógica para aprobar solicitud
        return true;
    }

    public Reporte generarReporte() {
        // Lógica para generar reporte
        return new Reporte();
    }

    public boolean gestionarUsuarios() {
        // Lógica para gestionar usuarios
        return true;
    }

    // Getters y Setters
    public String getIdAdmin() { return idAdmin; }
    public void setIdAdmin(String idAdmin) { this.idAdmin = idAdmin; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public nivelAcceso getNivel() { return nivel; }
    public void setNivel(nivelAcceso nivel) { this.nivel = nivel; }
}