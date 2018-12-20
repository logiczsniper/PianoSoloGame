class SongBank {

    private final Song songOne = new Song("Song One", ".Z.S.X.D.C.V.G.B.H.N.J.M.", "Easy");
    private final Song songTwo = new Song("Song Two", ".Z.", "Easy");
    private final Song songThree = new Song("Song Three", ".Z", "Medium");
    private final Song songFour = new Song("Song Four", ".Z", "Medium");
    private final Song songFive = new Song("Song Five", ".Z.S.X.D.C.V.G.B.H.N.J.M", "Hard");

    private final Song[] allSongs = {songOne, songTwo, songThree, songFour, songFive};

    private Song[] getAllSongs() {
        return this.allSongs;
    }

    Song getSongByTitle(String title) {

        for (Song song : this.getAllSongs()) {
            String fullTitle = song.title + " - " + song.difficulty;
            if (fullTitle.equals(title)) {
                return song;
            }
        }
        return null;
    }
}
