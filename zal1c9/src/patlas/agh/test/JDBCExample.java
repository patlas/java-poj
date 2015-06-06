package patlas.agh.test;

//STEP 1. Import required packages
import java.sql.*;

public class JDBCExample {

	
 
 public static void main(String[] args) {
	 Connection c = null;
	 Statement stmt = null;
	    try {
	      //Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:../satelliteDB.db");
	      
	      stmt = c.createStatement();
	      String sql = "CREATE TABLE COM " +
	                   "(ID INT PRIMARY KEY     NOT NULL," +
	                   " NAME           TEXT    NOT NULL, " + 
	                   " AGE            INT     NOT NULL, " + 
	                   " ADDRESS        CHAR(50), " + 
	                   " SALARY         REAL)"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.close();
	      
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	  }

}