
package proyecto_3.mvc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;


public class Vista_n extends javax.swing.JFrame implements Observer {
    
    JPanel panelPantalla = new JPanel();
    JButton[][] pixs = new JButton [51][51]; 
    boolean coord = false;
    
    
    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONObject updateScreenPixel= new JSONObject();
    public JSONObject SpawnsArray = new JSONObject();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray(); 
    public boolean move;

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }
    public boolean R1;

    public boolean isR1() {
        return R1;
    }

    public void setR1(boolean R1) {
        this.R1 = R1;
    }

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
        Boolean move = JSON.getBoolean("Moverse");
        JSONArray coordMarcador = JSON.getJSONArray("Coordenadas de Marcador");
        Boolean R1 = JSON.getBoolean("R1");
        
        setIniciar(juego);
        setPixeles(pixeles);
        setUpdateScreenPixel(coordIniciales);
        System.out.println(coordIniciales); // --------------------
        setSpawnsArray(coordSpaws);
        setActiveButtons(ActButtons);
        setMove(move);
        setUpdateScreenScore(coordMarcador);
        setR1(R1);
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
        JSONObject JSON = getJSON();
        
        if (isIniciar()){
            
            System.out.println("Lanzar Thread de inicio");
            String color = JSON.getString("Color Inicio");
            Color clrJuego;
            if("gris".equals(color)){clrJuego = Color.lightGray;}
            else{clrJuego = Color.black;}
            Inicio inicio = new Inicio(this.panelPantalla, clrJuego);
            Thread i = new Thread(inicio);
            i.start();
            coord = true;
            
        }
        
        if (isR1()){
            System.out.println("Lanzar Thread de disparo");
            Disparo disparo = new Disparo(true,this.panelPantalla);
            Thread d = new Thread(disparo);
            d.start();
            
        }
        
        /*if (isMove()){
            System.out.println("Lanzar Thread de movimiento");
            System.out.println(getActiveButtons() + "\n" + getUpdateScreenPixel());
            Movimiento movimiento = new Movimiento(true, this.getActiveButtons(),this.getUpdateScreenPixel());
            Thread m = new Thread(movimiento);
            m.start();
        }*/
        
            
        }
    
    public void Coordenadas(){
        JSONObject JSON = getJSON();
        JSONObject iniciales = JSON.getJSONObject("Coordenadas Iniciales");
        String colorIni = JSON.getString("Color Inicio");
        JSONObject spawns = JSON.getJSONObject("Coordenadas de SPAWN");
        JSONArray jugador = iniciales.getJSONArray("Jugador");
        int posX = jugador.getInt(1);
        int posY = jugador.getInt(0);

        JSONArray coordX = spawns.getJSONArray("Coordenadas X");
        JSONArray coordY = spawns.getJSONArray("Coordenadas Y");
        JSONArray colores = spawns.getJSONArray("Colores");

        int cont = coordX.length() - 1;

        while(cont >= 0){
            int x = coordX.getInt(cont);
            int y = coordY.getInt(cont);
            String color2 = colores.getString(cont);
            Color clr;

            switch (color2) {
                case "cyan":
                    clr = Color.cyan;
                    break;
                case "orange":
                    clr = Color.orange;
                    break;
                case "red":
                    clr = Color.red;
                    break;
                case "pink":
                    clr = Color.pink;
                    break;
                case "black":
                    clr = Color.black;
                    break;
                default:
                    clr = Color.blue;
                    break;
            }

            pixs[y][x].setBackground(clr);
            cont -= 1;
        }
        
        Color cj;
        if("amarillo".equals(jugador.getString(2))){cj = Color.yellow;}
        else{cj = Color.green;}
        
        pixs[posY][posX].setBackground(cj);
        pixs[posY+1][posX+1].setBackground(cj);
        pixs[posY-1][posX-1].setBackground(cj);
        pixs[posY-1][posX+1].setBackground(cj);
        pixs[posY+1][posX-1].setBackground(cj);
        pixs[posY+1][posX].setBackground(cj);
        pixs[posY-1][posX].setBackground(cj);
        pixs[posY][posX+1].setBackground(cj);
        pixs[posY][posX-1].setBackground(cj);
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
                //new Vista_n().setVisible(true);
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
        
        JSONObject json = new JSONObject((String)arg);
        setJSON(json);
        determinarJSON();
        System.out.println(isR1());
        PantallaActiva();
        if (coord){
            Coordenadas();
        }
        
        
        System.out.println("Update "+"\n");
        //this.txtTexto.append((String) );
    }
}
