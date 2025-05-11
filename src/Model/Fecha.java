package Model;

import java.sql.Time;
import java.util.Date;

public class Fecha {
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;

    public boolean estaDisponible(Cancha cancha) {
        // Lógica para verificar disponibilidad
        return true;
    }

    public static Fecha crearRango(Date fecha, Time horaInicio, Time horaFin) {
        Fecha nuevaFecha = new Fecha();
        nuevaFecha.setFecha(fecha);
        nuevaFecha.setHoraInicio(horaInicio);
        nuevaFecha.setHoraFin(horaFin);
        return nuevaFecha;
    }

    // Getters y Setters
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }
    public Time getHoraFin() { return horaFin; }
    public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }
}