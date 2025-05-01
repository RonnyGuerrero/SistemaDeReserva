/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Actor;

import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Profesor extends Usuario{

    public String getProfesion() {
        return profesion;
    }

    public String getPrograma() {
        return programa;
    }

   

   
    private String profesion;
    private String programa;

    public Profesor(String profesion, String programa, String nombre, String apellido, String genero, String gmail, String numeroTelefonico, String id) {
        super(nombre, apellido, genero, gmail, numeroTelefonico, id);
        this.profesion = profesion;
        this.programa = programa;
    }
    @Override
    public List<String>
      obtenerCampos(){
          return Arrays.asList(nombre, apellido, genero, gmail, numeroTelefonico, id, profesion, programa);
      }
}
   
