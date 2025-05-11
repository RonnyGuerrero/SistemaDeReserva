
package Actor;

import java.util.*;

import javax.swing.JOptionPane;




public class Estudiante extends Usuario{

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
    private String programa;

    public Estudiante(String programa, String nombre, String apellido, String genero, String gmail, String numeroTelefonico, String id) {
        super(nombre, apellido, genero, gmail, numeroTelefonico, id);
        this.programa = programa;
        
    }
    @Override
    public List<String>
     obtenerCampos(){
         return Arrays.asList(nombre, apellido, genero, gmail, numeroTelefonico, id, programa);
     }
     
 
   
}
