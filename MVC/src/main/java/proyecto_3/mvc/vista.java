/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3.mvc;

import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class vista extends javax.swing.JFrame implements Runnable {
    
    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONArray updateScreenPixel= new JSONArray();
    public JSONArray SpawnsArray = new JSONArray();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 51));

        fondo.setBackground(new java.awt.Color(153, 153, 153));
        fondo.setFont(new java.awt.Font("Stencil", 2, 24)); // NOI18N
        fondo.setForeground(new java.awt.Color(255, 204, 0));
        fondo.setText("Loading...");
        fondo.setInheritsPopupMenu(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(fondo)
                .addContainerGap(203, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(150, 150, 150)
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        fondo.getAccessibleContext().setAccessibleParent(fondo);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public vista() {
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
    }   
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }
    @Override
    public void run() {
       
    System.out.println("Entra al run: Vista");


    try{
        ServerSocket servidor = new ServerSocket(1111); //servidor//indicamos que puerto utilizar (socket para vista-controlador)
        Socket misocket = servidor.accept();
        DataInputStream recibirJSON = new DataInputStream(misocket.getInputStream());
        System.out.println("Se acepto el servidor en el controlador");
        String entrada = recibirJSON.readUTF(); //guardamos los datos recibidos
        System.out.println("JSON entrada:" +entrada);
        JSONObject json = new JSONObject(entrada); //definimos la entrada como u
        setJSON(json);


        servidor.close();
        SistemaVista();
        
    }catch(IOException e){
        System.out.println("error_controlador: " + e);
    }   catch (ParseException ex) {
            Logger.getLogger(vista.class.getName()).log(Level.SEVERE, null, ex);
        };
    }
    
    public void printCourseDetails(boolean Iniciar, int pixeles, JSONArray updateScreenPixel,JSONArray SpawnsArray,JSONArray updateScreenScore){
          System.out.println("Configuracion del juego: ");
          System.out.println("Iniciar Juego: " + Iniciar);
          System.out.println("Cantidad de Pixeles: " + pixeles);
          System.out.println("CoordPixeles Iniciales: " + updateScreenPixel);
          System.out.println("CoordPixeles del Marcador: " + updateScreenScore);
          System.out.println("CoordSpawn de objetos: " + SpawnsArray);
       }
    
    
    public void SistemaVista() throws ParseException{//obtenemos los valores del json y los distribuimos por las variables de la vista
        //entramos al sistema vista para hacer set de los valores provenientes del socket PUERTO: 1111
       
        JSONObject JSON = getJSON();
        
        Boolean juego = JSON.getBoolean("Juego");
        int pixeles = JSON.getInt("Pixeles");
        JSONArray coordIniciales = JSON.getJSONArray("Coordenadas Iniciales");
        JSONArray coordSpaws = JSON.getJSONArray("Coordenadas de SPAWN");
        JSONArray ActButtons = JSON.getJSONArray("Lista de activacion de botones");
        JSONArray coordMarcador = JSON.getJSONArray("Coordenadas de Marcador");
        
        setIniciar(juego);
        setPixeles(pixeles);
        setUpdateScreenPixel(coordIniciales);
        setSpawnsArray(coordSpaws);
        setActiveButtons(ActButtons);
        setUpdateScreenScore(coordMarcador);
        
        if (isIniciar()){iniciarPantalla(isIniciar());}
   
    }
    
    public void setJSON(JSONObject JSON){
        this.JSON = JSON;
    }
    public JSONObject getJSON(){
      return JSON;
    }
    
    public boolean isIniciar() {
        return iniciar;
    }

    public void setIniciar(boolean iniciar) {
        this.iniciar = iniciar;
    }

    public int getPixeles() {
        return pixeles;
    }

    public void setPixeles(int pixeles) {
        this.pixeles = pixeles;
    }

    public JSONArray getUpdateScreenPixel() {
        return updateScreenPixel;
    }

    public void setUpdateScreenPixel(JSONArray updateScreenPixel) {
        this.updateScreenPixel = updateScreenPixel;
    }

    public JSONArray getSpawnsArray() {
        return SpawnsArray;
    }

    public void setSpawnsArray(JSONArray SpawnsArray) {
        this.SpawnsArray = SpawnsArray;
    }

    public JSONArray getUpdateScreenScore() {
        return updateScreenScore;
    }

    public void setUpdateScreenScore(JSONArray updateScreenScore) {
        this.updateScreenScore = updateScreenScore;
    }

    public JSONArray getActiveButtons() {
        return ActiveButtons;
    }

    public void setActiveButtons(JSONArray ActiveButtons) {
        this.ActiveButtons = ActiveButtons;
    }
    
    public static void iniciarPantalla(boolean iniciar)
    {
        if (iniciar)
        {
            JFrame Ventana = new JFrame("VideoJuego");
            //Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            Ventana.setLayout(new GridLayout(1, 1));
            Ventana.add(Pantalla());

            Ventana.setLocationRelativeTo(null);
            Ventana.setPreferredSize(new Dimension(500, 500));
            Ventana.pack();
            Ventana.setVisible(true); 
        }
       
    }
    public static JPanel Pantalla(){
        JPanel panelPantalla = new JPanel();
        String[][] pixeles = new String [51][51]; // indicamos el volumen y la posicion de los pixeles 
        for(int y=0; y < pixeles.length; y++) {
            for(int x=0; x < pixeles[y].length; x++) {
                
               
                final JButton jButton = new JButton(pixeles[y][x]);
                panelPantalla.add(jButton);
                jButton.setBackground(Color.BLACK);
               }
        }
        panelPantalla.setLayout(new GridLayout(51, 51));
        
        return panelPantalla;
       }
    
    
    
   /* 
    
   public String entrada;
    
    public vista() {
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
    }   
    public static void main(String args[]) {
  
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }
    @Override
    public void run() { 
        
    }
    
    public String getJSON()
    {
        try{
            
            ServerSocket servidor = new ServerSocket(1101); //servidor
            
            while(true){
                Socket misocket = servidor.accept();
                DataInputStream recibirJSON = new DataInputStream(misocket.getInputStream());
               
                String entrada1= recibirJSON.readUTF(); //guardamos los datos recibidos
                entrada = entrada1;
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return entrada;
    }
    
    public static void iniciarPantalla(boolean iniciar)
    {
        if (iniciar)
        {
            JFrame Ventana = new JFrame("VideoJuego");
            //Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            Ventana.setLayout(new GridLayout(1, 1));
            Ventana.add(Pantalla());

            Ventana.setLocationRelativeTo(null);
            Ventana.setPreferredSize(new Dimension(500, 500));
            Ventana.pack();
            Ventana.setVisible(true); 
        }
       
    }
    public static JPanel Pantalla(){
        JPanel panelPantalla = new JPanel();
        String[][] pixeles = new String [51][51]; // indicamos el volumen y la posicion de los pixeles 
        for(int y=0; y < pixeles.length; y++) {
            for(int x=0; x < pixeles[y].length; x++) {
                
               
                final JButton jButton = new JButton(pixeles[y][x]);
                panelPantalla.add(jButton);
                jButton.setBackground(Color.BLACK);
               }
        }
        panelPantalla.setLayout(new GridLayout(51, 51));
        
        return panelPantalla;
       }
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    // End of variables declaration//GEN-END:variables

    

    
}


