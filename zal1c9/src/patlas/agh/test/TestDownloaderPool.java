package patlas.agh.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import patlas.agh.Downloader;
import patlas.agh.DownloaderPool;
import patlas.agh.Preference;
import patlas.agh.Preferences;

public class TestDownloaderPool {

	public static void main(String[] args) {

		/*Wczytanie zawarto�ci pliku preferences*/
		Preferences.loadSettings();
		
		/* List obiekt�w Downloader - runnable */
		ArrayList<Downloader> list = new ArrayList<Downloader>();
		
		/* Wykorzystanie wczytanych preferencji do stworzenia obiekt�w runnable
		 * i umieszczenia ich na li�cie obiekt�w do uruchomienia + prezentacja 
		 * konstruktora Downloader(Preference)
		 */
		for(Preference x : Preferences.prefList)
		{
			list.add(new Downloader(x));
			System.out.println("Dodalem (konstruktor1): "+ x.getAddr());
		}
		
		
		
		URL url = null;
		try {
			url = new URL("http://onet.pl");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}	
		if(url != null){
			/* jw dodanie obiektu do listy + prezentacja konstruktora Downloader(URL)*/
			list.add(new Downloader(url));
			System.out.println("Dodalem (konstruktor2): "+ url.toString());
		}
		
		/* jw dodanie obiektu do listy + prezentacja konstruktora Downloader(String) */
		list.add(new Downloader("http://seguro.pl"));
		
		/* jw dodanie obiektu do listy + prezentacja konstruktora Downloader(String,String) */
		list.add(new Downloader("http://tme.pl","tme2.html"));
		
		
		
		DownloaderPool dp = new DownloaderPool();		
		try {
			dp.addAll(list);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Pobra�em wszystko");
			
		
		
		
		
	}

}
