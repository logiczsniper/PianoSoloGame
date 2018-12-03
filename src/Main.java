import javax.swing.*;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        songSelection();
    }

    private static void songSelection() {
        JFrame selectionScreen = new JFrame("Piano Solo");
        selectionScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] availableSongs = {"Song One - Easy", "Song Two - Easy", "Song Three - Medium", "Song Four - Medium", "Song Five - Hard"};

        JComboBox<String> songList = new JComboBox<>(availableSongs);
        songList.setSelectedIndex(4);

        songList.setBounds(60, 10, 180, 30);
        selectionScreen.add(songList);

        JButton goButton = new JButton("Play");
        goButton.setBounds(60, 50, 180, 30);

        ActionListener baseListener = e -> {
            try {
                String selectedSong = (String) songList.getSelectedItem();
                playGame(selectedSong);
                selectionScreen.setVisible(false);
                selectionScreen.dispose();
            } catch (Exception el) {
                el.printStackTrace();
            }
        };

        goButton.addActionListener(baseListener);
        selectionScreen.add(goButton);

        selectionScreen.setSize(315, 150);
        selectionScreen.setLayout(null);
        selectionScreen.setVisible(true);
    }

    private static void playGame(String songTitle) {
        SongBank songBank = new SongBank();
        Song chosenSong = songBank.getSongByTitle(songTitle);

        JFrame gameScreen = new JFrame("Piano Solo");
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Hello");
        title.setBounds(300, 300, 200, 50);

        gameScreen.add(title);

        gameScreen.setSize(800, 800);
        gameScreen.setLayout(null);
        gameScreen.setVisible(true);
    }
}
