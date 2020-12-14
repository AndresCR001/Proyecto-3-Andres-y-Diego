/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_3.mvc;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Xandr
 */
public class Disparo implements Runnable{
    
        public boolean disparar;
        public JPanel screen;



    
    public Disparo(boolean disparar, JPanel screen){
        
        this.disparar = disparar;
        this.screen = screen;
        //this.activarDisparo();
        
        
    }
    @Override
    public void run() {
        
        if(disparar){
            Component[] components =screen.getComponents(); //cualquier emergencia pasar esto a un ciclo y crear la lista manual (de no poder er específico el boton) 
            for(Component component : components)
            {
                if(component instanceof JButton){

                    JButton button = (JButton) component;
                    button.setBackground(Color.red);
            //si la bala le atina a algun enemigo o se sale del grid se elinimina 
            //setR1(false);
                }
            }
            
        }
    }
    
}
