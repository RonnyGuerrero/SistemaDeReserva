
package Actor;

 import java.util.List;
public abstract class Usuario {

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getGenero() {
        return genero;
    }

    public String getGmail() {
        return gmail;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public String getId() {
        return id;
    }
    
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    
    protected String nombre;
    protected String apellido;
    protected String genero;
    protected String gmail;
    protected String numeroTelefonico;
    protected String id;
    protected String tipoUsuario;

    public Usuario(String nombre, String apellido, String genero, String gmail, String numeroTelefonico, String id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.gmail = gmail;
        this.numeroTelefonico = numeroTelefonico;
        this.id = id;
    }
   public abstract List<String>
     obtenerCampos();
    
}
