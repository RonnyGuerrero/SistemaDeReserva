/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
import Model.*;

import Model.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elise
 */
public class BienestarInterfaz extends javax.swing.JFrame {
      private List<Evento> listaEventos;
      private Evento eventoSeleccionado;
      private static final String DIRECTORIO_EVENTOS = "C:/Users/elise/OneDrive/Desktop/reservas/";
    /**
     * Creates new form Bienestar
     */
    public BienestarInterfaz() {
        initComponents();
        setLocationRelativeTo(null);
        listaEventos = new ArrayList<>();
        cargarEventosDesdeArchivo();
    }
    public void cargarEventosDesdeArchivo(){
        File directorio = new File(DIRECTORIO_EVENTOS);
        if (!directorio.exists()){
            directorio.mkdirs();
            return;
        }
        
        File[] archivos = directorio.listFiles((dir, name) -> name.endsWith(".txt"));
        if (archivos == null) return;
        
        DefaultTableModel model = (DefaultTableModel) tablaBienestar.getModel();
        
        model.setRowCount(0);
        listaEventos.clear();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        for (File archivo : archivos) {
            try (BufferedReader leer = new BufferedReader(new FileReader(archivo))) {
                
                String linea;
                Evento evento = new Evento();
                
                
                while ((linea = leer.readLine()) != null) {
                    if (linea.startsWith("NOMBRE_EVENTO: ")){
                        evento.setNombreEvento(linea.substring("NOMBRE_EVENTO:".length()));
                        
                    }else if (linea.startsWith("DESCRIPCION: ")) {
                        evento.setDescripcion(linea.substring("DESCRIPCION: ".length()));
                        
                      
                    }else if (linea.startsWith("FECHA_INICIO: ")) {
                        evento.setFechaInicio(sdf.parse(linea.substring("FECHA_INICIO: ".length())));
                        
                        
                    }else if (linea.startsWith("FECHA_CREADA: ")) {
                        evento.setFechaCreada(linea.substring("FECHA_CREADA: ".length()));
                        
                    }else if (linea.startsWith("ESTADO: ")) {
                        evento.setEstado(linea.substring("ESTADO: ".length()));
                    } else if (linea.startsWith("NOMBRE_USUARIO: ")) {
                        evento.setNombreuUsuario(linea.substring("NOMBRE_USUARIO: ".length()));
                    }
                }
                if (!evento.getNombreEvento().isEmpty()) {
                    
                    listaEventos.add(evento);
                    model.addRow(new Object[]{
                        listaEventos.size(),
                       evento.getNombreuUsuario(),
                        evento.getFechaCreada(),
                        sdf.format(evento.getFechaInicio()),
                        "",
                        evento.getNombreEvento(),
                        evento.getDescripcion(),
                        
                        
                    });
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error al leer archivo" +e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
        
    }
    
    private void actualizarEstadoEvento(){
        if (eventoSeleccionado == null) {
            JOptionPane.showMessageDialog(null, "Selecciones un evento "
                    + "primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String nuevoEvento = comboEstado.getSelectedItem().toString();
        
        if (nuevoEvento.equals("selecciona")) {
            JOptionPane.showMessageDialog(null, "Selecciones un estado valido",
                    "Adveertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        //actualizar estado
        eventoSeleccionado.setEstado(nuevoEvento);
        
        //actualizar el archivo del estado
        actualizarArchivosEvntos(eventoSeleccionado);
        
        //actualizar tabla
        DefaultTableModel model = (DefaultTableModel) tablaBienestar.getModel();
        int selectedRow  = tablaBienestar.getSelectedRow();
        if (selectedRow != -1){
            model.setValueAt(nuevoEvento, selectedRow, 7);
        }
        JOptionPane.showMessageDialog(null, "Estado del evento actualizado a:" +
                nuevoEvento, "Exito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void actualizarArchivosEvntos(Evento evento){
        String nombreArchivo = DIRECTORIO_EVENTOS + evento.getNombreEvento().replaceAll("[^a-zA-Z0-9]", 
                "") + ".txt";
        File archivo = new File(nombreArchivo);
        
        try (BufferedReader lea = new BufferedReader(new FileReader(archivo))){
            StringBuilder contenido = new StringBuilder();
            String linea;
            
            while ((linea = lea.readLine()) != null) {
                if (linea.startsWith("ESTADO: ")) {
                    contenido.append("ESTADO: ").append(evento.getEstado()).append("\n");
                    
                }else {
                    contenido.append(linea).append("\n");
                    
                }
            }
           
                
            
              try (FileWriter escritor = new FileWriter(archivo)) {
                  escritor.write(contenido.toString());
                  
              } 
             
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar el archivo"+ e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Detalles = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        txtIdEstado = new javax.swing.JTextField();
        txtNOmbreEstado = new javax.swing.JTextField();
        fechaCierreEstdo = new javax.swing.JTextField();
        txtfehcInicioEstado = new javax.swing.JTextField();
        txtEventoEstado = new javax.swing.JTextField();
        txtDescripcionEstado = new javax.swing.JTextField();
        txtFechaSolicitudEstado = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        comboEstado = new javax.swing.JComboBox<>();
        EstadoEvento = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBienestar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("DETALLES"));

        txtIdEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));

        txtNOmbreEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("NOMBRE"));

        fechaCierreEstdo.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE CIERRE"));

        txtfehcInicioEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE INICIO"));

        txtEventoEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("EVENTO"));

        txtDescripcionEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("DESCRIPCION"));

        txtFechaSolicitudEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE SOLICITUD"));

        jButton3.setText("ACEPTAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecciona", "pendiente", "aprobada", "rechazada", "cancelada", "finalizada" }));
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        EstadoEvento.setBorder(javax.swing.BorderFactory.createTitledBorder("ESTADO"));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDescripcionEstado)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaSolicitudEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                            .addComponent(fechaCierreEstdo)
                            .addComponent(txtIdEstado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtNOmbreEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtfehcInicioEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtEventoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(67, 67, 67))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(EstadoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNOmbreEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfehcInicioEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaSolicitudEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fechaCierreEstdo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEventoEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(txtDescripcionEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(EstadoEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout DetallesLayout = new javax.swing.GroupLayout(Detalles.getContentPane());
        Detalles.getContentPane().setLayout(DetallesLayout);
        DetallesLayout.setHorizontalGroup(
            DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DetallesLayout.setVerticalGroup(
            DetallesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaBienestar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NOMBRE", "FECHA DE SOLICITUD", "FECHA DE INICIO", "FECHA DE CIERRE", "EVENTO", "DESCRIPCION", "ESTADO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaBienestar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jButton1.setText("ELIMINAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("DETALLE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 539, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(526, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tablaBienestar.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Selecciones un evento "
                    + "primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
           return;
        }
        Evento evento = listaEventos.get(selectedRow);
        String nombreArchivo = DIRECTORIO_EVENTOS + evento.getNombreEvento().replaceAll("[^a-zA-Z0-9]", 
                "_") + ".txt";
        File archivo = new File(nombreArchivo);
        
        if (archivo.exists()){ 
           if (archivo.delete()){
               DefaultTableModel model = (DefaultTableModel) tablaBienestar.getModel();
               
               model.removeRow(selectedRow);
               listaEventos.remove(selectedRow);
               JOptionPane.showMessageDialog(null, "Evento eliminado correctamente",
                       "Evento", JOptionPane.INFORMATION_MESSAGE);
               
           }else {
               JOptionPane.showMessageDialog(null, "No se pudo eliminar "
                       + "el archivo del evento", "Error",JOptionPane.ERROR_MESSAGE);
                       
           }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int selectedRow = tablaBienestar.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Selecciones un evento primero", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        eventoSeleccionado = listaEventos.get(selectedRow);
        
        txtIdEstado.setText(String.valueOf(selectedRow + 1));
        txtNOmbreEstado.setText(eventoSeleccionado.getNombreEvento());
        txtfehcInicioEstado.setText(eventoSeleccionado.getFechaInicio() != null ? 
                new SimpleDateFormat("yyyy-MM-dd").format(eventoSeleccionado.getFechaInicio()) : "");
        txtFechaSolicitudEstado.setText(eventoSeleccionado.getFechaCreada());
        txtEventoEstado.setText("Evento");
        txtDescripcionEstado.setText(eventoSeleccionado.getDescripcion());
        
        comboEstado.setSelectedItem(eventoSeleccionado.getEstado());
        
                
        
        Detalles.setVisible(true);
        Detalles.setSize(590, 600);
        Detalles.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Detalles.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        actualizarEstadoEvento();
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BienestarInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BienestarInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BienestarInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BienestarInterfaz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BienestarInterfaz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Detalles;
    private javax.swing.JLabel EstadoEvento;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JTextField fechaCierreEstdo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaBienestar;
    private javax.swing.JTextField txtDescripcionEstado;
    private javax.swing.JTextField txtEventoEstado;
    private javax.swing.JTextField txtFechaSolicitudEstado;
    private javax.swing.JTextField txtIdEstado;
    private javax.swing.JTextField txtNOmbreEstado;
    private javax.swing.JTextField txtfehcInicioEstado;
    // End of variables declaration//GEN-END:variables
}
