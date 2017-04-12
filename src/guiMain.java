import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.swing.table.DefaultTableModel;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.stock.StockQuote;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Panel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextPane;
import java.awt.Dimension; 

public class guiMain {
	
	JFrame window;
	JTextField search;
	JButton searchBut;
	JButton logoutButton;
	JButton dowButton;
	JButton graphButton;
	JLabel clock = new JLabel("Clock");
	Graph g = new Graph();
	private JTable table;
	JComboBox<String> comboBox;
	String filename;
	Stock myStock;
	Stock SD;
	List<HistoricalQuote> stockHistory;
	boolean dowActive;
	JTextPane txtPane = new JTextPane();
	ArrayList<String> stockList;

	guiMain(String user) throws NumberFormatException, IOException, ParseException 
	{
		ArrayList<String> dateArray = new ArrayList<String>();
		ArrayList<Double> closeArray = new ArrayList<Double>();
		
	    //dateArray = readFromFile.GetDateArray();
		//closeArray = readFromFile.GetCloseArray();
		
		//final int dataAmount = closeArray.size();
		
		// Basic window
		window = new JFrame("PROFITS R' US STOCK ANALYSIS");
		window.setSize(1000, 699);
		window.getContentPane().setBackground(Color.DARK_GRAY);
		window.getContentPane().setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		// Stock Searcher
		search = new JTextField();
		search.setColumns(15);
		//search.setSize(new Dimension(126, 22));
		search.setSize(search.getPreferredSize());
		search.setLocation(690, 10);
		search.setToolTipText("Enter stock");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String userText = search.getText().toUpperCase();

				try
				{
					Stock myStock = YahooFinance.get(userText);
					boolean found = false;
					if(myStock.getQuote().getPrice() != null)
					{
						/*for(int x = 0; x < comboBox.getItemCount(); x++)
						{
							if(comboBox.getItemAt(x).equals(userText))
							{
								found = true;
							}
						}*/
						
						filename = "userProfiles/" + user + ".txt";
						
						stockList = new ArrayList<String>();

						FileReader file;
						
						try
						{
							file = new FileReader(filename);
							BufferedReader buff = new BufferedReader(file);
							
							String line = null;
							
							while (((line = buff.readLine()) != null))
							{
								stockList.add(line);
							}
							
							buff.close();
						}
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						for(int x = 0; x < stockList.size(); x++)
						{
							if(stockList.get(x).equals(userText))
							{
								found = true;
							}
						}
						
						if(found == false)
						{
							JOptionPane.showMessageDialog(window, "Stock added!");
							comboBox.addItem(userText);
							if(stockList.size() == 0)
							{
								stockList.add("");
							}
							stockList.add(userText);
							txtPane.setText("My stocks:\n------------------------------------\n" + stockList.subList(1, stockList.size()));
							
							BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
							out.write("\n" + userText);
							out.close();
						}
						else
						{
							JOptionPane.showMessageDialog(window, "Duplicate stock!");
						}
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(window, "Invalid stock!");
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(window, "Invalid stock!");
				}
			}
		});
		window.getContentPane().add(search);
		
		logoutButton = new JButton("Log out");
		//logoutButton.setSize(new Dimension(76, 21));
		logoutButton.setSize(logoutButton.getPreferredSize());
		logoutButton.setLocation(226, 9);
		
		logoutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				window.dispose();
				guiLogin main = new guiLogin();
			}
		});
		window.getContentPane().add(logoutButton);
		
		dowActive = false;
		dowButton = new JButton("History");
		//dowButton.setSize(new Dimension(84, 21));
		dowButton.setSize(dowButton.getPreferredSize());
		dowButton.setLocation(136, 9);
		
		dowButton.setText("DOW 30");
		
		dowButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(dowActive == false)
				{
					dowButton.setText("History");
					comboBox.removeAllItems();
					
					comboBox.addItem("MMM");
					comboBox.addItem("AXP");
					comboBox.addItem("AAPL");
					comboBox.addItem("BA");
					comboBox.addItem("CAT");
					comboBox.addItem("CVX");
					comboBox.addItem("CSCO");
					comboBox.addItem("KO");
					comboBox.addItem("DIS");
					comboBox.addItem("DD");
					comboBox.addItem("XOM");
					comboBox.addItem("GE");
					comboBox.addItem("GS");
					comboBox.addItem("HD");
					comboBox.addItem("IBM");
					comboBox.addItem("INTC");
					comboBox.addItem("JNJ");
					comboBox.addItem("JPM");
					comboBox.addItem("MCD");
					comboBox.addItem("MRK");
					comboBox.addItem("MSFT");
					comboBox.addItem("NKE");
					comboBox.addItem("PFE");
					comboBox.addItem("PG");
					comboBox.addItem("TRV");
					comboBox.addItem("UTX");
					comboBox.addItem("UNH");
					comboBox.addItem("VZ");
					comboBox.addItem("V");
					comboBox.addItem("WMT");
					
					dowActive = true;
				}
				else
				{
					dowButton.setText("DOW 30");
					comboBox.removeAllItems();
					
					filename = "userProfiles/" + user + ".txt";
					
					stockList = new ArrayList<String>();

					FileReader file;
					
					try
					{
						file = new FileReader(filename);
						BufferedReader buff = new BufferedReader(file);
						
						String line = null;
						
						while (((line = buff.readLine()) != null))
						{
							stockList.add(line);
						}
						
						buff.close();
					}
					catch (FileNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(stockList.size() > 0)
					{
						for(int boxSize = 1; boxSize < stockList.size(); boxSize++)
						{
							comboBox.addItem(stockList.get(boxSize));
						}
					}
					
					dowActive = false;
				}
			}
		});
		window.getContentPane().add(dowButton);
		
		// Search button
		searchBut = new JButton("Search");
		//searchBut.setSize(new Dimension(76, 29));
		searchBut.setSize(searchBut.getPreferredSize());
		searchBut.setLocation(883, 9);
		searchBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String userText = search.getText().toUpperCase();

				try
				{
					Stock myStock = YahooFinance.get(userText);
					boolean found = false;
					if(myStock.getQuote().getPrice() != null)
					{
						/*for(int x = 0; x < comboBox.getItemCount(); x++)
						{
							if(comboBox.getItemAt(x).equals(userText))
							{
								found = true;
							}
						}*/
						
						filename = "userProfiles/" + user + ".txt";
						
						stockList = new ArrayList<String>();

						FileReader file;
						
						try
						{
							file = new FileReader(filename);
							BufferedReader buff = new BufferedReader(file);
							
							String line = null;
							
							while (((line = buff.readLine()) != null))
							{
								stockList.add(line);
							}
							
							buff.close();
						}
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						for(int x = 0; x < stockList.size(); x++)
						{
							if(stockList.get(x).equals(userText))
							{
								found = true;
							}
						}
						
						if(found == false)
						{
							JOptionPane.showMessageDialog(window, "Stock added!");
							comboBox.addItem(userText);
							if(stockList.size() == 0)
							{
								stockList.add("");
							}
							stockList.add(userText);
							txtPane.setText("My stocks:\n------------------------------------\n" + stockList.subList(1, stockList.size()));
							
							BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
							out.write("\n" + userText);
							out.close();
						}
						else
						{
							JOptionPane.showMessageDialog(window, "Duplicate stock!");
						}
						
						
					}
					else
					{
						JOptionPane.showMessageDialog(window, "Invalid stock!");
					}
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(window, "Invalid stock!");
				}
			}
		});
		window.getContentPane().add(searchBut);

		// Information about stock

		// Have another JTabbedPane the main one will encap the one below
		JTabbedPane main = new JTabbedPane(JTabbedPane.TOP);
		main.setBounds(20, 52, 950, 607);
		window.getContentPane().add(main);
		
		Calendar cal= new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = (1+ cal.get(Calendar.MONTH));
		int year = cal.get(Calendar.YEAR);
		
		JPanel main2 = new JPanel();
		main.addTab("Home", null, main2, null);

		// main2.add(panel);
		main2.setLayout(null);

		JPanel box1 = new JPanel();
		box1.setBounds(11, 21, 453, 281);
		box1.setBorder(new TitledBorder(null, "Welcome " + user + "! Today's date is "+day+"/"+month+"/"+year, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		main2.add(box1);
		box1.setLayout(null);
		/*
		JTextPane txtPane = new JTextPane();
		txtPane.setBounds(10, 27, 433, 243);
		txtPane.setText("BLAHBLAH \n Stocks: ");
		box1.add(txtPane);
		*/
		JPanel box2 = new JPanel();
		box2.setBounds(474, 21, 461, 535);
		main2.add(box2);
		
		JLabel lblNewLabel = new JLabel("New label");
		String path = "img/img.jpg";
		ImageIcon img = new ImageIcon(path);
		lblNewLabel.setIcon(img);
		lblNewLabel.setBounds(10,10,50,50);
		box2.add(lblNewLabel);
		
		JPanel box3 = new JPanel();
		box3.setBorder(new TitledBorder(null, "Quick Guide", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		box3.setBounds(11, 313, 453, 228);
		main2.add(box3);
		box3.setLayout(null);
		
		JTextPane txtpane2 = new JTextPane();
		txtpane2.setText("1) Graph a stock by selecting it from the top-left dropdown menu.\n\n"
				+ "2) Add a stock by searching for it using the search field.\n\n"
				+ "3) Toggle between personal and DOW 30 stocks by pressing the \n    button beside the top-left dropdown menu.\n\n"
				+ "4) Adjust the range or add/remove moving averages through the \n    dropdown menus located beneath the graph.\n\n"
				+ "5) Buy/sell indicators will automatically be displayed if multiple \n    moving averages are present.");
		txtpane2.setBounds(10, 26, 433, 191);
		box3.add(txtpane2);
		
		JPanel main1 = new JPanel();
		main.addTab("Stock Data", null, main1, null);
		main1.setLayout(null);

		JPanel main3 = new JPanel();
		main.addTab("Credits", null, main3, null);
		main3.setLayout(null);
		table = new JTable();
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"Vasiliki Boutas"},
				{"Dinh Bui"},
				{"Sri Ram Ede"},
				{"Jordan Orsini"},
				{"Mehal Patel"},
				{"Matthew To"},
				{"Matthew Verrucci"},
			},
			new String[] {
				"Development Team"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(144);
		table.setBounds(52, 59, 825, 112);
		main3.add(table);
		
		JLabel lblDevelopementTeam = new JLabel("Developement Team");
		lblDevelopementTeam.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblDevelopementTeam.setBounds(52, 34, 188, 14);
		main3.add(lblDevelopementTeam);

		// Tabs
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 0, 920, 568);
		main1.add(tabs);

		JPanel tab1 = new JPanel();
		tabs.addTab(user, null, tab1, null);
		JPanel insideTab1 = new JPanel();
		
		insideTab1.setBorder(new TitledBorder(null, "Graph", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		insideTab1.setBounds(0, 0, 900, 496);
		
		 Graph gPanel = new Graph(g.createAndShowGui(0,0, closeArray, dateArray, 0));
		 //gPanel.setBorder(new TitledBorder(null, "Graph", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		 //System.out.println(g.createAndShowGui(0,0)); 
		// gPanel.setBounds(0, 0, 900, 496);
		 gPanel.setBounds(10,20,875,445);
		 insideTab1.add(window.getContentPane().add(gPanel));
		
		 JLabel Axis = new JLabel("Price vs. Days");
		 Axis.setBounds(425, 470 ,106,16);
		insideTab1.add(Axis);
		
		JComboBox<String> days = new JComboBox();
		days.addItem("None");
		days.addItem("20 day");
		days.addItem("50 day");
		//days.addItem("100 day");
		//days.addItem("200 day");
		days.setBounds(372, 500, 108, 20);
		JLabel dayLabel = new JLabel("Moving Average [Short]:");
		dayLabel.setForeground(Color.ORANGE);
		dayLabel.setBounds(222,500,150,16);
		tab1.add(dayLabel);
		tab1.add(days);
		
		JComboBox<String> days2 = new JComboBox();
		days2.addItem("None");
		//days.addItem("20 day");
		//days.addItem("50 day");
		days2.addItem("100 day");
		days2.addItem("200 day");
		days2.setBounds(645, 500, 108, 20);
		JLabel dayLabel2 = new JLabel("Moving Average [Long]:");
		dayLabel2.setForeground(Color.MAGENTA);
		dayLabel2.setBounds(495,500,150,16);
		tab1.add(dayLabel2);
		tab1.add(days2);
		
		JComboBox<String> rangeBox = new JComboBox();
		rangeBox.addItem("All");
		rangeBox.addItem("Past year");
		rangeBox.addItem("Past 2 years");
		rangeBox.addItem("Past 5 years");
		rangeBox.setBounds(100, 500, 108, 20);
		JLabel yearLabel = new JLabel("Sample Range:");
		yearLabel.setBounds(10,500,95,16);
		yearLabel.setForeground(new Color(44, 102, 230, 180));
		tab1.add(yearLabel);
		tab1.add(rangeBox);
		
		graphButton = new JButton("Generate graph");
		graphButton.setSize(graphButton.getPreferredSize());
		graphButton.setLocation(760, 495);

		graphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBox.getItemCount() != 0)
				{
					if(dowActive == true)
					{
						filename = "userProfiles/" + user + ".txt";
						
						stockList = new ArrayList<String>();

						FileReader file;
						
						try
						{
							file = new FileReader(filename);
							BufferedReader buff = new BufferedReader(file);
							
							String line = null;
							
							while (((line = buff.readLine()) != null))
							{
								stockList.add(line);
							}
							
							buff.close();
						}
						catch (FileNotFoundException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						boolean found = false;
						
						for(int x = 0; x < stockList.size(); x++)
						{
							if(stockList.get(x).equals(comboBox.getSelectedItem()))
							{
								found = true;
							}
						}
						
						if(found == false)
						{
							//JOptionPane.showMessageDialog(window, "Stock added!");
							//comboBox.addItem(userText);
							if(stockList.size() == 0)
							{
								stockList.add("");
							}
							stockList.add((String)comboBox.getSelectedItem());
							txtPane.setText("My stocks:\n------------------------------------\n" + stockList.subList(1, stockList.size()));
							
							BufferedWriter out = null;
							try {
								out = new BufferedWriter(new FileWriter(filename, true));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								out.write("\n" + comboBox.getSelectedItem());
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								out.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						/*else
						{
							JOptionPane.showMessageDialog(window, "Duplicate stock!");
						}*/
						
					}
					
					ArrayList<String> dateArray = new ArrayList<String>();
				
					ArrayList<Double> closeArray = new ArrayList<Double>();
					
				    //dateArray = readFromFile.GetDateArray();
					//closeArray = readFromFile.GetCloseArray();
					
					Calendar fromAll = Calendar.getInstance();
					  Calendar from1 = Calendar.getInstance();
					  Calendar from2 = Calendar.getInstance();
					  Calendar from5 = Calendar.getInstance();
					  
					  Calendar to = Calendar.getInstance();
					  Calendar cal = Calendar.getInstance();
					  
					  fromAll.add(Calendar.YEAR, -1000);
					  from1.add(Calendar.YEAR, -1);
					  from2.add(Calendar.YEAR, -2);
					  from5.add(Calendar.YEAR, -5);
					  
					  String stockSelection = (String)comboBox.getSelectedItem();
					  
					  try 
					  {
						myStock = YahooFinance.get(stockSelection);
					  } 
					  catch (IOException e1) 
					  {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					  }
					  
				    int selectedMovingAverage1 = 0;
				    
					if(days.getSelectedItem().equals("20 day"))
					{
						selectedMovingAverage1 = 20;
					}
					if(days.getSelectedItem().equals("50 day"))
					{
						selectedMovingAverage1 = 50;
					}
					
					int selectedMovingAverage2 = 0;
					if(days2.getSelectedItem().equals("100 day"))
					{
						selectedMovingAverage2 = 100;
					}
					if(days2.getSelectedItem().equals("200 day"))
					{
						selectedMovingAverage2 = 200;
					}
					 
					 String rangeSelection = (String)rangeBox.getSelectedItem();
					 
					 int selectedSampleRange = 0;
					
					 if(rangeSelection.equals("All"))
					 {
						 //selectedSampleRange = closeArray.size();
						 try 
						 {
							stockHistory = myStock.getHistory(fromAll, to, Interval.DAILY);
						 } 
						 catch (IOException e) 
						 {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 }
						 
						 selectedSampleRange = stockHistory.size();
					 }
					 if(rangeSelection.equals("Past year"))
					 {
						 //selectedSampleRange = 365;
						 try 
						 {
							stockHistory = myStock.getHistory(from1, to, Interval.DAILY);
						 } 
						 catch (IOException e) 
						 {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 }
						 
						 selectedSampleRange = stockHistory.size();
					 }
					 if(rangeSelection.equals("Past 2 years"))
					 {
						 //selectedSampleRange = 730;
						 try 
						 {
							stockHistory = myStock.getHistory(from2, to, Interval.DAILY);
						 } 
						 catch (IOException e) 
						 {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 }
						 
						 selectedSampleRange = stockHistory.size();
					 }
					 if(rangeSelection.equals("Past 5 years"))
					 {
						 //selectedSampleRange = 1825;
						 try 
						 {
							stockHistory = myStock.getHistory(from5, to, Interval.DAILY);
						 } 
						 catch (IOException e) 
						 {
							// TODO Auto-generated catch block
							e.printStackTrace();
						 }
						 
						 selectedSampleRange = stockHistory.size();
					 }
					 
					 for(int x = 0; x < stockHistory.size(); x++)
					  {
						  cal = stockHistory.get(x).getDate();
						  
						  String myMonth = Integer.toString(cal.get(Calendar.MONTH) + 1);
				          
						  if(myMonth.length() == 1)
				          {
				          	myMonth = "0" + myMonth;
				          }
						  
						  String myYear = Integer.toString(cal.get(Calendar.YEAR));
						  
						  dateArray.add(myMonth + "/" + myYear);
						  closeArray.add(stockHistory.get(x).getClose().doubleValue());
						  
						 // System.out.println(myMonth + "/" + myYear);
						  //System.out.println(stockHistory.get(x).getClose());
						  //System.out.println();
					  }
					 
					 Collections.reverse(dateArray);
					 Collections.reverse(closeArray);
					 
					 /*for(int x = 0; x< dateArray.size(); x ++)
					 {
						 System.out.println(dateArray.get(x));
						 System.out.println(closeArray.get(x));
						 System.out.println();
					 }*/
					 
					Graph gPanel = null;
					try 
					{
						gPanel = new Graph(g.createAndShowGui(selectedSampleRange, selectedMovingAverage1, closeArray, dateArray, selectedMovingAverage2));
					} 
					catch (NumberFormatException | IOException | ParseException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					/*try 
					{
						System.out.println(g.createAndShowGui(mvd,mvd));
					}
					catch (NumberFormatException | IOException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
					gPanel.setBounds(10, 20, 875, 450);
					insideTab1.add(window.getContentPane().add(gPanel));
					
					//insideTab1.repaint();
					gPanel.repaint();
					gPanel.revalidate();
					//insideTab1.revalidate();
				}
				else
				{
					JOptionPane.showMessageDialog(window, "No stock selected!");
				}
			}

		});
		tab1.add(graphButton);

		tab1.add(insideTab1);
		insideTab1.setLayout(null);
		
		/*JLabel Axis2 = new JLabel("Time (Days)");
		Axis2.setBounds(789, 471, 74, 14);
		insideTab1.add(Axis2);*/

		tab1.setLayout(null);

		JPanel tab2 = new JPanel();
		tabs.addTab("DOW 30", null, tab2, null);
		tab2.setLayout(null);
		
		JPanel dow30 = new JPanel();
		dow30.setBounds(10, 11, 880, 518);
		tab2.add(dow30);
		
		JLabel dow30Img = new JLabel();
		path = "img/finalLogo.jpg";
		ImageIcon img2 = new ImageIcon(path);
		dow30Img.setIcon(img2);
		dow30.add(dow30Img);
		
		

		comboBox = new JComboBox();
		
		filename = "userProfiles/" + user + ".txt";
		
		stockList = new ArrayList<String>();

		FileReader file;
		
		try
		{
			file = new FileReader(filename);
			BufferedReader buff = new BufferedReader(file);
			
			String line = null;
			
			while (((line = buff.readLine()) != null))
			{
				stockList.add(line);
			}
			
			buff.close();
		}
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(stockList.size() > 0)
		{
			for(int boxSize = 1; boxSize < stockList.size(); boxSize++)
			{
				comboBox.addItem(stockList.get(boxSize));
			}
		}
		
		
		
	
		txtPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPane.setBounds(10, 27, 433, 120);
		txtPane.setText("My stocks:\n------------------------------------\n");
		
		if(stockList.size() > 0)
		{
			txtPane.setText("My stocks:\n------------------------------------\n" + stockList.subList(1, stockList.size()));
		}
		box1.add(txtPane);
		
		JTextPane txtPane2 = new JTextPane();
		txtPane2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtPane2.setBounds(10, 150, 433, 120);
		
		String stockofDay = (String)comboBox.getSelectedItem();
		try {
			
			
			if(stockofDay == null)
			{
				
				
				txtPane2.setText("No stock selected!\n------------------------------------\n"
						+ "Current Price: N/A"
						+"\nPrevious Close: N/A");
						
				box1.add(txtPane2);
			}
			else
			{
				SD= YahooFinance.get(stockofDay);
				
				txtPane2.setText(stockofDay 
						+"\n------------------------------------"
						+ "\nCurrent Price: $" +SD.getQuote().getPrice() 
						+"\nPrevious Close: $" +SD.getQuote().getPreviousClose());
					
			}
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		box1.add(txtPane2);
		
		
		ActionListener actionListener = new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

	
					
					String stockofDay = (String)comboBox.getSelectedItem();
					try {
						
						
						if(stockofDay == null)
						{
							//SD  = YahooFinance.get("AAPL");
							
							txtPane2.setText("No stock selected!\n------------------------------------\n"
									+ "Current Price: N/A"
									+"\nPrevious Close: N/A");
									
							box1.add(txtPane2);
						}
						else
						{
							SD= YahooFinance.get(stockofDay);
							
							txtPane2.setText(stockofDay
									+"\n------------------------------------"
									+ "\nCurrent Price: $" +SD.getQuote().getPrice() 
									+"\nPrevious Close: $" +SD.getQuote().getPreviousClose());
								
						}
							
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
			
			}
			
		};
		comboBox.addActionListener(actionListener);

		
		//System.out.println();
		
		comboBox.setBounds(25, 13, 108, 20);
		window.getContentPane().add(comboBox);

		window.setResizable(false);

	}

	// Main method
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException { // TODO Auto-generated

		new guiMain("admin");
		

	}
}
