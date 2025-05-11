/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actor;

import java.util.*;
/**
 *
 * @author Lenovo
 */
public class Bienestar extends Usuario{

    public String getDireccion() {
        return direccion;
    }

    public String getOficio() {
        return oficio;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setOficio(String oficio) {
        this.oficio = oficio;
    }
    private String direccion;
    private String oficio;

    public Bienestar(String direccion, String oficio, String nombre, String apellido, String genero, String gmail, String numeroTelefonico, String id) {
        super(nombre, apellido, genero, gmail, numeroTelefonico, id);
        this.direccion = direccion;
        this.oficio = oficio;
    }
    
    @Override
   public List<String>
   obtenerCampos(){
       return Arrays.asList(nombre, apellido, genero, gmail, numeroTelefonico, id,direccion, oficio) ;
   }        
    
}
    

