
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
    
    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONObject updateScreenPixel= new JSONObject();
    public JSONObject SpawnsArray = new JSONObject();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();
    
    conexionSockets SQ = new conexionSockets();
    
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
       SQ.recibirJSON(2121);
        
    }
    
    //{"Lista de activacion de botones":[],"Coordenadas Iniciales":[],"Juego":true,"Coordenadas de Marcador":[],"Pixeles":51,"Coordenadas de SPAWN":[]}
    public void SistemaControlador(){
        Thread hilo = new Thread(this);
        hilo.start();
        setJSON(SQ.getJSON());
        System.out.println("Entrando al sistema controlador");
        JSONObject entradaJSON = getJSON();
        
        
        int pixeles = entradaJSON.getInt("Pixeles");
        JSONObject coordIniciales = entradaJSON.getJSONObject("Coordenadas Iniciales");
        JSONObject coordSpaws = entradaJSON.getJSONObject("Coordenadas de SPAWN");
        JSONArray coordMarcador = entradaJSON.getJSONArray("Coordenadas de Marcador");
        
        
        setPixeles(pixeles);
        setUpdateScreenPixel(coordIniciales);
        setSpawnsArray(coordSpaws);
        setUpdateScreenScore(coordMarcador);
        
        JSONObject json = new JSONObject();
        
        //reformulamos el JSON
        json.put("Juego",isIniciar());//convertimos iniciar en un string para poder escibirlo en un JSON
        json.put("Pixeles",getPixeles());
        json.put("Coordenadas Iniciales",getUpdateScreenPixel());// agregar lista de las SpawnsArray que se modifican por vista(juego)
        json.put("Coordenadas de SPAWN",getSpawnsArray());//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        json.put("Lista de activacion de botones", getActiveButtons());
        json.put("Coordenadas de Marcador",getUpdateScreenScore());
                
        System.out.println("Enviar: " + json);
        setJSON(json);
        }
    
    
    
    /*private String BtoS(Boolean iniciar){
        if(iniciar){
            return "true";
        }else{
            return "false";
        }
    }*/
       
    public JSONObject getJSON() {
        return JSON;
    }

    public void setJSON(JSONObject JSON) {
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

        btnIniciar.setBackground(new java.awt.Color(0, 0, 0));
        btnIniciar.setFont(new java.awt.Font("Stencil", 2, 12)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(255, 204, 0));
        btnIniciar.setText("Iniciar juego");
        btnIniciar.setBorderPainted(false);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        btnIzq.setBackground(new java.awt.Color(0, 0, 0));
        btnIzq.setFont(new java.awt.Font("Stencil", 2, 24)); // NOI18N
        btnIzq.setForeground(new java.awt.Color(255, 204, 0));
        btnIzq.setText("<");
        btnIzq.setBorderPainted(false);
        btnIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzqActionPerformed(evt);
            }
        });

        btnArriba.setBackground(new java.awt.Color(0, 0, 0));
        btnArriba.setFont(new java.awt.Font("Stencil", 2, 24)); // NOI18N
        btnArriba.setForeground(new java.awt.Color(255, 204, 0));
        btnArriba.setText("^");
        btnArriba.setBorderPainted(false);
        btnArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArribaActionPerformed(evt);
            }
        });

        btnDerecha.setBackground(new java.awt.Color(0, 0, 0));
        btnDerecha.setFont(new java.awt.Font("Stencil", 2, 24)); // NOI18N
        btnDerecha.setForeground(new java.awt.Color(255, 204, 0));
        btnDerecha.setText(">");
        btnDerecha.setBorderPainted(false);
        btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerechaActionPerformed(evt);
            }
        });

        btnAbajo.setBackground(new java.awt.Color(0, 0, 0));
        btnAbajo.setFont(new java.awt.Font("Stencil", 2, 24)); // NOI18N
        btnAbajo.setForeground(new java.awt.Color(255, 204, 0));
        btnAbajo.setText("v");
        btnAbajo.setBorderPainted(false);
        btnAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnIniciar)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAbajo)
                            .addComponent(btnArriba))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnIzq)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(btnDerecha)
                        .addGap(36, 36, 36))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnArriba)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDerecha)
                    .addComponent(btnIzq))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbajo)
                .addContainerGap())
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
        SistemaControlador();
        //1001
        SQ.enviarJSON(getJSON(),1001); //enviamos el JSON al modelo para actualizar los datos
    
    }//GEN-LAST:event_btnArribaActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        
        JSONArray botonesIniciales = new JSONArray();
        botonesIniciales.put(0);
        botonesIniciales.put(0);
        botonesIniciales.put(0);
        botonesIniciales.put(0);
        
        setIniciar(true);
        setActiveButtons(botonesIniciales);

        SistemaControlador(); // se llama al sistema controlador para actualizar socketDATA
        System.out.println("Se envio el archivo de vuelta");
        //1001
        SQ.enviarJSON(getJSON(),1001); //enviamos el JSON al modelo para actualizar los datos
    
        
       
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzqActionPerformed
        //extraer el valor X del JSON y restarlo en uno al presionar
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(0));//arriba
                btnLista.put(new Integer(0));//abajo
                btnLista.put(new Integer(1));//izquierda
                btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
        SistemaControlador();
        //1001
        SQ.enviarJSON(getJSON(),1001); //enviamos el JSON al modelo para actualizar los datos
    

    }//GEN-LAST:event_btnIzqActionPerformed

    private void btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDerechaActionPerformed
        //extraer el valor X del JSON e incrementarlo en uno 
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(0));//arriba
                btnLista.put(new Integer(0));//abajo
                btnLista.put(new Integer(0));//izquierda
                btnLista.put(new Integer(1));//derecha
                
        setActiveButtons(btnLista);
        SistemaControlador();
        //1001
        SQ.enviarJSON(getJSON(),1001); //enviamos el JSON al modelo para actualizar los datos
    

    }//GEN-LAST:event_btnDerechaActionPerformed

    private void btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoActionPerformed
        //extraer el valor del JSON en Y y restarle uno.
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
                btnLista.put(new Integer(0));//arriba
                btnLista.put(new Integer(1));//abajo
                btnLista.put(new Integer(0));//izquierda
                btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
        SistemaControlador();
        //1001
        SQ.enviarJSON(getJSON(),1001); //enviamos el JSON al modelo para actualizar los datos
    

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

