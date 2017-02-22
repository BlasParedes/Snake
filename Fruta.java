/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * 
 * @author jose.paredes
 * Esta será la clase de donde serán instanciados todos los objetos que 
 */
public class Fruta {
    private final int x;
    private final int y;
    private final ImageIcon Imagen;
    private Rectangle rentangulo;
    /**
     * Inicializa todos los atributos de la fruta tales como sus coordenadas xy y su imagen
     * <p></p>
     * Las dimensiones de la fruta (ancho y alto) serán tomadas directamente de 
     * la imagen introducida como un objeto de la clase <code>javax.swing.ImageIcon</code>.
     * <p></p>
     * El Rectangulo interno será usado para la verificacion de los choques con los
     * distintos objetos, tomará los valores de las coordenadas y las dimensiones
     * directamente de los del objeto y se actualizarán junto a ellos.
     * 
     * @param x coordenada X de la fruta.
     * @param y coordenada Y de la fruta.
     * @param Imagen imagen que llevará la fruta.
     */
    public Fruta(int x, int y, ImageIcon Imagen) {
        this.x = x;
        this.y = y;
        this.Imagen = Imagen;
        this.rentangulo = new Rectangle(x,y,Imagen.getIconWidth(),Imagen.getIconHeight());
    }
    /**
     * Retorna la coordenada X de la fruta.
     * @return coordenada X de la fruta.
     */
    public int getX() {
        return x;
    }
    /**
     * Retorna la coordenada Y de la fruta.
     * @return coordenada Y de la fruta.
     */
    public int getY() {
        return y;
    }
    /**
     * Retorna la altura de la fruta.
     * @return altura de la fruta.
     */
    public int getHeight() {
        return Imagen.getIconHeight();
    }
    /**
     * Retorna la anchura de la fruta.
     * @return anchura de la fruta.
     */
    public int getWidth() {
        return Imagen.getIconWidth();
    }
    /**
     * Retorna la Imagen de la fruta.
     * @return imagen de la fruta.
     */
    public ImageIcon getImagen() {
        return Imagen;
    }
    /**
     * Retorna el rectangulo interno de la fruta, esta funcion será usada para 
     * verificar el choque de la fruta con otro objeto.
     * @return rectangulo interno de la fruta
     */
    public Rectangle getRentangulo() {
        return rentangulo;
    }
    
    
}
