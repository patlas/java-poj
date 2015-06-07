package patlas.agh;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Parser {
	
	private File file = null;
	
	public Parser(Downloader webpage)
	{
		if(webpage.isDownloaded == true)
		{
			file = webpage.getFile(); //DODAÆ LOG!!
			//System.out.println(webpage.getFile().toString());
		}
//		System.out.println(webpage.getFile().toString());
	}
	
	public Parser(File f)
	{
		file = f;
	}
	
	public Parser(String fName)
	{
		file = new File(fName);
	}
	
	public ArrayList<ArrayList<String>> getTransponder(){
		
		//File file = new File("web_pages/hotbird.html");
		Document doc;
		
		ArrayList<ArrayList<String>> parsedTransponders = new ArrayList<ArrayList<String>>();
		
		try {
			doc = Jsoup.parse(file, "UTF-8");
			//Element link = doc.select("TRY").first();
			ArrayList<Element> transponderList = new ArrayList<Element>();
			//pageList = doc.getElementsByTag("PAGE");
			transponderList = doc.getElementsByClass("frq");
			
			ArrayList<String> transponderParams; 
				
			for(Element transponder : transponderList)
			{
				transponderParams = new ArrayList<String>();
				for(Element params : transponder.getElementsByClass("bld"))
				{
					transponderParams.add(params.text());
					//System.out.println(params.text());
				}
				//break;
				parsedTransponders.add(transponderParams);
				//System.out.println("----------------------------");
			}
			
		} catch (IOException e) {
			System.out.println("FILE IS NOT DOWNLOADED!!!"); //DODAÆ LOG!!!
			e.printStackTrace();
		}
		
		return parsedTransponders;
	}
	
	
	
public ArrayList<ArrayList<ArrayList<String>>> getChannels(){
		
		//File file = new File("web_pages/hotbird.html");
		Document doc;
		
		ArrayList<ArrayList<ArrayList<String>>> parsedTranspondersChannelList = new ArrayList<ArrayList<ArrayList<String>>>();
		ArrayList<ArrayList<String>> channelGroups;
		ArrayList<String> channelList;
		
		try {
			doc = Jsoup.parse(file, "UTF-8");
			//Element link = doc.select("TRY").first();
			ArrayList<Element> transponderChannelList = new ArrayList<Element>();
			//pageList = doc.getElementsByTag("PAGE");
			transponderChannelList = doc.getElementsByClass("fl");
			
			for(Element transponderCL : transponderChannelList)
			{
				channelGroups = new ArrayList<ArrayList<String>>();
				for(Element tCL : transponderCL.getElementsByTag("tr"))
				{
					int index = 0;
					channelList = new ArrayList<String>();
					for(Element tCG : tCL.getElementsByTag("td"))
					{
						if(index++ < 2) continue; //pomijanie 2 pierwszych kolumn "œmieci"
						//System.out.println(tCG.text());
						channelList.add(tCG.text());
					}
					channelGroups.add(channelList);

				}	
				
				parsedTranspondersChannelList.add(channelGroups);

			}
			
		} catch (IOException e) {
			System.out.println("FILE IS NOT DOWNLOADED!!!"); //DODAÆ LOG!!!
			e.printStackTrace();
		}
			
		return parsedTranspondersChannelList;
	}
	
	

}
