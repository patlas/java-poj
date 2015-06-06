package patlas.agh.test;

import java.util.ArrayList;

import patlas.agh.Downloader;
import patlas.agh.Parser;
import patlas.agh.SqlDB;

public class TestSqlDB {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<ArrayList<String>> transponders;// = new ArrayList<ArrayList<String>>();
    	ArrayList<ArrayList<ArrayList<String>>> channels;
    	Downloader dw = new Downloader("http://pl.kingofsat.net/pos-13E_pol.php");
    	dw.run();
    	Parser parser = new Parser(dw);
    	
    	transponders = parser.getTransponder();
		
		
		SqlDB db = new SqlDB();
		
		db.connectDB("test.db");
		db.insertRow(transponders);

	}

}
