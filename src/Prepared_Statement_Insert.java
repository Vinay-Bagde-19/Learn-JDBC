import java.sql.*;
import java.util.Scanner;

public class Prepared_Statement_Insert {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Query
        String query = "INSERT INTO student(rollNo, name, course, fees) VALUES(?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");  // Loading jdbc Drivers
            System.out.println("Drivers Loaded successfully!!");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully !!");

            PreparedStatement preparedStatement = con.prepareStatement(query);
//            preparedStatement.setInt(1,6); //Here pass the placeholder number and its value as parameter
//            preparedStatement.setString(2,"Sayli");
//            preparedStatement.setString(3,"BSc. IT");
//            preparedStatement.setDouble(4,40000);

            Scanner input = new Scanner(System.in);
            System.out.print("Enter the roll number : ");
            int rollNo = input.nextInt();
            System.out.print("Enter the name : ");
            String name = input.next();
            System.out.print("Enter the course : ");
            String course = input.next();
            System.out.print("Enter the fees : ");
            double fees = input.nextDouble();

            preparedStatement.setInt(1,rollNo);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,course);
            preparedStatement.setDouble(4,fees);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Data inserted Successfully !!");
            }else {
                System.out.println("Data insertion Failed!");
            }

            preparedStatement.close();
            con.close();
            System.out.println();
            System.out.println("Connection Closed Successfully !!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
