package com.cybertek.jdbc.day1;

import java.sql.*;

public class IteratingResultSet {
    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@3.81.133.103:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        Statement stmnt = conn.createStatement();
        ResultSet rs   =   stmnt.executeQuery("SELECT * FROM REGIONS") ;

        rs.next();
        System.out.println( rs.getString(1) + " " + rs.getString(2) );

        rs.next();
        System.out.println( rs.getString(1) + " " + rs.getString(2) );



    }
}

