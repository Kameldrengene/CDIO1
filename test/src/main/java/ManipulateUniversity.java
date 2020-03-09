import java.sql.*;
import java.util.Scanner;

public class ManipulateUniversity {
    public static void main(String[] args) {
        //Edit the variables below to match a specific setup
        String host = "localhost";
        String port = "3306";
        String username = "Schmidt";
        String password = "7984";

        //Do not edit these variables
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" 
			+ host + ":" + port + "/university?characterEncoding=latin1";

        try {
            Class.forName(driver);
            Scanner scanner = new Scanner(System.in);
            String sqlManipulation;

            System.out.println("Type sql manipulation: ");
            // Provide user sql manipulation with INSERT, UPDATE, DELETE, CREATE or DROP
            sqlManipulation = scanner.nextLine();

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlManipulation);
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}