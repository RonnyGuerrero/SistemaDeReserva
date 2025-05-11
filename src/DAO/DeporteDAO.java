package DAO;

import Model.Deporte;
import java.util.List;

public class DeporteDAO {
    public boolean agregarDeporte(Deporte deporte) {
        // Lógica para agregar deporte
        return true;
    }

    public boolean actualizarDeporte(Deporte deporte) {
        // Lógica para actualizar deporte
        return true;
    }

    public boolean eliminarDeporte(String idDeporte) {
        // Lógica para eliminar deporte
        return true;
    }

    public Deporte obtenerDeportePorId(String idDeporte) {
        // Lógica para obtener deporte por ID
        return null;
    }

    public List<Deporte> listarTodosDeportes() {
        // Lógica para listar todos los deportes
        return null;
    }

    public boolean asociarCanchaDeporte(String idDeporte, String idCancha) {
        // Lógica para asociar cancha a deporte
        return true;
    }
}