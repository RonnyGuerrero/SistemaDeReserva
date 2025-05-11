package Model;

public class Usuario {
    private String nombre;
    private String apellido;
    private String genero;
    private String gmail;
    private String numeroTelefonico;
    private String id;
    private String tipoUsuario;

    public boolean validarEmail() {
        return gmail != null && gmail.contains("@") && gmail.endsWith(".com");
    }

    public boolean actualizarPerfil() {
        // Lógica para actualizar perfil
        return true;
    }

    // Getters y Setters
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
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}