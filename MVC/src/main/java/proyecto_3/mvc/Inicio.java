
package proyecto_3.mvc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Xandr
 */
public class Inicio implements Runnable {
    
    private JPanel screen;
    
    public Inicio(JPanel screen){
        
        this.screen = screen;
        
    }

    @Override
    public void run() {
        
        Component[] components =screen.getComponents(); //cualquier emergencia pasar esto a un ciclo y crear la lista manual (de no poder er específico el boton) 
        for(Component component : components)
        {
            if(component instanceof JButton){ //definir los colores según JSON

                JButton button = (JButton) component;
                button.setBackground(Color.yellow);
        //si la bala le atina a algun enemigo o se sale del grid se elinimina 
        //setR1(false);
            }
        }
    }
    
}
