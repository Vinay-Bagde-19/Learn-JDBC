import java.sql.*;

public class Update {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Query to Perform
        String query = "UPDATE student SET course = 'BCCA', fees = 18000 WHERE rollNo = 2;";

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
            //executeUpdate used to store data to database

            if (rowsAffected > 0){
                System.out.printf("Insert Successfully %d row(s) affected", rowsAffected);
            }

            stmt.close();
            con.close();

            System.out.println("\nConnection Closed Successfully !!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
