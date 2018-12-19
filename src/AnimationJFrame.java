import javafx.util.Pair;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;


class AnimationJFrame extends JFrame {

    final int WINDOW_WIDTH = 418;
    final int WINDOW_HEIGHT = 597;
    private Song chosenSong;

    int lifeRemaining = 2;

    HashMap<Pair, Board> notePositions = new HashMap<>();

    AnimationJFrame(String title, Song chosenSong) {
        super(title);
        this.chosenSong = chosenSong;
        initUI();
    }

    void animateNote(int init_x) {
        Board noteBoard = new Board(init_x, this, this.chosenSong);
        add(noteBoard);
        pack();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        Pair notePos = new Pair<>(noteBoard.x, noteBoard.y);
        notePositions.put(notePos, noteBoard);
    }

    void updateNoteHitValues(Board note, Pair oldNotePos) {
        if (!(note.y >= 450)) {
            this.notePositions.put(new Pair<>(note.x, note.y), this.notePositions.remove(oldNotePos));
        } else {
            this.notePositions.remove(oldNotePos, note);
        }
    }

    private void initUI() {

        try {
            setContentPane(new JPanelWithBackground("static/piano4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new SpringLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}