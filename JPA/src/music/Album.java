package music;

import jakarta.persistence.*;

@Entity
@Table(name = "albums")
public class Album implements Comparable<Album> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int album_id;

    @Column(name= "album_name")
    private String album_name;

    public Album() {
    }

    public Album(String albumName){
        this.album_name = albumName;
    }

    public Album(int album_id, String album_name) {
        this.album_id = album_id;
        this.album_name = album_name;
    }

    public String getAlbum_name() {
        return album_name;
    }

    public void setAlbum_name(String album_name) {
        this.album_name = album_name;
    }

    @Override
    public String toString() {
        return "album{" +
                "album_id=" + album_id +
                ", album_name='" + album_name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Album o) {
        return album_name.compareTo(o.getAlbum_name());
    }
}
