package Model;

import Model.oConstantes.diaSemana;
import java.sql.Time;
import java.util.Date;

public class Fecha {
    private Date fecha;
    private Time horaInicio;
    private Time horaFin;
    private diaSemana diaSemana;

    // Getters y Setters
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { 
        this.fecha = fecha;
        // Actualizar día de la semana automáticamente
        // (implementación simplificada)
    }
    
    public Time getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Time horaInicio) { this.horaInicio = horaInicio; }
    
    public Time getHoraFin() { return horaFin; }
    public void setHoraFin(Time horaFin) { this.horaFin = horaFin; }
    
    public diaSemana getDiaSemana() { return diaSemana; }
    public void setDiaSemana(diaSemana diaSemana) { this.diaSemana = diaSemana; }

    // Métodos de negocio
    public static Fecha crearRango(Date fecha, Time horaInicio, Time horaFin) {
        Fecha nuevaFecha = new Fecha();
        nuevaFecha.setFecha(fecha);
        nuevaFecha.setHoraInicio(horaInicio);
        nuevaFecha.setHoraFin(horaFin);
        return nuevaFecha;
    }
}