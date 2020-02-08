package game_scroller;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LeftRightScrollerPanel extends ScrollerPanel {

	private int x1=0;
	private int x2;


	/* Left-Right Scroll */

	public LeftRightScrollerPanel() {
		try {
			background = ImageIO.read(getClass().getResource("background.jpg"));
			backgroundWidth = background.getWidth(); // You need this if you are scrolling left-right backgroundHeight =
														// background.getHeight(); //You need this if you are scrolling
														// up-down
		} catch (Exception e) {
			System.out.println("Could not find background image");
		}
		x2=frameWidth;
		drawTimer.start();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(background, 0, 0, frameWidth, frameHeight, x1, 0, x2, frameHeight, this);
		moveBackground();

	}

	 void moveBackground() {
		if (x1 >= (backgroundWidth - frameWidth)) {
			x1 = 0;
			x2 = frameWidth;
		} else {
			x1 += scrollSpeed;
			x2 += scrollSpeed;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		moveBackground();
		repaint();
	}
}
