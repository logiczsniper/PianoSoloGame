import org.jfugue.player.Player;
import org.jfugue.theory.Note;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        songSelection();
    }

    private static void songSelection() {
        JFrame selectionScreen = new JFrame("Piano Solo");
        selectionScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] availableSongs = {"Song Five - Hard", "Song Four - Medium", "Song Three - Medium", "Song Two - Easy", "Song One - Easy"};

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

        EventQueue.invokeLater(() -> {
            AnimationJFrame gameScreen = new AnimationJFrame("Piano Solo");

            gameScreen.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_Z:
                            player.play(new Note("C").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_S:
                            player.play(new Note("C#").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_X:
                            player.play(new Note("D").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_D:
                            player.play(new Note("D#").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_C:
                            player.play(new Note("E").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_V:
                            player.play(new Note("F").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_G:
                            player.play(new Note("F#").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_B:
                            player.play(new Note("G").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_H:
                            player.play(new Note("G#").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_N:
                            player.play(new Note("A").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_J:
                            player.play(new Note("A#").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_M:
                            player.play(new Note("B").setDuration(chosenSong.defaultNoteDuration));
                            break;
                        case KeyEvent.VK_K:
                            player.play(new Note("B#").setDuration(chosenSong.defaultNoteDuration));
                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            gameScreen.setSize(418, 597);
            gameScreen.setLayout(null);
            gameScreen.setVisible(true);
        });
    }
}
