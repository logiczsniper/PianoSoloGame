import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    private final int INITIAL_X;
    private AnimationJFrame originalJFrame;

    private Image note;
    int x, y;
    private int y_change;

    boolean canHit = false;
    boolean hasBeenHit = false;

    Board(int init_x, AnimationJFrame originalJFrame, Song chosenSong) {
        this.INITIAL_X = init_x;
        this.originalJFrame = originalJFrame;

        switch (chosenSong.difficulty) {
            case "Easy":
                this.y_change = 1;
                this.originalJFrame.lifeRemaining = 6;
                break;
            case "Medium":
                this.y_change = 2;
                this.originalJFrame.lifeRemaining = 4;
                break;
            case "Hard":
                this.y_change = 4;
                this.originalJFrame.lifeRemaining = 2;
                break;
        }

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

    private String getNoteImagePath(boolean hit) {

        int[] blackKeyValues = {39, 97, 211, 270, 328};

        if (contains(blackKeyValues, this.INITIAL_X)) {
            if (hit) {
                return "static/noteBase.png";
            }
            return "static/noteSharpFlat.png";
        }
        if (hit) {
            return "static/noteSharpFlat.png";
        }
        return "static/noteBase.png";
    }

    void loadImage(boolean hit) {

        ImageIcon ii = new ImageIcon(getNoteImagePath(hit));
        note = ii.getImage();
    }

    private void initBoard() {

        int PANEL_WIDTH = originalJFrame.WINDOW_WIDTH;
        int PANEL_HEIGHT = originalJFrame.WINDOW_HEIGHT;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        setOpaque(false);

        loadImage(false);

        x = INITIAL_X;
        y = -200;

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

        Pair oldNotePos = new Pair<>(x, y);

        y += y_change;
        this.canHit = y > 290 && y < 400;
        this.originalJFrame.updateNoteHitValues(this, oldNotePos);

        if (y == 360 && !this.hasBeenHit) {
            originalJFrame.lifeRemaining -= 1;
        }
        System.out.println(originalJFrame.lifeRemaining);

        repaint();
    }
}