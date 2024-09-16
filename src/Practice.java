import java.sql.*;

public class Practice {
    public static void main(String[] args) {
        String userName = "root";
        String password = "19112003";
        String url = "jdbc:mysql://localhost:3306/practice";
        String query = "INSERT INTO employee (id, empName, empDepartment, salary) VALUES(101, 'VINAY BAGDE', 'Java Development', 50000.0)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded Successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Connection established Successfully!!");
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(query);

            if (rowAffected > 0){
                System.out.println("Inserted Successfully !!");
            }else {
                System.out.println("Insertion Failed !!");
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
