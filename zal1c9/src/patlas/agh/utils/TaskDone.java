/**
 * 
 */
package patlas.agh.utils;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.Future;





import patlas.agh.utils.CheckListItem;
import patlas.agh.Downloader;
import patlas.agh.DownloaderPool;
import patlas.agh.Parser;
import patlas.agh.Preferences;
import patlas.agh.SqlDB;
import patlas.agh.exception.MyException;

/**
 * @author PatLas
 *
 */
public class TaskDone implements Runnable{
	
	private ArrayList<Downloader> listDownloaderPool;
	private String dataBase;
	private CheckListItem[] listItem;
	
	public TaskDone(ArrayList<Downloader> list, String db, CheckListItem[] item)
	{		
		listDownloaderPool = list;		
		dataBase = db;
		listItem = item;
	};
	
	public void run()
	{
		DownloaderPool dp = new DownloaderPool();
		//CheckListItem[] listItem = new CheckListItem[Preferences.prefList.size()];
		try {
			ArrayList<Future<?>> tasks = dp.addAll(listDownloaderPool);
			for(int index=0; index<tasks.size();)
			{
				if(Thread.currentThread().isInterrupted()==true)
				{			
					dp.stopNow();
	            	System.out.println("Parsing ABORTED");
					//Thread.currentThread().interrupt();				
				}
				
				
				if(tasks.get(index).isDone() == true) index++;
			}
			
			SqlDB db = new SqlDB(dataBase);
			
			for(int index=0; index<Preferences.prefList.size();index++)
			{
				if(Thread.interrupted()) System.out.println("Parsing ABORTED2");
				if(Thread.currentThread().isInterrupted()==true)
				{
					System.out.println("Parsing ABORTED"); return;
					//Thread.currentThread().interrupt();
				}
				
				if(listItem[index].isSelected()==true)
				{
					Parser parser = new Parser(new Downloader(Preferences.prefList.get(index)));
					db.connectDB();
					//db.clearDB();
					db.insertTransponderRows(parser.getTransponder());
					db.insertChannelRows(parser.getChannels());
					
					//System.out.println(Preferences.prefList.get(index).getAddr());
				}
			}
			
			
			
		} catch (MalformedURLException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
		} catch (MyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} /*catch(InterruptedException inte){
            inte.printStackTrace();
            //if(inte instanceof InterruptedException) {
                // just in case this Runnable is actually called directly,
                // rather than in a new thread, don't want to swallow the
                // flag:
            	dp.stopNow();
            	System.out.println("Parsing ABORTED");
                Thread.currentThread().interrupt();
            //}
		}*/
				
		System.out.println("Parsing DONE"); //DODAÆ LOG I DIALOG BOXA
	};

}
