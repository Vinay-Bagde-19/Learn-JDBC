package Image_Handling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;

public class ImagesInFolder {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/mydatabase";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Image
        String folder_path = "C:\\Users\\Asus\\Pictures\\Download Images\\MySQL\\";

        //Query
        String query = "SELECT imageData from image_table where imageID = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");  // Loading jdbc Drivers
            System.out.println("Drivers Loaded successfully!!");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                byte[] imageData = resultSet.getBytes("imageData");
                String imagePath = folder_path + "ExpectedImage.jpg";
                FileOutputStream fileOutputStream = new FileOutputStream(imagePath);
                fileOutputStream.write(imageData);
                System.out.println("Image Found And Placed Successfully!!");

            }else {
                System.out.println("Image Not Found!");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
