import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import com.mysql.cj.jdbc.*;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try{
            props.load(Files.newInputStream(Path.of("music.properties"), StandardOpenOption.READ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scanner sc = new Scanner(System.in);

        String albumName = sc.nextLine();
        //String query = "SELECT * FROM music.albumview WHERE album_name= '%s'".formatted(albumName);
        String query = """
                WITH RankedRows AS (
                                    SELECT *,
                                    ROW_NUMBER() OVER (ORDER BY artist_id) AS row_num
                                    FROM music.artists
                                    )
                                SELECT *
                                    FROM RankedRows
                                    WHERE row_num <= 10""";

        var dataSource = new MysqlDataSource();
        dataSource.setPort(Integer.parseInt(props.getProperty("port")));
        dataSource.setServerName(props.getProperty("serverName"));
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try(var connection = dataSource.getConnection(
                props.getProperty("user"), System.getenv("MYSQL_PASS")
        );
            Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);

            var meta = resultSet.getMetaData();
//            System.out.println("-------------------------------");

//            while(resultSet.next()){
//                System.out.printf("%d - %s - %s \n",
//                        resultSet.getInt("track_number"),
//                        resultSet.getString("artist_name"),
//                        resultSet.getString("song_title"));
//            }

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
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}