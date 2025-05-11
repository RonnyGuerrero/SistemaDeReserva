package Model;

import Model.oConstantes.diaSemana;
import java.util.List;
import java.util.Map;

public class Horario {
    private Map<diaSemana, List<Fecha>> horariosSemana;

    public boolean agregarHorario(diaSemana dia, Fecha fecha) {
        // Lógica para agregar horario
        return true;
    }

    public boolean removerHorario(diaSemana dia, Fecha fecha) {
        // Lógica para remover horario
        return true;
    }

    public boolean consultarDisponibilidad(diaSemana dia, Fecha fecha) {
        // Lógica para consultar disponibilidad
        return true;
    }

    // Getters y Setters
    public Map<diaSemana, List<Fecha>> getHorariosSemana() { return horariosSemana; }
    public void setHorariosSemana(Map<diaSemana, List<Fecha>> horariosSemana) { this.horariosSemana = horariosSemana; }
}