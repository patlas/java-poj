/**
 * 
 */
package patlas.agh;

import javax.swing.JOptionPane;

import patlas.agh.exception.MyException;

/**
 * @author PatLas
 *
 */
public class DownloaderWithParseDB extends Downloader {

	public DownloaderWithParseDB(Preference pref) {
		super(pref);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		//Thread.currentThread().setName(""+this.getFile().toString());
		super.run();
		
		while(SqlDB.IN_USE == true){ 
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				
				//DODA� LOGGER -> task zosta� zabity!!!
				System.out.println("Takiego zabito: "+Thread.currentThread().getName());
				return;
				//e.printStackTrace();
				
			}
			/*try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
			/*try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} */
			//System.out.println("Czekam " + Thread.currentThread().getName());
		}
		SqlDB.IN_USE = true;
		
		System.out.println("JADE");
		
		SqlDB db = new SqlDB(SqlDB.DB_NAME);
		Parser parser = new Parser(this);
		try {
			db.connectDB();
			//db.clearDB();
			db.insertTransponderRows(parser.getTransponder());
			db.insertChannelRows(parser.getChannels());
			//SqlDB.incPosition();
			SqlDB.IN_USE = false;
			//this.notifyAll();
			if(this.getFile().delete() == true); // DODAC LOGGER (nie)uda�o si� usun�� pliku
			System.out.println("Pobra�em i sko�czy�em");
			
			JOptionPane.showMessageDialog(null,"Strona o adresie: "+ this.getStringUrl() + " zosta�a"
					+ " przetworzona pomy�lnie!","Pobieranie zako�czone",
				    JOptionPane.INFORMATION_MESSAGE
				  );
			
		} catch (MyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
