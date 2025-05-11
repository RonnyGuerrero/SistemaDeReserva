package DAO;

import Model.Usuario;
import java.util.List;

public class UsuarioDAO {
    public boolean crearUsuario(Usuario usuario) {
        // Lógica para crear usuario
        return true;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        // Lógica para actualizar usuario
        return true;
    }

    public boolean eliminarUsuario(String idUsuario) {
        // Lógica para eliminar usuario
        return true;
    }

    public Usuario obtenerUsuarioPorId(String idUsuario) {
        // Lógica para obtener usuario por ID
        return null;
    }

    public List<Usuario> listarTodosUsuarios() {
        // Lógica para listar todos los usuarios
        return null;
    }

    public boolean cambiarContrasena(String idUsuario, String nuevaContrasena) {
        // Lógica para cambiar contraseña
        return true;
    }
}