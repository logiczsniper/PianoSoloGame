import org.jfugue.player.Player;
import org.jfugue.theory.Note;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

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
        Player player = new Player();

        JFrame gameScreen = new JFrame("Piano Solo");
        gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            gameScreen.setContentPane(new JPanelWithBackground("static/piano4.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameScreen.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_Z:
                        player.play(new Note("C").setDuration(0.5));
                        break;
                    case KeyEvent.VK_S:
                        player.play(new Note("C#").setDuration(0.5));
                        break;
                    case KeyEvent.VK_X:
                        player.play(new Note("D").setDuration(0.5));
                        break;
                    case KeyEvent.VK_D:
                        player.play(new Note("D#").setDuration(0.5));
                        break;
                    case KeyEvent.VK_C:
                        player.play(new Note("E").setDuration(0.5));
                        break;
                    case KeyEvent.VK_V:
                        player.play(new Note("F").setDuration(0.5));
                        break;
                    case KeyEvent.VK_G:
                        player.play(new Note("F#").setDuration(0.5));
                        break;
                    case KeyEvent.VK_B:
                        player.play(new Note("G").setDuration(0.5));
                        break;
                    case KeyEvent.VK_H:
                        player.play(new Note("G#").setDuration(0.5));
                        break;
                    case KeyEvent.VK_N:
                        player.play(new Note("A").setDuration(0.5));
                        break;
                    case KeyEvent.VK_J:
                        player.play(new Note("A#").setDuration(0.5));
                        break;
                    case KeyEvent.VK_M:
                        player.play(new Note("B").setDuration(0.5));
                        break;
                    case KeyEvent.VK_K:
                        player.play(new Note("B#").setDuration(0.5));
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                player.getManagedPlayer().reset();
            }
        });

        gameScreen.setSize(418, 597);
        gameScreen.setLayout(null);
        gameScreen.setVisible(true);
    }
}
