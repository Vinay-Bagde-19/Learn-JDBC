import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBC_Structure {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/Students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Establish the connection
        try (Connection connection = DriverManager.getConnection(url, username, password)){
            System.out.println("Connected to the database.");
            System.out.println(connection);

            //Perform database operations here

        } catch (SQLException e){
            System.out.println("Connection Failed : " + e.getMessage());
        }
    }
}
