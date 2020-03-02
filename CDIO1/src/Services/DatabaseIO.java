package Services;

import java.sql.*;

public class DatabaseIO {
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String sqlite_DRIVER = "org.sqlite.JDBC";
    private String DatabaseURL;
    private String USER;
    private String PASS;
    private boolean connected = false;
    private Connection conn = null;
    private Statement stmt = null;
    private String dbType = "";


    public DatabaseIO(String USER, String PASSWORD, String URL) {
        this.USER = USER;
        this.PASS = PASSWORD;
        this.DatabaseURL = "jdbc:mysql://" + URL + ":3306/";
        dbType = "mysql";
    }

    public DatabaseIO(String URL) {
        this.DatabaseURL = "jdbc:sqlite:"+URL;
        dbType = "sqlite";
    }

    public void connect() {
        if(!connected){
            try {
                if(dbType.equals("mysql")){
                    Class.forName(JDBC_DRIVER);
                } else if(dbType.equals("sqlite")){
                    Class.forName(sqlite_DRIVER);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println("Connecting to DB");
            try{
                if(dbType.equals("mysql")){
                    conn = DriverManager.getConnection(DatabaseURL, USER, PASS);
                } else if(dbType.equals("sqlite")){
                    conn = DriverManager.getConnection(DatabaseURL);
                }

                connected = true;
            }catch(SQLException e){
                System.out.println("Connecting failed");
                connected=false;
                e.printStackTrace();
            }
        } else{
            System.out.println("Already connected");
        }
    }
    public void update(String query){
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
                System.out.println(query+" has been executed");
            } catch (SQLException e) {
                System.out.println(query+" failed to execute");
                e.printStackTrace();
            }
        }
    }

    public void insert(int userID, String userName, String ini, String cpr, String password, String roles){
        PreparedStatement ps;
        String sql = "INSERT INTO userdto (userID, userName, ini, cpr, password, roles) VALUES(?, ?, ?, ?, ?, ?);";
        try{
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2,userName);
            ps.setString(3,ini);
            ps.setString(4,cpr);
            ps.setString(5,password);
            ps.setString(6,roles);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(sql + " failed");
            e.printStackTrace();
        }
        System.out.println(sql + " executed");
    }

    public ResultSet query(String query){
        ResultSet result = null;
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                result = stmt.executeQuery(query);
                System.out.println(query+" has been executed");
            } catch (SQLException e) {
                System.out.println(query+" failed to execute");
                e.printStackTrace();
            }
        }
        return result;
    }

    public void closeConnection(){
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
