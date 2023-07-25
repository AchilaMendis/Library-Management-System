package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbCon {
    static Connection con;
    static String url = "jdbc:mysql://localhost/library_db";
    static String uname = "root";
    static String pass = "";


    public static Connection getConnection() throws Exception{
        if(con == null){
            con = DriverManager.getConnection(url,uname, pass);
        }
        return con;
    }

}