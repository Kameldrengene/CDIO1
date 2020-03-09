import java.sql.*;
// Java package for accessing and processing data in a relational database
import java.util.Scanner;
// Scanner class found in java.util package gets user console input

// Declaring the QueryUniversity class, main method and rightPad method
public class QueryUniversity {
    public static void main(String[] args) {
        //Edit the variables below to match a specific setup
        String host = "localhost";
        String port = "3306";
        String username = "Schmidt";
        String password = "7984";

        //Do not edit these variables
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" 
			+ host + ":" + port + "/university" + "?characterEncoding=latin1";
		// ?characterEncoding=latin1 makes codepages in database and Java compatible 

		// "try" defines a block to be tested for errors while it is being executed
        try {
            Class.forName(driver);
            Scanner scanner = new Scanner(System.in);
            String columnWidth;
            String sqlQuery;

            System.out.print("Type columnwidth: ");
			// Minimum length for all attribute names and values. For instance type: 10
            columnWidth = scanner.nextLine();
            int j = Integer.parseInt(columnWidth);

            System.out.println("Type sql query: ");
			// Provide user sql query. For instance type: SELECT * FROM instructor;
            sqlQuery = scanner.nextLine();

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
			
			// Print all attribute names
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rightPad(resultSetMetaData.getColumnName(i), j));
            }

            System.out.println();
			
			// Print all table rows
            while (resultSet.next()) {
				// Print all attribute values in the row
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(rightPad(resultSet.getString(i), j));
                }
                System.out.println();
            }

            connection.close();
		// "catch" defines a block to be executed, if an error occurs in the try block
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	// rightPad method pads short attributenames and values to same columnwidth
    public static String rightPad(String str, int num) {
        return String.format("%1$-" + num + "s", str);
    }
} 