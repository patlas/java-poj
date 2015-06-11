package patlas.agh;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTree;
import javax.swing.JLabel;
import javax.swing.ListModel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;

import java.awt.SystemColor;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.Future;

import javax.swing.JScrollPane;

import patlas.agh.exception.MyException;
import patlas.agh.utils.CheckListItem;
import patlas.agh.utils.DataBaseViewer;
import patlas.agh.utils.TaskDone;
import patlas.agh.utils.ThreadCompleteListener;

public class MyGUI extends JFrame{
	private JTextField timeoutText;
	private JTextField addrText;	
	private CheckListItem[] listItem;
	private ArrayList<Downloader> listDownloaderPool = new ArrayList<Downloader>();
	private String dataBase = "test.db";
	private Thread parsingThread = null;
	private DownloaderPool downloaderPool = null;
	
	public void notifyOfThreadComplete(Downloader downloader)
	{
		
	}
	
	
	public MyGUI() {
		setResizable(false);
		
		JList list_1 = new JList();
		
		getContentPane().setLayout(null);
		
		/*JList settings */

		
		Preferences.loadSettings();
		/*CheckListItem[]*/ listItem = new CheckListItem[Preferences.prefList.size()];
		
		for(int index=0; index<Preferences.prefList.size();index++)
		{
			listItem[index] = new CheckListItem(Preferences.prefList.get(index).getAddr());
		}

	
		
		JLabel timeoutLabel = new JLabel("TIMEOUT:");
		timeoutLabel.setBounds(10, 180, 64, 14);
		getContentPane().add(timeoutLabel);
		
		JButton saveBtn = new JButton("SAVE");
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Preferences.saveSettings();
				
			}
		});
		saveBtn.setBounds(10, 205, 179, 48);
		getContentPane().add(saveBtn);
		
		timeoutText = new JTextField();
		timeoutText.setBounds(103, 177, 86, 20);
		getContentPane().add(timeoutText);
		timeoutText.setColumns(10);
		
		addrText = new JTextField();
		addrText.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addrText.setBounds(10, 151, 137, 20);
		getContentPane().add(addrText);
		addrText.setColumns(10);
		
		JLabel addressLabel = new JLabel("ADDRESS:");
		addressLabel.setBounds(10, 133, 63, 14);
		getContentPane().add(addressLabel);
		
		JButton addBtn = new JButton("+");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(addrText.getText().isEmpty() == false && timeoutText.getText().isEmpty() == false)
				{
					new Preference(addrText.getText(), Integer.parseInt(timeoutText.getText()));
					listItem = new CheckListItem[Preferences.prefList.size()];
					for(int index=0; index<Preferences.prefList.size();index++)
					{
						listItem[index] = new CheckListItem(Preferences.prefList.get(index).getAddr());
					}
					
					list_1.setListData(listItem);
					list_1.validate();
					list_1.repaint();
				}
				
			}
		});
		addBtn.setBounds(148, 151, 41, 20);
		getContentPane().add(addBtn);
		
		
		JButton downloadBtn = new JButton("DOWNLOAD");
		downloadBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				for(int index=0; index<Preferences.prefList.size();index++)
				{
					if(listItem[index].isSelected()==true)
					{
						// DOBRE STARE ROZWIAZANIE
						//listDownloaderPool.add(new Downloader(Preferences.prefList.get(index)));
						listDownloaderPool.add(new DownloaderWithParseDB(Preferences.prefList.get(index)));
						
						//listDownloaderPool.get(index).addListener(this);
						
						//patlas.agh.addListener(this);
						//System.out.println(Preferences.prefList.get(index).getAddr());
						
					}
				}
				
				downloaderPool = new DownloaderPool();		
				try {
					ArrayList<Future<?>> tasks = downloaderPool.addAll(listDownloaderPool);
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				/*parsingThread = new Thread(new TaskDone(listDownloaderPool,dataBase, listItem));
				parsingThread.start();*/
				
				/*
				DownloaderPool dp = new DownloaderPool();		
				try {
					ArrayList<Future<?>> tasks = dp.addAll(listDownloaderPool);
					for(int index=0; index<tasks.size();)
					{
						if(tasks.get(index).isDone() == true) index++;
					}
					
					SqlDB db = new SqlDB(dataBase);
					
					for(int index=0; index<Preferences.prefList.size();index++)
					{
						if(listItem[index].isSelected()==true)
						{
							Parser parser = new Parser(new Downloader(Preferences.prefList.get(index)));
							db.connectDB();
							//db.clearDB();
							db.insertTransponderRows(parser.getTransponder());
							db.insertChannelRows(parser.getChannels());
							SqlDB.incPosition();
							//System.out.println(Preferences.prefList.get(index).getAddr());
						}
					}
					
					
					
				} catch (MalformedURLException err) {
					// TODO Auto-generated catch block
					err.printStackTrace();
				} catch (MyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				
				
				
				listDownloaderPool.clear();
			}
		});
		downloadBtn.setBounds(217, 23, 164, 58);
		getContentPane().add(downloadBtn);
		
		JButton exitBtn = new JButton("EXIT");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		exitBtn.setBounds(217, 158, 164, 95);
		getContentPane().add(exitBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 23, 179, 104);
		getContentPane().add(scrollPane);
		
		
		
		scrollPane.setViewportView(list_1);
		list_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
		list_1.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		list_1.setBorder(new TitledBorder(new LineBorder(null), "WEB PAGE LIST:", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		list_1.setListData(listItem);
		
		list_1.setCellRenderer(new CheckListRenderer());	
		list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JButton removeBtn = new JButton("-");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						
				
				for(int index=0; index<Preferences.prefList.size();index++)
				{
					if(listItem[index].isSelected()==true)
					{
						Preferences.prefList.remove(index);
					}
				}
				
				listItem = new CheckListItem[Preferences.prefList.size()];
				
				for(int index=0; index<Preferences.prefList.size();index++)
				{
					listItem[index] = new CheckListItem(Preferences.prefList.get(index).getAddr());
				}
				
				list_1.setListData(listItem);
				list_1.validate();
				list_1.repaint();
				
				
			}
		});
		removeBtn.setBounds(148, 129, 41, 20);
		getContentPane().add(removeBtn);
		
		JButton abortBtn = new JButton("ABORT DOWNLOADING");
		abortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				
					if(downloaderPool != null)
					{
						downloaderPool.stopNow();
						System.out.println("Taks stopped!");
					}
						
					
			}
		});
		abortBtn.setBounds(217, 92, 164, 55);
		getContentPane().add(abortBtn);
		
		list_1.addMouseListener(new MouseAdapter()	
		{	
			public void mouseClicked(MouseEvent event)
			{
		
				JList list = (JList) event.getSource();	
				int index = list.locationToIndex(event.getPoint());		
				CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
				item.setSelected(! item.isSelected());
				list.repaint(list.getCellBounds(index, index));
		
			}
		});  
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(NORMAL);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnDataBase = new JMenu("Data base");
		menuBar.add(mnDataBase);
		
		JMenuItem mntmClear = new JMenuItem("Clear");
		mntmClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SqlDB dB = new SqlDB(dataBase);
					dB.connectDB();
					dB.clearDB();
				} catch (MyException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmShowDb = new JMenuItem("Show DB");
		mntmShowDb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				javax.swing.SwingUtilities.invokeLater(new Runnable() { 
					public void run()
					{
						DataBaseViewer dbView = new DataBaseViewer(); 
						dbView.pack(); 
						dbView.setVisible(true); 
						//dbView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						//dbView.setSize(400, 320);
						dbView.setMinimumSize(new Dimension(550,320));
					} 	
					
				}); 
				
				
			}
		});
		mnDataBase.add(mntmShowDb);
		mnDataBase.add(mntmClear);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmCreator = new JMenuItem("Creator");
		mntmCreator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 JOptionPane.showMessageDialog(null,
					    "Author of that program is Patryk £aœ student of AGH UST in Cracov. \n"
					    + " Main purpose of that program was obtain knowledge about JAVA"
					    + " programming language and gain programming skills." ,
					    "About author and program purpose :)",
					    JOptionPane.INFORMATION_MESSAGE
					  );
				
			}
		});
		mnAbout.add(mntmCreator);
		
		
		//list.setM
		

	}
}




/*class CheckListItem
{
	private String  label;
	private boolean isSelected = false;


	public CheckListItem(String label)
	{
		this.label = label;
	}
	
	public boolean isSelected()
	{
		return isSelected;
	}
	
	public void setSelected(boolean isSelected)
	{
		this.isSelected = isSelected;
	}
	
	public String toString()
	{
		return label;
	}

}*/

// Handles rendering cells in the list using a check box

class CheckListRenderer extends JCheckBox implements ListCellRenderer
{
	public Component getListCellRendererComponent(
	JList list, Object value, int index,
	boolean isSelected, boolean hasFocus)
	{
		setEnabled(list.isEnabled());
		setSelected(((CheckListItem)value).isSelected());
		setFont(list.getFont());
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		setText(value.toString());
		
		return this;
	}

}



