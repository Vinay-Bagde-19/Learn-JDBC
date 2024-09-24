import java.sql.*;

public class Prepared_Statements {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Query
        String query = "SELECT * FROM student WHERE rollNo = ? AND course = ?";

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
            preparedStatement.setInt(1,3); //Here pass the placeholder number and its value as parameter
            preparedStatement.setString(2,"BCA");

            //Getting result and printing it
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int rollNo = resultSet.getInt("rollNo");
                String name = resultSet.getString("name");
                String course = resultSet.getString("course");
                double fees = resultSet.getDouble("fees");

                System.out.println();
                System.out.println("******************************");
                System.out.println("Roll No. : " + rollNo
                        + "\nStudent Name : " + name
                        + "\nCourse Enrolled in : " + course
                        + "\nFees : " + fees);
                System.out.println("******************************");
            }

            resultSet.close();
            preparedStatement.close();
            con.close();
            System.out.println();
            System.out.println("Connection Closed Successfully !!!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
