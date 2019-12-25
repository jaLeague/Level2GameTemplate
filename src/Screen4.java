import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.Timer;

@SuppressWarnings("serial")
public class Screen4 extends Screen implements ActionListener {
    Font titleFont = new Font( "Times New Roman", Font.PLAIN, 48 );
    Level2GameTemplate game;
    
    Timer timer;
    int fps = 60;
    String endGame = "Press 'Enter' to stop game";
    int endGameX = 100;
    
    public static final int ALIEN_IMAGE_WIDTH = 75;
    public static final int ALIEN_IMAGE_HEIGHT = 75;
    int spaceShipX = 100;
    int spaceShipY = 100;
    int spaceShipXSpeed = 0;
    int spaceShipYSpeed = 0;
    int alienX = 350;
    int alienY = 150;
    int alienWidth = ALIEN_IMAGE_WIDTH;
    int alienHeight = ALIEN_IMAGE_HEIGHT;
    int alienSpeed = 0;
    int alienRotationDeg = 0;
    int alienRotationSpeedDeg = 0;
    
    KeyAdapter keyAdapter;

    public Screen4(Level2GameTemplate game) {
        super( game.frame );
        this.game = game;

        timer = new Timer( 1000 / fps, this );
    }

    public void setup() {
        game.frame.setTitle( "Multiple controllable game objects" );

        // Reset initial positions
        spaceShipX = 100;
        spaceShipY = 100;
        alienX = 350;
        alienY = 150;
        
        // Create a variable holding the new key listener
        keyAdapter = new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                switch( keyCode ) {
                case KeyEvent.VK_UP:
                    spaceShipYSpeed = -5;
                    break;
                    
                case KeyEvent.VK_DOWN:
                    spaceShipYSpeed = 5;
                    break;
                    
                case KeyEvent.VK_LEFT:
                    spaceShipXSpeed = -5;
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    spaceShipXSpeed = 5;
                    break;
                    
                case KeyEvent.VK_E:
                    alienRotationSpeedDeg = -1;
                    break;
                    
                case KeyEvent.VK_R:
                    alienRotationSpeedDeg = 1;
                    break;
                    
                case KeyEvent.VK_SPACE:
                    alienSpeed = 5;
                    break;
                    
                default:
                    // Do nothing
                    break;
                }
            }
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                switch( keyCode ) {
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_RIGHT:
                    spaceShipXSpeed = 0;
                    spaceShipYSpeed = 0;
                    break;
                    
                case KeyEvent.VK_E:
                case KeyEvent.VK_R:
                    alienRotationSpeedDeg = 0;
                    break;
                    
                case KeyEvent.VK_SPACE:
                    alienSpeed = 0;
                    break;
                    
                case KeyEvent.VK_ENTER:
                    endGameX = 30;
                    endGame = "Press 'Enter' again to switch screens";
                    
                    // Add the original key listener back to the frame
                    game.frame.addKeyListener( game );
                    break;
                    
                default:
                    // Do nothing
                    break;
                }
            }
        };
        
        // Remove the original key listener and add the new one
        game.frame.removeKeyListener( game );
        game.frame.addKeyListener( keyAdapter );
        
        timer.start();

        this.revalidate();
        this.repaint();
    }

    public void cleanUp() {
        super.cleanUp();
        
        timer.stop();
        
        // Remove the listener from this JPanel
        game.frame.removeKeyListener( keyAdapter );
    }
    
    public void moveSpaceShip() {
        spaceShipX += spaceShipXSpeed;
        spaceShipY += spaceShipYSpeed;
    }
    
    public void moveAlien() {
        
        if( alienRotationSpeedDeg != 0 ) {
            alienRotationDeg += alienRotationSpeedDeg;
        }
        
        if( alienSpeed != 0 ) {
            // 1. Add 90 degrees because 0 degrees is EAST and we want to move in
            //    the direction of the head of the image, which is facing NORTH.
            // 2. Subtract from Y because negative y is NORTH. Subtract from X because
            //    of the offset by 90 degrees reverses the direction.
            alienX -= Math.cos( Math.toRadians(alienRotationDeg + 90 ) ) * alienSpeed;
            alienY -= Math.sin( Math.toRadians( alienRotationDeg + 90 ) ) * alienSpeed;
        }
    }
    
    // https://stackoverflow.com/questions/8639567/java-rotating-images
    public BufferedImage rotateImage( BufferedImage image, int rotationDeg ) {
        double rotationRad = Math.toRadians(rotationDeg);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRad, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

        return op.filter(image, null);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent( g );

        g.setColor( Color.YELLOW );
        g.fillRect( 0, 0, Screen.WIDTH, Screen.HEIGHT );
        g.setColor( Color.BLACK );
        g.setFont( titleFont );
        g.drawString( "Screen 4", 200, 100 );
        
        g.setFont( new Font( "Arial", Font.PLAIN, 16 ) );
        g.drawString( "move: Up,down,left,right", 60, 280 );
        g.setFont( new Font( "Arial", Font.PLAIN, 16 ) );
        g.drawString( "rotate: e, r. move: space", 320, 280 );
        
        g.setFont( new Font( "Arial", Font.PLAIN, 32 ) );
        g.drawString( endGame, endGameX, 600 );
        
        g.drawImage( spaceShip, spaceShipX, spaceShipY, 75, 150, null );
        g.drawImage( rotateImage( alienSmall, alienRotationDeg ), alienX, alienY, null );
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if( arg0.getSource() == timer ) {
            moveSpaceShip();
            moveAlien();
            this.repaint();
        }
    }
}
