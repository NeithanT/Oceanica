package menea.Vanity;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageLoader {

    public static final String IMAGE_PATH = "/assets/";
    
    public Image loadImage(String id) {
        
        try {
            return ImageIO.read(getClass().getResource(IMAGE_PATH + id + ".png"));

        } catch (IOException e) {
            System.out.println("No se pudo cargar");
        }
        return null;
    }
    
    public Image loadImage(String id, int width, int height) {
        
        try {

            return ImageIO.read(getClass().getResource(IMAGE_PATH + id + ".png"));
            
        } catch (IOException e) {
            System.out.println("No se pudo cargar");
        }
        return null;
    }
    
    public ImageIcon loadImageIcon(String id) {
        
        Image img = loadImage(id);
        
        if (img != null)
            return new ImageIcon(loadImage(id));
        else
            return new ImageIcon();
    }
    
    public ImageIcon loadImageIcon(String id, int width, int height) {

        Image img = loadImage(id);

        if (img != null) {
            img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        }
        else
            return new ImageIcon();

    }
    
    
}
