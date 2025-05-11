package DAO;

import Model.Evento;
import java.util.List;

public class EventoDAO {
    public boolean crearEvento(Evento evento) {
        // Lógica para crear evento
        return true;
    }

    public boolean actualizarEvento(Evento evento) {
        // Lógica para actualizar evento
        return true;
    }

    public boolean cancelarEvento(String idEvento) {
        // Lógica para cancelar evento
        return true;
    }

    public Evento obtenerEventoPorId(String idEvento) {
        // Lógica para obtener evento por ID
        return null;
    }

    public List<Evento> listarTodosEventos() {
        // Lógica para listar todos los eventos
        return null;
    }

    public boolean agregarParticipante(String idEvento, String idUsuario) {
        // Lógica para agregar participante
        return true;
    }
}