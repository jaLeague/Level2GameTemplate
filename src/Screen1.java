import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class Screen1 extends Screen implements MouseMotionListener, ActionListener {
    Font titleFont = new Font( "Playfair Display", Font.PLAIN, 48 );
    Level2GameTemplate game;

    JPanel a;
    JLabel labelStory;
    JButton button1;
    JButton button2;
    JButton button3;
    JTextField textField;

    int spaceShipX;
    int spaceShipY;
    int spaceShipWidth;
    int spaceShipHeight;
    Color bgColor;

    Cursor originalCursor;
    Cursor newCursor;
    
    public Screen1(Level2GameTemplate game) {
        super( game.frame );
        this.game = game;

        this.addMouseMotionListener( this );
    }

    public void setup() {
        this.game.frame.setTitle( "Swing Components and Graphics Example" );
        this.setLayout( new BorderLayout() );
        
        bgColor = Color.GREEN;
        
        originalCursor = game.frame.getCursor();
        newCursor = Toolkit.getDefaultToolkit().createCustomCursor( infinityCur, new Point(0, 0), "inf cursor");
        game.frame.setCursor( newCursor );
        
        labelStory = new JLabel( "Change background color to A, B, or C" );
        labelStory.setFont( new Font( "Tahoma", ( Font.BOLD | Font.ITALIC ), 24 ) );

        button1 = new JButton( "A" );
        button2 = new JButton( "B" );
        button3 = new JButton( "C" );

        // Don't allow button presses to shift focus on the keyListener in the frame
        button1.setFocusable( false );
        button2.setFocusable( false );
        button3.setFocusable( false );
        button1.addActionListener( this );
        button2.addActionListener( this );
        button3.addActionListener( this );

        textField = new JTextField( 20 );
        textField.setText( "<Here is your fate>" );
        textField.setEditable( false );
        textField.setFocusable( false );

        a = new JPanel();
        a.setLayout( new GridBagLayout() );
        a.setBackground( Color.MAGENTA );
        a.setPreferredSize( new Dimension( Screen.WIDTH, Screen.HEIGHT / 2 ) );
        a.setMaximumSize( new Dimension( Screen.WIDTH, Screen.HEIGHT / 2 ) );
        a.setAlignmentX( Component.LEFT_ALIGNMENT );
        a.setAlignmentY( Component.BOTTOM_ALIGNMENT );

        addStoryLine();
        addButtons();
        addTextField();

        spaceShipX = 100;
        spaceShipY = 100;
        spaceShipWidth = 75;
        spaceShipHeight = 150;

        this.add( a, BorderLayout.SOUTH );
        game.frame.pack();
        this.repaint();
    }
    
    public void cleanUp() {
        super.cleanUp();
        game.frame.setCursor( originalCursor );
    }

    public void addStoryLine() {
        Border border = BorderFactory.createLineBorder( Color.BLACK );
        labelStory.setBorder( border );

        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.EAST;
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 4;
        gc.gridx = 0;
        gc.gridy = 0;
        a.add( labelStory, gc );
    }

    public void addButtons() {
        GridBagConstraints gc = new GridBagConstraints();

        // Number of pixels between adjacent components: top, left, bottom, right sides
        gc.insets = new Insets( 20, 0, 0, 0 );

        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0;
        gc.gridy = 1;
        gc.ipadx = 0;
        gc.ipadx = 100;
        a.add( button1, gc );

        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 1;
        gc.gridy = 1;
        a.add( button2, gc );

        gc.anchor = GridBagConstraints.CENTER;
        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 2;
        gc.gridy = 1;
        a.add( button3, gc );
    }

    public void addTextField() {
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridwidth = 3;
        gc.gridx = 0;
        gc.gridy = 2;
        gc.insets = new Insets( 20, 0, 0, 0 );
        a.add( textField, gc );
    }

    public void paintComponent(Graphics g) {
        super.paintComponent( g );

        g.setColor( bgColor );
        g.fillRect( 0, 0, Screen.WIDTH, Screen.HEIGHT / 2 );
        
        g.setColor( Color.BLACK );
        g.setFont( titleFont );
        g.drawString( "Screen 1", 200, 100 );

        g.setFont( new Font( "Arial", Font.PLAIN, 20 ) );
        g.drawString( "Rocketship follows the mouse", 50, 300 );

        g.drawImage( this.spaceShip, spaceShipX, spaceShipY, spaceShipWidth, spaceShipHeight, null );
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX() - spaceShipWidth / 2;
        int y = e.getY() - spaceShipHeight / 2;

        if( x >= 0 && x <= ( Screen.WIDTH - spaceShipWidth ) ) {
            spaceShipX = x;
            this.repaint();
        }
        if( y >= 0 && y <= ( ( Screen.HEIGHT / 2 ) - spaceShipHeight ) ) {
            spaceShipY = y;
            this.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        JButton buttonPressed = (JButton)arg0.getSource();

        if( buttonPressed == button1 ) {
            bgColor = Color.PINK;
            textField.setText( "Button A pressed" );
            this.repaint();
        } else if( buttonPressed == button2 ) {
            bgColor = Color.GREEN;
            textField.setText( "Button B pressed" );
            this.repaint();
        } else if( buttonPressed == button3 ) {
            bgColor = Color.BLUE;
            textField.setText( "Button C pressed" );
            this.repaint();
        }
    }
}
