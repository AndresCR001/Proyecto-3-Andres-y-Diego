
package proyecto_3.mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;


public class Vista_n extends javax.swing.JFrame implements Observer {
    
    JPanel panelPantalla = new JPanel();
    JButton[][] pixs = new JButton [51][51]; 

    public JButton[][] getPixs() {
        return pixs;
    }

    public void setPixs(JButton[][] pixs) {
        this.pixs = pixs;
    }
    
    

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

    public void setActiveButtons(JSONArray ActiveButtons) {
        this.ActiveButtons = ActiveButtons;
    }

    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONObject updateScreenPixel= new JSONObject();
    public JSONObject SpawnsArray = new JSONObject();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();
   
    
    public Vista_n() {
        initComponents();
        Servidor s = new Servidor(4700);
        s.addObserver(this);//indicamos que el frame va a ser un observador (al cual se le notifican cambios)
        Thread t = new Thread(s);
        t.start();
    }
    
    
    private void determinarJSON(){
        JSONObject JSON = getJSON();
        
        Boolean juego = JSON.getBoolean("Juego");
        int pixeles = JSON.getInt("Pixeles");
        JSONObject coordIniciales = JSON.getJSONObject("Coordenadas Iniciales");
        JSONObject coordSpaws = JSON.getJSONObject("Coordenadas de SPAWN");
        JSONArray ActButtons = JSON.getJSONArray("Lista de activacion de botones");
        JSONArray coordMarcador = JSON.getJSONArray("Coordenadas de Marcador");
        
        setIniciar(juego);
        setPixeles(pixeles);
        setUpdateScreenPixel(coordIniciales);
        setSpawnsArray(coordSpaws);
        setActiveButtons(ActButtons);
        setUpdateScreenScore(coordMarcador);
    }
    private void PantallaDesconectada(){
        
        this.setLayout(new GridLayout(1,1));
        // indicamos el volumen y la posicion de los pixeles 
        for(int y=0; y < 51; y++) {
            for(int x=0; x < 51; x++) {
            JButton jButton = new JButton();
                pixs[x][y] =  jButton;
                panelPantalla.add(jButton);


                jButton.setBackground(Color.black);
            }
        panelPantalla.setLayout(new GridLayout(51,51));
    }
        
        this.add(panelPantalla);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(500, 500));
        this.pack();
        this.setVisible(true);
        setPixs(pixs);
    }
    public void PantallaActiva(){
        JButton[][] pixeles = new JButton [51][51]; 
        if (isIniciar()){
        System.out.println("Updateeeee");}
        //seguir con la progra de I/O pixeles
            
        }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vista");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        new Vista_n().PantallaDesconectada();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista_n().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        
        //cada vez que se reciba un cambio en el modelo este se actualiza y me envia de vuelta las instrucciones necesarias 
        //llamar una funcion la cual me reciba el JSON (Object arg) y se actualice
        //funcion ejemplo setActualizar();
        System.out.println(arg.toString());
        
        JSONObject json = new JSONObject((String)arg);
        setJSON(json);
        determinarJSON();
        PantallaActiva();
        System.out.println("Update "+"\n");
        //this.txtTexto.append((String) );
    }
}
