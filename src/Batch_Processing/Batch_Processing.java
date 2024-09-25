package Batch_Processing;

import java.sql.*;
import java.util.Scanner;

public class Batch_Processing {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/students";

        //Database credentials
        String username = "root";
        String password = "19112003";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded Successfully!!");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");

            con.setAutoCommit(false);

        /*
            //Using Statement Interface
            Statement statement = con.createStatement();
            statement.addBatch("INSERT INTO student(rollNo, name, course, fees) VALUES(8, 'Nishant', 'BTech', 65000.0)");
            statement.addBatch("INSERT INTO student(rollNo, name, course, fees) VALUES(9, 'Yash', 'BE', 65000.0)");
            statement.addBatch("INSERT INTO student(rollNo, name, course, fees) VALUES(10, 'Sarvesh', 'BCCA', 65000.0)");

            int[] batchResult = statement.executeBatch();
            con.commit();
            System.out.println("Batch Executed Successfully!!!");

         */

            //Using Prepared Statement
            String query = "INSERT INTO student(rollNo, name, course, fees) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            Scanner input = new Scanner(System.in);

            while (true){
                System.out.print("Enter Roll No. : ");
                int rollNo = input.nextInt();
                System.out.print("Enter the Name : ");
                String name = input.next();
                System.out.print("Enter the Course : ");
                String course = input.next();
                System.out.print("Enter the fees : ");
                double fees = input.nextDouble();

                preparedStatement.setInt(1, rollNo);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, course);
                preparedStatement.setDouble(4, fees);

                preparedStatement.addBatch();
                System.out.print("Add more values Y/N : ");
                String choice = input.next();

                if (choice.equalsIgnoreCase("N")){
                    break;
                }
            }
            int[] batchResult = preparedStatement.executeBatch();
            con.commit();
            System.out.println("Batch Execute Successfully!!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
