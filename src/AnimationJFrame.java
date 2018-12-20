import javafx.util.Pair;

import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;


class AnimationJFrame extends JFrame {

    final int WINDOW_WIDTH = 418;
    final int WINDOW_HEIGHT = 597;
    private Song chosenSong;
    Hearts allHearts;

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

        switch (chosenSong.difficulty) {
            case "Easy":
                this.lifeRemaining = 6;
                break;
            case "Medium":
                this.lifeRemaining = 4;
                break;
            case "Hard":
                this.lifeRemaining = 2;
                break;
        }

        try {
            setContentPane(new JPanelWithBackground("static/piano.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new SpringLayout());
        setLocationRelativeTo(null);
    }
}