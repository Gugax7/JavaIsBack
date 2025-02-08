import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import music.Artist;

public class Main {
    public static void main(String[] args) {
        try(var sessionFactory = Persistence.createEntityManagerFactory(
                "music");
            EntityManager entityManager = sessionFactory.createEntityManager()
        ){
            var transaction = entityManager.getTransaction();
            transaction.begin();
//            entityManager.persist(new Artist("Player Tauz"));
            Artist artist = entityManager.find(Artist.class, 103);
            System.out.println(artist);
            artist.removeDuplicates();
//            artist.setArtist_name("Tauz");
            //entityManager.remove(artist);

            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}