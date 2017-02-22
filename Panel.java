/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
 * 
 * Esta clase contiene todos los elementos que componen al juego tales como
 * interface de usuario, personajes y sus componentes, timer de juego junto con
 * sus dimensiones.
 * <p></p>
 * Esta clase usa metodos heredados de la clase <code>javax.swing.JPanel</code>.
 * 
 * @see <code>javax.swing.JPanel</code>
 */
public class Panel extends JPanel{
    private Serpiente cabeza;
    private ArrayList<Serpiente> cuerpo = new ArrayList<>(); 
    private final Timer movimientoSerpiente;
    private int width;
    private int height;
    private Fruta fruta;
    private int puntos;
    /**
     * Constructor de la clase <code>Panel</code>, inicializa los elementos tales
     * como la cabeza y el <code>ArrayList</code> del cuerpo de la serpiente, 
     * posiciona la fruta, inicializa el timer del movimiento de la serpiente y
     * coloca en cero (0) el puntaje del jugador.
     * <p></p>
     * Esta clase utiliza metodos heredados de la clase <code>javax.swing.JFrame</code>.
     * 
     * @param X coordenada X del objeto Panel en el Frame
     * @param Y coordenada Y del objeto Panel en el Frame
     * @param Width Anchura del objeto Panel
     * @param Height Altura del objeto Panel 
     * 
     * @see <code>Serpiente</code> , <code>Fruta</code> , <code>Snake</code> , <code>javax.swing.JFrame</code> , <code>javax.swing.Timer</code> , <code>java.util.ArrayList</code> ,
     *      <code>java.awt.Graphics</code>
     */
    public Panel(int X,int Y,int Width,int Height) {
        super();
        this.height = Height;
        this.width = Width;
        this.setBounds(X, Y, Width, Height);
        this.setBackground(Color.lightGray);        
        this.puntos = 0;
        cabeza = new Serpiente( (this.width/2), (this.height/2), 0, new ImageIcon( getClass().getResource("/imagenes/cabeza.png") ) );
        movimientoSerpiente = new Timer(60,new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moverCuerpo();
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
               if( cabeza.getX() < 0 || cabeza.getY() < 0 || cabeza.getY() >  height-10 || cabeza.getX() > width-10 || chocaCuerpo() ){
                    int result = JOptionPane.showConfirmDialog(null, "Usted perdió\nSu puntaje fue: "+puntos+"\nDesea volver a jugar?", "Fin del Juego", JOptionPane.YES_NO_OPTION);
                    if( result == JOptionPane.YES_OPTION){
                        Reiniciar();
                    }else{
                        System.exit(0);
                    }
               }
               if( cabeza.getRentangulo().intersects(fruta.getRentangulo()) ){
                   puntos++;
                   cuerpo.add(new Serpiente(cabeza.getX(),cabeza.getY(),cabeza.getSentido(), new ImageIcon(getClass().getResource("/imagenes/cuerpo.png") ) ) );
                   generarFruta();
               }
            }
        });  
        generarFruta();        
        this.movimientoSerpiente.start();
        this.setVisible(true);
        repaint();
    }
    /**
     * Dibuja todos los elementos que conponen la interface grafica del juego,
     * primero cada parte del cuerpo, seguido de la cabeza, la fruta, y por
     * ultimo la puntuación del usuario.
     * 
     * @param g Objeto tipo <code>graphics</code> que sirve para dibujar los 
     *          elementos en pantalla.
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);        
        for (Serpiente seg : cuerpo) 
            g.drawImage(seg.getImagen().getImage(), seg.getX(), seg.getY(), null);
        g.drawImage(cabeza.getImagen().getImage(), cabeza.getX(), cabeza.getY(), null);
        g.drawImage(fruta.getImagen().getImage(),fruta.getX(), fruta.getY(), null);
        g.drawString("Puntaje: " + puntos, 10, 10);
    }
    /**
     * Determina el sentido que tomará la cabeza de la serpiente a partir de la
     * tecla oprimida por el usuario.
     * <p></p>
     * Esto solo puede ocurrir si no se cambia bruscamente de sentido, por ejemplo,
     * no se permite que la serpiente se mueva hacia la derecha si antes se estaba
     * moviendo a la izquierda o viceversa, lo mismo para el movimiento vertical.
     *  
     * @param sentido valor que determina la direccion del movimiento de la cabeza
     *                de la serpiente, solo puede tomar valores del 1 al 4.
     * 
     */
    public void moverCabeza(int sentido){
        if( (sentido == 1 && cabeza.getSentido() != 3) ||
                (sentido == 3 && cabeza.getSentido() != 1) ||
                (sentido == 2 && cabeza.getSentido() != 4) ||
                (sentido == 4 && cabeza.getSentido() != 2) ){
            cabeza.setSentido(sentido);
        }
    }
    /**
     * Genera una nueva fruta tomando posiciones 'X' y 'Y' aleatorias que estén
     * entre 10 hasta las dimensiones maximas del panel menos 20.
     * <p></p>
     * Las direcciones solo serán validas si son multiplos de 10 y estas no 
     * se generan sobre la cabeza o alguna parte del cuerpo de la serpiente.
     */
    public void generarFruta(){
        int x,y;
        ImageIcon image = new ImageIcon(getClass().getResource("/imagenes/fruta.png"));
        do{
            x = (int)(Math.random()*(width-20)) + 10;
            y = (int)(Math.random()*(height-20)) + 10;
        }while( !posicionValida(x, y, image.getIconWidth(), image.getIconHeight()) || x%10 != 0 || y%10 != 0);
        fruta = new Fruta(x, y, image);
    }
    /**
     * Mueve todas las partes del cuerpo de la serpiente a las posiciones que
     * tienen sus predecesoras, así la ultima posicion del cuerpo tomará los valores
     * 'X' y 'Y' de la penultima posicion y así sucesivamente.
     * <p></p>
     * En el caso del primer segmento este tomará la posicion 'X' y 'Y' de la 
     * cabeza.
     */
    public void moverCuerpo(){
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
    /**
     * Evalúa cada segmento del cuerpo de la serpiente revisando si este choca
     * con su cabeza.
     * 
     * @return true si la cabeza choca con alguna parte del cuerpo.
     */
    public boolean chocaCuerpo(){
        for(Serpiente seg : cuerpo)
            if( cabeza.getRentangulo().intersects(seg.getRentangulo()))
                return true;        
        return false;
    }
    /**
     * Coloca todos los elementos del juego tales como el cuerpo y la cabeza de
     * la serpiente y los puntos del jugador en sus valores iniciales.
     */
    public void Reiniciar(){
        cuerpo.clear();
        cabeza = new Serpiente((this.width/2), (this.height/2), 0, new ImageIcon("src/imagenes/cabeza.png"));
        puntos = 0;
        generarFruta();
    }
    
    /**
     * Verifica si la posicion dada es valida para colocar la fruta creando un 
     * rectangulo de prueba con las coordenadas y las dimensiones dadas como
     * parametros y comprobando cada una de las partes del cuerpo de la serpiente
     * y su cabeza verificando si alguna de estas chocan con el rectangulo de prueba.
     * 
     * @param x coordenada X de prueba
     * @param y coordenada Y de prueba
     * @param width anchura de la imagen de la fruta
     * @param height altura de la imagen de la fruta
     * @return true si el rectangulo de prueba no choca con la cabeza ni ninguna parte del cuerpo de
     *              la serpiente.
     */ 
    public boolean posicionValida(int x, int y, int width, int height){
        Rectangle rectanglePrueba = new Rectangle(x, y, width, height);
        if( cabeza.getRentangulo().intersects( rectanglePrueba ))
            return false;
        for( Serpiente seg : cuerpo)
            if( seg.getRentangulo().intersects(rectanglePrueba))
                return false;
        return true;
    }
}
