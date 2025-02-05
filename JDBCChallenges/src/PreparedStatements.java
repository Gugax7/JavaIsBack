import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class PreparedStatements {

    private static String ARTIST_INSERT =
            "INSERT INTO music.artists (artist_name) VALUES (?)";
    private static String ALBUM_INSERT =
            "INSERT INTO music.albums (artist_id, album_name) VALUES (?, ?)";
    private static String SONG_INSERT =
            "INSERT INTO music.songs (album_id, track_number, song_title) VALUES (?, ?, ?)";
    public static void main(String[] args) {
        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

        try{
            dataSource.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try(Connection conn = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS")))
        {
            addDataFromFile(conn);

            String sql = "SELECT * FROM music.albumview where artist_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"Bob Dylan");
            ResultSet rs = preparedStatement.executeQuery();
            printRecords(rs);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("DEEEEEEEEEEU PAAAAAAAAAAAU");
        }
    }

    public static boolean printRecords(ResultSet resultSet) throws SQLException {
        boolean foundData = false;
        var meta = resultSet.getMetaData();

        System.out.println("-------------------------------");
        for(int i = 1; i <= meta.getColumnCount(); i++){
            System.out.printf("%-15s",meta.getColumnName(i).toUpperCase());
        }
        System.out.println();
        while(resultSet.next()){
            for(int i = 1; i <= meta.getColumnCount();i++){
                System.out.printf("%-15s",resultSet.getString(i));
            }
            System.out.println();
            foundData = true;
        }

        return foundData;
    }

    private static int addArtist(PreparedStatement ps, Connection conn,
                                 String artistName) throws SQLException{

        int artist_id = -1;
        ps.setString(1, artistName);
        int insertedCount = ps.executeUpdate();
        if(insertedCount > 0){
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                artist_id = generatedKeys.getInt(1);
                System.out.println("Auto-incremented ID: " + artist_id);
            }
        }
        return artist_id;

    }

    private static int addAlbum(PreparedStatement ps, Connection conn, int artist_id,
                                 String albumName) throws SQLException{

        int album_id = -1;
        ps.setInt(1,artist_id);
        ps.setString(2, albumName);
        int insertedCount = ps.executeUpdate();
        if(insertedCount > 0){
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if(generatedKeys.next()){
                album_id = generatedKeys.getInt(1);
                System.out.println("Auto-incremented ID: " + album_id);
            }
        }
        return album_id;

    }

    private static void addSong(PreparedStatement ps, Connection conn,int album_id,
                               int trackNo, String songTitle) throws SQLException{

        ps.setInt(1,album_id);
        ps.setInt(2, trackNo);
        ps.setString(3,songTitle);
        ps.addBatch();
    }
    private static void addDataFromFile(Connection conn) throws SQLException{

            List<String> records = null;
        try {
            records = Files.readAllLines(Path.of("NewAlbums.csv"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String lastAlbum = null;
        String lastArtist = null;
        int albumId = -1;
        int artistId = -1;
        try(PreparedStatement psArtist = conn.prepareStatement(ARTIST_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psAlbum = conn.prepareStatement(ALBUM_INSERT,
                    Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psSong = conn.prepareStatement(SONG_INSERT,
                    Statement.RETURN_GENERATED_KEYS)){

            conn.setAutoCommit(false);

            for(String record : records){
                String[] columns = record.split(",");
                if(lastArtist == null || !lastArtist.equals(columns[0])){
                    lastArtist = columns[0];
                    artistId = addArtist(psArtist,conn,lastArtist);
                }
                if(lastAlbum == null || !lastAlbum.equals(columns[1])){
                    lastAlbum = columns[1];
                    albumId = addAlbum(psAlbum,conn,artistId,lastAlbum);
                }
                addSong(psSong,conn,albumId,
                        Integer.parseInt(columns[2]), columns[3]);
                //System.out.println(columns[0] + " " + columns[1] + " " + columns[2] + " " + columns[3] + " " + " " + columns.length);
            }
            int[] inserts = psSong.executeBatch();
            int totalInserts = Arrays.stream(inserts).sum();
            System.out.println("Total music inserts = " + totalInserts);
            conn.commit();
            conn.setAutoCommit(true);
        }catch (SQLException e){
            System.out.println("DEEEEEEEEEEU PAAAAAAAAAAAU");
            e.printStackTrace();
            conn.rollback();
        }
    }


}
