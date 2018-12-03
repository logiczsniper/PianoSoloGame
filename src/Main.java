import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) {
        songSelection();
    }

    private static void songSelection() {
        JFrame appScreen = new JFrame("Piano Solo");
        appScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] availableSongs = {"Song One - Easy", "Song Two - Easy", "Song Three - Medium", "Song Four - Medium", "Song Five - Hard"};

        JComboBox<String> songList = new JComboBox<>(availableSongs);
        songList.setSelectedIndex(4);

        songList.setBounds(60, 10, 180, 30);
        appScreen.add(songList);

        JButton goButton = new JButton("Play");
        goButton.setBounds(60, 50, 180, 30);

        ActionListener baseListener = e -> {
            try {
                String selectedSong = (String) songList.getSelectedItem();
                playGame(selectedSong);
                appScreen.dispatchEvent(new WindowEvent(appScreen, WindowEvent.WINDOW_CLOSING));
            } catch (Exception el) {
                el.printStackTrace();
            }
        };

        goButton.addActionListener(baseListener);
        appScreen.add(goButton);

        appScreen.setSize(315, 150);
        appScreen.setLayout(null);
        appScreen.setVisible(true);
    }

    private static void playGame(String songTitle) {
        System.out.println(songTitle);
    }
}
