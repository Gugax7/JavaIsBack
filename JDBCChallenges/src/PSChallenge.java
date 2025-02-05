import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PSChallenge {

    private static String INSERT_ORDER =
            "INSERT INTO storefront.order (order_date) VALUES (?)";
    private static String INSERT_DETAILS =
            "INSERT INTO storefront.order_details (order_id, item_description, quantity)" +
                    " VALUES (?, ?, ?)";

    public static void main(String[] args) {

        var dataBase = new MysqlDataSource();
        dataBase.setServerName("localhost");
        dataBase.setPort(3306);
        dataBase.setDatabaseName("storefront");

        try{
            dataBase.setContinueBatchOnError(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try(Connection conn = dataBase.getConnection(
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASS"))){

            addOrderFromFile(conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static int addOrder(Connection conn, String[] items) throws SQLException{
        int orderId = -1;

        try(PreparedStatement psOrder = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psDetails = conn.prepareStatement(INSERT_DETAILS,Statement.RETURN_GENERATED_KEYS))
        {
            conn.setAutoCommit(false);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String orderDate = LocalDateTime.now().format(dtf);
            psOrder.setString(1,orderDate);
            int inserts = psOrder.executeUpdate();

            if(inserts == 1){
                var resultSet = psOrder.getGeneratedKeys();
                if(resultSet.next()) {
                    orderId = resultSet.getInt(1);
                }
            }
            for(String item : items){
                String[] details = item.split(",");
                psDetails.setInt(1,orderId);
                psDetails.setString(2,details[2]);
                psDetails.setInt(3,Integer.parseInt(details[1]));
                psDetails.addBatch();
            }
            int[] detail_inserts = psDetails.executeBatch();
            int totalInserts = Arrays.stream(detail_inserts).sum();
            if(totalInserts != items.length){
                conn.rollback();
            }
            else{
                conn.commit();
            }
        conn.setAutoCommit(true);
        }catch (SQLException e){
            System.err.println("Error on inserting order and details");
        }
        return orderId;
    }
    private static void addOrderFromFile(Connection conn) throws SQLException{

        List<String> orders = null;
        try{
            orders = Files.readAllLines(Path.of("Orders.csv"));
        }catch(IOException e){
            System.err.println("READ FILE GOES WRONG!");
        }
        if(orders != null){
            for(int i = 0; i < orders.size(); i++){
                String[] parts = orders.get(i).split(",");
                if(parts[0].equals("order")){
                    ArrayList<String> items = new ArrayList<>();
                        for (int j = i+1; j < orders.size(); j++) {
                            if(orders.get(j).split(",")[0].equals("order") && j != i){
                                break;
                            }
                            items.add(orders.get(j));
                        }

                    String[] itemsArray = items.toArray(String[]::new);
                    addOrder(conn,itemsArray);
                }
            }
        }

    }
}
