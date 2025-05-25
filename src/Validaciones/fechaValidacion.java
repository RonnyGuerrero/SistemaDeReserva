/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Validaciones;

import java.util.Date;

public class fechaValidacion {
    
    public static boolean fechaNoAnteriorAHoy(Date fecha) {
        if (fecha == null) {
            return false;
        }
        
        Date hoy = new Date();
        hoy = eliminarHora(hoy);
        Date fechaComparar = eliminarHora(fecha);
        
        return !fechaComparar.before(hoy);
    }
    
    private static Date eliminarHora(Date fecha) {
        long tiempo = fecha.getTime();
        tiempo = tiempo / (1000 * 60 * 60 * 24); 
        tiempo = tiempo * (1000 * 60 * 60 * 24); 
        return new Date(tiempo);
    }
    
    public static boolean fechaEnRango(Date fecha, Date fechaMinima, Date fechaMaxima) {
        if (fecha == null) {
            return false;
        }
        
        Date fechaSinHora = eliminarHora(fecha);
        
        if (fechaMinima != null && fechaSinHora.before(eliminarHora(fechaMinima))) {
            return false;
        }
        
        if (fechaMaxima != null && fechaSinHora.after(eliminarHora(fechaMaxima))) {
            return false;
        }
        
        return true;
    }
}