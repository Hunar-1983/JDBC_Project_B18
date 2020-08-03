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

        // as long as rs.next() return true I know i have next row to print the data
        // we will keep loop as long as rs.next() return true
        while (rs.next()){
            System.out.println( rs.getString("REGION_ID") + " " + rs.getString("REGION_NAME") );
        }

        // task #1
        // Creating new

/*
        rs.next(); // currently we are at the first row
        System.out.println( rs.getString("REGION_ID") + " " + rs.getString("REGION_NAME") );

        rs.next(); // currently we are at the second row
        System.out.println( rs.getString(1) + " " + rs.getString(2) );

        rs.next(); // currently we are at the third row
        System.out.println( rs.getString("REGION_ID") + " " + rs.getString("REGION_NAME") );

        rs.next(); // currently we are at the forth row
        System.out.println( rs.getString(1) + " " + rs.getString(2) );

        System.out.println( rs.next()  ); // currently we are at the fifth row
        // System.out.println( rs.getString(1) + " " + rs.getString(2) );
*/



    }
}

