package DAO;

import Model.Deporte;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class DeporteDAO {
    private static final String DIRECTORIO = "data/deportes/";
    private static final String ARCHIVO_MAESTRO = DIRECTORIO + "deportes.txt";
    
    public DeporteDAO() {
        new File(DIRECTORIO).mkdirs();
    }
    
    public boolean registrarDeporte(Deporte deporte) {
        if (deporte == null || deporte.getIdDeporte() == null) return false;
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_MAESTRO, true))) {
            pw.println(String.join("|", 
                deporte.getIdDeporte(),
                deporte.getNombreDeporte(),
                String.valueOf(deporte.getJugadoresRequeridos()),
                deporte.getReglas()
            ));
            return true;
        } catch (IOException e) {
            System.err.println("Error registrando deporte: " + e.getMessage());
            return false;
        }
    }
    
    public List<Deporte> listarTodosDeportes() {
        List<Deporte> deportes = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_MAESTRO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                if (partes.length >= 4) {
                    Deporte d = new Deporte();
                    d.setIdDeporte(partes[0]);
                    d.setNombreDeporte(partes[1]);
                    d.setJugadoresRequeridos(Integer.parseInt(partes[2]));
                    d.setReglas(partes[3]);
                    deportes.add(d);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo deportes: " + e.getMessage());
        }
        
        return deportes;
    }
    
    public Deporte obtenerDeporte(String idDeporte) {
        return listarTodosDeportes().stream()
            .filter(d -> d.getIdDeporte().equals(idDeporte))
            .findFirst()
            .orElse(null);
    }
}