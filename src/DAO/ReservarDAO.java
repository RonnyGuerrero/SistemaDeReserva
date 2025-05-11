package DAO;

import Model.reservarCancha;
import java.util.List;

public class ReservarDAO {
    public boolean crearReserva(reservarCancha reserva) {
        // Lógica para crear reserva
        return true;
    }

    public boolean actualizarReserva(reservarCancha reserva) {
        // Lógica para actualizar reserva
        return true;
    }

    public boolean cancelarReserva(String idReserva) {
        // Lógica para cancelar reserva
        return true;
    }

    public reservarCancha obtenerReservaPorId(String idReserva) {
        // Lógica para obtener reserva por ID
        return null;
    }

    public List<reservarCancha> listarReservasPorUsuario(String idUsuario) {
        // Lógica para listar reservas por usuario
        return null;
    }

    public List<reservarCancha> listarTodasReservas() {
        // Lógica para listar todas las reservas
        return null;
    }
}