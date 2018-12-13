import javax.swing.*;
import java.io.IOException;

class AnimationJFrame extends JFrame {

    AnimationJFrame(String title) {
        super(title);
        initUI();
    }

    private void initUI() {

        try {
            setContentPane(new JPanelWithBackground("static/piano4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        add(new Board());
        pack();

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}