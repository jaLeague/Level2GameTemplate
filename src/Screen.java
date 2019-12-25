
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Screen extends JPanel {
    static final int WIDTH = 600;
    static final int HEIGHT = 800;
    
    public BufferedImage spaceShip;
    public BufferedImage alien;
    public BufferedImage alienSmall;
    public BufferedImage infinityCur;
    public BufferedImage background1;
    
    JFrame frame;
    GridBagConstraints c;
    Color backgroundColor;
    Audio audio;
    Visual visual;
    
    public Screen( JFrame frame ) {
        this.frame = frame;
        
        visual = new Visual();
        c = new GridBagConstraints();
        
        this.setPreferredSize( new Dimension( Screen.WIDTH, Screen.HEIGHT ) );
        
        try {
            infinityCur = ImageIO.read(this.getClass().getResourceAsStream("infinity_gauntlet1.png"));
            spaceShip = ImageIO.read(this.getClass().getResourceAsStream("shipUp.png"));
            alien = ImageIO.read(this.getClass().getResourceAsStream("enemy.png"));
            alienSmall = ImageIO.read(this.getClass().getResourceAsStream("enemy_small.png"));
            background1 = ImageIO.read(this.getClass().getResourceAsStream("gamebackground.jpg"));
        } catch( IOException e ) {
            System.out.println( "ERROR: unable to load image files" );
        }
        
        // Default panel layout, can be changed
        this.setLayout( new GridBagLayout() );
        this.frame.pack();
    }
    
    public void setup() {
        this.revalidate();
        this.repaint();
        this.frame.pack();
    }
    
    public void cleanUp() {
        this.removeAll();
        this.frame.getContentPane().remove( this );
        this.frame.requestFocus();
    }
}
