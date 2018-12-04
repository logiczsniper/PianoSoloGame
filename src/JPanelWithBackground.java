import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Regular JPanel except that it draws my own image as a background.
 */
public class JPanelWithBackground extends JPanel {

    private Image background_image;

    JPanelWithBackground(String fileName) throws IOException {
        background_image = ImageIO.read(new File(fileName));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(background_image, 0, 0, this);
    }
}
