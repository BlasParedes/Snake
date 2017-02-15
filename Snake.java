/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 *
 * @author jose.paredes
 */
public class Snake extends JFrame{
    private Panel panel;
    public Snake(int widht, int height, String title) {
        super(title);
        this.setSize(widht, height);
        this.setLayout(null);
        this.panel = new Panel(0,0,widht-20,height-40);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(this.panel);
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                panel.mover_Cabeza(e.getKeyCode()-36);
            }

            @Override
            public void keyReleased(KeyEvent e) {                
            }
        
        });
        this.setVisible(true);
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Snake sn = new Snake(800,600,"Snake");
    }
    
}
