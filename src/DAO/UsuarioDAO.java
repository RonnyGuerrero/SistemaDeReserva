package DAO;

import Model.*;
import Model.oConstantes.nivelAcceso;
import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;

public class UsuarioDAO {
    private static final Map<Class<? extends Usuario>, String> DIRECTORIOS = Map.of(
        Estudiante.class, "data/usuarios/estudiantes/",
        Profesor.class, "data/usuarios/profesores/",
        Bienestar.class, "data/usuarios/bienestar/",
        Administrador.class, "data/usuarios/administradores/"
    );
    
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    
    public UsuarioDAO() {
        crearDirectorios();
    }
    
    private void crearDirectorios() {
        DIRECTORIOS.values().forEach(dir -> new File(dir).mkdirs());
    }

    /**
     * Obtiene un usuario por su ID, buscando en todos los directorios
     * @param id
     */
    public Usuario obtenerUsuarioPorId(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        
        for (String directorio : DIRECTORIOS.values()) {
            File carpeta = new File(directorio);
            File[] archivos = carpeta.listFiles((dir, name) -> name.contains("_" + id + "_"));
            
            if (archivos != null && archivos.length > 0) {
                // Tomar el archivo más reciente
                File archivo = archivos[archivos.length - 1];
                return leerUsuarioDeArchivo(archivo);
            }
        }
        
        return null;
    }

    /**
     * Busca un usuario por email (para recuperación de contraseña)
     */
    public Usuario buscarUsuarioPorEmail(String email) {
        if (email == null || email.isEmpty()) {
            return null;
        }
        
        for (String directorio : DIRECTORIOS.values()) {
            File carpeta = new File(directorio);
            File[] archivos = carpeta.listFiles();
            
            if (archivos != null) {
                for (File archivo : archivos) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
                        String linea;
                        while ((linea = reader.readLine()) != null) {
                            if (linea.startsWith("Email: ") && linea.substring(7).trim().equalsIgnoreCase(email)) {
                                return leerUsuarioDeArchivo(archivo);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("Error al buscar usuario por email: " + e.getMessage());
                    }
                }
            }
        }
        return null;
    }

    private Usuario leerUsuarioDeArchivo(File archivo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            Map<String, String> datos = new HashMap<>();
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("ID: ")) datos.put("id", linea.substring(4).trim());
                else if (linea.startsWith("Nombre: ")) datos.put("nombre", linea.substring(8).trim());
                else if (linea.startsWith("Apellido: ")) datos.put("apellido", linea.substring(10).trim());
                else if (linea.startsWith("Email: ")) datos.put("email", linea.substring(7).trim());
                else if (linea.startsWith("TipoUsuario: ")) datos.put("tipo", linea.substring(13).trim());
                // ... otros campos
            }
            
            return crearUsuarioDesdeDatos(datos);
        } catch (IOException e) {
            System.err.println("Error al leer archivo de usuario: " + e.getMessage());
            return null;
        }
    }

    private Usuario crearUsuarioDesdeDatos(Map<String, String> datos) {
        String tipo = datos.get("tipo");
        if (tipo == null) return null;
        
        switch (tipo.toUpperCase()) {
            case "ESTUDIANTE":
                Estudiante est = new Estudiante();
                setDatosBasicos(est, datos);
                est.setPrograma(datos.get("programa"));
                est.setSemestre(datos.get("semestre"));
                est.setFacultad(datos.get("facultad"));
                return est;
                
            case "PROFESOR":
                Profesor prof = new Profesor();
                setDatosBasicos(prof, datos);
                prof.setProfesion(datos.get("profesion"));
                prof.setPrograma(datos.get("programa"));
                prof.setFacultad(datos.get("facultad"));
                return prof;
                
            case "BIENESTAR":
                Bienestar bien = new Bienestar();
                setDatosBasicos(bien, datos);
                bien.setDireccion(datos.get("direccion"));
                bien.setOficio(datos.get("oficio"));
                bien.setDepartamento(datos.get("departamento"));
                return bien;
                
            case "ADMINISTRADOR":
                Administrador admin = new Administrador();
                setDatosBasicos(admin, datos);
                admin.setNivel(nivelAcceso.valueOf(datos.get("nivel")));
                return admin;
                
            default:
                return null;
        }
    }

    private void setDatosBasicos(Usuario usuario, Map<String, String> datos) {
        usuario.setId(datos.get("id"));
        usuario.setNombre(datos.get("nombre"));
        usuario.setApellido(datos.get("apellido"));
        usuario.setGmail(datos.get("email"));
        usuario.setTipoUsuario(datos.get("tipo"));
        // ... otros campos comunes
    }

    // ... otros métodos existentes (registrarUsuario, autenticarUsuario, etc.)
}