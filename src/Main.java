import javafx.util.Pair;
import org.jfugue.player.Player;
import org.jfugue.theory.Note;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) {
        songSelection();
    }

    private static void songSelection() {
        JFrame selectionScreen = new JFrame("Piano Solo");
        screenSetUp(selectionScreen);

        String[] availableSongs = {"Song Five - Hard", "Song Four - Medium", "Song Three - Medium", "Song Two - Easy", "Song One - Easy"};

        JComboBox<String> songList = new JComboBox<>(availableSongs);
        songList.setSelectedIndex(4);
        songList.setBackground(Color.WHITE);
        songList.setFont(new Font("Bodoni", Font.PLAIN, 15));
        songList.setForeground(new Color(47, 46, 44));

        songList.setBounds(60, 10, 180, 30);
        selectionScreen.add(songList);

        JButton goButton = new JButton(new ImageIcon("static/playButton.png"));
        goButton.setBackground(Color.WHITE);
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

    private static void analyzeKeyEvent(char character, boolean animateNote, AnimationJFrame gameScreen, Player player,
                                        Song chosenSong) {

        int x_value = 0;
        String currentNote = "";

        switch (Character.toLowerCase(character)) {
            case '.':
                if (animateNote) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 'z':
                x_value = 4;
                currentNote = "C";
                break;
            case 's':
                x_value = 39;
                currentNote = "C#";
                break;
            case 'x':
                x_value = 69;
                currentNote = "D";
                break;
            case 'd':
                x_value = 97;
                currentNote = "D#";
                break;
            case 'c':
                x_value = 133;
                currentNote = "E";
                break;
            case 'v':
                x_value = 173;
                currentNote = "F";
                break;
            case 'g':
                x_value = 211;
                currentNote = "F#";
                break;
            case 'b':
                x_value = 241;
                currentNote = "G";
                break;
            case 'h':
                x_value = 270;
                currentNote = "G#";
                break;
            case 'n':
                x_value = 301;
                currentNote = "A";
                break;
            case 'j':
                x_value = 328;
                currentNote = "A#";
                break;
            case 'm':
                x_value = 363;
                currentNote = "B";
                break;

            default:
                System.out.println("Note Failure");
        }

        if (animateNote && x_value != 0) {
            gameScreen.animateNote(x_value);
        } else if (!(currentNote.equals(""))) {
            playNote(player, chosenSong, currentNote);
            noteHitRecognition(x_value, gameScreen.notePositions);
        }
    }


    public static class NoteDisplayThread extends Thread {

        AnimationJFrame gameScreen;
        Song chosenSong;
        Player player;

        NoteDisplayThread(AnimationJFrame gameScreen, Song chosenSong, Player player) {
            this.gameScreen = gameScreen;
            this.chosenSong = chosenSong;
            this.player = player;
        }

        public void run() {
            for (char character : chosenSong.value.toCharArray()) {
                analyzeKeyEvent(character, true, gameScreen, player, chosenSong);
            }
        }
    }

    public static class NotePlayThread extends Thread {

        Player player;
        Song chosenSong;
        String currentNote;

        NotePlayThread(Player player, Song chosenSong, String currentNote) {
            this.player = player;
            this.chosenSong = chosenSong;
            this.currentNote = currentNote;
        }

        public void run() {
            try {
                player.play(new Note(currentNote).setDuration(chosenSong.defaultNoteDuration));
            } catch (java.lang.IllegalStateException ISE) {
                System.out.println("Failed to play two notes at once");
            }
        }
    }

    private static void screenSetUp(JFrame screen) {
        screen.getContentPane().setBackground(new Color(47, 46, 44));
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setIconImage(Toolkit.getDefaultToolkit().getImage("static/appIcon.png"));
    }

    private static Hearts displayLife(int maxLives) {
        JFrame heartScreen = new JFrame("Life Remaining: ");
        heartScreen.setSize(385, 120);
        Hearts allHearts = new Hearts(maxLives);
        heartScreen.add(allHearts);
        screenSetUp(heartScreen);
        heartScreen.setVisible(true);

        return allHearts;
    }

    private static void playGame(String songTitle) {
        SongBank songBank = new SongBank();
        Song chosenSong = songBank.getSongByTitle(songTitle);
        Player player = new Player();

        EventQueue.invokeLater(() -> {
            AnimationJFrame gameScreen = new AnimationJFrame("Piano Solo", chosenSong);
            gameScreen.allHearts = displayLife(gameScreen.lifeRemaining);
            screenSetUp(gameScreen);
            gameScreen.setSize(gameScreen.WINDOW_WIDTH, gameScreen.WINDOW_HEIGHT);

            NoteDisplayThread noteDisplayThread = new NoteDisplayThread(gameScreen, chosenSong, player);
            noteDisplayThread.start();

            gameScreen.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    analyzeKeyEvent(e.getKeyChar(), false, gameScreen, player, chosenSong);
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            gameScreen.setVisible(true);
        });
    }

    private static void noteHitRecognition(int x_value, HashMap<Pair, Board> notePositions) {
        try {
            Board potentialNote = identifyNote(notePositions, x_value);
            assert potentialNote != null;
            potentialNote.hasBeenHit = true;
            potentialNote.loadImage(true);
        } catch (NullPointerException ignored) {
        }
    }

    private static Board identifyNote(HashMap<Pair, Board> notePositions, int x_value) {
        for (Board note : notePositions.values()) {
            if (note.x == x_value && note.canHit) {
                return note;
            }
        }
        return null;
    }

    private static void playNote(Player player, Song chosenSong, String currentNote) {
        NotePlayThread notePlayThread = new NotePlayThread(player, chosenSong, currentNote);
        notePlayThread.start();
    }
}
