package game_scroller;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class UpDownScrollerPanel extends ScrollerPanel {

	private int y1=0;
	private int y2;


	/* Left-Right Scroll */

	public UpDownScrollerPanel() {
		try {
			background = ImageIO.read(getClass().getResource("ladder.jpg"));
			backgroundHeight = background.getHeight(); // You need this if you are scrolling left-right backgroundHeight =
														// background.getHeight(); //You need this if you are scrolling
														// up-down
		} catch (Exception e) {
			System.out.println("Could not find background image");
		}
		y2=frameHeight;
		drawTimer.start();
	}

	protected void paintComponent(Graphics g) {
		
		g.drawImage(background, 0, 0, frameWidth, frameHeight, 0, y1, frameWidth, y2, this);
		moveBackground();

	}

	 void moveBackground() {
		if (y1 >= (backgroundHeight - frameHeight)) {
			y1 = 0;
			y2 = frameHeight;
		} else {
			y1 += scrollSpeed;
			y2 += scrollSpeed;
		}

	}


}
