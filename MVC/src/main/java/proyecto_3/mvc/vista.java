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
    public JSONObject updateScreenPixel= new JSONObject();
    public JSONObject SpawnsArray = new JSONObject();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();
    conexionSockets SQ = new conexionSockets();

    
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
    
    
        SQ.recibirJSON(1111);//1111
        setJSON(SQ.getJSON());
        SistemaVista();
    
    }
      
    
    public void printCourseDetails(boolean Iniciar, int pixeles, JSONObject updateScreenPixel,JSONObject SpawnsArray,JSONObject updateScreenScore){
          System.out.println("Configuracion del juego: ");
          System.out.println("Iniciar Juego: " + Iniciar);
          System.out.println("Cantidad de Pixeles: " + pixeles);
          System.out.println("CoordPixeles Iniciales: " + updateScreenPixel);
          System.out.println("CoordPixeles del Marcador: " + updateScreenScore);
          System.out.println("CoordSpawn de objetos: " + SpawnsArray);
       }
    
    
    public void SistemaVista(){//obtenemos los valores del json y los distribuimos por las variables de la vista
        //entramos al sistema vista para hacer set de los valores provenientes del socket PUERTO: 1111
       
        JSONObject JSON = getJSON();
        
        Boolean juego = JSON.getBoolean("Juego");
        int pixeles = JSON.getInt("Pixeles");
        JSONObject coordIniciales = JSON.getJSONObject("Coordenadas Iniciales");
        JSONObject coordSpaws = JSON.getJSONObject("Coordenadas de SPAWN");
        JSONArray ActButtons = JSON.getJSONArray("Lista de activacion de botones");
        JSONArray coordMarcador = JSON.getJSONArray("Coordenadas de Marcador");
        
        setIniciar(juego);
        setPixeles(pixeles);
        setUpdateScreenPixel(coordIniciales);
        setSpawnsArray(coordSpaws);
        setActiveButtons(ActButtons);
        setUpdateScreenScore(coordMarcador);
        
        if (isIniciar()){iniciarPantalla(isIniciar(), getUpdateScreenPixel(), getSpawnsArray(), getActiveButtons());}
       
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

    public JSONObject getUpdateScreenPixel() {
        return updateScreenPixel;
    }

    public void setUpdateScreenPixel(JSONObject updateScreenPixel) {
        this.updateScreenPixel = updateScreenPixel;
    }

    public JSONObject getSpawnsArray() {
        return SpawnsArray;
    }

    public void setSpawnsArray(JSONObject SpawnsArray) {
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
    
    public static void iniciarPantalla(boolean iniciar, JSONObject InitPixel,JSONObject Spawns ,JSONArray ActiveButtons)
    {
        if (iniciar)
        {
            JFrame Ventana = new JFrame("VideoJuego");
            //Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            Ventana.setLayout(new GridLayout(1, 1));
            Ventana.add(Pantalla(InitPixel, Spawns, ActiveButtons));

            Ventana.setLocationRelativeTo(null);
            Ventana.setPreferredSize(new Dimension(500, 500));
            Ventana.pack();
            Ventana.setVisible(true); 
        }
       
    }
    public static JPanel Pantalla(JSONObject InitPixel, JSONObject Spawns, JSONArray ActiveButtons){
        
        int up = ActiveButtons.getInt(0);//ARRIBA
        int down = ActiveButtons.getInt(1);//ABAJO
        int left = ActiveButtons.getInt(2);//L
        int right = ActiveButtons.getInt(3);//R
        
        JSONArray jugador = InitPixel.getJSONArray("jugador");
        int posX = jugador.getInt(1);
        int posY = jugador.getInt(0);
        
        Color cj;
        if("yellow".equals(jugador.getString(2))){
            cj = Color.yellow;
        }
        else{
            cj = Color.green;
        }
        
        if (up == 1){posY+=1;}
        if (down == 1){posY-=1;}
        if (left == 1){posX-=1;}
        if (right == 1){posX+=1;}
        
        
        
        JSONArray enemigo1 = Spawns.getJSONArray("enemigo1");
        int enemigo1_posX = enemigo1.getInt(1);
        int enemigo1_posY = enemigo1.getInt(0);
        
        Color c1;
        if("cyan".equals(enemigo1.getString(2))){
            c1 = Color.cyan;
        }
        else{
            c1 = Color.gray;
        }
        
        JSONArray enemigo2 = Spawns.getJSONArray("enemigo2");
        int enemigo2_posX = enemigo2.getInt(1);
        int enemigo2_posY = enemigo2.getInt(0);
        
        Color c2;
        if("red".equals(enemigo2.getString(2))){
            c2 = Color.red;
        }
        else{
            c2 = Color.gray;
        }
        
        JSONArray enemigo3 = Spawns.getJSONArray("enemigo3");
        int enemigo3_posX = enemigo3.getInt(1);
        int enemigo3_posY = enemigo3.getInt(0);
        
        Color c3;
        if("pink".equals(enemigo3.getString(2))){
            c3 = Color.pink;
        }
        else{
            c3 = Color.gray;
        }
        
        
        JPanel panelPantalla = new JPanel();
        String[][] pixeles = new String [51][51]; // indicamos el volumen y la posicion de los pixeles 
        for(int y=0; y < pixeles.length; y++) {
            for(int x=0; x < pixeles[y].length; x++) {
                
                final JButton jButton = new JButton(pixeles[y][x]);
                panelPantalla.add(jButton);
                
                if (x == posX && y == posY){jButton.setBackground(cj);
                
                }else if ((x == enemigo1_posX && y == enemigo1_posY)){jButton.setBackground(c1);
                
                }else if ((x == enemigo2_posX && y == enemigo2_posY)){jButton.setBackground(c2);
                
                }else if ((x == enemigo3_posX && y == enemigo3_posY)){jButton.setBackground(c3);
                
                }else{
                jButton.setBackground(Color.black);
               }
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


