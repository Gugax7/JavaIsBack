import java.sql.*;
import java.util.Arrays;

public class MusicDML {
    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/music",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS")
        );
            Statement statement = connection.createStatement())
        {
            String tableName = "music.artists";
            String columnName = "artist_name";
            String columnValue = "Jorge";
            if(!executeSelect(statement,tableName,columnName,columnValue)){
              insertArtistAlbum(statement,columnValue,columnValue);
            }
            else{
                //updateRecord(statement,tableName,columnName,columnValue,columnName,columnValue.toUpperCase());
                deleteArtistAlbum(connection,statement,columnValue,columnValue);
                executeSelect(statement,"music.albumview", "album_name", columnValue);
                executeSelect(statement,"music.albums", "album_name",columnValue);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    private static boolean executeSelect(Statement statement, String table, String columnName,String columnValue) throws SQLException {
        String query = "SELECT * FROM %s WHERE %s='%s'"
                .formatted(table,columnName,columnValue);
        var rs = statement.executeQuery(query);
        if(rs != null){
            return printRecords(rs);
        }
        return false;

    }
    private static boolean insertRecord(Statement statement, String table,
                                        String[] columnNames, String[] columnValues) throws SQLException{
        String colNames = String.join(",", columnNames);
        String colValues = String.join("','", columnValues);
        String query = "INSERT INTO %s (%s) VALUES ('%s')"
                .formatted(table, colNames, colValues);
        System.out.println(query);
        boolean insertResult = statement.execute(query);
//        System.out.println("Insert result: " + insertResult);
//        return insertResult;
        // this query is an update, so it will return always false.

        int recordsInserted = statement.getUpdateCount();
        if(recordsInserted > 0){
            executeSelect(statement,table,columnNames[0],columnValues[0]);
        }
        return recordsInserted > 0;

    }

    private static boolean updateRecord(Statement statement, String table,
                                        String matchedColumn, String matchedValue,
                                        String updatedColumn, String updatedValue)
            throws SQLException
    {
        String query = "UPDATE %s SET %s='%s' WHERE %s='%s'"
                .formatted(table,updatedColumn,updatedValue,matchedColumn,matchedValue);
        System.out.println(query);
        statement.execute(query);
        int recordsUpdated = statement.getUpdateCount();
        if(recordsUpdated > 0){
            executeSelect(statement,table,updatedColumn,updatedValue);
        }
        return recordsUpdated > 0;
    }

    public static void insertArtistAlbum(Statement statement, String artistName, String albumName)
        throws SQLException
    {
        String artistInsert = "INSERT INTO music.artists (artist_name) VALUES (%s)"
                .formatted(statement.enquoteLiteral(artistName));
        System.out.println(artistInsert);
        statement.execute(artistInsert, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = statement.getGeneratedKeys();
        int artistId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
        String albumInsert = ("INSERT INTO music.albums (album_name, artist_id)") + " VALUES (%s, %d)"
                .formatted(statement.enquoteLiteral(albumName), artistId);
        System.out.println(albumInsert);
        statement.execute(albumInsert, Statement.RETURN_GENERATED_KEYS);
        rs = statement.getGeneratedKeys();
        int albumId = (rs != null && rs.next()) ? rs.getInt(1) : -1;

        String[] songs = new String[]{
                "Ultimos de nos",
                "Pela espada",
                "Luz da humanidade",
                "Judas",
                "Guerra que velhos travam para jovens lutar"
        };
        String songInsert = "INSERT INTO music.songs " +
                "(track_number, song_title, album_id) VALUES (%d, %s, %d)";

        for(int i = 0; i < songs.length; i++){
            String songQuery = songInsert.formatted(i + 1, statement.enquoteLiteral(songs[i]), albumId);
            System.out.println(songQuery);

            statement.execute(songQuery);
        }
        executeSelect(statement,"music.albumview", "album_name", artistName);


    }

    private static void deleteArtistAlbum(Connection conn, Statement statement, String artistName, String albumName)
        throws SQLException{
        try{
            System.out.println("AUTO COMMIT -> " + conn.getAutoCommit());
            conn.setAutoCommit(false);
            String deleteSongs = """
                    DELETE FROM music.songs WHERE album_id=
                    (SELECT ALBUM_ID from music.albums WHERE album_name='%s')""".formatted(albumName);
//            int deletedSongs = statement.executeUpdate(deleteSongs);
//            System.out.printf("Deleted %d songs from music.songs%n",deletedSongs);
            String deleteAlbums = "DELETE FROM music.albums WHERE album_name='%s'".formatted(albumName);
//            int deletedAlbums = statement.executeUpdate(deleteAlbums);
//            System.out.printf("Deleted %d albums from music.albums%n",deletedAlbums);
            String deleteArtist = "DELETE FROM music.artists WHERE artist_name='%s'".formatted(artistName);

            System.out.println(artistName);
            statement.addBatch(deleteSongs);
            statement.addBatch(deleteAlbums);
            statement.addBatch(deleteArtist);
            int[] results = statement.executeBatch();
            System.out.println(Arrays.toString(results));
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
            conn.rollback();
        }
        conn.setAutoCommit(true);
    }
}
