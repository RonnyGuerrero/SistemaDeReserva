package DAO;

import Model.*;
import Model.oConstantes.estadoReserva;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;

public class ReservarDAO {
    private static final String DIRECTORIO = "data/reservas/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd_HHmmss");
    
    private final CanchaDAO canchaDAO;
    private final UsuarioDAO usuarioDAO;
    
    public ReservarDAO() {
        new File(DIRECTORIO).mkdirs();
        this.canchaDAO = new CanchaDAO();
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public boolean crearReserva(reservarCancha reserva) {
        if (reserva == null || reserva.getIdReserva() == null) return false;
        
        String archivo = DIRECTORIO + "reserva_" + reserva.getIdReserva() + ".txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("ID: " + reserva.getIdReserva());
            pw.println("Cancha: " + reserva.getCancha().getIdCancha());
            pw.println("Usuario: " + reserva.getUsuario().getId());
            pw.println("Fecha: " + DATE_FORMAT.format(reserva.getFecha()));
            pw.println("Estado: " + reserva.getEstado());
            pw.println("Motivo: " + reserva.getMotivo());
            return true;
        } catch (IOException e) {
            System.err.println("Error creando reserva: " + e.getMessage());
            return false;
        }
    }
    
    public List<reservarCancha> obtenerReservasPorUsuario(String idUsuario) {
        File[] archivos = new File(DIRECTORIO).listFiles();
        if (archivos == null) return Collections.emptyList();
        
        return Arrays.stream(archivos)
            .map(this::leerReservaDesdeArchivo)
            .filter(Objects::nonNull)
            .filter(r -> r.getUsuario().getId().equals(idUsuario))
            .collect(Collectors.toList());
    }
    
    public List<reservarCancha> obtenerReservasPorCancha(String idCancha) {
        File[] archivos = new File(DIRECTORIO).listFiles();
        if (archivos == null) return Collections.emptyList();
        
        return Arrays.stream(archivos)
            .map(this::leerReservaDesdeArchivo)
            .filter(Objects::nonNull)
            .filter(r -> r.getCancha().getIdCancha().equals(idCancha))
            .collect(Collectors.toList());
    }
    
    private reservarCancha leerReservaDesdeArchivo(File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            Map<String, String> datos = new HashMap<>();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ", 2);
                if (partes.length == 2) {
                    datos.put(partes[0].trim(), partes[1].trim());
                }
            }
            
            reservarCancha reserva = new reservarCancha();
            reserva.setIdReserva(datos.get("ID"));
            
            Cancha cancha = canchaDAO.obtenerCancha(datos.get("Cancha"));
            reserva.setCancha(cancha);
            
            Usuario usuario = usuarioDAO.obtenerUsuarioPorId(datos.get("Usuario"));
            reserva.setUsuario(usuario);
            
            reserva.setFecha(DATE_FORMAT.parse(datos.get("Fecha")));
            reserva.setEstado(estadoReserva.valueOf(datos.get("Estado")));
            reserva.setMotivo(datos.get("Motivo"));
            
            return reserva;
        } catch (Exception e) {
            System.err.println("Error leyendo reserva: " + e.getMessage());
            return null;
        }
    }
}