import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private final int INITIAL_X;
    private final int INITIAL_Y = -200;
    private AnimationJFrame originalJFrame;

    private Image note;
    private int x, y;

    Board(int init_x, AnimationJFrame originalJFrame) {
        this.INITIAL_X = init_x;
        this.originalJFrame = originalJFrame;
        initBoard();
    }

    private boolean contains(int[] inputArray, int toFind) {

        boolean found = false;

        for (int givenInteger : inputArray) {
            if (toFind == givenInteger) {
                found = true;
                break;
            }
        }

        return found;
    }

    private String getNoteImagePath() {

        int[] blackKeyValues = {39, 97, 211, 270, 328};

        if (contains(blackKeyValues, this.INITIAL_X)) {
            return "static/noteSharpFlat.png";
        }

        return "static/noteBase.png";
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon(getNoteImagePath());
        note = ii.getImage();
    }

    private void initBoard() {

        int PANEL_WIDTH = originalJFrame.WINDOW_WIDTH;
        int PANEL_HEIGHT = originalJFrame.WINDOW_HEIGHT;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        setOpaque(false);

        loadImage();

        x = INITIAL_X;
        y = INITIAL_Y;

        int DELAY = 10;
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

        y += 2;

        if (y > 350) {

            y = INITIAL_Y;
        }

        repaint();
    }
}