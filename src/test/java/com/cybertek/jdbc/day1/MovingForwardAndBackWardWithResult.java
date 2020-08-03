package com.cybertek.jdbc.day1;

import java.sql.*;

public class MovingForwardAndBackWardWithResult {
    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@3.81.133.103:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        // if we create the Statement in this way , this will generate a forward only resultset
        // meaning we can only move forward with next() and can not move backward with previous
        // Statement stmnt = conn.createStatement();
        // ResultSet.TYPE_SCROLL_INSENSITIVE will make the resultset created from this statement
        // be able to move forward and backward,
        // ResultSet.CONCUR_READ_ONLY  will make resultset readonly and that's what we need

        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY) ;

        //ResultSet rs   =   stmnt.executeQuery("SELECT * FROM COUNTRIES WHERE REGION_ID = 1") ;
        ResultSet rs   =   stmnt.executeQuery("SELECT * FROM COUNTRIES") ;

        rs.next() ;
        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));
        rs.next() ;
        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));

        // how do i go back to previous row
        rs.previous();
        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));

        // moving the cursor freely between rows
//        rs.previous(); // we are at the first row, this will moves us to before first location so we get the error
//        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));

        // moving to the last row direction
        rs.last(); // this will move the cursor to the last location
        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));

        // moving to the first row direction
        rs.first();  // this will move the cursor to the first location
        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));

        // moving to the specific row direction
        rs.absolute(5); // this will move the cursor to the 5th location
        System.out.println(rs.getString("COUNTRY_ID") + " " + rs.getString("COUNTRY_NAME"));

        // how to move to before first row location
        rs.beforeFirst(); // this will move the cursor to the before first location
        rs.isBeforeFirst();  // to make sure we are at the before first row

        // how to move to after last row location
        rs.afterLast(); // this will move the cursor to the after last location
        rs.isAfterLast(); // to make sure we are at the after last row


    }

}
