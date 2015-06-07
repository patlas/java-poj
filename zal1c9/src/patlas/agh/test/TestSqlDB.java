package patlas.agh.test;

import java.util.ArrayList;

import patlas.agh.Downloader;
import patlas.agh.Parser;
import patlas.agh.SqlDB;
import patlas.agh.exception.MyException;

public class TestSqlDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<ArrayList<String>> transponders,transponders2;// = new ArrayList<ArrayList<String>>();
    	ArrayList<ArrayList<ArrayList<String>>> channels,channels2;
    	Downloader dw = new Downloader("http://pl.kingofsat.net/pos-13E_pol.php");
    	dw.run();
    	Parser parser = new Parser(dw);
    	
    	transponders = parser.getTransponder();
		channels = parser.getChannels();
		
		SqlDB db = new SqlDB("test.db");
		
		
		Downloader dw2 = new Downloader("http://pl.kingofsat.net/pos-13E-fta_pol.php");
    	dw2.run();
    	Parser parser2 = new Parser(dw2);
    	
    	transponders2 = parser2.getTransponder();
    	channels2 = parser2.getChannels();
		
		SqlDB db2 = new SqlDB("test.db");
		
		try {
			
			db.connectDB();
			db2.connectDB();
			db.insertTransponderRows(transponders);
			db.insertChannelRows(channels);
			db2.insertTransponderRows(transponders2);
			db2.insertChannelRows(channels2);
			//db.clearDB();
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
