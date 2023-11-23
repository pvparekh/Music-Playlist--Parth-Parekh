package music;

/**
 * This class represents a song, with a string for the song name, artist, 
 * album, and year
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 */

public class Song {
    private String          songName;
    private String          artist;
    private int             year;
    private int             popularity;
    private String          link;
    private final String    DIRECTORY = "songs/";

    /*
     * Constructor
     */
    public Song(String songName, String artist, int year, int popularity, String link) {
        this.songName = songName;
        this.artist = artist;
        this.year = year;
        this.popularity = popularity;
        this.link = DIRECTORY + year + "/" + link;
    }

    /*
     * Constructor with link of the song set to null
     */
    public Song(String songName, String artist, int year, int popularity) {
        this.songName = songName;
        this.artist = artist;
        this.year = year;
        this.popularity = popularity;
        this.link = null;
    }

    /*
     * Default constructor initializes year and popularity to 0  
     */
    public Song() {
        this(null, null, 0, 0, null);
    }

    /*
     * Prints the Song object
     */
    @Override
    public String toString() {
        StringBuilder printSong = new StringBuilder();
        printSong.append(songName + " (");
        printSong.append(artist + ", y=" + year + ", p=" + popularity + ")");
        return printSong.toString();
    }

    /*
     * HashCode method
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((songName == null) ? 0 : songName.hashCode());
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        result = prime * result + year;
        result = prime * result + popularity;
        return result;
    }

    /*
     * Equals method (used for removeSong())
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (songName == null) {
            if (other.songName != null)
                return false;
        } else if (!songName.equals(other.songName))
            return false;
        if (artist == null) {
            if (other.artist != null)
                return false;
        } else if (!artist.equals(other.artist))
            return false;
        if (year != other.year)
            return false;
        if (popularity != other.popularity)
            return false;
        return true;
    }

    /* Getter and setter methods */
    public String getSongName() { return songName; }
    public void setSongName(String s) { songName = s; }

    public String getArtist() { return artist; }
    public void setArtist(String art) { artist = art; }

    public int getYear() { return year; }
    public void setYear(int y) { year = y; }

    public int getPopularity() { return popularity; }
    public void setPopularity(int p) { popularity = p; }

    public String getLink() { return link; }
    public void setLink(String l) { link = DIRECTORY + year + "/" + l; }

    public String getDirectory() { return DIRECTORY; }

}
