import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class delete {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Query to Perform
        String query = "DELETE FROM student WHERE rollNo = 4;";

        // Loading jdbc Drivers
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers Loaded successfully!!");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            Statement stmt = con.createStatement();
            int rowsAffected = stmt.executeUpdate(query);
            //executeUpdate used to store or update data of database

            if (rowsAffected > 0){
                System.out.printf("Delete Successfully %d row(s) affected", rowsAffected);
            }else {
                System.out.println("Deletion Failed !!");
            }

            stmt.close();
            con.close();

            System.out.println("\nConnection Closed Successfully !!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
