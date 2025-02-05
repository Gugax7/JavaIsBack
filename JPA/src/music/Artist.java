package music;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "artist_id")
    private int artist_id;

    @Column(name= "artist_name")
    private String artist_name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="artist_id")
    List<Album> albums = new ArrayList<>();

    public Artist() {
    }

    public Artist(String artistName){
        this.artist_name = artistName;
    }

    public Artist(int artist_id, String artist_name) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public void removeDuplicates(){
        var set = new TreeSet<>(albums);
        albums.clear();
        albums.addAll(set);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artist_id=" + artist_id +
                ", artist_name='" + artist_name + '\'' +
                ", albums='" + albums + '\'' +
                '}';
    }
}
