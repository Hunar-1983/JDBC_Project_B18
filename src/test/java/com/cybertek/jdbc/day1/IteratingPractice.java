package com.cybertek.jdbc.day1;

import java.sql.*;

public class IteratingPractice {
    public static void main(String[] args) throws SQLException {

        String connectionStr = "jdbc:oracle:thin:@3.81.133.103:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        Statement stmnt = conn.createStatement();
       // ResultSet rs   =   stmnt.executeQuery("SELECT * FROM countries Where region_id = 2") ;
         ResultSet rs   =   stmnt.executeQuery("SELECT * FROM countries") ;

        while (rs.next()){
            System.out.println( rs.getString(1) + "    " +
                                rs.getString(2) + "    " +
                                rs.getString(3) );
        }
        rs.previous();  // here we get an error coz our statement does not let us to go back to previous step

    }
}
