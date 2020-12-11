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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Observable;


public class Client extends Observable implements Runnable {

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private boolean connected;
    private int port=1212;
    private String hostName="localhost";

    public Client() {
	connected = false;
    }

    public void connect(String hostName, int port) throws IOException {
        if(!connected)
        {
	    this.hostName = hostName;
            this.port = port;
            socket = new Socket(hostName,port);
            //get I/O from socket
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(),true);

            connected = true;
            //initiate reading from server...
            Thread t = new Thread(this);
            t.start(); //will call run method of this class
        }
    }

    public void sendMessage(String msg) throws IOException
    {
        if(connected) {
        pw.println(msg);
        } else throw new IOException("Not connected to server");
    }

    public void disconnect() {
        if(socket != null && connected){
            try {
                socket.close();
            }catch(IOException ioe) {
			//unable to close, nothing to do...
            }finally {
		this.connected = false;
            }
        }
    }

    public void run() {
	String msg = ""; //holds the msg recieved from server
        try {
            while(connected && (msg = br.readLine())!= null){
               System.out.println("Server:"+msg);
               //notify observers//
               this.setChanged();
//notify+send out recieved msg to Observers
               this.notifyObservers(msg);
            }
        }catch(IOException ioe) { }
        
        finally { connected = false; }
    }

    public boolean isConnected() {
	return connected;
    }


    public int getPort(){
        return port;
    }

    public void setPort(int port){
        this.port = port;
    }

    public String getHostName(){
        return hostName;
    }

    public void setHostName(String hostName){
        this.hostName = hostName;
    }
    
    
    public static void main(String[] argv)throws IOException {
        Client c = new Client();
        c.connect("localhost",1212);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String msg = "";
        while(!msg.equalsIgnoreCase("quit"))
        {
           msg = br.readLine();
           c.sendMessage(msg);
        }
        c.disconnect();
    }
}
