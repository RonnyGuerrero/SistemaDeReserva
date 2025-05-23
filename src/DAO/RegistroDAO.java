package DAO;

import Model.Estudiante;
import Model.Profesor;
import Model.Bienestar;
import Model.Usuario;
import java.io.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class RegistroDAO {
    
    private static final String DIRECTORIO_ESTUDIANTES = "C:/Users/Usuario/Desktop/Estudiante/";
    private static final String DIRECTORIO_PROFESORES = "C:/Users/Usuario/Desktop/Profesor/";
    private static final String DIRECTORIO_BIENESTAR ="C:/Users/Usuario/Desktop/Bienestar/";


    public boolean registrarEstudiante(Usuario usuario) {
        if (!(usuario instanceof Estudiante)) {
            System.err.println("Error: El usuario no es un estudiante");
            return false;
        }

        Estudiante estudiante = (Estudiante) usuario;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String nombreArchivo = DIRECTORIO_ESTUDIANTES + "estudiante_" + 
                              estudiante.getId() + "_" + timestamp + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            writer.write("=== DATOS DEL ESTUDIANTE ===\n");
            writer.write("ID: " + estudiante.getId() + "\n");
            writer.write("Nombre: " + estudiante.getNombre() + "\n");
            writer.write("Apellido: " + estudiante.getApellido() + "\n");
            writer.write("Género: " + estudiante.getGenero() + "\n");
            writer.write("Email: " + estudiante.getGmail() + "\n");
            writer.write("Teléfono: " + estudiante.getNumeroTelefonico() + "\n");
            writer.write("Tipo Usuario: " + estudiante.getTipoUsuario() + "\n");
            
           
            writer.write("\n=== DATOS ACADÉMICOS ===\n");
            writer.write("Programa: " + estudiante.getPrograma() + "\n");
            writer.write("Semestre: " + estudiante.getSemestre() + "\n");
            writer.write("Facultad: " + estudiante.getFacultad() + "\n");
            
            
            writer.write("\n=== INFORMACIÓN DE REGISTRO ===\n");
            writer.write("Fecha Registro: " + new Date() + "\n");
            writer.write("Archivo: " + nombreArchivo + "\n");
            
            JOptionPane.showMessageDialog(null,"Estudiante registrado correctamente en: " + nombreArchivo);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error al registrar estudiante: " + e.getMessage());
            return false;
        }
        
    }

    public boolean registrarProfesor(Usuario usuario) {
       if (!(usuario instanceof Profesor)) {
            System.err.println("Error: El usuario no es un profesor");
            return false;
        }

       Profesor profesor= (Profesor) usuario;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String nombreArchivo = DIRECTORIO_PROFESORES + "profesor_" + 
                              profesor.getId() + "_" + timestamp + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            writer.write("=== DATOS DEL PROFESOR ===\n");
            writer.write("ID: " + profesor.getId() + "\n");
            writer.write("Nombre: " + profesor.getNombre() + "\n");
            writer.write("Apellido: " + profesor.getApellido() + "\n");
            writer.write("Género: " + profesor.getGenero() + "\n");
            writer.write("Email: " + profesor.getGmail() + "\n");
            writer.write("Teléfono: " + profesor.getNumeroTelefonico() + "\n");
            writer.write("Tipo Usuario: " + profesor.getTipoUsuario() + "\n");
            
           
            writer.write("\n=== DATOS ACADÉMICOS ===\n");
            writer.write("Programa: " + profesor.getPrograma() + "\n");
            writer.write("Facultad: " + profesor.getFacultad()+ "\n");
            writer.write("Profesion: " + profesor.getProfesion()+ "\n");
            
            
            writer.write("\n=== INFORMACIÓN DE REGISTRO ===\n");
            writer.write("Fecha Registro: " + new Date() + "\n");
            writer.write("Archivo: " + nombreArchivo + "\n");
            
            System.out.println("Profesor registrado correctamente en: " + nombreArchivo);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error al registrar profesor: " + e.getMessage());
            return false;
        }
        
    }

    public boolean registrarBienestar(Usuario usuario) {
       if (!(usuario instanceof Bienestar)) {
            System.err.println("Error: El usuario no es un admin");
            return false;
        }

       Bienestar bienestar= (Bienestar) usuario;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        String nombreArchivo = DIRECTORIO_BIENESTAR + "bienestar_" + 
                              bienestar.getId() + "_" + timestamp + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            writer.write("=== DATOS DE BIENESTAR ===\n");
            writer.write("ID: " + bienestar.getId() + "\n");
            writer.write("Nombre: " + bienestar.getNombre() + "\n");
            writer.write("Apellido: " + bienestar.getApellido() + "\n");
            writer.write("Género: " + bienestar.getGenero() + "\n");
            writer.write("Email: " + bienestar.getGmail() + "\n");
            writer.write("Teléfono: " + bienestar.getNumeroTelefonico() + "\n");
            writer.write("Tipo Usuario: " + bienestar.getTipoUsuario() + "\n");
            
           
            writer.write("\n=== DATOS ACADÉMICOS ===\n");
            writer.write("Departamento: " + bienestar.getDepartamento()+ "\n");
            writer.write("Direccion: " + bienestar.getDireccion()+ "\n");
            writer.write("Oficio: " + bienestar.getOficio()+ "\n");
            
            
            writer.write("\n=== INFORMACIÓN DE REGISTRO ===\n");
            writer.write("Fecha Registro: " + new Date() + "\n");
            writer.write("Archivo: " + nombreArchivo + "\n");
            
            System.out.println("Admin registrado correctamente en: " + nombreArchivo);
            return true;
            
        } catch (IOException e) {
            System.err.println("Error al registrar Admin: " + e.getMessage());
            return false;
        }
        
    }

   
}