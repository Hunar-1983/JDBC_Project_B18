package com.cybertek.jdbc.Day2;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB_Utility {

    // adding static field so we can access in all static methods
    private static Connection conn;
    private static Statement stmnt;
    private static ResultSet rs ;


    /*
     * a static method to create connection
     * with valid url and username password
     * */
    public static void createConnection() {

        String connectionStr = "jdbc:oracle:thin:@52.71.242.164:1521:XE";
        String username = "hr";
        String password = "hr";

        try {
            conn = DriverManager.getConnection(connectionStr, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (SQLException e) {
            System.out.println("CONNECTION HAS FAILED!");
            e.printStackTrace();
        }

    }


    /*
     * */

    /**
     * a static method to get the ResultSet object
     * with valid connection by executing query
     * @param query
     * @return ResultSet object that contain the result just in cases needed outside the class
     */
    public static ResultSet runQuery(String query) {

        try {
            stmnt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            rs = stmnt.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    /**
     * cleaning up the resources
     */
    public static void destroy(){

        try{
            if(rs!=null){
                rs.close();
            }
            if(stmnt!=null){
                rs.close();
            }
            if(conn!=null){
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * @param columnIndex the column you want to get a list out of
     * @return List of String that contains entire column data from 1st row to last row
     */
    public static List<String> getColumnDataAsList(int columnIndex){

        List<String> columnDataLst = new ArrayList<>();
        try {
            rs.beforeFirst();  // moving the cursor to before first location

            while(rs.next() ){

                String data =  rs.getString(columnIndex) ;
                // getting the data from that column and adding to the the list
                columnDataLst.add( data  );

            }
            rs.beforeFirst();  // moving the cursor to before first location after we are done
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAsList ");
            e.printStackTrace();
        }

        return columnDataLst;
    }

    /**
     *
     * @param columnName the column you want to get a list out of
     * @return List of String that contains entire column data from the column name specified
     */
    public static List<String> getColumnDataAsList(String columnName){

        List<String> columnDataLst = new ArrayList<>();
        try {
            rs.beforeFirst();  // moving the cursor to before first location

            while(rs.next() ){

                String data =  rs.getString(columnName) ;
                // getting the data from that column and adding to the the list
                columnDataLst.add( data  );

            }
            rs.beforeFirst();  // moving the cursor to before first location after we are done
        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAsList ");
            e.printStackTrace();
        }

        return columnDataLst;
    }


    /**
     * We want to store certian row data as a map
     * give me number 3 row  --->> Map<String,String>   {region_id : 3 , region_name : Asia}
     */
    public static Map<String,String> getRowMap( int rowNum ){

        Map<String,String> rowMap = new HashMap<>();
        try{

            rs.absolute(rowNum);

            ResultSetMetaData rsmd = rs.getMetaData();
            for (int colNum = 1; colNum <= getColumnCNT() ; colNum++) {
                String colName = rsmd.getColumnName( colNum );
                String colValue= rs.getString( colNum ) ;
                rowMap.put(colName, colValue);
            }
            rs.beforeFirst();

        }catch (SQLException e){
            System.out.println("ERRROR AT ROW MAP FUNCTION");
        }

        return rowMap;
    }

    /**
     *
     * @return The entire resultset as List of Row Map
     */
    public static List<Map<String,String> > getAllDataAsListOfMap(){

        List<Map<String,String> > rowMapList = new ArrayList<>();
        for (int i = 1; i <= getRowCount(); i++) {
            rowMapList.add(   getRowMap(i)    ) ;
        }
        return rowMapList ;

    }



    /*
     * Getting single column cell value at certain row
     * row 2 column 3  -->> the data
     * */

    /**
     * Getting single column cell value at certain row
     * @param rowNum    row number we want to get data from
     * @param columnIndex  column index we want to get the data from
     * @return the data in String
     */
    public static String getColumnDataAtRow (int rowNum , int columnIndex){
        // take home tasks
        // imporve this method and check for valid rowNum and columnIndex
        // if invalid return emptyString
        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnIndex ) ;

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }

        return result ;
    }


    /**
     *
     * @param rowNum
     * @param columnName
     * @return the data at that row with that column name
     */
    public static String getColumnDataAtRow (int rowNum , String columnName){
        // take home tasks
        // imporve this method and check for valid rowNum and columnIndex
        // if invalid return emptyString
        String result = "" ;
        try {
            rs.absolute( rowNum ) ;
            result = rs.getString( columnName ) ;

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getColumnDataAtRow ");
            e.printStackTrace();
        }

        return result ;
    }

    /**
     * Get the row count of the resultSet
     * @return the row number of the resultset given
     */
    public static int getRowCount(){

        int rowCount = 0 ;

        try {
            rs.last(); // move to last row
            rowCount = rs.getRow(); // get the row number and assign it to rowCount
            // moving back the cursor to before first location just in case
            rs.beforeFirst();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ERROR WHILE GETTING ROW COUNT");
        }
        return rowCount ;
    }

    /*
     * a method to get the column count of the current ResultSet
     *
     *   getColumnCNT()
     *
     * */
    public static int getColumnCNT() {

        int colCount = 0;

        try {
            ResultSetMetaData rsmd = rs.getMetaData();
            colCount = rsmd.getColumnCount();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE COUNTING THE COLUMNS");
            e.printStackTrace();
        }

        return colCount;
    }


    // getting the entire row as List<String>

    /**
     *
     * @param rowNum the row number you want the list from
     * @return List of String that contains the row data
     */
    public static List<String> getRowDataAsList(int rowNum){

        List<String> rowDataList = new ArrayList<>();
        // how to move to that Row with rowNum
        try {
            rs.absolute(rowNum);
            // iterate over each and every column and add the valie to the list
            for (int colNum = 1; colNum <=  getColumnCNT() ; colNum++) {
                rowDataList.add(    rs.getString( colNum)    );
            }
            //moving the cursor back to before first location just in case
            rs.beforeFirst();

        } catch (SQLException e) {
            System.out.println("ERROR WHILE getRowDataAsList ");
            e.printStackTrace();
        }

        return rowDataList;
    }





    /*
     * a method to display all the data in the result set
     *
     * */
    public static void displayAllData() {

        // get the first row data  | without knowing the column names
        int colCount = DB_Utility.getColumnCNT();
        // in order to get whole result cursor must be at before first location !

        try {
            // in order to start from beginning , we should be at beforefirst location
            rs.beforeFirst(); // this is for below loop to work
            while (rs.next() == true) { // row iteration

                for (int i = 1; i <= colCount; i++) { // column iteration
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println(); /// adding a blank line for next line
            }
            // now the cursor is at after last location
            // move it back to before first location so we can have no issue calling the method again
            rs.beforeFirst(); // this is for next method that might need to be at before first location

        } catch (SQLException e) {
            System.out.println("ERROR WHILE GETTING ALL DATA");
            e.printStackTrace();
        }

    }








}