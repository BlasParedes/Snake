/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author jose.paredes
 */
public class Panel extends JPanel{
    private Serpiente cabeza;
    private ArrayList<Serpiente> cuerpo = new ArrayList<>(); 
    private final Timer movimiento_serpiente;
    private int width;
    private int height;
    private Fruta fruta;
    private int puntos;
    public Panel(int X,int Y,int Width,int Height) {
        super();
        this.height = Height;
        this.width = Width;
        this.setBounds(X, Y, Width, Height);
        this.setBackground(Color.lightGray);        
        this.puntos = 0;
        cabeza = new Serpiente( (this.width/2), (this.height/2), 0, new ImageIcon("src/imagenes/cabeza.png") );
        movimiento_serpiente = new Timer(60,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mover_cuerpo();
                switch(cabeza.getSentido()){
                    case 1:
                        cabeza.setX(cabeza.getX()-10);
                        break;
                    case 2:
                        cabeza.setY(cabeza.getY()-10);
                        break;
                    case 3:
                        cabeza.setX(cabeza.getX()+10); 
                        break;
                    case 4:
                        cabeza.setY(cabeza.getY()+10);
                        break;
                }
                repaint();
               if( cabeza.getRentangulo().intersectsLine(0, 0, width, 0) || 
                       cabeza.getRentangulo().intersectsLine(0, height, width, height) ||
                       cabeza.getRentangulo().intersectsLine(0, 0, 0, height) ||
                       cabeza.getRentangulo().intersectsLine(width, 0, width, height) ||
                       choca_cuerpo() ){
                    int result = JOptionPane.showConfirmDialog(null, "Usted perdió\nSu puntaje fue: "+puntos+"\nDesea volver a jugar?", "Fin del Juego", JOptionPane.YES_NO_OPTION);
                    if( result == JOptionPane.YES_OPTION){
                        reiniciar();
                    }else{
                        System.exit(0);
                    }
               }
               if( cabeza.getRentangulo().intersects(fruta.getRentangulo()) ){
                   puntos++;
                   cuerpo.add(new Serpiente(cabeza.getX(),cabeza.getY(),cabeza.getSentido(), new ImageIcon("src/imagenes/cuerpo.png") ) );
                   generar_pelota();
               }
            }
        });  
        generar_pelota();        
        this.movimiento_serpiente.start();
        this.setVisible(true);
        repaint();
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);        
        for (Serpiente seg : cuerpo) {
            g.drawImage(seg.getImagen().getImage(), seg.getX(), seg.getY(), null);
        }
        g.drawImage(cabeza.getImagen().getImage(), cabeza.getX(), cabeza.getY(), null);
        if( fruta != null){
            g.setColor(Color.red);
            g.drawImage(fruta.getImagen().getImage(),fruta.getX(), fruta.getY(), null);
        }
        g.drawString("Puntaje: " + puntos, 10, 10);
    }
    public void mover_Cabeza(int sentido){
        if( (sentido == 1 && cabeza.getSentido() != 3) ||
                (sentido == 3 && cabeza.getSentido() != 1) ||
                (sentido == 2 && cabeza.getSentido() != 4) ||
                (sentido == 4 && cabeza.getSentido() != 2) ){
        cabeza.setSentido(sentido);
        }
    }
    public void generar_pelota(){
        int x,y;
        do{
            x = (int)(Math.random()*(width-30)) + 1;
            y = (int)(Math.random()*(height-30)) + 1;
            fruta = new Fruta(x,y,new ImageIcon("src/imagenes/fruta.png") );
        }while(cabeza.getRentangulo().intersects(fruta.getRentangulo()) || x%10 != 0 || y%10 != 0);
    }
    public void mover_cuerpo(){
        for( int i=cuerpo.size()-1 ;i>=0 ;i-- ){
            if( i > 0 ){
                cuerpo.get(i).setX( cuerpo.get(i-1).getX() );
                cuerpo.get(i).setY( cuerpo.get(i-1).getY() );
            }else{                
                cuerpo.get(i).setX( cabeza.getX() );
                cuerpo.get(i).setY( cabeza.getY());
            }
        }
    }
    
    public boolean choca_cuerpo(){
        for(Serpiente seg : cuerpo)
            if( cabeza.getRentangulo().intersects(seg.getRentangulo()))
                return true;        
        return false;
    }
    
    public void reiniciar(){
        cuerpo.clear();
        cabeza = new Serpiente( (this.width/2), (this.height/2), 0, new ImageIcon("src/imagenes/cabeza.png") );
        puntos = 0;
        generar_pelota();
    }
}
