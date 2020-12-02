
package proyecto_3.mvc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Xandr
 */
public class controlador extends javax.swing.JFrame implements Runnable { 
    
    public String JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONArray updateScreenPixel= new JSONArray();
    public JSONArray SpawnsArray = new JSONArray();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();
    
    public controlador(){
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
    }
        
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new controlador().setVisible(true);
            }
        });
    }
    
    @Override
    public void run() {
        System.out.println("Entra al run: Controlador"); // recibimos los datos del puerto 1011 que proviene de modelo
       try{
            ServerSocket servidor = new ServerSocket(1011); //servidor//indicamos que puerto utilizar (socket para modelo-controlador)
            Socket misocket = servidor.accept();
            
            DataInputStream recibirJSON = new DataInputStream(misocket.getInputStream());
            System.out.println("Se acepto el servidor en el controlador");
            String entrada = recibirJSON.readUTF(); //guardamos los datos recibidos
            System.out.println("JSON entrada:" +entrada);
            setJSON(entrada);
            
            
            servidor.close();
            
            
        }catch(IOException e){
            System.out.println("error_controlador: " + e);
        }
    }
    
    public void SistemaControlador(){
        System.out.println("entrando al sistema controlador");
        
        System.out.println("JSON: "+getJSON());
        JSONObject json = new JSONObject();
        
        //reformulamos el JSON
        json.put("Juego",BtoS(isIniciar()));//convertimos iniciar en un string para poder escibirlo en un JSON
        json.put("Pixles",getPixeles());
        json.put("Coordenadas Iniciales",getUpdateScreenPixel());// agregar lista de las SpawnsArray que se modifican por vista(juego)
        json.put("Coordenadas de SPAWN",getSpawnsArray());//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        json.put("Lista de activacion de botones ", getActiveButtons());
        json.put("Coordenadas de Marcador",getUpdateScreenScore());
                
                
        System.out.println("Enviando JSON desde SistemaControlador");
        setJSON(json.toString());//json debe ser de tipo Objeto, investigar como se realiza 
        System.out.println(json);
        
        //repartimos la informacion por medio de sockets
        enviarJSON(json); //enviamos el JSON al modelo para actualizar los datos
        enviarJSONVista(json);// enviamos el JSON a la vista para que este actualizada 
    }
    
    private void enviarJSON(JSONObject json) //--> modelo
    {
        try{
        //enviar constantemente el JSON

        Socket socket = new Socket("localhost",1001);//IP y puerto // puero 1001 para enviar de vuelta informacion
        DataOutputStream enviarJSON = new DataOutputStream(socket.getOutputStream()); 


        enviarJSON.writeUTF(json.toString());

        socket.close();
                
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    private void enviarJSONVista(JSONObject json) //--> Vista
    {
        try{
        //enviar constantemente el JSON

        Socket socket = new Socket("localhost",1111);//IP y puerto // puero 1001 para enviar de vuelta informacion
        DataOutputStream enviarJSON = new DataOutputStream(socket.getOutputStream()); 


        enviarJSON.writeUTF(json.toString());

        socket.close();
                
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    private String BtoS(Boolean iniciar){
        if(iniciar){
            return "true";
        }else{
            return "false";
        }
    }
       

    public String getJSON() {
        return JSON;
    }

    public void setJSON(String JSON) {
        this.JSON = JSON;
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

    //implements Runnable {
    public void setActiveButtons(JSONArray ActiveButtons) {
        this.ActiveButtons = ActiveButtons;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciar = new javax.swing.JButton();
        btnIzq = new javax.swing.JButton();
        btnArriba = new javax.swing.JButton();
        btnDerecha = new javax.swing.JButton();
        btnAbajo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnIniciar.setText("Iniciar juego");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnIzq.setText("Flecha Izquierda");
        btnIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzqActionPerformed(evt);
            }
        });

        btnArriba.setText("Flecha Arriba");
        btnArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArribaActionPerformed(evt);
            }
        });

        btnDerecha.setText("Flecha Derecha");
        btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerechaActionPerformed(evt);
            }
        });

        btnAbajo.setText("Flecha Abajo");
        btnAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnIniciar)
                .addGap(55, 55, 55)
                .addComponent(btnIzq)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDerecha)
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnArriba)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAbajo)
                        .addGap(114, 114, 114))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(btnArriba)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnIniciar)
                        .addGap(43, 43, 43)
                        .addComponent(btnAbajo)
                        .addGap(40, 40, 40))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIzq)
                            .addComponent(btnDerecha))
                        .addGap(91, 91, 91))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArribaActionPerformed
        //extraer el valor Y del JSON e incrementarlo en uno al presionar
        
        //realizar operecion para cambiar la posicion
        
        
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(1));//arriba
                btnLista.put(new Integer(0));//abajo
                btnLista.put(new Integer(0));//izquierda
                btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
    }//GEN-LAST:event_btnArribaActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        
        System.out.println("Conexion realizada");
        setIniciar(true);
        System.out.println("Se establecieron nuevos valores");


        SistemaControlador(); // se llama al sistema controlador para actualizar socketDATA
        System.out.println("Se envio el archivo de vuelta");
        
       
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzqActionPerformed
        //extraer el valor X del JSON y restarlo en uno al presionar
        
        
        
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(0));//arriba
                btnLista.put(new Integer(0));//abajo
                btnLista.put(new Integer(1));//izquierda
                btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
    }//GEN-LAST:event_btnIzqActionPerformed

    private void btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDerechaActionPerformed
        //extraer el valor X del JSON e incrementarlo en uno 
        
        
        
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(0));//arriba
                btnLista.put(new Integer(0));//abajo
                btnLista.put(new Integer(0));//izquierda
                btnLista.put(new Integer(1));//derecha
                
        setActiveButtons(btnLista);
    }//GEN-LAST:event_btnDerechaActionPerformed

    private void btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoActionPerformed
        //extraer el valor del JSON en Y y restarle uno.
        
        
        
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(0));//arriba
                btnLista.put(new Integer(1));//abajo
                btnLista.put(new Integer(0));//izquierda
                btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
    }//GEN-LAST:event_btnAbajoActionPerformed
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbajo;
    private javax.swing.JButton btnArriba;
    private javax.swing.JButton btnDerecha;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnIzq;
    // End of variables declaration//GEN-END:variables

    

    

    /* public String entrada;
   
    public controlador() 
    {
        initComponents();
        Thread hilo = new Thread(this);
        hilo.start();
    }
    
    @Override
    public void run() {
        try{
            
             
            ServerSocket servidor = new ServerSocket(1101); //servidor
            
            while(true){
                Socket misocket = servidor.accept();
                DataInputStream recibirJSON = new DataInputStream(misocket.getInputStream());
               
                String entrada = recibirJSON.readUTF(); //guardamos los datos recibidos
                getJSON(entrada);//enviamos el JSON al metodo getJSON 
            
           
                Socket socket = new Socket("localhost",1101);//IP y puerto
                DataOutputStream enviarJSON = new DataOutputStream(socket.getOutputStream()); 

                enviarJSON.writeUTF(getJSON(entrada));

                socket.close();
            
            }
            
        }catch(IOException e){
            System.out.print(e);
        }
        
    }
    
     public String getJSON(String entrada)
    {
        this.entrada = entrada;
        return this.entrada;
    }
*/
}

