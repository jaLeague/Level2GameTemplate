package game_scroller;
import javax.swing.JFrame;

public class Scroller {
	public static final int FRAME_HEIGHT = 520;
	public static final int FRAME_WIDTH = 650;
	
	JFrame frame = new JFrame();
	LeftRightScrollerPanel panel  =new LeftRightScrollerPanel();
	

public void run() {
	
	frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	frame.add(panel);
	
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
}
}
