package test;

import Services.SQLDatabaseIO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseIOTest {
    public static void main(String[] args) {
        SQLDatabaseIO db = new SQLDatabaseIO("root","root","localhost");{
            db.update("CREATE DATABASE CDIO_DB_TEST;");
            db.update("DROP DATABASE cdio_db_test;");
            db.query("use university;");
            ResultSet query = db.query("SELECT * FROM student;");
            try {
                while(query.next()){
                    System.out.println();
                    System.out.print(query.getInt("StudID")+" | ");
                    System.out.print(query.getString("StudName")+" | ");
                    System.out.print(query.getDate("Birth")+" ");
                    System.out.print(query.getString("DeptName")+" | ");
                    System.out.print(query.getInt("ToTCredits")+" | ");
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
