package DAO;

import Model.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class LoginDAO {
    
    private static final Map<String,String> DIRECTORIOS = new HashMap<>();
    static {
    DIRECTORIOS.put("ESTUDIANTE", "C:/Users/Usuario/Desktop/proyectoDatos/Usuarios/Estudiante/");
    DIRECTORIOS.put("PROFESOR", "C:/Users/Usuario/Desktop/proyectoDatos/Usuarios/Profesor/");
    DIRECTORIOS.put("BIENESTAR", "C:/Users/Usuario/Desktop/proyectoDatos/Usuarios/Bienestar/");

    }
    
    public Usuario autenticarUsuario(String gmail, String id) {
        for (Map.Entry<String, String> entry : DIRECTORIOS.entrySet()) {
            String tipoUsuario = entry.getKey();
            String directorio = entry.getValue();
            
            Usuario usuario = buscarUsuarioEnDirectorio(gmail, id, directorio, tipoUsuario);
            if (usuario != null) {
                return usuario;
            }
        }
        
        System.err.println("Credenciales incorrectas o usuario no existe");
        return null;
    }
    
    private Usuario buscarUsuarioEnDirectorio(String gmail, String id, String directorio, String tipoUsuarioDirectorio) {
    File carpeta = new File(directorio);
    File[] archivos = carpeta.listFiles();
    
    if (archivos != null) {
        for (File archivo : archivos) {
            try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                Map<String, String> datos = extraerDatosUsuario(reader);
                
                if (gmail.equalsIgnoreCase(datos.get("email")) && 
                    id.equals(datos.get("id"))) {
                    
                    return crearUsuarioSegunTipo(datos.get("tipoUsuario"), datos);
                }
            } catch (IOException e) {
                System.err.println("Error al leer archivo: " + archivo.getName());
            }
        }
    }
    return null;
}
    
private Map<String, String> extraerDatosUsuario(BufferedReader reader) throws IOException {
    Map<String, String> datos = new HashMap<>();
    String linea;
    boolean inAcademicSection = false;
    
    while ((linea = reader.readLine()) != null) {
        if (linea.startsWith("===")) {
            // Check which section we're entering
            if (linea.contains("DATOS ACADÉMICOS")) {
                inAcademicSection = true;
            } else if (linea.contains("INFORMACIÓN DE REGISTRO")) {
                inAcademicSection = false;
            }
            continue;
        }
        
        if (linea.startsWith("ID: ")) datos.put("id", linea.substring(4).trim());
        else if (linea.startsWith("Nombre: ")) datos.put("nombre", linea.substring(8).trim());
        else if (linea.startsWith("Apellido: ")) datos.put("apellido", linea.substring(10).trim());
        else if (linea.startsWith("Email: ")) datos.put("email", linea.substring(7).trim());
        else if (inAcademicSection && linea.startsWith("Programa: ")) datos.put("programa", linea.substring(10).trim());
        else if (inAcademicSection && linea.startsWith("Facultad: ")) datos.put("facultad", linea.substring(10).trim());
        else if (linea.startsWith("Tipo Usuario: ")) datos.put("tipoUsuario", linea.substring(14).trim());
    }
    
    return datos;
}
    
    private Usuario crearUsuarioSegunTipo(String tipoUsuario, Map<String, String> datos) {
    String tipo = datos.get("tipoUsuario").toUpperCase();
    
    switch (tipo) {
        case "ESTUDIANTE":
            Estudiante estudiante = new Estudiante();
            estudiante.setId(datos.get("id"));
            estudiante.setNombre(datos.get("nombre"));
            estudiante.setApellido(datos.get("apellido"));
            estudiante.setGmail(datos.get("email"));
            estudiante.setPrograma(datos.get("programa"));
            estudiante.setFacultad(datos.get("facultad"));
            estudiante.setTipoUsuario("Estudiante");
            return estudiante;
            
        case "PROFESOR":
            Profesor profesor = new Profesor();
            profesor.setId(datos.get("id"));
            profesor.setNombre(datos.get("nombre"));
            profesor.setApellido(datos.get("apellido"));
            profesor.setGmail(datos.get("email"));
            profesor.setPrograma(datos.get("programa"));
            profesor.setFacultad(datos.get("facultad"));
            profesor.setTipoUsuario("Profesor");
            return profesor;
            
        case "BIENESTAR":
            Bienestar bienestar = new Bienestar();
            bienestar.setId(datos.get("id"));
            bienestar.setNombre(datos.get("nombre"));
            bienestar.setApellido(datos.get("apellido"));
            bienestar.setGmail(datos.get("email"));
            bienestar.setDepartamento(datos.get("departamento"));
            bienestar.setTipoUsuario("Bienestar");
            return bienestar;
     
        default:
            return null;
    }
}
    
    public boolean recuperarContrasena(String gmail) {
        for (String directorio : DIRECTORIOS.values()) {
            File carpeta = new File(directorio);
            File[] archivos = carpeta.listFiles();
            
            if (archivos != null) {
                for (File archivo : archivos) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        String emailArchivo = null;
                        String idArchivo = null;
                        
                        while ((linea = reader.readLine()) != null) {
                            if (linea.startsWith("Email: ")) {
                                emailArchivo = linea.substring(7).trim();
                            } else if (linea.startsWith("ID: ")) {
                                idArchivo = linea.substring(4).trim();
                            }
                        }
                        
                        if (gmail.equalsIgnoreCase(emailArchivo)) {
                            JOptionPane.showMessageDialog(null,"Se ha enviado un recordatorio a " + gmail);
                            JOptionPane.showMessageDialog(null,"Su ID (contraseña) es: " + idArchivo);
                            return true;
                        }
                    } catch (IOException e) {
                        System.err.println("Error al leer archivo: " + archivo.getName());
                    }
                }
            }
        }
        
        System.err.println("No se encontró usuario con email: " + gmail);
        return false;
    }
}