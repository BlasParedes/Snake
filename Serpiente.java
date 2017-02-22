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
 * Esta será la clase de donde serán instanciados todos los objetos que compondrán
 * la cabeza y el cuerpo de la serpiente, contiene atributos que serán necesarios
 * para el desarrollo del juego tales como las coordenadas xy, la imagen y el sentido
 * que llevará el movimiento del segmento de la serpiente.
 */
public class Serpiente {
    private int x;
    private int y;
    private final ImageIcon Imagen;
    private int sentido;
    private final Rectangle rentangulo;
    /**
     * Inicializa todos los atributos del segmento del cuerpo de la serpiente
     * tales como sus coordenadas xy, su imagen y el sentido que lleva su movimiento.
     * <p></p>
     * Este sentido estará definido como un parametro entero que tomará los sigientes
     * valores:
     *      1 - Izquierda
     *      2 - Arriba
     *      3 - Derecha
     *      4 - Abajo
     * Cualquier otro valor introducido que sea diferente de estos será considerado 
     * invalido y por lo tanto hará que se detenga el segmento de la serpiente.
     * <p></p>
     * Las dimensiones del segmento de la serpiente (ancho y alto) serán tomadas
     * directamente de la imagen introducida como un objeto de la clase <code>javax.swing.ImageIcon</code>.
     * <p></p>
     * El Rectangulo interno será usado para la verificacion de los choques con los
     * distintos objetos, tomará los valores de las coordenadas y las dimensiones
     * directamente de los del objeto y se actualizarán junto a ellos.
     * 
     * @param x coordenada X del segmento de la serpiente.
     * @param y coordenada Y del segmento de la serpiente.
     * @param Sentido sentido que llevará el movimiento del segmento de la serpiente.
     * @param Imagen imagen que llevará el segmento de la serpiente.
     */
    public Serpiente(int x, int y, int Sentido, ImageIcon Imagen) {
        this.x = x;
        this.y = y;
        this.sentido = Sentido;
        this.Imagen = Imagen;
        this.rentangulo = new Rectangle(x, y, Imagen.getIconWidth(), Imagen.getIconHeight());
    }

    /**
     * Retorna el rectangulo interno del segmento de la serpiente, esta funcion 
     * será usada para verificar el choque del segmento de la serpiente con otro 
     * objeto.
     * 
     * @return rectangulo interno del segmento de la serpiente.
     */
    public Rectangle getRentangulo() {
        return rentangulo;
    }
    
    /**
     * Retorna la coordenada X del segmento de la serpiente en el Panel. 
     * @return coordenada X del segmento de la serpiente en el Panel.
     */
    public int getX() {
        return x;
    }
    
    /**
     * Retorna la coordenada Y del segmento de la serpiente en el Panel.
     * @return coordenada Y del segmento de la serpiente en el Panel.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Retorna la altura del segmento de la serpiente.
     * @return altura del segmento de la serpiente.
     */
    public int getHeight() {
        return Imagen.getIconHeight();
    }

    /**
     * Retorna la anchura del segmento de la serpiente.
     * @return anchura del segmento de la serpiente.
     */
    public int getWidth() {
        return Imagen.getIconWidth();
    }

    /**
     * Retorna la imagen del segmento de la serpiente.
     * @return imagen del segmento de la serpiente.
     */
    public ImageIcon getImagen() {
        return Imagen;
    }
    /**
     * Actualiza la coordenada x del segmento de la serpiente, tambien se actualiza
     * la coordenada x del objeto rectangulo.
     * @param x nueva coordenada x.
     */
    public void setX(int x) {
        this.x = x;
        this.rentangulo.setBounds(x, y, Imagen.getIconWidth(), Imagen.getIconHeight());
    }
    /**
     * Actualiza la coordenada y del segmento de la serpiente, tambien se actualiza
     * la coordenada y del objeto rectangulo.
     * @param y nueva coordenada y.
     */
    public void setY(int y) {
        this.y = y;
        this.rentangulo.setBounds(x, y, Imagen.getIconWidth(), Imagen.getIconHeight());

    }
    /**
     * Retorna el sentido del movimiento que lleva el segmento de la serpiente.
     * @return sentido del movimiento del segmento de la serpiente.
     */
    public int getSentido() {
        return sentido;
    }

    /**
     * Actualiza el sentido en el que se moverá el segmento de la serpiente.
     * @param sentido nuevo sentido que tomará el segmento de la serpiente.
     */
    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    
}
