/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3.mvc;

/**
 *
 * @author Xandr
 */
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Observable implements Runnable {
    private BufferedReader br;
    private PrintWriter pw;
    private Socket socket;
    private boolean running;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        running = false;
        //get I/O from socket
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);
            running = true; //set status
        }catch (IOException ioe) {
            throw ioe;
        }
    }

    public void stopClient()
    {
        try {
            this.socket.close();
        }catch(IOException ioe){ };
    }

    public void run(){
        String msg = ""; //will hold message sent from client
        try {//sent out initial welcome message etc. if required...
            
            this.pw.println("Welcome to Java based Server");
        
        
            while ((msg = br.readLine()) != null && running) {
                pw.println(msg); //echo msg back to client//
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            running = false;
        
        try {
            //it's time to close the socket
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Closing connection");

        //notify the observers for cleanup etc.
        this.setChanged();              //inherit from Observable
        this.notifyObservers(this);     //inherit from Observable
    }
}
