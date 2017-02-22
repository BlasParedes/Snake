/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 *
 * @author jose.paredes.
 * @since 1.0
 * 
 * Esta es la clase principal del proyecto, en ella se establece ancho, alto y 
 * ubicacion en pantalla del frame principal del programa y se inicializa el panel
 * donde mostrará todo lo referente al juego.
 */
public class Snake extends JFrame{
    private Panel panel;
    private JMenuBar barraMenu;
    private JMenuItem JuegoNuevo;
    private JMenuItem Instrucciones;
    private JMenuItem creditos;
    private JMenuItem Salir;
    /**
     * Inicializa el frame principal del programa junto con todos sus propiedades,
     * constantes y objetos, este metodo utilizará los metodos heredados de la
     * clase <code>javax.swing.JFrame</code>.
     * <p></p>
     * Inicializa tambien al objeto de tipo <code>Panel</code> y lo agrega al
     * objeto creado de tipo panel.
     * 
     * @param widht  ancho del Frame mostrado en pantalla.
     * @param height alto del Frame mostrado en pantalla.
     * @param title  titulo que llevará el Frame.
     * @see   <code>javax.swing.JFrame</code> 
     */
    public Snake(int widht, int height, String title) {
        super(title);
        this.setSize(widht, height);
        this.setLayout(null);
        this.panel = new Panel(0,0,780,560);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.add(this.panel);
        InitMenuBar();
        this.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                panel.moverCabeza(e.getKeyCode()-36);
            }

            @Override
            public void keyReleased(KeyEvent e) {                
            }
        
        });
        this.setVisible(true);
        
    }
    /**
     * Inicializa y coloca la barra de menú y sus componentes en el Frame
     */
    public void InitMenuBar(){
        this.barraMenu= new JMenuBar();
        JMenu inicio = new JMenu("Inicio");
        this.JuegoNuevo = new JMenuItem("Juego Nuevo");
        JuegoNuevo.addActionListener(new botonesAction());
        inicio.add(JuegoNuevo);
        inicio.add(new JSeparator());
        this.creditos = new JMenuItem("Creditos");
        creditos.addActionListener(new botonesAction());
        inicio.add(creditos);
        this.Instrucciones = new JMenuItem("Instrucciones");
        Instrucciones.addActionListener(new botonesAction());
        inicio.add(Instrucciones);
        inicio.add(new JSeparator());
        this.Salir = new JMenuItem("Salir");
        Salir.addActionListener(new botonesAction());
        inicio.add(Salir);
        this.barraMenu.add(inicio);
        this.setJMenuBar(this.barraMenu);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Snake sn = new Snake(785,610,"Snake");
    }
    /**
     * Esta clase define el comportamiento de los botones del menú desplegable 
     * "inicio" en la barra de menu del frame.
     */
    private class botonesAction implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if( e.getSource() == JuegoNuevo )
                panel.Reiniciar();
            else if( e.getSource() == creditos)
                JOptionPane.showMessageDialog(rootPane,"Desarrollado pos José Blas Parededes Rojas v-26016615 para la unidad curricular \"Organización del Computador\" \n en la Universidad Nacional Experimental del Táchira (UNET)","Creditos",1);                
            else if( e.getSource() == Instrucciones )
                JOptionPane.showMessageDialog(rootPane, "Utilice las teclas de movimiento para que la serpiente se mueva en la direccion deseada\nLa serpiente muere si choca con los bordes o con ella misma","Instrucciones",1);
            else if( e.getSource() == Salir )                
                System.exit(0);
        }
    };
    
}
