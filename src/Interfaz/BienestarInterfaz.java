/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaz;
 import Model.*;
import Model.Evento;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
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
          setResizable(false);
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
                        
                     }else if (linea.startsWith("ESTADO: ")){
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
                        evento.getEstado()
                        
                        
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
        
        
        eventoSeleccionado.setEstado(nuevoEvento);
        
       
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
        txtFechaSolicitudEstado = new javax.swing.JTextField();
        comboEstado = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcionEstado = new javax.swing.JTextArea();
        btnPanelAtras = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnPanelAceptar = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaBienestar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        panelBoton = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelBoton2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        Detalles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DetallesMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DETALLES", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(0, 0, 0))); // NOI18N

        txtIdEstado.setEditable(false);
        txtIdEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("ID"));

        txtNOmbreEstado.setEditable(false);
        txtNOmbreEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("NOMBRE"));

        fechaCierreEstdo.setEditable(false);
        fechaCierreEstdo.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE CIERRE"));

        txtfehcInicioEstado.setEditable(false);
        txtfehcInicioEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE INICIO"));

        txtEventoEstado.setEditable(false);
        txtEventoEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("EVENTO"));

        txtFechaSolicitudEstado.setEditable(false);
        txtFechaSolicitudEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("FECHA DE SOLICITUD"));

        comboEstado.setBackground(new java.awt.Color(51, 51, 255));
        comboEstado.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        comboEstado.setForeground(new java.awt.Color(0, 0, 0));
        comboEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "selecciona", "pendiente", "aprobada", "rechazada", "cancelada", "finalizada" }));
        comboEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        comboEstado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comboEstadoMouseEntered(evt);
            }
        });
        comboEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstadoActionPerformed(evt);
            }
        });

        txtDescripcionEstado.setEditable(false);
        txtDescripcionEstado.setColumns(20);
        txtDescripcionEstado.setRows(5);
        txtDescripcionEstado.setBorder(javax.swing.BorderFactory.createTitledBorder("DESCRIPCION"));
        jScrollPane2.setViewportView(txtDescripcionEstado);

        btnPanelAtras.setBackground(new java.awt.Color(51, 51, 255));
        btnPanelAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPanelAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelAtrasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPanelAtrasMouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("ATRAS");

        javax.swing.GroupLayout btnPanelAtrasLayout = new javax.swing.GroupLayout(btnPanelAtras);
        btnPanelAtras.setLayout(btnPanelAtrasLayout);
        btnPanelAtrasLayout.setHorizontalGroup(
            btnPanelAtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnPanelAtrasLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(30, 30, 30))
        );
        btnPanelAtrasLayout.setVerticalGroup(
            btnPanelAtrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelAtrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnPanelAceptar.setBackground(new java.awt.Color(51, 51, 255));
        btnPanelAceptar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPanelAceptar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPanelAceptarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPanelAceptarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPanelAceptarMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("ACEPTAR");

        javax.swing.GroupLayout btnPanelAceptarLayout = new javax.swing.GroupLayout(btnPanelAceptar);
        btnPanelAceptar.setLayout(btnPanelAceptarLayout);
        btnPanelAceptarLayout.setHorizontalGroup(
            btnPanelAceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelAceptarLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel4)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        btnPanelAceptarLayout.setVerticalGroup(
            btnPanelAceptarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnPanelAceptarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("ESTADO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnPanelAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btnPanelAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
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
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
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
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnPanelAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPanelAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(62, 62, 62))
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

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        panelBoton.setBackground(new java.awt.Color(51, 51, 255));
        panelBoton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBoton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBotonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBotonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBotonMouseExited(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("DETALLES");

        javax.swing.GroupLayout panelBotonLayout = new javax.swing.GroupLayout(panelBoton);
        panelBoton.setLayout(panelBotonLayout);
        panelBotonLayout.setHorizontalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonLayout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(122, 122, 122))
        );
        panelBotonLayout.setVerticalGroup(
            panelBotonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBotonLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(26, 26, 26))
        );

        panelBoton2.setBackground(new java.awt.Color(51, 51, 255));
        panelBoton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBoton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelBoton2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelBoton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelBoton2MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ELIMINAR");

        javax.swing.GroupLayout panelBoton2Layout = new javax.swing.GroupLayout(panelBoton2);
        panelBoton2.setLayout(panelBoton2Layout);
        panelBoton2Layout.setHorizontalGroup(
            panelBoton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton2Layout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(101, 101, 101))
        );
        panelBoton2Layout.setVerticalGroup(
            panelBoton2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBoton2Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(27, 27, 27))
        );

        jPanel4.setBackground(new java.awt.Color(51, 51, 255));
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("GENERAR REPORTE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(65, 65, 65))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(29, 29, 29))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/inico (1).png"))); // NOI18N
        jLabel7.setToolTipText("INICIO");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/menu (1).jpg"))); // NOI18N
        jLabel8.setToolTipText("MENU");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(panelBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(panelBoton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(panelBoton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(panelBoton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
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

    private void comboEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEstadoActionPerformed

    private void panelBotonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotonMouseClicked
        // TODO add your handling code here:
       detalles();
    }//GEN-LAST:event_panelBotonMouseClicked

    private void panelBotonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotonMouseEntered
        // TODO add your handling code here:normal=[0,0,51] [0,0,204][0,51,204] al pasar mause=0,51,209
        Border bordeNormal =  BorderFactory.createLineBorder(new java.awt.Color(0,0,51), 2);
        panelBoton.setBackground(new java.awt.Color(0,51,209));
        panelBoton.setBorder(bordeNormal);

    }//GEN-LAST:event_panelBotonMouseEntered

    private void panelBotonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBotonMouseExited
        // TODO add your handling code here:
        Border borde = BorderFactory.createLineBorder(new java.awt.Color(51,51,255),1);
        panelBoton.setBorder(borde);
        panelBoton.setBackground(new java.awt.Color(51,51,255));
    }//GEN-LAST:event_panelBotonMouseExited

    private void panelBoton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton2MouseClicked
        // TODO add your handling code here:
        eliminar();
    }//GEN-LAST:event_panelBoton2MouseClicked

    private void panelBoton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton2MouseEntered
        // TODO add your handling code here:normal=[0,0,51] [0,0,204][0,51,204] al pasar mause=0,51,209
        Border nuevoBorte = BorderFactory.createLineBorder(new java.awt.Color(0,0,51),2);
        panelBoton2.setBackground(new java.awt.Color(0,51,209));
        panelBoton2.setBorder(nuevoBorte);
    }//GEN-LAST:event_panelBoton2MouseEntered

    private void panelBoton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelBoton2MouseExited
        // TODO add your handling code here:
        Border borde =BorderFactory.createLineBorder(new java.awt.Color(51,51,255),1);
        panelBoton2.setBorder(borde);
        panelBoton2.setBackground(new java.awt.Color(51,51,255));
    }//GEN-LAST:event_panelBoton2MouseExited

    private void btnPanelAtrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAtrasMouseClicked
        // TODO add your handling code here:
         Detalles.dispose();
    }//GEN-LAST:event_btnPanelAtrasMouseClicked

    private void btnPanelAceptarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAceptarMouseClicked
        // TODO add your handling code here:
        actualizarEstadoEvento();
    }//GEN-LAST:event_btnPanelAceptarMouseClicked

    private void comboEstadoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboEstadoMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_comboEstadoMouseEntered

    private void btnPanelAceptarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAceptarMouseEntered
        // TODO add your handling code here::normal=[0,0,51,5 al pasar mause=0,51,209
        Border bordeNormal = BorderFactory.createLineBorder(new java.awt.Color(0,0,51),2);
        btnPanelAceptar.setBorder(bordeNormal);
        btnPanelAceptar.setBackground(new java.awt.Color(0,51,209));
        
        
    }//GEN-LAST:event_btnPanelAceptarMouseEntered

    private void btnPanelAceptarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAceptarMouseExited
        // TODO add your handling code here:
         Border borde = BorderFactory.createLineBorder(new java.awt.Color(51,51,255),1);
        btnPanelAceptar.setBorder(borde);
        btnPanelAceptar.setBackground(new java.awt.Color(51,51,255));
    }//GEN-LAST:event_btnPanelAceptarMouseExited

    private void btnPanelAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAtrasMouseEntered
        // TODO add your handling code here:
         Border bordeNormal = BorderFactory.createLineBorder(new java.awt.Color(0,0,51),2);
        btnPanelAtras.setBorder(bordeNormal);
        btnPanelAtras.setBackground(new java.awt.Color(0,51,209));
    }//GEN-LAST:event_btnPanelAtrasMouseEntered

    private void btnPanelAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPanelAtrasMouseExited
        // TODO add your handling code here:
         Border borde = BorderFactory.createLineBorder(new java.awt.Color(51,51,255),1);
        btnPanelAtras.setBorder(borde);
        btnPanelAtras.setBackground(new java.awt.Color(51,51,255));
    }//GEN-LAST:event_btnPanelAtrasMouseExited

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
          try {
              // TODO add your handling code here:
              generarReporte();
          } catch (DocumentException ex) {
              Logger.getLogger(BienestarInterfaz.class.getName()).log(Level.SEVERE, null, ex);
          }
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
        // TODO add your handling code here:
       Border k = BorderFactory.createLineBorder(Color.WHITE);
        jPanel4.setBorder(k);
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
        // TODO add your handling code here:
        Border k = BorderFactory.createLineBorder(null);
        jPanel4.setBorder(k);
    }//GEN-LAST:event_jPanel4MouseExited

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        Login o = new Login();
        o.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        BienvenidoBienestar n = new BienvenidoBienestar();
        n.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void DetallesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DetallesMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_DetallesMouseClicked

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
    private javax.swing.JPanel btnPanelAceptar;
    private javax.swing.JPanel btnPanelAtras;
    private javax.swing.JComboBox<String> comboEstado;
    private javax.swing.JTextField fechaCierreEstdo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelBoton;
    private javax.swing.JPanel panelBoton2;
    private javax.swing.JTable tablaBienestar;
    private javax.swing.JTextArea txtDescripcionEstado;
    private javax.swing.JTextField txtEventoEstado;
    private javax.swing.JTextField txtFechaSolicitudEstado;
    private javax.swing.JTextField txtIdEstado;
    private javax.swing.JTextField txtNOmbreEstado;
    private javax.swing.JTextField txtfehcInicioEstado;
    // End of variables declaration//GEN-END:variables

   private void generarReporte() throws DocumentException {
         if (listaEventos.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No hay eventos registrados para generar el reporte", 
                "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
    }

   
    String directorioReportes = DIRECTORIO_EVENTOS + "reportes/";
    File carpetaReportes = new File(directorioReportes);
    if (!carpetaReportes.exists()) {
        carpetaReportes.mkdirs();
    }

    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
    String nombreArchivo = directorioReportes + "reporte_eventos_" + sdf.format(new Date()) + ".pdf";

    Document document = new Document();
    try {
        
        PdfWriter.getInstance(document, new FileOutputStream(nombreArchivo));
        document.open();

        
        Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLUE);
        Paragraph title = new Paragraph("Reporte de Eventos - Bienestar Universitario", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        
        Font dateFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
        Paragraph date = new Paragraph("Generado el: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), dateFont);
        date.setAlignment(Element.ALIGN_RIGHT);
        date.setSpacingAfter(20);
        document.add(date);

        
        PdfPTable table = new PdfPTable(7); 
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        
        String[] headers = {"ID", "Usuario", "Evento", "Fecha Solicitud", "Fecha Inicio", "Descripción", "Estado"};
        Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);

        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, headerFont));
            cell.setBackgroundColor(new BaseColor(0, 102, 204)); 
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);
        }

        
        Font dataFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
        SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
        int id = 1;

        for (Evento evento : listaEventos) {
            
            table.addCell(new Phrase(String.valueOf(id++), dataFont));
            
           
            table.addCell(new Phrase(evento.getNombreuUsuario() != null ? evento.getNombreuUsuario() : "", dataFont));
            
            
            table.addCell(new Phrase(evento.getNombreEvento(), dataFont));
            
           
            table.addCell(new Phrase(evento.getFechaCreada(), dataFont));
            
            
            String fechaInicio = evento.getFechaInicio() != null ? fechaFormat.format(evento.getFechaInicio()) : "";
            table.addCell(new Phrase(fechaInicio, dataFont));
            
           
            String descripcion = evento.getDescripcion() != null ? 
                (evento.getDescripcion().length() > 50 ? evento.getDescripcion().substring(0, 47) + "..." : evento.getDescripcion()) : "";
            table.addCell(new Phrase(descripcion, dataFont));
            
            
            PdfPCell estadoCell = new PdfPCell(new Phrase(evento.getEstado(), dataFont));
            estadoCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            
          
            switch(evento.getEstado().toLowerCase()) {
                case "aprobada":
                    estadoCell.setBackgroundColor(new BaseColor(144, 238, 144)); 
                    break;
                case "rechazada":
                    estadoCell.setBackgroundColor(new BaseColor(255, 102, 102)); 
                case "pendiente":
                    estadoCell.setBackgroundColor(new BaseColor(255, 255, 153));
                    break;
                default:
                    estadoCell.setBackgroundColor(BaseColor.WHITE);
            }
            
            table.addCell(estadoCell);
        }

        document.add(table);

        
        Paragraph total = new Paragraph("Total de eventos: " + listaEventos.size(), 
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK));
        total.setAlignment(Element.ALIGN_RIGHT);
        total.setSpacingBefore(10f);
        document.add(total);

        JOptionPane.showMessageDialog(null, "Reporte PDF generado exitosamente en:\n" + nombreArchivo, 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al generar el reporte PDF: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } finally {
        if (document != null && document.isOpen()) {
            document.close();
        }
    }
    }
   
   private void detalles(){
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
   }
   
   private void eliminar(){
       int selectedRow = tablaBienestar.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar",  "Advertencia",JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nombreEvento = tablaBienestar.getValueAt(selectedRow, 1).toString();
        String nombreArchivo = DIRECTORIO_EVENTOS + nombreEvento.replaceAll("[^a-zA-Z0-9]", "") + ".txt";

        File archivo = new File(nombreArchivo);
        if (archivo.exists()){
            archivo.delete();
        }
        DefaultTableModel model = (DefaultTableModel) tablaBienestar.getModel();
        model.removeRow(selectedRow);

   }
}
