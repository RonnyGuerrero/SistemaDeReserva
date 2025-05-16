package DAO;

import Model.Reporte;
import Model.oConstantes.tipoReporte;
import java.io.*;
import java.text.*;
import java.util.*;

public class ReporteDAO {
    private static final String DIRECTORIO = "data/reportes/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    private static int contador = 1;
    
    public ReporteDAO() {
        new File(DIRECTORIO).mkdirs();
        // Inicializar contador con el máximo ID existente
        File[] archivos = new File(DIRECTORIO).listFiles();
        if (archivos != null && archivos.length > 0) {
            contador = Arrays.stream(archivos)
                .mapToInt(f -> {
                    try {
                        return Integer.parseInt(f.getName().split("_")[1].split("\\.")[0]);
                    } catch (Exception e) {
                        return 0;
                    }
                })
                .max()
                .orElse(0) + 1;
        }
    }
    
    public String generarReporte(Reporte reporte) {
        if (reporte == null) return null;
        
        String idReporte = "REP" + String.format("%04d", contador++);
        String archivo = DIRECTORIO + "reporte_" + idReporte + ".txt";
        
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("ID: " + idReporte);
            pw.println("Tipo: " + reporte.getTipo());
            pw.println("Fecha: " + DATE_FORMAT.format(reporte.getFechaGeneracion()));
            pw.println("Contenido: " + reporte.getContenido());
            return idReporte;
        } catch (IOException e) {
            System.err.println("Error generando reporte: " + e.getMessage());
            return null;
        }
    }
    
    public Reporte obtenerReporte(String idReporte) {
        String archivo = DIRECTORIO + "reporte_" + idReporte + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            Reporte reporte = new Reporte();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ", 2);
                if (partes.length == 2) {
                    switch (partes[0]) {
                        case "ID":
                            reporte.setIdReporte(partes[1]);
                            break;
                        case "Tipo":
                            reporte.setTipo(tipoReporte.valueOf(partes[1]));
                            break;
                        case "Fecha":
                            reporte.setFechaGeneracion(DATE_FORMAT.parse(partes[1]));
                            break;
                        case "Contenido":
                            reporte.setContenido(partes[1]);
                            break;
                    }
                }
            }
            
            return reporte;
        } catch (Exception e) {
            System.err.println("Error leyendo reporte: " + e.getMessage());
            return null;
        }
    }
}