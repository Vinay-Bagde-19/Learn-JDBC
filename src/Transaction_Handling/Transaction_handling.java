package Transaction_Handling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transaction_handling {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/mydatabase";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Queries
        String withdrawQuery = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
        String depositQuery = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers Loaded successfully!!");

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");
            con.setAutoCommit(false); //Setting Auto Commiting to false now we have to manually commit changes

            try {
                PreparedStatement withdrawStatement = con.prepareStatement(withdrawQuery);
                PreparedStatement depositStatement = con.prepareStatement(depositQuery);

                //Taking 500 from account123
                withdrawStatement.setDouble(1, 500.00);
                withdrawStatement.setString(2, "account123");

                //Giving or adding 500 to account456
                depositStatement.setDouble(1, 500.00);
                depositStatement.setString(2,"account456");

                //Executing both the queries
                int rowsAffectedWithdraw = withdrawStatement.executeUpdate();
                int rowsAffectedDeposit = depositStatement.executeUpdate();

                if (rowsAffectedWithdraw > 0 && rowsAffectedDeposit > 0){
                    con.commit();
                    System.out.println("Transaction Successful!!");
                }else {
                    con.rollback();  // If transaction not successful rollback to origin or original
                    System.out.println("Transaction Failed!");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
