package DAO;

import Model.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.stream.*;

public class EventoDAO {
    private static final String DIRECTORIO = "C:/Users/Usuario/Desktop/proyectoDatos/Solicitudes/";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    
    
    public boolean crearEvento(Evento evento) {
        String archivo = DIRECTORIO + "evento_" + evento.getIdEvento() + ".txt";
        try (BufferedWriter pw = new BufferedWriter(new FileWriter(archivo))) {
            pw.write("ID: " + evento.getIdEvento());
            pw.write("Nombre: " + evento.getNombreEvento());
            pw.write("Descripcion: " + evento.getDescripcion());
            pw.write("Fecha Inicio: " + DATE_FORMAT.format(evento.getFechaInicio()));
            pw.write("Fecha Cierre: "+ DATE_FORMAT.format(evento.getFechaCierre()));
            pw.write("Participantes: " + evento.getParticipantes());
            pw.write("Organizador: " + evento.getOrganizador().getId());
            
            
            return true;
        } catch (IOException e) {
            System.err.println("Error creando evento: " + e.getMessage());
            return false;
        }
    }
    
   
    
    public Evento obtenerEvento(String idEvento) {
        String archivo = DIRECTORIO + "evento_" + idEvento + ".txt";
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            Map<String, String> datos = new HashMap<>();
            String linea;
            
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(": ", 2);
                if (partes.length == 2) {
                    datos.put(partes[0].trim(), partes[1].trim());
                }
            }
            
            Evento evento = new Evento();
            UsuarioDAO usuarioDAO =new UsuarioDAO();
            evento.setIdEvento(datos.get("ID"));
            evento.setNombreEvento(datos.get("Nombre"));
            evento.setDescripcion(datos.get("Descripcion"));
            evento.setFechaInicio(DATE_FORMAT.parse(datos.get("Fecha Inicio")));
            evento.setFechaInicio(DATE_FORMAT.parse(datos.get("Fecha Cierre")));
            evento.setParticipantes(datos.get("Participantes"));

            
            Usuario organizador = usuarioDAO.obtenerUsuarioPorId(datos.get("Organizador"));
            evento.setOrganizador(organizador);
            
            
            return evento;
        } catch (Exception e) {
            System.err.println("Error leyendo evento: " + e.getMessage());
            return null;
        }
    }
    
}