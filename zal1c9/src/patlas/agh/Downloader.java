package patlas.agh;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Downloader implements Runnable {

	private URL url;
	private File file;
	public int numtry = 1;
	public static long TIMEOUT=0;;
	private String fName;
	
	public Boolean isDownloaded = false;
	private String dir= "web_pages/";
	
	
	public static void setTimeout(long time)
	{
		TIMEOUT = time;
	}
	
	
	private static String MD5string(String str) //tworzenie nazw plików je¿eli takowych nie podano
	{
		MessageDigest md5;
		String ret = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(StandardCharsets.UTF_8.encode(str));
			ret = String.format("%032x", new BigInteger(1, md5.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();//DODAÆ LOGGER!!!
		}
		//System.out.println(String.valueOf(Math.abs(str.hashCode())));
		//Integer x = Integer.valueOf("895487832138352138574", 16);
		//System.out.println(ret);
				
		return String.valueOf(Math.abs(str.hashCode()));//ret;
	}
	
	
	/*public Boolean downloadPage()
	{
		return this.downloadPage(StandardCharsets.UTF_8);
	}*/
	
	public void run(/*Charset charSet*/)
	{
	    InputStream is = null;
	    BufferedReader br;
	    BufferedWriter bw = null;
	    String line;
	    System.out.println("Teraz dzialam ja:" + url.toString()); //zakomentowac !!
	    
	    System.setProperty("http.agent", ""); 
	    
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
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is/*,charSet*/));
	        bw = new BufferedWriter(new FileWriter(file,false));
	        //PrintWriter writer = new PrintWriter( new FileOutputStream(file, false) );
	        while ((line = br.readLine()) != null) {
	            //System.out.println(line);
	        	bw.write(line);
	        	bw.newLine();
	        }
	    } catch (MalformedURLException mue) {
	         mue.printStackTrace();
	    } catch (IOException ioe) {
	         ioe.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	            if (bw != null) bw.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    isDownloaded = true;
	    //return true;
	}
	
	public String getStringUrl()
	{
		return url.toString();
	}
	
	
	public File getFile()
	{
		return file;
	}
	
	public Downloader(String urlString)
	{
		fName = "f_"+Downloader.MD5string(urlString)+".html";
		file = new File(dir+fName);
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	public Downloader(URL Url)
	{
		fName = "f_"+Downloader.MD5string(Url.toString())+".html";
		file = new File(dir+fName);
		url = Url;
		isDownloaded = true;
	}
	
	public Downloader(String urlString, String fileName)
	{
		if(fileName.contains("/") == true)
		{
			dir = "";	
			fName = fileName;
		}
		else
		{
			if(fileName.contains(".html")==true)
				fName = fileName;
			else
				fName = fileName+".html";
		}
		
		file = new File(dir+fName);
		
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		isDownloaded = true;
			
	}

	
	public Downloader(Preference pref)
	{
		String urlString = pref.getAddr();
		TIMEOUT = Preference.TIMEOUT;
		numtry = pref.getNumTry();
		
		fName = "f_"+Downloader.MD5string(urlString)+".html";
		file = new File(dir+fName);
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		isDownloaded = true;
	}
	
	
}
