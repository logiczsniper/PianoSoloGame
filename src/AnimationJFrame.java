import javax.swing.*;
import java.io.IOException;

class AnimationJFrame extends JFrame {

    final int WINDOW_WIDTH = 418;
    final int WINDOW_HEIGHT = 597;

    AnimationJFrame(String title) {
        super(title);
        initUI();
    }

    void animateNote(int init_x) {
        add(new Board(init_x, this));
        pack();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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