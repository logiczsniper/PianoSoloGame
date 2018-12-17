import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;


class AnimationJFrame extends JFrame {

    final int WINDOW_WIDTH = 418;
    final int WINDOW_HEIGHT = 597;
    private Song chosenSong;

    int lifeRemaining = 2;
    HashMap<Integer, Boolean> noteHitValues = new HashMap<>();
    HashMap<Integer, Board> noteXValues = new HashMap<>();

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

        noteHitValues.put(init_x, noteBoard.canHit);
        noteXValues.put(init_x, noteBoard);
    }

    void updateNoteHitValues(Integer init_x, Boolean canHit, Board note) {
        if (!(note.y >= 400)) {
            this.noteHitValues.replace(init_x, canHit);
            this.noteXValues.replace(init_x, note);
        } else {
            this.noteHitValues.remove(init_x, canHit);
            this.noteXValues.remove(init_x, note);
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