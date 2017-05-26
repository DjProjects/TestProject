package com.catracker.utility;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class SQLserverConnection {
 /**
  * @param args
  */
  public SQLserverConnection() throws ClassNotFoundException, SQLException {
//   String query = "Select EmpId FROM tblRegisteredEmployees where Gender='M'"; 
   String query = "SELECT * from Newtesting;"; 
  // String ss=GlobalFunctions.launchProperties(browser);
   Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
      //   Connection connection = DriverManager.getConnection("jdbc:sqlserver://167.114.119.195;database=VS_Dev;integratedSecurity=true;");
   //String DBName=
   
   
   Connection connection = DriverManager.getConnection("jdbc:sqlserver://167.114.119.195;database=VS_QA;","Sa", "Vertiv@2016");
         
         
         
         Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(query);
       while(rs.next()){
    	  String C_ID=rs.getString("C_ID"); 
          String C_Name=rs.getString("C_Name");
          String C_Address=rs.getString("C_Address");
          
          System.out.println(C_ID+" "+C_Name+" "+C_Address);
        
          
         }
     }
     public static void main(String[] args) throws ClassNotFoundException, SQLException {
     new SQLserverConnection();
     }
  }
