package game_scroller;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Scroller {
	public static  int FRAME_HEIGHT;
	public static  int FRAME_WIDTH;
	
	JFrame frame = new JFrame();
	ScrollerPanel panel;
	

public void run() {
	

	
	int choice = JOptionPane.showOptionDialog(null, "vertical or horizontal scroll?", "direction", 0,
			JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Vertical", "Horizontal"},null);
	
	if (choice==1) { 
		FRAME_HEIGHT = 520;
		FRAME_WIDTH = 1300/2;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		panel = new LeftRightScrollerPanel(); }
	else { 
		FRAME_HEIGHT = 406/2;
		FRAME_WIDTH = 166;
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
			panel = new UpDownScrollerPanel(); }
	
	
	
	frame.add(panel);
	//frame.pack();
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	
}
}
