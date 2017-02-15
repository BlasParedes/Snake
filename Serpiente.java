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
 */
public class Serpiente {
    /*Sentido: 0:detenido, 1:izquierda, 2: arriba, 3:derecha, 4:abajo */
    private int x;
    private int y;
    private final int height;
    private final int width;
    private ImageIcon Imagen;
    private int sentido;
    private Rectangle rentangulo;
    
    public Serpiente(int x, int y, int Sentido, ImageIcon Imagen) {
        this.x = x;
        this.y = y;
        this.width = Imagen.getIconWidth();        
        this.height = Imagen.getIconHeight();
        this.sentido = Sentido;
        this.Imagen = Imagen;
        this.rentangulo = new Rectangle(x, y, width, height);
    }

    public Serpiente(int x, int y, int Sentido, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.Imagen = null;        
        this.sentido = Sentido;
        this.rentangulo = new Rectangle(x, y, width, height);
    }

    public Rectangle getRentangulo() {
        return rentangulo;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ImageIcon getImagen() {
        return Imagen;
    }

    public void setX(int x) {
        this.x = x;
        this.rentangulo.setBounds(x, y, width, height);
    }

    public void setY(int y) {
        this.y = y;
        this.rentangulo.setBounds(x, y, width, height);

    }

    public int getSentido() {
        return sentido;
    }

    public void setSentido(int sentido) {
        this.sentido = sentido;
    }

    public void setImagen(ImageIcon Imagen) {
        this.Imagen = Imagen;
    }
    
}
