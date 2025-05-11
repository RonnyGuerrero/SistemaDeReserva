
package Registro;

import Actor.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Lenovo
 */
public class Registro {
    private String archivo;
    private Map<String, List<Usuario>> datos;
    private Map<String, Class<? extends Usuario >>  tipoClaseMap ;
    public Registro(String archivo, Map<String, List<Object>> datos) {
        this.archivo = archivo;
        this.datos = new HashMap<>();
        this.datos.put("ESTUDIANTE", new ArrayList<>());
        this.datos.put("PROFESOR", new ArrayList<>());
        this.datos.put("BIENESTAR", new ArrayList<>());
        
        this.tipoClaseMap = new HashMap<>();
       
        this.tipoClaseMap.put("ESTUDIANTE", Estudiante.class);
        this.tipoClaseMap.put("PROFESOR", Profesor.class);
        this.tipoClaseMap.put("BIENESTAR", Bienestar.class);
    }
    
    
    public void guardarDatos(String tipo, Usuario usuario) throws FileNotFoundException, IOException{
        try (BufferedWriter escritor = new BufferedWriter( new FileWriter(archivo, true))){
            List<String> campos = usuario.obtenerCampos();
            escritor.write(tipo + ",," + String.join(",", campos));
            escritor.newLine();
            
            datos.get(tipo).add(usuario);
            JOptionPane.showMessageDialog(null,tipo+"-Guardado exitosamente");
                    
        }catch (IOException e){
            JOptionPane.showMessageDialog(null,"ERROR AL GUARDAR"+tipo+ ": \n"+ e.getMessage());
        }
    }
    
    public void guadarDatos() 
        throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",", -1);
                String tipo = partes[0];

                Class<? extends Usuario> clase = tipoClaseMap.get(tipo);
                if (clase != null) {
                    String[] argumentos = Arrays.copyOfRange(partes, 2, partes.length);
                    try {
                        Usuario usuario = clase.getConstructor(Arrays.stream(argumentos)
                                .map(s -> String.class).toArray(Class[]::new))
                                .newInstance((Object[]) argumentos);

                        datos.get(tipo).add(usuario);
                        JOptionPane.showMessageDialog(null, "Usuario cargado: " + linea);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error al instanciar clase: " + e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR LOS DATOS: \n" + e.getMessage());
        }
    }

    public Map<String, List<Usuario>> getDatos() {
        return datos;
    }
         }
        
    
    
           
