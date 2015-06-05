package patlas.agh;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DownloaderPool {

	//private ArrayList<Downloader> list = new ArrayList<Downloader>();
	private ThreadPoolExecutor exec;
	
	public DownloaderPool()
	{
		//list = null;
	}
	
	
	public ArrayList<Future<?>> addAll(ArrayList<Downloader> list) throws MalformedURLException
	{
			
		ArrayList<Future<?>> ret = new ArrayList<Future<?>>();
		
		BlockingQueue<Runnable> runnables = new ArrayBlockingQueue<Runnable>(1024);
		ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 16, 60, TimeUnit.SECONDS, runnables);
		
		exec = executor;
		
		for (Downloader dwn : list)
			ret.add(executor.submit(dwn));
		//System.out.println(executor.getActiveCount());
		executor.shutdown();
		
		return ret;
	}
	
	public void stopNow()
	{
		exec.shutdownNow();
	}

}
