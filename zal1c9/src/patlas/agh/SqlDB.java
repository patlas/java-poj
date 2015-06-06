package patlas.agh;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlDB {
	
	Connection con = null;
	
	public void connectDB(String loc)
	{
		Boolean exist = false;
		if((new File(loc)).exists() == true) exist = true;
		
		try {
			con = DriverManager.getConnection("jdbc:sqlite:"+loc);
			
			if(exist == false)
			{
				Statement stmt = con.createStatement();
				String sql = "CREATE TABLE TRANSPONDERS "
		                   + "(ID INT PRIMARY 	KEY     NOT NULL,"
		                   + " POSITION       	TEXT    NOT NULL, "
		                   + " SATELLITE      	TEXT    NOT NULL, "
		                   + " FREQ           	CHAR(50)," 
		                   + " POLAR          	CHAR(2),"
		                   + " TRANSPONDER	  	TEXT	NOT NULL,"
		                   + " STREAM			TEXT	NOT NULL,"
		                   + " SYMBOL_RATE		TEXT	NOT NULL,"
		                   + " FEC				TEXT	NOT NULL)"; 
				
		      stmt.executeUpdate(sql);
		      
		      sql =   "CREATE TABLE CHANNELS "
		      		+ "(ID INT PRIMARY 	KEY     NOT NULL,"
		      		+ " NAME			TEXT, "
		      		+ " COUNTRY			TEXT, "
		      		+ " CATEGORY		TEXT, "
		      		+ " PACKET			TEXT, "
		      		+ " CODING			TEXT, "
		      		+ " SID				TEXT, "
		      		+ " VPID			TEXT, "
		      		+ " AUDIO			TEXT, "
		      		+ " PMT				TEXT, "
		      		+ " PCR				TEXT, "
		      		+ " TXT             TEXT, "			
		      		+ " LAST_UPDATE		TEXT) ";
		      
		      stmt.executeUpdate(sql);
		      
		      
		      stmt.close();
		      con.close();			
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/*sString sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
            "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
	*/
	public void insertRow(ArrayList<ArrayList<String>> transponders)
	{
		String values = new String();
		
		for(ArrayList<String>  tr : transponders)
        {
			/*if(tr.size() == 10)//mamy radio
			{
				values+= "NULL, NULL,";
			}
			*/
        	for( String t : tr)
        	{
        		values+= " "+t+",";
        	}
        	System.out.println(values);
        	values = "";
        }
		
		
	}
	

}
