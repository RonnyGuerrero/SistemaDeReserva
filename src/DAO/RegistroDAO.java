package DAO;

import Model.Estudiante;
import Model.Profesor;
import Model.Bienestar;
import Model.Usuario;

public class RegistroDAO {
    public boolean registrarEstudiante(Usuario usuario) {
        // Lógica para registrar estudiante en la base de datos
        return true;
    }

    public boolean registrarProfesor(Usuario usuario) {
        // Lógica para registrar profesor en la base de datos
        return true;
    }

    public boolean registrarBienestar(Usuario usuario) {
        // Lógica para registrar personal de bienestar en la base de datos
        return true;
    }

    public boolean validarDatos(Usuario usuario) {
        // Lógica para validar datos antes de registrar
        return true;
    }

    public boolean existeUsuario(String id) {
        // Lógica para verificar si el usuario ya existe
        return false;
    }
}