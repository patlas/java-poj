package patlas.agh;

import java.net.MalformedURLException;
import java.net.URL;

import patlas.agh.utils.TwoTypeList;
//
//public class Test {
//	
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
////		Preference x = new Preference();
////		Preference y = new Preference("adsfadf", 2);
////		Preference yf = new Preference("Joanna", 22);
////		Preference yfx = new Preference("Patryk", 25);
////		Preference.setTIMEOUT(20);
////		x.setAddr("www.agbc.pl");
////		//System.out.println(x.getAddr());
////		
////		Preferences.saveSettings();
////		Preferences.loadSettings();
////		
////		
////		for(Preference a : Preferences.prefList)
////			System.out.println(a.getAddr());
//		
//		//Downloader p = new Downloader("http://pl.kingofsat.net/sat-hb8.php");
//		
//		/*URL url = null;
//		try {
//			url = new URL("http://onet.pl");
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//		if(url != null)
//			new Downloader(url).downloadPage();
//		
//		
//		new Downloader("http://seguro.pl").downloadPage();
//		new Downloader("http://tme.pl","tme2.html").downloadPage();
//		*/
//		//System.out.println(p.url.toString());
//		//System.out.println(Downloader.MD5string("patlas"));
//		//System.out.println(Preferences.prefList.get(0).getAddr());
//		//System.out.println(Preferences.prefList.size());
//		//System.out.println(Preferences.prefList.get(Preferences.prefList.size()-2).getAddr());
//		
//		
//		
//		
//		
//	}
//
//}
//*/







import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;

import org.jsoup.nodes.Element;



public class Test {
   /* public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Task());

        try {
            System.out.println("Started..");
            System.out.println(future.get(5, TimeUnit.SECONDS));
            System.out.println("Finished!");
        } catch (TimeoutException e) {
            System.out.println("Terminated!");
        }

        executor.shutdownNow();

    }*/
	
	
	
	public static void createAndShowGUI() { 
		MyGUI gui = new MyGUI(); 
		gui.pack(); 
		gui.setVisible(true); 
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(400, 320);
		} 
	
	
		public static void main(String[] args) { 
		
			//DownloaderPoolParse x = new DownloaderPoolParse(new Preference());
			
			
			javax.swing.SwingUtilities.invokeLater(new Runnable() { 
		public void run() { createAndShowGUI(); } 
		}); 
		} 
		
		
		
		
	
}

class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(4000); // Just to demo a long running task of 4 seconds.
        return "Ready!";
    }
}

