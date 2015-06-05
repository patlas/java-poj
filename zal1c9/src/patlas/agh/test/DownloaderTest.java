package patlas.agh.test;

import java.net.MalformedURLException;
import java.net.URL;

import patlas.agh.Downloader;
import patlas.agh.Preference;
import patlas.agh.Preferences;

public class DownloaderTest {

	public static void main(String[] args) {


		URL url = null;
		try {
			url = new URL("http://onet.pl");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if(url != null)
			new Downloader(url).downloadPage();
		
		
		new Downloader("http://seguro.pl").downloadPage();
		new Downloader("http://tme.pl","tme2.html").downloadPage();
		
		Preference pref = new Preference("http://allegro.pl",7);
		Preferences.saveSettings();
		
		//System.out.println(pref.getAddr() + pref.getNumTry());
		new Downloader(pref).downloadPage();
		
		System.out.println("Przyk³adowe pobieranie");

	}

}
