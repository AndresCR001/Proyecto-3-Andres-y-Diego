/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3.mvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Xandr
 */

public class Servidor extends Observable implements Runnable{
    
    private int puerto;
    
    public Servidor(int puerto){
        this.puerto = puerto;
    }
    
    
    @Override
    public void run() {
        
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
       
        try{
        
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor Iniciado");
            
            while (true){
                
                sc = servidor.accept();
                
                System.out.println("Cliente Conectado");
                in = new DataInputStream(sc.getInputStream());
                //out = new DataOutputStream (sc.getOutputStream());
                
                String mensaje = in.readUTF();
                
                System.out.println(mensaje);
                
                //indicamos a los observadores el cambio realizado
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();
                
                sc.close();
                System.out.println("Cliente Desconectado");

            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    
    
   
}
