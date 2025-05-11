package DAO;

import Model.Cancha;
import Model.Fecha;
import java.util.List;

public class CanchaDAO {
    public boolean agregarCancha(Cancha cancha) {
        // Lógica para agregar cancha
        return true;
    }

    public boolean actualizarCancha(Cancha cancha) {
        // Lógica para actualizar cancha
        return true;
    }

    public boolean eliminarCancha(String idCancha) {
        // Lógica para eliminar cancha
        return true;
    }

    public Cancha obtenerCanchaPorId(String idCancha) {
        // Lógica para obtener cancha por ID
        return null;
    }

    public List<Cancha> listarTodasCanchas() {
        // Lógica para listar todas las canchas
        return null;
    }

    public boolean verificarDisponibilidad(String idCancha, Fecha fecha) {
        // Lógica para verificar disponibilidad
        return true;
    }
}