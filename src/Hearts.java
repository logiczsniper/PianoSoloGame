import javax.swing.*;
import java.awt.*;

public class Hearts extends JPanel {

    int maxLives;
    private Image heart;

    Hearts(int maxLives) {
        this.maxLives = maxLives;
        setPreferredSize(new Dimension(385, 120));
        setOpaque(false);
        loadImage();

    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("static/heart.png");
        heart = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHeart(g);
    }

    private void drawHeart(Graphics g) {

        int y = 10;

        for (int x = 5; x <= maxLives * 60; x += 60) {
            g.drawImage(heart, x, y, this);
            Toolkit.getDefaultToolkit().sync();
        }

    }
}