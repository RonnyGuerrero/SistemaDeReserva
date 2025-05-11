package Model;

public class Bienestar extends Usuario {
    private String direccion;
    private String oficio;
    private String departamento;

    public Evento crearEvento(Cancha cancha, Fecha fecha) {
        // Lógica para crear evento
        return new Evento();
    }

    public boolean gestionarEvento(Evento evento) {
        // Lógica para gestionar evento
        return true;
    }

    public boolean asignarRecursos(Evento evento) {
        // Lógica para asignar recursos
        return true;
    }

    // Getters y Setters
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getOficio() { return oficio; }
    public void setOficio(String oficio) { this.oficio = oficio; }
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}