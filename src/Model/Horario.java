package Model;
import Model.oConstantes.diaSemana;
import java.util.*;

public class Horario {
    private Map<diaSemana, List<Fecha>> horariosSemana;

    public Horario() {
        this.horariosSemana = new EnumMap<>(diaSemana.class);
        // Inicializar todos los días
        for (diaSemana dia : diaSemana.values()) {
            horariosSemana.put(dia, new ArrayList<>());
        }
    }

    // Getters y Setters
    public Map<diaSemana, List<Fecha>> getHorariosSemana() { return horariosSemana; }

    // Métodos de negocio
    public boolean agregarHorario(diaSemana dia, Fecha fecha) {
        return horariosSemana.get(dia).add(fecha);
    }
    
    public boolean removerHorario(diaSemana dia, Fecha fecha) {
        return horariosSemana.get(dia).remove(fecha);
    }
    
    public boolean consultarDisponibilidad(diaSemana dia, Fecha fecha) {
        return horariosSemana.get(dia).stream()
            .noneMatch(f -> existeSuperposicion(f, fecha));
    }
    
    private boolean existeSuperposicion(Fecha f1, Fecha f2) {
        return f1.getHoraInicio().before(f2.getHoraFin()) && 
               f1.getHoraFin().after(f2.getHoraInicio());
    }
}