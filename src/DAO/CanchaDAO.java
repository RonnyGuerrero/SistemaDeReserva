package DAO;

import Model.*;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class CanchaDAO {
    private static final String DIRECTORIO = "data/canchas/";
    
    public CanchaDAO() {
        new File(DIRECTORIO).mkdirs();
    }
    
    public boolean guardarCancha(Cancha cancha) {
        if (cancha == null || cancha.getIdCancha() == null) return false;
        
        String archivo = DIRECTORIO + "cancha_" + cancha.getIdCancha() + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("ID: " + cancha.getIdCancha());
            pw.println("Nombre: " + cancha.getNombre());
            pw.println("Tipo: " + cancha.getTipoCancha());
            pw.println("Capacidad: " + cancha.getCapacidad());
            pw.println("Disponible: " + cancha.isDisponibilidad());
            if (cancha.getDeporteAsociado() != null) {
                pw.println("Deporte: " + cancha.getDeporteAsociado().getIdDeporte());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error guardando cancha: " + e.getMessage());
            return false;
        }
    }
    
    public Cancha obtenerCancha(String idCancha) {
        String archivo = DIRECTORIO + "cancha_" + idCancha + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            Map<String, String> datos = new HashMap<>();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ", 2);
                if (partes.length == 2) {
                    datos.put(partes[0].trim(), partes[1].trim());
                }
            }
            
            Cancha cancha = new Cancha();
            cancha.setIdCancha(datos.get("ID"));
            cancha.setNombre(datos.get("Nombre"));
            cancha.setTipoCancha(datos.get("Tipo"));
            cancha.setCapacidad(Integer.parseInt(datos.get("Capacidad")));
            cancha.setDisponibilidad(Boolean.parseBoolean(datos.get("Disponible")));
            
            // Se necesita el DeporteDAO para asociar el deporte completo
            return cancha;
        } catch (IOException e) {
            System.err.println("Error leyendo cancha: " + e.getMessage());
            return null;
        }
    }
    
    public List<Cancha> listarCanchasDisponibles() {
        File[] archivos = new File(DIRECTORIO).listFiles();
        if (archivos == null) return Collections.emptyList();
        
        return Arrays.stream(archivos)
            .map(this::leerCanchaDesdeArchivo)
            .filter(Objects::nonNull)
            .filter(Cancha::isDisponibilidad)
            .collect(Collectors.toList());
    }
    
    private Cancha leerCanchaDesdeArchivo(File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            Map<String, String> datos = new HashMap<>();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ", 2);
                if (partes.length == 2) {
                    datos.put(partes[0].trim(), partes[1].trim());
                }
            }
            
            Cancha cancha = new Cancha();
            cancha.setIdCancha(datos.get("ID"));
            cancha.setNombre(datos.get("Nombre"));
            cancha.setTipoCancha(datos.get("Tipo"));
            cancha.setCapacidad(Integer.parseInt(datos.get("Capacidad")));
            cancha.setDisponibilidad(Boolean.parseBoolean(datos.get("Disponible")));
            
            return cancha;
        } catch (IOException e) {
            System.err.println("Error leyendo cancha: " + e.getMessage());
            return null;
        }
    }
}