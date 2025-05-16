package Model;

public class Usuario {
    protected String id;
    protected String nombre;
    protected String apellido;
    protected String genero;
    protected String gmail;
    protected String numeroTelefonico;
    protected String tipoUsuario;

    // Constructor
    public Usuario() {}

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    
    public String getGmail() { return gmail; }
    public void setGmail(String gmail) { this.gmail = gmail; }
    
    public String getNumeroTelefonico() { return numeroTelefonico; }
    public void setNumeroTelefonico(String numeroTelefonico) { this.numeroTelefonico = numeroTelefonico; }
    
    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    // Métodos comunes
    public boolean validarEmail() {
        return gmail != null && gmail.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}