import java.sql.*;

public class Insert {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Query to Perform
        String query = "INSERT INTO student(rollNo, name, course, fees) VALUES(4, 'Kunal', 'BSc. Data Science', 45000.0);";

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
            //executeUpdate used to store or updated data of database

            if (rowsAffected > 0){
                System.out.printf("Insert Successfully %d row(s) affected", rowsAffected);
            }else {
                System.out.println("Insertion Failed !!");
            }

            stmt.close();
            con.close();

            System.out.println("\nConnection Closed Successfully !!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
