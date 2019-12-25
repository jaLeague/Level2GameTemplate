import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class Screen3 extends Screen implements ActionListener, MouseListener {
    Font titleFont = new Font( "Courier New", Font.PLAIN, 48 );
    Level2GameTemplate game;
    
    Color bgColor;

    int alienX;
    int alienY;
    int newAlienX;
    int newAlienY;
    int alienWidth = 75;
    int alienHeight = 75;
    int alienSpeed = 10;
    
    Timer timer1;
    Timer timer2;
    long counter;

    public Screen3(Level2GameTemplate game) {
        super( game.frame );
        this.game = game;
        
        this.addMouseListener( this );
        
        bgColor = Color.WHITE;
        timer1 = new Timer( 1000, this );
        timer2 = new Timer( 1000 / 60, this );
        counter = 0;
    }

    public void setup() {
        game.frame.setTitle( "Switching background & mouse move Example" );
        bgColor = Color.WHITE;

        alienX = 100;
        alienY = 100;
        newAlienX = alienX;
        newAlienY = alienY;
        
        timer1.start();
        timer2.start();
        
        this.revalidate();
        this.repaint();
    }

    public void cleanUp() {
        super.cleanUp();
        timer1.stop();
        timer2.stop();
        counter = 0;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent( g );

        // Change the background color every 3 seconds
        if( counter != 0 ) {
            if( counter % 9 == 0 ) {
                bgColor = Color.CYAN;
            } else if( counter % 6 == 0 ) {
                bgColor = Color.ORANGE;
            } else if( counter % 3 == 0 ) {
                bgColor = Color.DARK_GRAY;
            }
        }
        g.setColor( bgColor );
        g.fillRect( 0, 0, Screen.WIDTH, Screen.HEIGHT );

        g.setColor( Color.BLACK );
        g.setFont( titleFont );
        g.drawString( "Screen 3", 150, 300 );

        g.setFont( new Font( "Arial", Font.PLAIN, 16 ) );
        g.drawString( "Click mouse to move", 70, 200 );
        
        g.setFont( new Font( "Helvetica", Font.BOLD | Font.ITALIC, 50 ) );
        g.drawString( "Counter: " + counter, 160, 400 );
        
        g.drawImage( alien, alienX, alienY, alienWidth, alienHeight,  null );
        
        // Could also use a mouse movement listener. This is an alternate way to
        // detect mouse position.
        Point p = MouseInfo.getPointerInfo().getLocation();
        g.drawLine( alienX + ( alienWidth / 2 ), alienY + ( alienHeight / 2 ), p.x, p.y );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if( source == timer1 ) {
            counter++;
            this.repaint();
        } else if( source == timer2 ) {
            int diffX = newAlienX - alienX;
            int diffY = newAlienY - alienY;
            
            if( Math.abs( diffX ) > alienSpeed || Math.abs( diffY ) > alienSpeed ) {
                double angleRad = Math.atan2(diffY, diffX);

                alienX += Math.cos( angleRad ) * alienSpeed;
                alienY += Math.sin( angleRad ) * alienSpeed;
            }
            
            this.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        Point p = MouseInfo.getPointerInfo().getLocation();
        newAlienX = p.x;
        newAlienY = p.y;
        
        System.out.println( "x: " + newAlienX + " y: " + newAlienY );
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
