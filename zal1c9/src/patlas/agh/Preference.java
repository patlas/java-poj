package patlas.agh;


public class Preference {
	

	
	public static  int TIMEOUT = 80;
	private String addr;
	//private String name;
	private int numTry;
	
	public Preference(String addr, int num)
	{
		this.numTry = num;
		this.addr = addr;
		Preferences.prefList.add(this);
	}
	
	public Preference()
	{
		Preferences.prefList.add(this);
	}
	
	//do usuniecia
	public Preference( int num, String addr)
	{
		this.numTry = num;
		this.addr = addr;
	}
	
	public static final void setTIMEOUT(int timeout)
	{
		Preference.TIMEOUT = timeout;
	}
	
	public void setAddr(String link)
	{
		this.addr = link;
	}

	public String getAddr()
	{
		return this.addr; 
	}
	
	public void setNumTry(int num)
	{
		this.numTry = num;
	}
	
	public int getNumTry()
	{
		return this.numTry;
	}
	
	
}

