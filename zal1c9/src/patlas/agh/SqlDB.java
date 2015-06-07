package patlas.agh;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import patlas.agh.exception.MyException;

public class SqlDB {
	
	Connection con = null;
	private static int FREE_POSITION=0;
	
	private String loc = null;
	private int myID = 0;
	
	public SqlDB(String localization)
	{
		loc = localization;
		myID = 1000*FREE_POSITION;
		FREE_POSITION++;
	}
	
	public void connectDB() throws MyException
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
		      		+ "(ID INT 			NOT NULL,"
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
			throw new MyException(e.toString());
			//e.printStackTrace();
		}
		
	}

	/*sString sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) " +
            "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );"; 
	*/
	public void insertTransponderRows(ArrayList<ArrayList<String>> transponders) throws MyException
	{
		
		if(con == null) throw new MyException("Database does not exist or is not open");
		
		int index = 0;
		for(ArrayList<String>  tr : transponders)
        {
			/*if(tr.size() == 10)//mamy radio
			{
				values+= "NULL, NULL,";
			}
			*/
			index++;
			StringBuilder values = new StringBuilder();
        	for( String t : tr)
        	{
        		values.append( " \""+t+"\",");
        		
        	}
        	values.deleteCharAt(values.length()-1);
        	
        	try {
				Statement stmt = con.createStatement();
				
				String sql =  "INSERT INTO TRANSPONDERS (ID,POSITION,SATELLITE,FREQ,POLAR,"
							+ "TRANSPONDER,STREAM,SYMBOL_RATE,FEC) "
							+ "VALUES("+(myID+index)+"," + values.toString()+");";
				
				stmt.executeUpdate(sql);
				//System.out.println(sql);
			} catch (SQLException e) {
				throw new MyException(e.toString());
				//e.printStackTrace();
			}
        }	
	}
	
	
	public void insertChannelRows(ArrayList<ArrayList<ArrayList<String>>> channelGroups) throws MyException
	{
		
		if(con == null) throw new MyException("Database does not exist or is not open");
		
		int index = 0;
		
		for(ArrayList<ArrayList<String>> channels : channelGroups)
		{
			index++;

			StringBuilder insertQuery = new StringBuilder();
			for(ArrayList<String>  ch : channels)
	        {
				
				StringBuilder values = new StringBuilder(); 
				Boolean nullAdded = true;
				
				if(ch.size() == 10)//mamy radio
				{
					nullAdded = false;
				}
				
				values.append("("+(myID+index)+",");
	        	for( String c : ch)
	        	{
	        		values.append( " \""+c+"\",");
	        		if(nullAdded == false)
	        		{
	        			values.append("NULL, NULL,");
	        			nullAdded = true;
	        		}
	        		
	        	}
	        	values.deleteCharAt(values.length()-1);
	        	values.append(')');
	        	insertQuery.append(values.toString()+", \n");
	        	
	        	//System.out.println(values.toString());
	        }	
			insertQuery.deleteCharAt(insertQuery.length()-3);
			
			//System.out.println(insertQuery.toString());
			//if(index>1) break;
			
			
			try {
				Statement stmt = con.createStatement();
				
				String sql =  "INSERT INTO CHANNELS (ID,NAME,COUNTRY,CATEGORY,PACKET,"
							+ "CODING,SID,VPID,AUDIO,PMT,PCR,TXT,LAST_UPDATE) "
							+ "VALUES "+ insertQuery.toString() +";";
				
				stmt.executeUpdate(sql);
				//System.out.println(sql);
			} catch (SQLException e) {
				throw new MyException(e.toString());
				//e.printStackTrace();
			}
		
		}
	}	
	
	
	
	public void clearDB() throws MyException
	{
		if(con == null) throw new MyException("Database does not exist or is not open");
		
		try {
			Statement stmt = con.createStatement();
			String sql =  "DELETE  FROM TRANSPONDERS; "
						+ "DELETE  FROM CHANNELS;";
			
			stmt.executeUpdate(sql);
			
		} catch (SQLException e) {

			throw new MyException(e.toString()); 
		}
		
	}
	

}
