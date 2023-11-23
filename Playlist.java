package music;

/**
 * This class represents a playlist containing many songs.
 * 
 * The Playlist is a Circular Linked list of SongNode objects.
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 */

public class Playlist {
    private SongNode last; // reference to the last node in the Circular Linked List
    private int      size; // the number of SongNodes (songs) in the list

    /*
     * Constructor
     */
    public Playlist(SongNode last, int size) {
        this.last = last;
        this.size = size;
    }

    /*
     * Default constructor initializes the size to 0
     */
    public Playlist() {
        this(null, 0);
    }

    /* Getter and setter methods */
    public SongNode getLast() {return last;}
    public void setLast(SongNode last) {this.last = last;}

    public int getSize() {return size;}
    public void setSize(int size) {this.size = size;}
}
