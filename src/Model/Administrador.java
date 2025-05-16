package Model;

import Model.oConstantes.nivelAcceso;

public class Administrador extends Usuario {
    private String contrasena;
    private nivelAcceso nivel;

    // Getters y Setters
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    
    public nivelAcceso getNivel() { return nivel; }
    public void setNivel(nivelAcceso nivel) { this.nivel = nivel; }
}