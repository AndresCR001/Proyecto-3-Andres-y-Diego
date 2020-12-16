
package proyecto_3.mvc;

import java.util.Observable;
import java.util.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Xandr
 */
public class SpaceInvaders extends javax.swing.JFrame implements Observer{
    
    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONObject updateScreenPixel= new JSONObject();
    public JSONObject SpawnsArray = new JSONObject();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();
    public boolean move;
    private int posX, posY;

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
    public boolean crear = true;

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
    private JSONObject getScreen() {
        //con este par de ciclos de for estamos indiccando de que color va a ser la casilla
        /* vamos a implementar la sigiente lista
            1:amarillo
            2:azul
            3;blanco
            4:cyan
            5:gris
            6:lima
            7:magenta
            8:marron
            9:morado
            10:navy
            11:negro
            12:olive
            13:rojo
            14:silver
            15:teal
            16:verde
        
        */
        JSONObject XY_ = new JSONObject();
        
        JSONArray XY = new JSONArray();
        XY.put(45); //y
        XY.put(25); //x
        XY.put("green"); //color
        
        XY_.put("Jugador", XY);
        
        
        return XY_;
    }

    private JSONObject getSpawns() {
        //declaramos los valores de las coordenadas donde van a estar ubicados los enemigos
        
        JSONObject XY_ = new JSONObject();
        
        JSONArray coordX = new JSONArray(); //Coordenadas en X de los pixeles
        coordX.put(5);
        coordX.put(10);
        
        JSONArray coordY = new JSONArray(); //Coordenadas en Y
        coordY.put(5);
        coordY.put(25);
        
        JSONArray colores = new JSONArray();  //Colores de los pixeles
        colores.put("white");
        
        
        //color - enemigo 
        XY_.put("Coordenadas X", coordX);
        XY_.put("Coordenadas Y", coordY);
        XY_.put("Colores", colores);
        
        return XY_;
    }

    private JSONArray getScoreScreen() {
         JSONArray lista= new JSONArray();
         //crear lista de las coordenadas del score
         //definir los valores de las casillas que serán utilizadas para el marcador 
        
        return lista;
    }
    private JSONArray getButtons(){
        JSONArray lista = new JSONArray();
        
        lista.put(0);
        lista.put(0);
        lista.put(0);
        lista.put(0);
        
        return lista;
    }
    
    
    
    

    public SpaceInvaders() {
        initComponents();
        this.getRootPane().setDefaultButton(this.btnEnviar);
        if (crear){crearJSON();crear=false;}
        Servidor s = new Servidor(4000);
        s.addObserver(this);//indicamos que el frame va a ser un observador (al cual se le notifican cambios)
        Thread t = new Thread(s);
        t.start();
        
    }
    
    public void crearJSON()
    { //escribimos el JSON con todas las solicitudes
       
       //se declara el valor de la informacion para tener una estructura inicial
       //pasar esto a una funcion aparte para actualizar los datos del modelo constantemente
        this.setIniciar(false);
        this.setPixeles(51);
        this.setUpdateScreenPixel(getScreen());
        this.setSpawnsArray(getSpawns());
        this.setActiveButtons(getButtons());
        this.setMove(false);
        this.setUpdateScreenScore(getScoreScreen());
        this.setR1(false);
        //-----------------------------------------------------------
        
       
        JSONObject Sistema = new JSONObject();//contiene todos los datos, tanto del Jugador 1 como el de los enemigos/consumibles
        
        Sistema.put("Juego",isIniciar());//valor true o false que proviene del btn iniciar juego
        Sistema.put("Color Inicio", "gris");
        Sistema.put("Pixeles",getPixeles());
        Sistema.put("Coordenadas Iniciales",getUpdateScreenPixel());// agregar lista de las SpawnsArray que se modifican por vista(juego)
        Sistema.put("Coordenadas de SPAWN",getSpawnsArray());//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        Sistema.put("Lista de activacion de botones", getActiveButtons());
        Sistema.put("Moverse",isMove());
        Sistema.put("Coordenadas de Marcador",getUpdateScreenScore());
        Sistema.put("R1", isR1());
        
        setJSON(Sistema);
        
        String mensaje = getJSON().toString();
        Cliente c = new Cliente(4500,mensaje);
        Thread t = new Thread(c);
        t.start();
        Cliente c2 = new Cliente(4700,mensaje);
        Thread t2 = new Thread(c2);
        t2.start();
       
    }
    private void setActualizar(){
        JSONObject JSON = getJSON();
        
        //se definen los valores provenientes del JSON 
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
        setSpawnsArray(coordSpaws);
        setActiveButtons(ActButtons);
        setMove(move);
        setUpdateScreenScore(coordMarcador);
        setR1(R1);//asegurarse de no ocupar ser false
        
        if (isMove()){
            posX = getUpdateScreenPixel().getJSONArray("Jugador").getInt(0);
            posY = getUpdateScreenPixel().getJSONArray("Jugador").getInt(1);
            System.out.println("Lanzar Thread de movimiento");
            System.out.println(getActiveButtons() + "\n" + getUpdateScreenPixel());
            direccion(getActiveButtons());
            //Movimiento movimiento = new Movimiento(true, this.getActiveButtons(),this.getUpdateScreenPixel());
            //Thread m = new Thread(movimiento);
            //m.start();
        }
        
        JSONObject Sistema = new JSONObject();//contiene todos los datos, tanto del Jugador 1 como el de los enemigos/consumibles
        
        Sistema.put("Juego",isIniciar());//valor true o false que proviene del btn iniciar juego
        Sistema.put("Color Inicio", "gris");
        Sistema.put("Pixeles",getPixeles());
        Sistema.put("Coordenadas Iniciales",getUpdateScreenPixel());// agregar lista de las SpawnsArray que se modifican por vista(juego)
        System.out.println("Actualizado: " + getUpdateScreenPixel());
        Sistema.put("Coordenadas de SPAWN",getSpawnsArray());//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        Sistema.put("Lista de activacion de botones", getActiveButtons());
        Sistema.put("Moverse", isMove());
        Sistema.put("Coordenadas de Marcador",getUpdateScreenScore());
        Sistema.put("R1", isR1());
        
        setJSON(Sistema);
    }
    
    private void direccion(JSONArray ActiveBtns){
        
        int up = ActiveBtns.getInt(0);//ARRIBA
        int down = ActiveBtns.getInt(1);//ABAJO
        int left = ActiveBtns.getInt(2);//L
        int right = ActiveBtns.getInt(3);//R
        
        if (up == 1){posY+=1;}
        if (down == 1){posY-=1;}
        if (left == 1){posX-=1;}
        if (right == 1){posX+=1;}
        
        
        // establece las nuevas coordenadas  del jugador para enviarlas a la vista
        JSONArray coords = new JSONArray();
        coords.put(posX);
        coords.put(posY);
        coords.put(getUpdateScreenPixel().getJSONArray("Jugador").getString(2));
        
        JSONObject newPos = new JSONObject();
        newPos.put("Jugador", coords);
        
        //se establece el nuevo JSONObject
        setUpdateScreenPixel(newPos);
        
        System.out.println("Direccion: " + getUpdateScreenPixel());
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        btnEnviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMsj = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Modelo");

        txtTexto.setColumns(20);
        txtTexto.setRows(5);
        jScrollPane1.setViewportView(txtTexto);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(txtMsj);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEnviar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarActionPerformed
        
        //usar esto para notificar los cambios, cambiar el metodo y llamarlo en el Update
        
        this.txtTexto.append(getJSON().toString()+"\n");
        
        
        
    }//GEN-LAST:event_btnEnviarActionPerformed

    public static void main(String args[]) {
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SpaceInvaders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane txtMsj;
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        
        /*cuando se hace un cambio desde el controlador, se acciona este Update
        se notificará a la vista por medio del puerto 4700*/
        this.txtTexto.append((String) arg + "\n");
        
        JSONObject json = new JSONObject((String)arg);
        setJSON(json);
        
        //llamar a una funcion que me cambie los pixeles segun instrucciones
        //funcion ejemplo setActualizar();
        setActualizar();
        this.txtTexto.append("Se actualizo la vista" + "\n");
        //notificacion(envio del string=
        Cliente c = new Cliente(4700,getJSON().toString());
        Thread t = new Thread(c);
        t.start();
        
        Cliente c2 = new Cliente(4500,getJSON().toString());
        Thread t2 = new Thread(c2);
        t2.start();
        
    }
}
