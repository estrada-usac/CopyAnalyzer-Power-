/*
 *************** [Clase Editor] ***************
 * Esta clase tendrá la interfaz gráfica de   *
 * la aplicación que será una venta principal *
 * que consta de un editor de texto y el      *
 * funcionamiento que debe tener la aplicación*
 * ****************************************** *
 * Desarrollador Por: Erick Estrada Aroche    *
 * GIT: estrada-usac                          *
 * ****************************************** *
*/

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class Editor extends JFrame {

   // Declaraciones
   JPanel panel, panelConsola;
   int contTab; // Contador De Pestañas

   // Constructor
   public Editor() {

      // Crear Ventana
      super("FIUSAC Copy Analizer");
      super.setExtendedState(JFrame.MAXIMIZED_BOTH);
      super.setResizable(true);
      super.setVisible(true);
      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      contTab = 1;

      Interfaz();
   }

   public void Interfaz() {

      // Fuentes
      Font fuentePequena = new Font("", Font.BOLD, 20);

      // Crear Panel
      panel = new JPanel();
      panel.setLayout(null);
      panel.setBackground(Color.WHITE);
      super.add(panel);

      /* ------------------- Barra Menu ------------------- */
      JMenuBar barraMenu = new JMenuBar();
      panel.add(barraMenu);
      barraMenu.setBounds(0, 0, 1500, 25);

      // Pestañas De Barra Menu
      // Pestaña Archivo
      JMenu menuArchivo = new JMenu(" Archivo  ");
      barraMenu.add(menuArchivo);
      menuArchivo.setFont(fuentePequena);
      // SubMenu En Pestaña Menu
      JMenuItem subAbrir = new JMenuItem("Abrir");
      menuArchivo.add(subAbrir);
      subAbrir.setFont(fuentePequena);
      // SubMenu En Pestaña Menu
      JMenuItem subGuardar = new JMenuItem("Guardar");
      menuArchivo.add(subGuardar);
      subGuardar.setFont(fuentePequena);
      // SubMenu En Pestaña Menu
      JMenuItem subGuardarComo = new JMenuItem("Guardar Como");
      menuArchivo.add(subGuardarComo);
      subGuardarComo.setFont(fuentePequena);

      // Pestaña Pestañas
      JMenu menuPestanas = new JMenu(" Pestanas  ");
      barraMenu.add(menuPestanas);
      menuPestanas.setFont(fuentePequena);
      // SubMenu En Pestaña Pestanas
      JMenuItem subCrearPestana = new JMenuItem("Crear Pestana");
      menuPestanas.add(subCrearPestana);
      subCrearPestana.setFont(fuentePequena);
      // SubMenu En Pestaña Pestanas
      JMenuItem subEliminarPestana = new JMenuItem("Eliminar Pestana");
      menuPestanas.add(subEliminarPestana);
      subEliminarPestana.setFont(fuentePequena);

      // Pestaña Ejecutar
      JMenu menuEjecutar = new JMenu(" Ejecutar  ");
      barraMenu.add(menuEjecutar);
      menuEjecutar.setFont(fuentePequena);

      // Pestaña Reportes
      JMenu menuReportes = new JMenu(" Reportes  ");
      barraMenu.add(menuReportes);
      menuReportes.setFont(fuentePequena);
      // SubMenu En Pestaña Reportes
      JMenuItem subReporteErrores = new JMenuItem("Reporte De Errores");
      menuReportes.add(subReporteErrores);
      subReporteErrores.setFont(fuentePequena);
      // SubMenu En Pestaña Reportes
      JMenuItem subReporteEstadistico = new JMenuItem("Reporte Estadistico");
      menuReportes.add(subReporteEstadistico);
      subReporteEstadistico.setFont(fuentePequena);
      // SubMenu En Pestaña Reportes
      JMenuItem subReporteTokens = new JMenuItem("Reporte De Tokens");
      menuReportes.add(subReporteTokens);
      subReporteTokens.setFont(fuentePequena);
      // SubMenu En Pestaña Reportes
      JMenuItem subReporteJSON = new JMenuItem("Reporte JSON");
      menuReportes.add(subReporteJSON);
      subReporteJSON.setFont(fuentePequena);

      // Pestaña Ayuda
      JMenu menuAyuda = new JMenu(" Ayuda  ");
      barraMenu.add(menuAyuda);
      menuAyuda.setFont(fuentePequena);
      // SubMenu En Pestaña Ayuda
      JMenuItem subAcercaDe = new JMenuItem("Acerca De");
      menuAyuda.add(subAcercaDe);
      subAcercaDe.setFont(fuentePequena);

      /* ----------------- Fin Barra Menu ----------------- */


      /* ---------------- Editor De Texto  ---------------- */
      JTabbedPane pestanas = new JTabbedPane(); // Crear Menú De Pestañas
      pestanas.setBounds(30, 50, 800, 650);     // Establecer Posición Y Tamaño

      // Crear Pestaña Por Defecto
      JEditorPane editor1 = new JEditorPane();  // Editor 1
      editor1.setName("Tab 1");                  // Asignar Nombre A JEditorPane (Sirve Como ID)
      //JComponent component1 = editor1;          // Se Agrega Como Componente El JEditorPane
      pestanas.addTab("Tab 1", editor1); // Se Agrega Al Menú De Pestañas La Pestaña 1
      panel.add(pestanas);                      // Se Agrega Al Panel Principal El Menú De Pestañas


     // Botón Crear Pestaña De la Barra Menú
      subCrearPestana.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){

         contTab++;                                // Contador Para Nombre De Pestaña Y De Componente
         String nameTab = "Tab " + contTab;        // Nombre De Pestaña Y De Componente
         JEditorPane editor = new JEditorPane();   // Crear JEditorPane Para Editor
         editor.setName(nameTab);                  // Establecer Nombre A JEditorPane (Sirve Como ID)
         JComponent component = editor;            // Crear Componente Asignandole El JEditorPane
         pestanas.addTab(nameTab, component);      // Agregar Como Nueva Pestaña Nueva El Componente

      }
      });

      // Botón Abrir De La Barra Menú, Pestaña Archivo
      subAbrir.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent e){

         try {
            // Uso De JFileChooser Para Abrir Ventana Para Buscar Archivo
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(chooser);

            // Abrimos El Archivo Seleccionado En JFileChooser
            File archivo = chooser.getSelectedFile();

            // Recorremos El Archivo Para Mostrarlo En Editor
            String lineaLectura = "";
            if (archivo != null) {
               FileReader archivos = new FileReader(archivo);
               BufferedReader lee = new BufferedReader(archivos);
               while((lineaLectura=lee.readLine()) != null){
                  JEditorPane auxEditor = (JEditorPane)pestanas.getComponentAt(pestanas.getSelectedIndex());
                  auxEditor.setText(auxEditor.getText() + lineaLectura + "\n");

               }
               lee.close();

            }
         } catch(IOException ex) {

         }


         //FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo .fca, js", ".fca", ".js");
         //chooser.setFileFilter(filtro);


      }
      });

      /* -------------- Fin Editor De Texto --------------- */

      /* -------------------- Consola --------------------- */
      panelConsola = new JPanel();
      panelConsola.setLayout(null);
      panelConsola.setBackground(Color.BLACK);
      panelConsola.setBounds(850, 70, 490, 630);
      panel.add(panelConsola);

      /* ------------------ Fin Consola ------------------- */


   }

   public static void main(String[] args) {
      Editor nuevo = new Editor();
   }

}
