package DAO;

import Model.Usuario;

public class LoginDAO {
    public boolean autenticarUsuario(String id, String contrasena) {
        // Lógica para autenticar usuario en la base de datos
        return true;
    }

    public boolean recuperarContrasena(String gmail) {
        // Lógica para recuperar contraseña
        return true;
    }

    public Usuario obtenerUsuarioPorCredenciales(String id, String contrasena) {
        // Lógica para obtener usuario
        return null;
    }
}