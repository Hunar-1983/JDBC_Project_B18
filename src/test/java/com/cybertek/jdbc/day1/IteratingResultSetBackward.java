package com.cybertek.jdbc.day1;

import java.sql.*;

public class IteratingResultSetBackward {
    public static void main(String[] args) throws SQLException {
        String connectionStr = "jdbc:oracle:thin:@3.81.133.103:1521:XE";
        String username = "hr";
        String password = "hr";

        Connection conn = DriverManager.getConnection(connectionStr,username,password) ;
        Statement stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY) ;
        ResultSet rs   =   stmnt.executeQuery("SELECT * FROM Regions") ;

        // first move to the after last location
        // then keep moving to previous row as long as there is more row above
        rs.afterLast();
//        System.out.println(  rs.isAfterLast()  );

//        rs.previous();
//        System.out.println(   rs.getString(1) + "   "+ rs.getString(2)   );

        while (rs.previous()){ // to get all the result from the last to the first row
            System.out.println(   rs.getString(1) + "   "+ rs.getString(2)   );
        }

        System.out.println("------------- more moving -------------");
        // how to move to second row from here
        rs.absolute(2);
        System.out.println(   rs.getString(1) + "   "+ rs.getString(2)   );

        rs.first(); // to move to the first row
        System.out.println(   rs.getString(1) + "   "+ rs.getString(2)   );

        rs.last(); // to move to the last row
        System.out.println(   rs.getString(1) + "   "+ rs.getString(2)   );





    }


}
