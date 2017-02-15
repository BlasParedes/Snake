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
public class Fruta {
    private final int x;
    private final int y;
    private final int height;
    private final int width;
    private final ImageIcon Imagen;
    private Rectangle rentangulo;
    public Fruta(int x, int y, ImageIcon Imagen) {
        this.x = x;
        this.y = y;
        this.width = Imagen.getIconWidth();        
        this.height = Imagen.getIconHeight();
        this.Imagen = Imagen;
        this.rentangulo = new Rectangle(x,y,width,height);
    }

    public Fruta(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.Imagen = null;
        this.rentangulo = new Rectangle(x,y,width,height);
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

    public Rectangle getRentangulo() {
        return rentangulo;
    }
    
    
}
