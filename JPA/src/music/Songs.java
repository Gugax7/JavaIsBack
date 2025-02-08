package music;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "songs")
public class Songs {

    @Id
    @Column(name = "song_id")
    private int songId;
    @Column(name = "song_title")
    private String songTitle;
    @Column(name = "track_number")
    private int trackNumber;

    public Songs() {
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songId=" + songId +
                ", songTitle='" + songTitle + '\'' +
                ", trackNumber=" + trackNumber +
                '}';
    }
}
