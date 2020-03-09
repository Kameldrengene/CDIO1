package Services;

import java.sql.*;

public class SQLDatabaseIO {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String DatabaseURL;
    private String USER;
    private String PASS;
    private String db_name = "cdio1_2020";
    private boolean connected = false;
    private Connection conn = null;
    private Statement stmt = null;


    public SQLDatabaseIO(String USER, String PASSWORD, String URL) {
        this.USER = USER;
        this.PASS = PASSWORD;
        this.DatabaseURL = "jdbc:mysql://" + URL + ":3306/";
    }

    public void connect() {
        if(!connected){
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            System.out.println("Connecting to DB");
            try{
                conn = DriverManager.getConnection(DatabaseURL, USER, PASS);
                connected = true;
            }catch(SQLException e){
                //System.out.println("Connecting failed");
                connected=false;
                e.printStackTrace();
            }
        } else{
            //System.out.println("Already connected");
        }
    }
    public void update(String query){
        if(!connected){
            //System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("use "+db_name);
                stmt.executeUpdate(query);
                //System.out.println(query+" has been executed");
            } catch (SQLException e) {
                //System.out.println(query+" failed to execute");
                e.printStackTrace();
            }
        }
    }

    public ResultSet query(String query){
        ResultSet result = null;
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("use "+db_name);
                result = stmt.executeQuery(query);
//                System.out.println(query+" has been executed");
            } catch (SQLException e) {
//                System.out.println(query+" failed to execute");
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close(){
        if(connected){
            try {
                conn.close();
                connected=false;
            } catch (SQLException e) {
                connected=true;
                e.printStackTrace();
            }

        }
    }

}
