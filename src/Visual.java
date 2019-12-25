import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Visual {
    
    public JLabel createLabelImage(String fileName, int width, int height) {
        URL imageURL = getClass().getResource(fileName);
        if (imageURL == null) {
            System.err.println("Could not find image " + fileName);
            return new JLabel();
        }
        return loadImage( imageURL, width, height );
    }
    
    private JLabel loadImage(URL imageURL, int width, int height) {
        ImageIcon icon = new ImageIcon(imageURL);
        
        // Resize image
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance( width, height, Image.SCALE_SMOOTH );
        icon = new ImageIcon( newimg );
        
        JLabel label = new JLabel(icon);
        
        return label;
    }
}
