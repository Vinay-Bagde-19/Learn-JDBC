package Image_Handling;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ImageHandling {
    public static void main(String[] args) {
        //Database URL
        String url = "jdbc:mysql://localhost:3306/mydatabase";

        //Database credentials
        String username = "root";
        String password = "19112003";

        //Image path
        String imagePath = "C:\\Users\\Asus\\Pictures\\Download Images\\Butterfly image.jpg";

        //Query
        String query = "INSERT INTO image_table(imageData) VALUES(?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");  // Loading jdbc Drivers
            System.out.println("Drivers Loaded successfully!!");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Established Successfully!!");

            //Converting image into Binary format using FileInputStream class
            FileInputStream fileInputStream = new FileInputStream(imagePath);
            byte[] ImageData = new byte[fileInputStream.available()]; //Binary data of image is stored in the array ImageData
            fileInputStream.read(ImageData);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1,ImageData);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                System.out.println("Image inserted successfully !!");
            }else {
                System.out.println("Image insertion Failed!");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
