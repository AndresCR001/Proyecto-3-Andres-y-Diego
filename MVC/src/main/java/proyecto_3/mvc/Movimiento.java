
package proyecto_3.mvc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Xandr
 */
public class Movimiento implements Runnable{
    
    private boolean moverse;
    private JPanel screen;
    private JSONArray ActiveBtns;
    private JSONArray posicion;
    private int posX, posY;

    
    public Movimiento(Boolean moverse, JSONArray ActiveBtns,JSONObject Posicion){ // JPanel screen, <-- cambio //se puede agregar como parametro la lista de botones para saber a que lado moverse
        
        this.moverse = moverse;
        this.ActiveBtns = ActiveBtns;
        this.posicion = Posicion.getJSONArray("Jugador");
        this.posX = this.posicion.getInt(0);
        this.posY = this.posicion.getInt(1);
        System.out.println(ActiveBtns);
        System.out.println(posicion);
    }
    
    private void direccion(JSONArray ActiveBtns){
        
        int up = ActiveBtns.getInt(0);//ARRIBA
        int down = ActiveBtns.getInt(1);//ABAJO
        int left = ActiveBtns.getInt(2);//L
        int right = ActiveBtns.getInt(3);//R
        
        if (up == 1){this.posY+=1;}
        if (down == 1){this.posY-=1;}
        if (left == 1){this.posX-=1;}
        if (right == 1){this.posX+=1;}
        
        System.out.println(posX+"\n"+posY);
        
        
                
    }

    @Override
    public void run() {
        
        direccion(ActiveBtns); //le damos el valor segun el movimiento que haya realizado
      
        /*if (moverse){
            Component[] components =screen.getComponents(); //cualquier emergencia pasar esto a un ciclo y crear la lista manual (de no poder ser específico el boton) 
            for(Component component : components)
            {
                if(component instanceof JButton){

                    JButton button = (JButton) component;
                    button.setBackground(Color.blue);
            //si la bala le atina a algun enemigo o se sale del grid se elinimina 
            //setR1(false);
                }
            }
        }*/
        
    }
    
}
