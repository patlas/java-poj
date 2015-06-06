package patlas.agh.test;

import java.util.ArrayList;

import patlas.agh.Downloader;
import patlas.agh.Parser;

public class TestParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		ArrayList<ArrayList<String>> transponders;// = new ArrayList<ArrayList<String>>();
    	ArrayList<ArrayList<ArrayList<String>>> channels;
    	Downloader dw = new Downloader("http://pl.kingofsat.net/pos-13E_pol.php");
    	dw.run();
    	Parser parser = new Parser(dw);
    	transponders = parser.getTransponder();

    	System.out.println("TRANSPONDERS LIST:");
        
        for(ArrayList<String>  tr : transponders)
        {
        	for( String t : tr)
        	{
        		System.out.println(t);
        	}
        	System.out.println("-----------------");
        }
        
        System.out.println("\n\nAll avaliable channels in transponders:");
        
    	channels = parser.getChannels();
    	//System.out.println(channels.size());
    	for(ArrayList<ArrayList<String>>  ch : channels)
        {
    		//System.out.println(ch.size());
        	for( ArrayList<String> c : ch)
        	{
        		for (String s : c)
        			System.out.println(s);
        		System.out.println("-----------------");
        	}
        	//break;
        	System.out.println("+++++++++++++++++");
        }
    	
	}

}
