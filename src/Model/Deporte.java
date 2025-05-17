package Model;

public class Deporte {
    private String idDeporte;
    private String nombreDeporte;
    private String reglas;
    private int jugadoresRequeridos;

    // Getters y Setters
    public String getIdDeporte() { return idDeporte; }
    public void setIdDeporte(String idDeporte) { this.idDeporte = idDeporte; }
    
    public String getNombreDeporte() { return nombreDeporte; }
    public void setNombreDeporte(String nombreDeporte) { this.nombreDeporte = nombreDeporte; }
    
    public String getReglas() { return reglas; }
    public void setReglas(String reglas) { this.reglas = reglas; }
    
    public int getJugadoresRequeridos() { return jugadoresRequeridos; }
    public void setJugadoresRequeridos(int jugadoresRequeridos) { this.jugadoresRequeridos = jugadoresRequeridos; }

    // Métodos de negocio
    public String getReglamento() {
        return "Reglamento oficial de " + nombreDeporte + ":\n" + reglas;
    }
}