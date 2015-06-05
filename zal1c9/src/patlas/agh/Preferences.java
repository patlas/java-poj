package patlas.agh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import patlas.agh.utils.TwoTypeList;

public class Preferences {

	
	static String prefFile = ".preferences";
	static String dir = "";
	public static List<Preference> prefList = new ArrayList<Preference>();
	
	
	
	public static void saveSettings()
	{
		File file = new File(dir+prefFile);
		
		if(!file.exists())
		{
			try{
				file.createNewFile();
			}
			catch(IOException ioe){
		         System.out.println("Exception occurred:"); //DODAÆ LOGGER!!!
		    	 ioe.printStackTrace();
			}
		}
		
		try {
				PrintWriter writer = new PrintWriter( new FileOutputStream(file, false) );	
				
				for(Preference pref : prefList )
				{
					writer.println("<PAGE>");
					writer.println("\t<ADDR>"+pref.getAddr()+"</ADDR>");
					writer.println("\t<TRY>"+pref.getNumTry()+"</TRY>");
					writer.println("</PAGE>");
					writer.println("");
				}
				
				writer.close();
		
		} catch (FileNotFoundException e) {
			e.printStackTrace(); //DODAÆ LOGGER!!!
		}
	}
	
	private static TwoTypeList<String,Integer> readSettings() // PRZEROBIÆ
	{
		
		File file = new File(dir+prefFile);
			
		if(!file.exists())
		{
			//napisaæ exception
			System.out.println("FILE DOESN'T EXIST!!");
			return null;
		}
		
		/*TESTS
		String html = "<PAGE><ADDR>Patryk</ADDR> <TRY>25</TRY></PAGE> <PAGE><ADDR>Test</ADDR></PAGE>";		
		Document doc = Jsoup.parse(html);
		*/
	
		Document doc;
		TwoTypeList<String,Integer> lst =  new TwoTypeList<String, Integer>();
		try {
			
			doc = Jsoup.parse(file, "UTF-8");
			//Element link = doc.select("TRY").first();
			ArrayList<Element> pageList = new ArrayList<Element>();
			pageList = doc.getElementsByTag("PAGE");
			
			
			for( Element page : pageList )
			{
				lst.addItem(page.select("ADDR").text(), Integer.parseInt(page.select("TRY").text(),10));
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lst;
	}
	
	public static void loadSettings()
	{
		prefList.clear();
		TwoTypeList<String,Integer> tmp = new TwoTypeList<String, Integer>();
		tmp = Preferences.readSettings();
		
		for(int index=0; index<tmp.size; index++){
			
			/*Preference a =*/new Preference(tmp.getAitem(index), tmp.getBitem(index));
			//System.out.println("_"+tmp.getAitem(index));
						
		}
	}
	
}
