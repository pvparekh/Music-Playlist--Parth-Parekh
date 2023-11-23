package music;

import java.util.*;

/**
 * This class represents a library of song playlists.
 *
 * An ArrayList of Playlist objects represents the various playlists 
 * within one's library.
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 */
public class PlaylistLibrary {

    private ArrayList<Playlist> songLibrary; // contains various playlists

    /**
     * DO NOT EDIT!
     * Constructor for Library.
     * 
     * @param songLibrary passes in ArrayList of playlists
     */
    public PlaylistLibrary(ArrayList<Playlist> songLibrary) {
        this.songLibrary = songLibrary;
    }

    /**
     * DO NOT EDIT!
     * Default constructor for an empty library. 
     */
    public PlaylistLibrary() {
        this(null);
    }

    /**
     * This method reads the songs from an input csv file, and creates a 
     * playlist from it.
     * Each song is on a different line.
     * 
     * 1. Open the file using StdIn.setFile(filename);
     * 
     * 2. Declare a reference that will refer to the last song of the circular linked list.
     * 
     * 3. While there are still lines in the input file:
     *      1. read a song from the file
     *      2. create an object of type Song with the song information
     *      3. Create a SongNode object that holds the Song object from step 3.2.
     *      4. insert the Song object at the END of the circular linked list (use the reference from step 2)
     *      5. increase the count of the number of songs
     * 
     * 4. Create a Playlist object with the reference from step (2) and the number of songs in the playlist
     * 
     * 5. Return the Playlist object
     * 
     * Each line of the input file has the following format:
     *      songName,artist,year,popularity,link
     * 
     * To read a line, use StdIn.readline(), then use .split(",") to extract 
     * fields from the line.
     * 
     * If the playlist is empty, return a Playlist object with null for its last, 
     * and 0 for its size.
     * 
     * The input file has Songs in decreasing popularity order.
     * 
     * DO NOT implement a sorting method, PRACTICE add to end.
     * 
     * @param filename the playlist information input file
     * @return a Playlist object, which contains a reference to the LAST song 
     * in the ciruclar linkedlist playlist and the size of the playlist.
     */
    public Playlist createPlaylist(String filename) {

       
            // Create a new Playlist object
            Playlist playlist = new Playlist();
            
            // Open the file
            StdIn.setFile(filename);
            
            // Declare a SongNode reference that will refer to the last song of the circular LL
            SongNode lastSongNode = null;
            int songCount = 0; // This variable will count the number of songs in the list
            
            // While there are still lines in the input file:
            while (StdIn.hasNextLine()) {
                // Read a song from the file.
                String[] data = StdIn.readLine().split(",");
                String name = data[0];
                String artist = data[1];
                int year = Integer.parseInt(data[2]);
                int pop = Integer.parseInt(data[3]);
                String link = data[4];
                
                // Create a Song object with the information read from the file
                Song song = new Song(name, artist, year, pop, link);
                
                // Create a SongNode object that holds the Song object
                SongNode newSongNode = new SongNode(song, null);
             // If this is the first song 
               if (lastSongNode == null) {
                  newSongNode.setNext(newSongNode); // The first song points to itself
                } else {
                    // Insert the SongNode to the END of the circular LL
                    newSongNode.setNext(lastSongNode.getNext()); // Point the new song to the first song
                    lastSongNode.setNext(newSongNode); // The last song now points to the new song
                }
                
                // Update the last song node reference to the newly added song node
              lastSongNode = newSongNode;
              songCount++; //increase song count 
            }
            
            // set the playlist's last song and size
            playlist.setLast(lastSongNode);
            playlist.setSize(songCount);
            
            // return the Playlist object
            return playlist;
        }
        
        
    

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you. 
     * 
     * Adds a new playlist into the song library at a certain index.
     * 
     * 1. Calls createPlayList() with a file containing song information.
     * 2. Adds the new playlist created by createPlayList() into the songLibrary.
     * 
     * Note: initialize the songLibrary if it is null
     * 
     * @param filename the playlist information input file
     * @param playlistIndex the index of the location where the playlist will 
     * be added 
     */
    public void addPlaylist(String filename, int playlistIndex) {
        
        /* DO NOT UPDATE THIS METHOD */

        if ( songLibrary == null ) {
            songLibrary = new ArrayList<Playlist>();
        }
        if ( playlistIndex >= songLibrary.size() ) {
            songLibrary.add(createPlaylist(filename));
        } else {
            songLibrary.add(playlistIndex, createPlaylist(filename));
        }        
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * It takes a playlistIndex, and removes the playlist located at that index.
     * 
     * @param playlistIndex the index of the playlist to remove
     * @return true if the playlist has been deleted  
     */
    public boolean removePlaylist(int playlistIndex) {
        /* DO NOT UPDATE THIS METHOD */

        if ( songLibrary == null || playlistIndex >= songLibrary.size() ) {
            return false;
        }

        songLibrary.remove(playlistIndex);
            
        return true;
    }
    
    /** 
     * 
     * Adds the playlists from many files into the songLibrary
     * 
     * 1. Initialize the songLibrary
     * 
     * 2. For each of the filenames
     *       add the playlist into songLibrary
     * 
     * The playlist will have the same index in songLibrary as it has in
     * the filenames array. For example if the playlist is being created
     * from the filename[i] it will be added to songLibrary[i]. 
     * Use the addPlaylist() method. 
     * 
     * @param filenames an array of the filenames of playlists that should be 
     * added to the library
     */
    public void addAllPlaylists(String[] filenames) {
        songLibrary = new ArrayList<Playlist>();

    // For each of the playlists in the array of filenames, call addPlaylist() at their respective index
    for(int i = 0; i < filenames.length; i++) {
        addPlaylist(filenames[i], i);
    }
}
        
    

    /**
     * This method adds a song to a specified playlist at a given position.
     * 
     * The first node of the circular linked list is at position 1, the 
     * second node is at position 2 and so forth.
     * 
     * Return true if the song can be added at the given position within the 
     * specified playlist (and thus has been added to the playlist), false 
     * otherwise (and the song will not be added). 
     * 
     * Increment the size of the playlist if the song has been successfully
     * added to the playlist.
     * 
     * @param playlistIndex the index where the playlist will be added
     * @param position the position inthe playlist to which the song 
     * is to be added 
     * @param song the song to add
     * @return true if the song can be added and therefore has been added, 
     * false otherwise. 
     */
    public boolean insertSong(int playlistIndex, int position, Song song) {
        if (songLibrary== null) {
            return false;  // songLibrary hasn't been initialized, so return false immediately
        }
        // Check if the playlistIndex is out of bounds
        if (playlistIndex < 0 || playlistIndex >= songLibrary.size()) {
            return false;
        }
        // Get the specified playlist
        Playlist selectedPlaylist = songLibrary.get(playlistIndex);
        // Check if the position is invalid (less than 1 or greater than playlist size + 1)
        if (position < 1 || position > selectedPlaylist.getSize() + 1) {
            return false;
        }
        // Create a new song node
        SongNode newSongNode = new SongNode(song, null);
        // If adding the song to the first position
        if (position == 1) {
           SongNode currentFirstNode = selectedPlaylist.getLast().getNext();
           selectedPlaylist.getLast().setNext(newSongNode);
          newSongNode.setNext(currentFirstNode);
    
            // if the playlist only had one song
            if (selectedPlaylist.getSize() == 1) {
                selectedPlaylist.setLast(newSongNode);
            }
        }
        // If adding the song to end of the playlist
        else if (position == selectedPlaylist.getSize() + 1) {
            newSongNode.setNext(selectedPlaylist.getLast().getNext());
            selectedPlaylist.getLast().setNext(newSongNode);
            selectedPlaylist.setLast(newSongNode);
        }
        // inserting the song somewhere in the middle of the playlist
        else {
            SongNode current = selectedPlaylist.getLast().getNext(); // Start at the first song
            for (int i = 1; i < position - 1; i++) { // Traverse until the song before the desired position
                current = current.getNext();
            }
            newSongNode.setNext(current.getNext());
            current.setNext(newSongNode);
        }
    
        // Increase the size of the playlist
        selectedPlaylist.setSize(selectedPlaylist.getSize() + 1);
        
        return true;
    }
    

    /**
     * This method removes a song at a specified playlist, if the song exists. 
     *
     * Use the .equals() method of the Song class to check if an element of 
     * the circular linkedlist matches the specified song.
     * 
     * Return true if the song is found in the playlist (and thus has been 
     * removed), false otherwise (and thus nothing is removed). 
     * 
     * Decrease the playlist size by one if the song has been successfully
     * removed from the playlist.
     * 
     * @param playlistIndex the playlist index within the songLibrary where 
     * the song is to be added.
     * @param song the song to remove.
     * @return true if the song is present in the playlist and therefore has 
     * been removed, false otherwise.
     */
    public boolean removeSong(int playlistIndex, Song song) {
        // WRITE YOUR CODE HERE
         // first check if the playlist index is valid
    if (playlistIndex < 0 || playlistIndex >= songLibrary.size()) {
        return false;
    }

    Playlist targetPlaylist = songLibrary.get(playlistIndex);
    if (targetPlaylist == null || targetPlaylist.getSize() == 0) {
        return false;  // No playlist found or playlist is empty
    }

    SongNode current = targetPlaylist.getLast();
    SongNode prev = null;

    boolean found = false;  // To check if song was found in the list

    // Traverse the circular linked list
    do {
        prev = current;
        current = current.getNext();

        // Check if the current song matches the song to be removed
        if (song.equals(current.getSong())) {
            found = true;
            break;
        }
    } while (current != targetPlaylist.getLast());

    if (!found) {
        return false;  // Song not in the playlist
    }

    // If there's only one song in the playlist
    if (current == targetPlaylist.getLast() && current.getNext() == current) {
        targetPlaylist.setLast(null);
    } else {
        if (current == targetPlaylist.getLast()) {
            targetPlaylist.setLast(prev);
        }
        prev.setNext(current.getNext());
    }

    // Decrement the playlist size
    targetPlaylist.setSize(targetPlaylist.getSize() - 1);
    return true;
}
        
    

    /**
     * This method reverses the playlist located at playlistIndex
     * 
     * Each node in the circular linked list will point to the element that 
     * came before it.
     * 
     * After the list is reversed, the playlist located at playlistIndex will 
     * reference the first SongNode in the original playlist (new last).
     * 
     * @param playlistIndex the playlist to reverse
     */
    public void reversePlaylist(int playlistIndex) {
        // Check if the playlist index is valid
        if (playlistIndex < 0 || playlistIndex >= songLibrary.size()) {
            return; // Invalid index
        }
        Playlist targetPlaylist = songLibrary.get(playlistIndex);
        if (targetPlaylist == null || targetPlaylist.getSize() == 0) {
            return;  // No playlist found, or playlist is empty
        }
        // Initializing 
        SongNode previous = null;
        SongNode current = targetPlaylist.getLast().getNext(); // Point to the first node
        SongNode next = null;
    
        SongNode originalFirst = current;  // Save a reference to the original first node
        SongNode originalLast = targetPlaylist.getLast(); // Save a reference to the original last node
        do {
            // Store the next node
            next = current.getNext();
            // Reverse the direction of the current node
            current.setNext(previous);
            // Move previous and current pointers one step forward
            previous = current;
            current = next;
        } while (current != originalFirst);  // Stop when we reach the original first node again
    
        // Update the last node's next pointer to point to what is now the first node
        originalFirst.setNext(originalLast);
        // the playlist's last node should point to what was originally the first node
        targetPlaylist.setLast(originalFirst);
    }
    
    

    /**
     * This method merges two playlists.
     * 
     * Both playlists have songs in decreasing popularity order. The resulting 
     * playlist will also be in decreasing popularity order.
     * 
     * You may assume both playlists are already in decreasing popularity 
     * order. If the songs have the same popularity, add the song from the 
     * playlist with the lower playlistIndex first.
     * 
     * After the lists have been merged:
     *  - store the merged playlist at the lower playlistIndex
     *  - remove playlist at the higher playlistIndex 
     * 
     *
     * @param playlistIndex1 the first playlist to merge into one playlist
     * @param playlistIndex2 the second playlist to merge into one playlist
     */
    public void mergePlaylists(int playlistIndex1, int playlistIndex2) {
        // Check validity and bounds first 
        if (playlistIndex1 < 0 || playlistIndex2 < 0 || playlistIndex1 >= songLibrary.size() || playlistIndex2 >= songLibrary.size() || playlistIndex1 == playlistIndex2) {
            return; // Incase there is Invalid index or same index
        }
        Playlist playlist1 = songLibrary.get(playlistIndex1);
        Playlist playlist2 = songLibrary.get(playlistIndex2);
        // Check if playlists are not empty
        if (playlist1.getSize() ==0) {
            removePlaylist(playlistIndex1);
            return;
        } else if (playlist2.getSize() ==0) {
            removePlaylist(playlistIndex2);
            return;
        }
        int totalSize = playlist1.getSize() + playlist2.getSize(); // for updating size later
        SongNode current1 = playlist1.getLast() != null ? playlist1.getLast().getNext() : null; // Point to the first node
        SongNode current2 = playlist2.getLast() != null ? playlist2.getLast().getNext() : null;
        SongNode mergedLast = null, mergedFirst = null;
        // Merge until one list becomes empty
        while (current1 != null && current2 != null) {
            SongNode selected;
            if (current1.getSong().getPopularity() >= current2.getSong().getPopularity()) {
                selected = current1;
                current1 = current1.getNext() ==playlist1.getLast().getNext() ?null :current1.getNext();
            } else {
                selected = current2;
                current2 = current2.getNext() == playlist2.getLast().getNext()? null :current2.getNext();
            }
            if (mergedLast == null) {
                mergedFirst = mergedLast =selected;
            } else {
                mergedLast.setNext(selected);
                mergedLast =selected;
            }
        }
        // Append remaining songs from the non-empty list
        while (current1 != null) {
            mergedLast.setNext(current1);
            mergedLast = current1;
            current1 = current1.getNext() == playlist1.getLast().getNext() ? null : current1.getNext();
        }
        while (current2 != null) {
            mergedLast.setNext(current2);
            mergedLast = current2;
            current2 = current2.getNext() == playlist2.getLast().getNext() ? null : current2.getNext();
        }
        // Close the circular linked list
        if (mergedLast != null) {
            mergedLast.setNext(mergedFirst);
        }
        // Assign the merged playlist to the library position with the lower playlistIndex
        if (playlistIndex1 < playlistIndex2) {
            playlist1.setLast(mergedLast);
            playlist1.setSize(totalSize); // Set the size of merged playlist
            removePlaylist(playlistIndex2);
        } else {
            playlist2.setLast(mergedLast);
            playlist2.setSize(totalSize); // Set the size of merged playlist
            removePlaylist(playlistIndex1);
        }
    }
    
    
    

    /**
     * This method shuffles a specified playlist using the following procedure:
     * 
     * 1. Create a new playlist to store the shuffled playlist in.
     * 
     * 2. While the size of the original playlist is not 0, randomly generate a number 
     * using StdRandom.uniformInt(1, size+1). Size contains the current number
     * of items in the original playlist.
     * 
     * 3. Remove the corresponding node from the original playlist and insert 
     * it into the END of the new playlist (1 being the first node, 2 being the 
     * second, etc). 
     * 
     * 4. Update the old playlist with the new shuffled playlist.
     *    
     * @param index the playlist to shuffle in songLibrary
     */
    
     public void shufflePlaylist(int playlistIndex) {
        try {
            StdRandom.setSeed(2023);
            if (playlistIndex < 0 || playlistIndex >= songLibrary.size()) {
                //System.out.println("Invalid playlist index");
                return;
            }
            Playlist targetPlaylist = songLibrary.get(playlistIndex);
            if (targetPlaylist == null || targetPlaylist.getSize() == 0) {
                //System.out.println("No playlist found or playlist is empty");
                return;
            }
            Playlist shuffledPlaylist = new Playlist();
            int originalSize = targetPlaylist.getSize(); 
            for (int i = 0; i < originalSize; i++) {
                int randomIndex = StdRandom.uniformInt(1,targetPlaylist.getSize() + 1);
                SongNode previous = null;
                SongNode current = targetPlaylist.getLast().getNext(); // Start at the first node
                int currentIndex = 1;
                while (currentIndex < randomIndex) {
                    previous = current;
                    current = current.getNext();
                    currentIndex++;
                }
                // Remove the node from target
                if (previous != null) { // Not the first node
                    previous.setNext(current.getNext());
                    if (current == targetPlaylist.getLast()) { // If the curr node is the last node
                        targetPlaylist.setLast(previous);
                    }
                } else { // Removing the first node
                    targetPlaylist.getLast().setNext(current.getNext());
                    if (current == targetPlaylist.getLast()) { // If there's only one node left
                        targetPlaylist.setLast(null);
                    }
                }
                targetPlaylist.setSize(targetPlaylist.getSize()- 1);
                // Add the node to the shuffled playlist
                current.setNext(null); // Break the current node's existing references
                if (shuffledPlaylist.getLast() == null) {
                    shuffledPlaylist.setLast(current);
                    current.setNext(current);
                } else {
                    current.setNext(shuffledPlaylist.getLast().getNext());
                    shuffledPlaylist.getLast().setNext(current);
                    shuffledPlaylist.setLast(current);
                }
                shuffledPlaylist.setSize(shuffledPlaylist.getSize() + 1);  // Update the size of the new shuffled playlist
            }
            songLibrary.set(playlistIndex, shuffledPlaylist);
        } catch (Exception e) {
            //System.out.println("Exception encountered: ");
            e.printStackTrace();
        }
    }
    
    

    /**
     * This method sorts a specified playlist using linearithmic sort.
     * 
     * Set the playlist located at the corresponding playlistIndex
     * in decreasing popularity index order.
     * 
     * This method should  use a sort that has O(nlogn), such as with merge sort.
     * 
     * @param playlistIndex the playlist to shuffle
     */
    public void sortPlaylist ( int playlistIndex ) {

        // WRITE YOUR CODE HERE
        
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * Plays playlist by index; can use this method to debug.
     * 
     * @param playlistIndex the playlist to print
     * @param repeats number of times to repeat playlist
     * @throws InterruptedException
     */
    public void playPlaylist(int playlistIndex, int repeats) {
        /* DO NOT UPDATE THIS METHOD */

        final String NO_SONG_MSG = " has no link to a song! Playing next...";
        if (songLibrary.get(playlistIndex).getLast() == null) {
            StdOut.println("Nothing to play.");
            return;
        }

        SongNode ptr = songLibrary.get(playlistIndex).getLast().getNext(), first = ptr;

        do {
            StdOut.print("\r" + ptr.getSong().toString());
            if (ptr.getSong().getLink() != null) {
                StdAudio.play(ptr.getSong().getLink());
                for (int ii = 0; ii < ptr.getSong().toString().length(); ii++)
                    StdOut.print("\b \b");
            }
            else {
                StdOut.print(NO_SONG_MSG);
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (int ii = 0; ii < NO_SONG_MSG.length(); ii++)
                    StdOut.print("\b \b");
            }

            ptr = ptr.getNext();
            if (ptr == first) repeats--;
        } while (ptr != first || repeats > 0);
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * Prints playlist by index; can use this method to debug.
     * 
     * @param playlistIndex the playlist to print
     */
    public void printPlaylist(int playlistIndex) {
        StdOut.printf("%nPlaylist at index %d (%d song(s)):%n", playlistIndex, songLibrary.get(playlistIndex).getSize());
        if (songLibrary.get(playlistIndex).getLast() == null) {
            StdOut.println("EMPTY");
            return;
        }
        SongNode ptr;
        for (ptr = songLibrary.get(playlistIndex).getLast().getNext(); ptr != songLibrary.get(playlistIndex).getLast(); ptr = ptr.getNext() ) {
            StdOut.print(ptr.getSong().toString() + " -> ");
        }
        if (ptr == songLibrary.get(playlistIndex).getLast()) {
            StdOut.print(songLibrary.get(playlistIndex).getLast().getSong().toString() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    public void printLibrary() {
        if (songLibrary.size() == 0) {
            StdOut.println("\nYour library is empty!");
        } else {
                for (int ii = 0; ii < songLibrary.size(); ii++) {
                printPlaylist(ii);
            }
        }
    }

    /*
     * Used to get and set objects.
     * DO NOT edit.
     */
     public ArrayList<Playlist> getPlaylists() { return songLibrary; }
     public void setPlaylists(ArrayList<Playlist> p) { songLibrary = p; }
}
