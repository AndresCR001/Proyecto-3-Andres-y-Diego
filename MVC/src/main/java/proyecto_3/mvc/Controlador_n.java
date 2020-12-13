
package proyecto_3.mvc;

import java.util.Observable;
import java.util.Observer;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Xandr
 */
public class Controlador_n extends javax.swing.JFrame implements Observer {
    
    public JSONObject JSON;
    public boolean iniciar;
    public int pixeles;
    public JSONObject updateScreenPixel= new JSONObject();
    public JSONObject SpawnsArray = new JSONObject();
    public JSONArray updateScreenScore = new JSONArray();
    public JSONArray ActiveButtons = new JSONArray();

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
    
    
    
    

    public Controlador_n() {
        initComponents();
        this.getRootPane().setDefaultButton(this.btnIzq);
        Servidor s = new Servidor(4500);
        s.addObserver(this);//indicamos que el frame va a ser un observador (al cual se le notifican cambios)
        Thread t = new Thread(s);
        t.start();
        
    }
    public void setActualizar(){
        
        JSONObject JSON = getJSON();
        
        int pixeles = JSON.getInt("Pixeles");
        JSONObject coordIniciales = JSON.getJSONObject("Coordenadas Iniciales");
        JSONObject coordSpaws = JSON.getJSONObject("Coordenadas de SPAWN");
        JSONArray coordMarcador = JSON.getJSONArray("Coordenadas de Marcador");
        
        setPixeles(pixeles);
        setUpdateScreenPixel(coordIniciales);
        setSpawnsArray(coordSpaws);
        setUpdateScreenScore(coordMarcador);
        
        JSONObject Sistema = new JSONObject();//contiene todos los datos, tanto del Jugador 1 como el de los enemigos/consumibles
        
        Sistema.put("Juego",isIniciar());//valor true o false que proviene del btn iniciar juego
        Sistema.put("Pixeles",getPixeles());
        Sistema.put("Coordenadas Iniciales",getUpdateScreenPixel());// agregar lista de las SpawnsArray que se modifican por vista(juego)
        Sistema.put("Coordenadas de SPAWN",getSpawnsArray());//agregar lista de SpawnsArray donde spawnean enemigos o consumibles dependiendo de los requerimientos del juego
        Sistema.put("Lista de activacion de botones", getActiveButtons());
        Sistema.put("Coordenadas de Marcador",getUpdateScreenScore());
        
        setJSON(Sistema);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIzq = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        btnArriba = new javax.swing.JButton();
        btnDerecha = new javax.swing.JButton();
        btnAbajo = new javax.swing.JButton();
        btnIniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controlador");

        btnIzq.setText("Izq");
        btnIzq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzqActionPerformed(evt);
            }
        });

        txtTexto.setColumns(20);
        txtTexto.setRows(5);
        jScrollPane1.setViewportView(txtTexto);

        btnArriba.setText("Up");
        btnArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArribaActionPerformed(evt);
            }
        });

        btnDerecha.setText("Der");
        btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDerechaActionPerformed(evt);
            }
        });

        btnAbajo.setText("Down");
        btnAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoActionPerformed(evt);
            }
        });

        btnIniciar.setText("Iniciar Juego");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnIzq)
                        .addGap(79, 79, 79)
                        .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbajo)
                            .addComponent(btnArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(67, 67, 67))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIzq, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDerecha, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(btnArriba, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(btnAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIzqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzqActionPerformed
        
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
        btnLista.put(new Integer(0));//arriba
        btnLista.put(new Integer(0));//abajo
        btnLista.put(new Integer(1));//izquierda
        btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
        setActualizar();
        this.txtTexto.append("btnIzq"+"\n");
        
        //enviamos las acciones al modelo
        Cliente c = new Cliente(4000,getJSON().toString());
        Thread t = new Thread(c);
        t.start();
        
    }//GEN-LAST:event_btnIzqActionPerformed

    private void btnArribaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArribaActionPerformed
        
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
        btnLista.put(new Integer(1));//arriba
        btnLista.put(new Integer(0));//abajo
        btnLista.put(new Integer(0));//izquierda
        btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
        setActualizar();
        this.txtTexto.append("btnArriba"+"\n");
        
        //enviamos las acciones al modelo
        Cliente c = new Cliente(4000,getJSON().toString());
        Thread t = new Thread(c);
        t.start();
    }//GEN-LAST:event_btnArribaActionPerformed

    private void btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoActionPerformed
        //extraer el valor del JSON en Y y restarle uno.
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
        btnLista.put(new Integer(0));//arriba
        btnLista.put(new Integer(1));//abajo
        btnLista.put(new Integer(0));//izquierda
        btnLista.put(new Integer(0));//derecha
                
        setActiveButtons(btnLista);
        setActualizar();
        this.txtTexto.append("btnAbajo"+"\n");
        
        //enviamos las acciones al modelo
        Cliente c = new Cliente(4000,getJSON().toString());
        Thread t = new Thread(c);
        t.start();
    }//GEN-LAST:event_btnAbajoActionPerformed

    private void btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDerechaActionPerformed
        //extraer el valor X del JSON e incrementarlo en uno 
        JSONArray btnLista = new JSONArray(); //arreglo para establecer los valores de los botones que estan siendo presionados
        btnLista.put(new Integer(0));//arriba
        btnLista.put(new Integer(0));//abajo
        btnLista.put(new Integer(0));//izquierda
        btnLista.put(new Integer(1));//derecha
                
        setActiveButtons(btnLista);
        setActualizar();
        this.txtTexto.append("btnDerecha"+"\n");
        
        //enviamos las acciones al modelo
        Cliente c = new Cliente(4000,getJSON().toString());
        Thread t = new Thread(c);
        t.start();
    }//GEN-LAST:event_btnDerechaActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        JSONArray botonesIniciales = new JSONArray();
        botonesIniciales.put(0);
        botonesIniciales.put(0);
        botonesIniciales.put(0);
        botonesIniciales.put(0);
        
        setIniciar(true);
        setActiveButtons(botonesIniciales);
        
        setActualizar();
        this.txtTexto.append("btnIniciar"+"\n");
        
        //enviamos las acciones al modelo
        Cliente c = new Cliente(4000,getJSON().toString());
        Thread t = new Thread(c);
        t.start();
    }//GEN-LAST:event_btnIniciarActionPerformed

    public static void main(String args[]) {
        
        
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Controlador_n().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbajo;
    private javax.swing.JButton btnArriba;
    private javax.swing.JButton btnDerecha;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnIzq;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        
        this.txtTexto.append((String) arg);
        //definimos el JSON y lo actualizamos en el controlador
        JSONObject json = new JSONObject((String)arg);
        setJSON(json);
        setActualizar();

        
    }
}
