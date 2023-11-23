package music;

/**
 * This class represents a song node, with a song object representing the song 
 * and a reference to the song next in the playlist.
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 */

public class SongNode {
    private Song        song;  // the song contained in this node
    private SongNode    next;  // reference to the next song in the playlist

    /*
     * Constructor
     */
    public SongNode(Song song, SongNode next) {
        this.song = song;
        this.next = next;
    }

    /*
     * Default constructor
     */
    public SongNode() {
        this(null, null);
    }

    /* Getter and setter methods */
    public Song getSong() { return song; }
    public void setSong(Song s) { song = s; }

    public SongNode getNext() { return next; }
    public void setNext(SongNode n) { next = n; }
    
}
