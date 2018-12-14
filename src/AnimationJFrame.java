import javax.swing.*;
import java.io.IOException;

class AnimationJFrame extends JFrame {

    AnimationJFrame(String title) {
        super(title);
        initUI();
    }

    void animateNote(int init_x) {
        add(new Board(init_x, this));
        pack();
    }

    private void initUI() {

        try {
            setContentPane(new JPanelWithBackground("static/piano4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}