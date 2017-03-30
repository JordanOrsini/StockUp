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

public class guiMain {
	
	JFrame window;
	JLabel userName;
	JTextField search;
	JButton searchBut;
	JButton logoutButton;
	JButton graphButton;
	Graph g = new Graph();
	private JTable table;
	JComboBox<String> comboBox;
	String filename;
	Stock myStock;
	List<HistoricalQuote> stockHistory;

	guiMain(String user) throws NumberFormatException, IOException, ParseException 
	{
		readData readFromFile = null;
		try 
		{
			readFromFile = new readData();
		} 
		catch (NumberFormatException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		catch (ParseException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
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
		search.setSize(search.getPreferredSize());
		search.setLocation(693, 10);
		search.setToolTipText("Enter stock");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String user = search.getText().toUpperCase();

				try
				{
					Stock myStock = YahooFinance.get(user);
					boolean found = false;
					if(myStock.getQuote().getPrice() != null)
					{
						for(int x = 0; x < comboBox.getItemCount(); x++)
						{
							if(comboBox.getItemAt(x).equals(user))
							{
								found = true;
							}
						}
						
						if(found == false)
						{
							JOptionPane.showMessageDialog(window, "Stock added!");
							comboBox.addItem(user);
							
							BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
							out.write("\n" + user);
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
		logoutButton.setSize(logoutButton.getPreferredSize());
		logoutButton.setLocation(132, 7);
		
		logoutButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				window.dispose();
				guiLogin main = new guiLogin();
			}
		});
		window.getContentPane().add(logoutButton);
		
		// Search button
		searchBut = new JButton("Search");
		searchBut.setSize(searchBut.getPreferredSize());
		searchBut.setLocation(883, 10);
		searchBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				String user = search.getText().toUpperCase();

				try
				{
					Stock myStock = YahooFinance.get(user);
					boolean found = false;
					if(myStock.getQuote().getPrice() != null)
					{
						for(int x = 0; x < comboBox.getItemCount(); x++)
						{
							if(comboBox.getItemAt(x).equals(user))
							{
								found = true;
							}
						}
						
						if(found == false)
						{
							JOptionPane.showMessageDialog(window, "Stock added!");
							comboBox.addItem(user);
							
							BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
							out.write("\n" + user);
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

		JPanel main2 = new JPanel();
		main.addTab("Home", null, main2, null);
		
		JPanel panel = new JPanel();
		panel.setBounds(150, 0, 628, 557);
		JLabel label1 = new JLabel();
		String path = "img/img.jpg";
		ImageIcon img = new ImageIcon(path);

		label1.setIcon(img);
		label1.setBounds(10,10,50,50);
		panel.add(label1);
		
		
		main2.add(panel);
		main2.setLayout(null);

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
		insideTab1.setBounds(0, 0, 910, 496);
		
		 Graph gPanel = new Graph(g.createAndShowGui(0,0, closeArray, dateArray));
		 //System.out.println(g.createAndShowGui(0,0)); 
		 gPanel.setBounds(10, 20, 875, 445);
		 insideTab1.add(window.getContentPane().add(gPanel));
		
		 JLabel Axis = new JLabel("Price vs. Days");
		 Axis.setBounds(425, 470 ,106,14);
		insideTab1.add(Axis);
		 
		 
		
		JComboBox<String> days = new JComboBox();
		days.addItem("None");
		days.addItem("20 day");
		days.addItem("50 day");
		days.addItem("100 day");
		days.addItem("200 day");
		days.setBounds(300, 501, 108, 20);
		JLabel dayLabel = new JLabel("Moving Avg:");
		dayLabel.setBounds(225,504,100,14);
		tab1.add(dayLabel);
		tab1.add(days);
		
		JComboBox<String> rangeBox = new JComboBox();
		rangeBox.addItem("All");
		rangeBox.addItem("Past year");
		rangeBox.addItem("Past 2 years");
		rangeBox.addItem("Past 5 years");
		rangeBox.setBounds(100, 501, 80, 20);
		JLabel yearLabel = new JLabel("Sample Range:");
		yearLabel.setBounds(10,504,100,14);
		tab1.add(yearLabel);
		tab1.add(rangeBox);
		
		graphButton = new JButton("Generate graph");
		graphButton.setSize(graphButton.getPreferredSize());
		graphButton.setLocation(760, 495);

		graphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBox.getItemCount() != 0)
				{
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
					  
					
				    
				    int selectedMovingAverage = 0;
				    
					if(days.getSelectedItem().equals("20 day"))
					{
						selectedMovingAverage = 20;
					}
					if(days.getSelectedItem().equals("50 day"))
					{
						selectedMovingAverage = 50;
					}
					if(days.getSelectedItem().equals("100 day"))
					{
						selectedMovingAverage = 100;
					}
					if(days.getSelectedItem().equals("200 day"))
					{
						selectedMovingAverage = 200;
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
						gPanel = new Graph(g.createAndShowGui(selectedSampleRange, selectedMovingAverage, closeArray, dateArray));
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

		tab1.setLayout(null);

		JPanel tab2 = new JPanel();
		//tabs.addTab("History", null, tab2, null);
		tab2.setLayout(null);

		userName = new JLabel();//"*graph here");
		userName.setLocation(10, 5);
		userName.setSize(userName.getPreferredSize());
		tab2.add(userName);

		comboBox = new JComboBox();
		
		filename = "userProfiles/" + user + ".txt";
		
		ArrayList<String> stockList = new ArrayList<String>();

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
		
		comboBox.setBounds(25, 11, 108, 20);
		window.getContentPane().add(comboBox);

		window.setResizable(false);

	}


	// Main method
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException { // TODO Auto-generated

		new guiMain("admin");

	}
	
}
