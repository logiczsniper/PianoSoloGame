import org.jfugue.player.Player;
import org.jfugue.theory.Note;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.EventQueue;
import java.util.HashMap;

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


    public static class NoteDisplayThread extends Thread {

        AnimationJFrame gameScreen;
        Song chosenSong;

        NoteDisplayThread(AnimationJFrame gameScreen, Song chosenSong) {
            this.gameScreen = gameScreen;
            this.chosenSong = chosenSong;
        }

        public void run() {
            for (char character : chosenSong.value.toCharArray()) {
                switch (character) {
                    case '.':
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_Z:
                        gameScreen.animateNote(4);
                        break;
                    case KeyEvent.VK_S:
                        gameScreen.animateNote(39);
                        break;
                    case KeyEvent.VK_X:
                        gameScreen.animateNote(69);
                        break;
                    case KeyEvent.VK_D:
                        gameScreen.animateNote(97);
                        break;
                    case KeyEvent.VK_C:
                        gameScreen.animateNote(133);
                        break;
                    case KeyEvent.VK_V:
                        gameScreen.animateNote(173);
                        break;
                    case KeyEvent.VK_G:
                        gameScreen.animateNote(211);
                        break;
                    case KeyEvent.VK_B:
                        gameScreen.animateNote(241);
                        break;
                    case KeyEvent.VK_H:
                        gameScreen.animateNote(270);
                        break;
                    case KeyEvent.VK_N:
                        gameScreen.animateNote(301);
                        break;
                    case KeyEvent.VK_J:
                        gameScreen.animateNote(328);
                        break;
                    case KeyEvent.VK_M:
                        gameScreen.animateNote(363);
                        break;

                    default:
                        System.out.println("Note Unrecognised");
                }
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

    private static void playGame(String songTitle) {
        SongBank songBank = new SongBank();
        Song chosenSong = songBank.getSongByTitle(songTitle);
        Player player = new Player();

        EventQueue.invokeLater(() -> {
            AnimationJFrame gameScreen = new AnimationJFrame("Piano Solo", chosenSong);
            gameScreen.setSize(gameScreen.WINDOW_WIDTH, gameScreen.WINDOW_HEIGHT);

            NoteDisplayThread noteDisplayThread = new NoteDisplayThread(gameScreen, chosenSong);
            noteDisplayThread.start();

            gameScreen.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {

                    HashMap<Integer, Boolean> noteHitValues = gameScreen.noteHitValues;
                    HashMap<Integer, Board> noteXValues = gameScreen.noteXValues;

                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_Z:
                            playNote(player, chosenSong, "C");
                            noteHitRecognition(noteHitValues, noteXValues, 4);
                            break;
                        case KeyEvent.VK_S:
                            playNote(player, chosenSong, "C#");
                            noteHitRecognition(noteHitValues, noteXValues, 39);
                            break;
                        case KeyEvent.VK_X:
                            playNote(player, chosenSong, "D");
                            noteHitRecognition(noteHitValues, noteXValues, 69);
                            break;
                        case KeyEvent.VK_D:
                            playNote(player, chosenSong, "D#");
                            noteHitRecognition(noteHitValues, noteXValues, 97);
                            break;
                        case KeyEvent.VK_C:
                            playNote(player, chosenSong, "E");
                            noteHitRecognition(noteHitValues, noteXValues, 133);
                            break;
                        case KeyEvent.VK_V:
                            playNote(player, chosenSong, "F");
                            noteHitRecognition(noteHitValues, noteXValues, 173);
                            break;
                        case KeyEvent.VK_G:
                            playNote(player, chosenSong, "F#");
                            noteHitRecognition(noteHitValues, noteXValues, 211);
                            break;
                        case KeyEvent.VK_B:
                            playNote(player, chosenSong, "G");
                            noteHitRecognition(noteHitValues, noteXValues, 241);
                            break;
                        case KeyEvent.VK_H:
                            playNote(player, chosenSong, "G#");
                            noteHitRecognition(noteHitValues, noteXValues, 270);
                            break;
                        case KeyEvent.VK_N:
                            playNote(player, chosenSong, "A");
                            noteHitRecognition(noteHitValues, noteXValues, 301);
                            break;
                        case KeyEvent.VK_J:
                            playNote(player, chosenSong, "A#");
                            noteHitRecognition(noteHitValues, noteXValues, 328);
                            break;
                        case KeyEvent.VK_M:
                            playNote(player, chosenSong, "B");
                            noteHitRecognition(noteHitValues, noteXValues, 363);
                            break;
                    }
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            });

            gameScreen.setVisible(true);
        });
    }

    private static void noteHitRecognition(HashMap<Integer, Boolean> noteHitValues, HashMap<Integer, Board> noteXValues, int x_value) {
        try {
            if (noteHitValues.get(x_value)) {
                Board currentNote = noteXValues.get(x_value);
                currentNote.hasBeenHit = true;
                currentNote.loadImage(true);
            }
        } catch (NullPointerException npe) {
            System.out.println("No such note on the JFrame");
        }
    }

    private static void playNote(Player player, Song chosenSong, String currentNote) {
        NotePlayThread notePlayThread = new NotePlayThread(player, chosenSong, currentNote);
        notePlayThread.start();
    }
}
