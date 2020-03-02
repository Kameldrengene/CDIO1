package test;

import Services.DatabaseIO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseIOTest {
    public static void main(String[] args) {
        //DatabaseIO db = new DatabaseIO("root","root","localhost");
        DatabaseIO db = new DatabaseIO("C:/Users/blued/Documents/Github repos/CDIO1_2/CDIO1/test.db");
            db.connect();
//            db.update("CREATE DATABASE CDIO_DB_TEST;");
////            db.update("DROP DATABASE cdio_db_test;");
////            db.query("use university;");
            ResultSet query = db.query("SELECT * FROM userdto;");
            try {
                while(query.next()){
                    System.out.println();
                    System.out.print(query.getInt("userID")+" | ");
                    System.out.print(query.getString("userName")+" | ");
                    System.out.print(query.getString("ini")+" ");
                    System.out.print(query.getString("cpr")+" | ");
                    System.out.print(query.getString("password")+" | ");
                    System.out.print(query.getString("roles")+" | ");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            db.closeConnection();

    }
}
