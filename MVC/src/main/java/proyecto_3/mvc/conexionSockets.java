
package proyecto_3.mvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.json.JSONObject;

/**
 *
 * @author Xandr
 */
public class conexionSockets {
    JSONObject json = new JSONObject();
    
  public void enviarJSON(JSONObject json,int puerto) //--> modelo
    {
        try{
        //enviar constantemente el JSON
        Socket socket = new Socket("localhost",puerto);//IP y puerto // puero 1001 para enviar de vuelta informacion
        DataOutputStream enviarJSON = new DataOutputStream(socket.getOutputStream()); 


        enviarJSON.writeUTF(json.toString());
        System.out.println("Enviando...");
        socket.close();
        
                
        }catch(IOException e){
            System.out.println(e);
        }
    } 
  
  public void recibirJSON(int puerto){
      
  
      try{
            ServerSocket servidor = new ServerSocket(puerto); //servidor//indicamos que puerto utilizar (socket para modelo-controlador)
                
                Socket misocket = servidor.accept();
            
            
                DataInputStream recibirJSON = new DataInputStream(misocket.getInputStream());
                String entrada = recibirJSON.readUTF(); //guardamos los datos recibidos
                System.out.println("JSON entrada :" +entrada);
                JSONObject json = new JSONObject(entrada); //definimos la entrada como un JSON
                setJSON(json);
                
            
        }catch(IOException e){
            System.out.println("error_controlador: " + e);
        }
      
  }
  private void setJSON(JSONObject json){
      this.json = json;
  }
  public JSONObject getJSON(){
      return json;
  }
  
  
}
