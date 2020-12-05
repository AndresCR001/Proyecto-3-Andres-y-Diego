
package proyecto_3.mvc;

import com.google.gson.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import org.json.*;


public class modelo extends javax.swing.JFrame implements Runnable {
    
    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONArray updateScreenPixel= new JSONArray();
    public JSONArray SpawnsArray = new JSONArray();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();
    
    public boolean crear = true;
    
    
    public static void main(String args[]){
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new modelo().setVisible(true);
            }
        });
       
    }
    public modelo(){
       Thread hilo = new Thread(this);
       hilo.start();
    }
    
    @Override
    public void run() {
        
        System.out.println("Entra al run: Modelo");
        try{
            System.out.println("Crear JSON");
            if (crear){crearJSON();crear=false;}// inicializa el primer envio de datos
            
            ServerSocket servidor = new ServerSocket(1001); //servidor
            Socket misocket = servidor.accept();
            DataInputStream recibirJSON = new DataInputStream(misocket.getInputStream());
            String entrada = recibirJSON.readUTF(); //guardamos los datos recibidos
            System.out.println(entrada);
            JSONObject json = new JSONObject(entrada);
            setJSON(json);
            setActualizar();
            
            servidor.close();
                
        }catch(IOException e){
            System.out.print("error"+e);
        }
    }
    
    public void setActualizar(){
        JSONObject JSON = getJSON();
        System.out.println("SetActualizar: " + getJSON());
        
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
        
        enviarJSONVista(JSON);
    }
    
    
    public void crearJSON()
    { //escribimos el JSON con todas las solicitudes
       
       //se declara el valor de la informacion para tener una estructura inicial
       //pasar esto a una funcion aparte para actualizar los datos del modelo constantemente
        this.setIniciar(false);
        this.setPixeles(51);
        System.out.println("Faltan Updates");
        this.setUpdateScreenPixel(getScreen());
        this.setSpawnsArray(getSpawns());
        this.setActiveButtons(getButtons());
        this.setUpdateScreenScore(getScoreScreen());
        //-----------------------------------------------------------
        
       
        JSONObject Sistema = new JSONObject();//contiene todos los datos, tanto del Jugador 1 como el de los enemigos/consumibles
        
        Sistema.put("Juego",isIniciar());//valor true o false que proviene del btn iniciar juego
        Sistema.put("Pixeles",getPixeles());
        Sistema.put("Coordenadas Iniciales",getUpdateScreenPixel());// agregar lista de las SpawnsArray que se modifican por vista(juego)
        Sistema.put("Coordenadas de SPAWN",getSpawnsArray());//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        Sistema.put("Lista de activacion de botones", getActiveButtons());
        Sistema.put("Coordenadas de Marcador",getUpdateScreenScore());
        
        enviarJSON(Sistema);//se encarga de realizar la conexion y mandar el JSON
       
    }
    private void enviarJSON(JSONObject json) 
    {
        System.out.println("Enviando JSON");//se envia la informacion al controlador PUERTO: xxxx;
        try{
            //enviar constantemente el JSON
            
            Socket socket = new Socket("localhost",212);//IP y puerto//iniciamos el socket por donde nos comunicaremos con el controlador
            DataOutputStream enviarJSON = new DataOutputStream(socket.getOutputStream()); 
            enviarJSON.writeUTF(json.toString());

            socket.close();
            System.out.println("Se envio el siguiente JSON: "+json.toString());

        }catch(IOException e){
            System.out.println("Error(MODELO): " + e);
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
    
    public JSONObject getJSON() {
        return JSON;
    }

    public void setJSON(JSONObject JSON) {
        this.JSON = JSON;
    }
    public JSONArray getActiveButtons() {
        return ActiveButtons;
    }
    public void setActiveButtons(JSONArray ActiveButtons) {
        this.ActiveButtons = ActiveButtons;
    }

    
    public boolean isIniciar() {
        return iniciar;
    }
    public void setIniciar(boolean iniciar) {
        this.iniciar = iniciar;
    }
    
    

    public JSONArray getUpdateScreenPixel() {
        return updateScreenPixel;
    }
    public void setUpdateScreenPixel(JSONArray updateScreenPixel) {
        this.updateScreenPixel = updateScreenPixel;
    }
    
    

    public int getPixeles() {
        return pixeles;
    }
    public void setPixeles(int pixeles) {
        this.pixeles = pixeles;
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
    
    private JSONArray getScreen() {
         JSONArray lista= new JSONArray();
        //con este par de ciclos de for estamos indiccando de que color va aser la casilla
        /* vamos a implementar la sigiente lista
            1:amarillo
            2:azul
            3;blanco
            4:cyan
            5:gris
            6:lima
            7:magenta
            8:marron
            9:morado
            10:navy
            11:negro
            12:olive
            13:rojo
            14:silver
            15:teal
            16:verde
        
        */
        
        //pernsar par que usar esta lista 
        
        return lista;
    }

    private JSONArray getSpawns() {
        JSONArray lista= new JSONArray();
        //crear lista de las coordenadas de spawn
        
        lista.put(652);//personaje
        lista.put(3613);//enemigo
        lista.put(3713);//enemigo
        lista.put(3813);//enemigo
        
        return lista;
    }

    private JSONArray getScoreScreen() {
         JSONArray lista= new JSONArray();
         //crear lista de las coordenadas del score
         //definir los valores de las casillas que serán utilizadas para el marcador 
        
        return lista;
    }
    private JSONArray getButtons(){
        JSONArray lista = new JSONArray();
        
        lista.put(0);
        lista.put(0);
        lista.put(0);
        lista.put(0);
        
        return lista;
    }
}
    
    
    
    
    
       
    
    
    
    
   /* 
   public void modelo()
   {
       Thread hilo = new Thread(this);
       hilo.start();
   }
   public static void main(String args[]) 
   {
               System.out.println(setJSON());
   }
   public static String setJSON()
   { //escribimos el JSON con todas las solicitudes
        String mod;
        JSONObject Sistema = new JSONObject();//contiene todos los datos, tanto del Jugador 1 como el de los enemigos/consumibles
        
        Sistema.put("Juego",true);//valor true o false que proviene del btn iniciar juego
        
        Sistema.put("Pixles",new Integer(51));
        Sistema.put("Coordenadas",new Integer(2));// agregar lista de las SpawnsArray que se modifican por vista(juego)
        
        Sistema.put("Coordenadas de SPAWN",new Integer(2));//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        
        Sistema.put("Coordenadas de Marcador",new Integer(2));
        
        JSONArray Modelo =new JSONArray();
        Modelo.put(Sistema);
        
        mod=Modelo.toString();
        return mod ; 
   }
   public void enviarJSON()
   {
    try{
        //enviar constantemente el JSON

        Socket socket = new Socket("localhost",1101);//IP y puerto
        DataOutputStream enviarJSON = new DataOutputStream(socket.getOutputStream()); 


        enviarJSON.writeUTF(setJSON());

        socket.close();
                
        }catch(IOException e){
            System.out.println(e);
        }   
   }
   
    @Override
    public void run() 
    {
        while(true){
            enviarJSON();

        }
        
    }*/

    

    

