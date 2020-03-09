import java.sql.*; 
import java.util.Scanner; 

public class LoopingQueryUniversity{ 
	public static void main(String args[]){
		//Edit the variables below to match a specific setup
        String host = "localhost";
        String port = "3306";
        String username = "Schmidt";
        String password = "7984";

        //Do not edit these variables
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://" + host + ":" + port + "/university?characterEncoding=latin1";
		
		// Scanner setup, table columnwidth, and first sqlquery 
		Scanner scanner = new Scanner(System.in);
		String columnWidth;
		System.out.print("Type columnwidth: ");  
		columnWidth = scanner.nextLine(); 
		int j = Integer.parseInt(columnWidth);
		String sqlQuery;
		System.out.println("Type sql query or 'exit': ");  
		sqlQuery = scanner.nextLine(); 
		
		// Repeating SQL queries until the user type: exit
		while (!sqlQuery.equals("exit")){ 
			try{ 
				Class.forName(driver);
				Connection connection = DriverManager.getConnection(url, username, password);
				Statement statement=connection.createStatement();  
				ResultSet resultSet=statement.executeQuery(sqlQuery); 
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int columnCount = resultSetMetaData.getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(rightPad(resultSetMetaData.getColumnName(i), j)); 
				}
				System.out.println();
					while(resultSet.next()) {
						for (int i = 1;i <= columnCount; i++){
						System.out.print(rightPad(resultSet.getString(i), j)); 
						}
					System.out.println(); 
					}
				System.out.println();
				connection.close(); 
			} catch(Exception e){ 
			System.out.println(e); 
			}
		System.out.println("Type sql query or 'exit': ");  
		sqlQuery = scanner.nextLine(); 
		}
		System.out.println("Session done!");	
		}
		public static String rightPad(String str, int num) {
			return String.format("%1$-" + num + "s", str); 
		}
}