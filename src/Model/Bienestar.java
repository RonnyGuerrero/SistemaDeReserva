package Model;

public class Bienestar extends Usuario {
    private String direccion;
    private String oficio;
    private String departamento;

    // Getters y Setters
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    
    public String getOficio() { return oficio; }
    public void setOficio(String oficio) { this.oficio = oficio; }
    
    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }
}