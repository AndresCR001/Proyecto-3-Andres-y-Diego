
package proyecto_3.mvc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Xandr
 */
public class Movimiento implements Runnable{
    
    private boolean moverse;
    private JPanel screen;

    
    public Movimiento(Boolean moverse, JPanel screen){ //se puede agregar como parametro la lista de botones para saber a que lado moverse
        
        this.moverse = moverse;
        this.screen = screen;
    }

    @Override
    public void run() {
      
        if (moverse){
            Component[] components =screen.getComponents(); //cualquier emergencia pasar esto a un ciclo y crear la lista manual (de no poder er específico el boton) 
            for(Component component : components)
            {
                if(component instanceof JButton){

                    JButton button = (JButton) component;
                    button.setBackground(Color.blue);
            //si la bala le atina a algun enemigo o se sale del grid se elinimina 
            //setR1(false);
                }
            }
        }
        
    }
    
}
