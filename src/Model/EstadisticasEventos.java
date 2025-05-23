/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author elise
 */
public class EstadisticasEventos {
    public static void agregarEstadisticasAlreporte(Document documento, List<Evento> listaEventos)throws 
            DocumentException{
        if (listaEventos.isEmpty()) {
            return;
        }
        int totalEventos = listaEventos.size();
        int aprobados = 0;
        int rechazados = 0;
        int pendientes = 0;
        
        for (Evento evento : listaEventos) {
            switch (evento.getEstado().toLowerCase()) {
                case "aprobada":
                    aprobados++;
                    break;
                    
                case "rechazada":
                    rechazados++;
                    break;
                    
                case "pendiente":
                    pendientes++;
                    break;
            }
        }
        Paragraph estadisticas = new Paragraph();
        estadisticas.setSpacingBefore(20f);
        
        Font seccionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14,
                BaseColor.BLUE);
        Chunk titulo = new Chunk("Estadisticas de solicitudes\n",seccionFont);
        estadisticas.add(titulo);
        
        Font estarFont = FontFactory.getFont(FontFactory.HELVETICA, 12 , BaseColor.BLACK);
        estadisticas.add(new Chunk(String.format("Total de solicitudes: %s\n", totalEventos), estarFont));
        estadisticas.add(new Chunk(String.format("Aprobadas: %d (%.1f%%)\n", aprobados, (aprobados * 100.0 /
                totalEventos)), estarFont));
        estadisticas.add(new Chunk(String.format("Rechazadas: %d (%.1f%%)\n", rechazados,
                (rechazados * 100.0 / totalEventos)), estarFont));
        estadisticas.add(new Chunk(String.format("Pendiente: %d (%.1f%%) \n", pendientes,
                (pendientes * 100.0 / totalEventos)), estarFont));
        
        documento.add(estadisticas);
        
        agregarGraficaDePastel(documento, aprobados, rechazados, pendientes);
    }
        
        private static void agregarGraficaDePastel(Document documento, int aprobados, int rechazados, int pendientes)
             throws DocumentException{
            try {
                DefaultPieDataset pastel = new DefaultPieDataset();
                if (aprobados >0) pastel.setValue("Aprobadas", aprobados);
                if (rechazados >0) pastel.setValue("Rechazadas", rechazados);
                if (pendientes >0) pastel.setValue("Pendientes:", pendientes);
                
                JFreeChart chata = ChartFactory.createPieChart("Distribucion de estados de solicitudes",
                        pastel,
                        true,
                        true,
                        false);
                
                PiePlot pintar = (PiePlot) chata.getPlot();
                pintar.setSectionPaint("Aprobadas", new Color(144,238,144));
                pintar.setSectionPaint("Rechazadas", new Color(255,102,102));
                pintar.setSectionPaint("Pendientes", new Color(255,255,153));
                
                BufferedImage convetirAImagen = chata.createBufferedImage(500, 300);
                
                com.itextpdf.text.Image chartImage = com.itextpdf.text.Image.getInstance(convetirAImagen, null);
                
                chartImage.setAlignment(Element.ALIGN_CENTER);
                chartImage.setSpacingBefore(20f);
                chartImage.setSpacingAfter(20f);
                
                chartImage.scaleToFit(400, 250);
                
                documento.add(chartImage);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,"Error al generar el "
                        + "grafico:" +e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        
                e.printStackTrace();
            }
            
        } 
        
}



