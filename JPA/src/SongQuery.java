import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.*;
import music.Album;
import music.Artist;
import music.Songs;

import java.util.List;

public class SongQuery {
    public static void main(String[] args) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("music");
            EntityManager em = emf.createEntityManager()){

            String dashedString = "-".repeat(19);
            String word = "Life";
            var matches = getMatchedSongs(em,"%"+word+"%");
            System.out.printf("%-30s %-65s %s%n", "Artist", "Album", "Song Title");
            System.out.printf("%1$-30s %1$-65s %1$s%n",dashedString);

            matches.forEach(a -> {
                String artistName = a.getArtist_name();
                a.getAlbums().forEach(b -> {
                    String albumName = b.getAlbum_name();
                    b.getPlaylist().forEach(s ->{
                        String songTitle = s.getSongTitle();
                        if(songTitle.contains(word)){
                            System.out.printf("%-30s %-65s %s%n", artistName,albumName,songTitle);
                        }
                    });
                });
            });

            System.out.printf("%-30s %-65s %s%n", "Artist", "Album", "Song Title");
            System.out.printf("%1$-30s %1$-65s %1$s%n",dashedString);
            var bmatches = getMatchedSongsBuilder(em,"%"+word+"%");

            bmatches.forEach(m -> {
                System.out.printf("%-30s %-65s %s%n", (String)m[0], (String)m[1], (String)m[2]);
            });

        }catch (Exception e){
            System.err.println(e.getMessage());
        }

    }

    private static List<Artist> getMatchedSongs(EntityManager em, String matchedValue){

        String jpql = "SELECT a FROM Artist a JOIN albums album join playlist p " +
                "WHERE p.songTitle LIKE ?1";
        var query = em.createQuery(jpql, Artist.class);
        query.setParameter(1, matchedValue);
//        query.setParameter(2,"%Best Of%");
        return query.getResultList();
    }

    private static List<Object[]> getMatchedSongsBuilder(EntityManager entityManager,
                                                         String matchedValue){

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Artist> root = query.from(Artist.class);
        Join<Artist, Album> albumJoin = root.join("albums", JoinType.INNER);
        Join<Album, Songs> songsJoin = albumJoin.join("playlist", JoinType.INNER);

        query
                .multiselect(
                        root.get("artist_name"),
                        albumJoin.get("album_name"),
                        songsJoin.get("songTitle")
                )
                .where(builder.like(songsJoin.get("songTitle"), matchedValue))
                .orderBy(builder.asc(root.get("artist_name")));

        return entityManager.createQuery(query).getResultList();
    }
}
