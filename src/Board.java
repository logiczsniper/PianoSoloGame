import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private final int INITIAL_Y = -200;

    private Image note;
    private int x, y;

    Board() {

        initBoard();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("static/noteBase.png");
        note = ii.getImage();
    }

    private void initBoard() {

        int PANEL_WIDTH = 418;
        int PANEL_HEIGHT = 597;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        setOpaque(false);

        loadImage();

        x = 0;
        y = INITIAL_Y;

        int DELAY = 20;
        Timer timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawNote(g);
    }

    private void drawNote(Graphics g) {

        g.drawImage(note, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // System.out.println(y);

        y += 1;

        if (y > 350) {

            y = INITIAL_Y;
            x = 0;
        }

        repaint();
    }
}