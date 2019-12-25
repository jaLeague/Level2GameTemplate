import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Level2GameTemplate implements KeyListener {
    private Screen currentScreen;
    JFrame frame;
    Screen screen1;
    Screen screen2;
    Screen screen3;
    Screen screen4;
    Screen screen5;

    public Level2GameTemplate() {
        frame = new JFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.addKeyListener( this );
        
        screen1 = new Screen1( this );
        screen2 = new Screen2( this );
        screen3 = new Screen3( this );
        screen4 = new Screen4( this );
        screen5 = new Screen5( this );
        
        currentScreen = screen1;
        changeScreen( screen1 );
    }
    
    public void changeScreen( Screen screen ) {
        if( currentScreen != null ) {
            currentScreen.cleanUp();
        }
        
        this.frame.getContentPane().add( screen );
        screen.setup();
        currentScreen = screen;
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        int keyCode = arg0.getKeyCode();
        
        if( keyCode == KeyEvent.VK_ENTER ) {
            if( currentScreen == screen1 ) {
                changeScreen( screen2 );
                System.out.println( currentScreen );
            } else if( currentScreen == screen2 ) {
                changeScreen( screen3 );
                System.out.println( currentScreen );
            } else if( currentScreen == screen3 ) {
                changeScreen( screen4 );
                System.out.println( currentScreen );
            } else if( currentScreen == screen4 ) {
                changeScreen( screen5 );
                System.out.println( currentScreen );
            } else if( currentScreen == screen5 ) {
                changeScreen( screen1 );
                System.out.println( currentScreen );
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        
    }

}
