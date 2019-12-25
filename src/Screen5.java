import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Screen5 extends Screen implements ActionListener {
    Font titleFont = new Font( "Courier New", Font.PLAIN, 48 );
    Level2GameTemplate game;
    
    int alienX;
    int alienY;
    int alienWidth = 75;
    int alienHeight = 75;
    
    Timer timer1;
    Timer timer2;
    
    int backgroundXPos;
    int backgroundSpeed = 1;
    ArrayList<BufferedImage> backgrounds;
    
    MouseAdapter mouseAdapter;

    public Screen5(Level2GameTemplate game) {
        super( game.frame );
        this.game = game;
        
        // Create a variable holding the new key listener and add to JPanel
        mouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                if( SwingUtilities.isLeftMouseButton( e ) ) {
                    backgroundSpeed++;
                } else if( SwingUtilities.isRightMouseButton(e) ) {
                    backgroundSpeed--;
                }
            }
        };
        
        this.addMouseListener( mouseAdapter );
        
        timer1 = new Timer( 1000 / 60, this );
        timer2 = new Timer( 2000, this );
        
        // Only 2 images needed--after one is off left side of screen, reset it.
        backgrounds = new ArrayList<BufferedImage>();
        backgrounds.add( background1 );
        backgrounds.add( background1 );
    }

    public void setup() {
        game.frame.setTitle( "Scrolling background" );

        alienX = 100;
        alienY = 450;
        
        timer1.start();
        timer2.start();
        backgroundXPos = 0;
        backgroundSpeed = 0;
        
        this.revalidate();
        this.repaint();
    }

    public void cleanUp() {
        super.cleanUp();
        timer1.stop();
        timer2.stop();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent( g );

        for( int i = 0; i < backgrounds.size(); i++ ) {
            BufferedImage image = backgrounds.get( i );
            int xPos = backgroundXPos + ( i * Screen.WIDTH );
            g.drawImage( image, xPos, 0, Screen.WIDTH, Screen.HEIGHT, null );
        }

        g.setColor( Color.BLACK );
        g.setFont( titleFont );
        g.drawString( "Screen 5", 150, 300 );
        
        g.setFont( new Font( "Courier New", Font.PLAIN, 32 ) );
        g.drawString( ( "background speed: " + backgroundSpeed ), 100, 350 );
        
        g.setFont( new Font( "Courier New", Font.PLAIN, 18 ) );
        g.drawString( ( "(left click increase, right click decrease)" ), 55, 390 );

        g.drawImage( alien, alienX, alienY, alienWidth, alienHeight, null );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if( source == timer1 ) {
            backgroundXPos -= backgroundSpeed;
            
            if( backgroundXPos < -Screen.WIDTH ) {
                backgroundXPos = 0;
            }
            
            this.repaint();
        } else if( source == timer2 ) {
            backgroundSpeed++;
        }
    }
}
