import java.sql.*;

public class select {
    public static void main(String[] args) throws ClassNotFoundException{
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Query to Perform
        String query = "SELECT * FROM student;";

        try {
        Class.forName("com.mysql.jdbc.Driver");  // Loading jdbc Drivers
            System.out.println("Drivers Loaded successfully!!");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established successfully");
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            //executeQuery used to retrieve data from database

            while (result.next()){
                int rollNo = result.getInt("rollNo");
                String name = result.getString("name");
                String course = result.getString("course");
                double fees = result.getDouble("fees");

                System.out.println();
                System.out.println("******************************");
                System.out.println("Roll No. : " + rollNo
                        + "\nStudent Name : " + name
                        + "\nCourse Enrolled in : " + course
                        + "\nFees : " + fees);
            }
            result.close();
            stmt.close();
            con.close();

            System.out.println("\nConnection Closed Successfully !!");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
