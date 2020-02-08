package game_scroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ScrollerPanel extends JPanel implements ActionListener {
	 BufferedImage background;
	 int backgroundWidth = 0;
	 int backgroundHeight = 0;
	 int frameWidth = Scroller.FRAME_WIDTH;
	 int frameHeight = Scroller.FRAME_HEIGHT;

	 int scrollSpeed = 1;
	 Timer drawTimer = new Timer(1000/60,this);
	
	 void moveBackground() {
		 
	 }
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			moveBackground();
			repaint();
		}

}
