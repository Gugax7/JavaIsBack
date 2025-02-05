import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class CallableStatementsClass {

    private static final int ARTIST_COLUMN = 0;
    private static final int ALBUM_COLUMN = 1;
    private static final int SONG_COLUMN = 3;

    public static void main(String[] args) {

        Map<String, Map<String, String>> albums = null;

        try(var lines = Files.lines(Path.of("NewAlbums.csv"))) {
            albums = lines.map(s -> s.split(","))
                    .collect(Collectors.groupingBy(s->s[ARTIST_COLUMN],
                            Collectors.groupingBy(s->s[ALBUM_COLUMN],
                                    Collectors.mapping(s -> s[SONG_COLUMN],
                                            Collectors.joining(
                                                    "\",\"",
                                                    "[\"",
                                                    "\"]"
                                            )))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var dataSource = new MysqlDataSource();

        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("music");

        try(Connection conn = dataSource.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS")))
        {

            CallableStatement cs = conn.prepareCall(
                    "CALL music.addAlbumInOutCounts(?,?,?,?)"
            );
            albums.forEach((artist, albumMap) ->{
                albumMap.forEach((album,song)->{
                    try{
                        cs.setString(1,artist);
                        cs.setString(2,album);
                        cs.setString(3,song);
                        cs.setInt(4,10);
                        cs.registerOutParameter(4,Types.INTEGER);
                        cs.execute();
                        System.out.printf("%d songs were added to %s%n",cs.getInt(4), album);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            });

            String sql = "SELECT * FROM music.albumview WHERE artist_name = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,"Bob Dylan");
            ResultSet rs = preparedStatement.executeQuery();
            PreparedStatements.printRecords(rs);
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("DEEEEEEEEEEU PAAAAAAAAAAAU");
        }
    }
}
