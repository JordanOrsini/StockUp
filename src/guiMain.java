import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class guiMain {
	
	JFrame window;
	JLabel userName;
	JTextField search;
	JButton searchBut;
	JButton graphButton;
	Graph g = new Graph();

	guiMain() throws NumberFormatException, IOException, ParseException 
	{
		Main readFromFile = null;
		try 
		{
			readFromFile = new Main();
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
	    
		ArrayList<Date> dateArray = new ArrayList<Date>();
		ArrayList<Double> openArray = new ArrayList<Double>();
		ArrayList<Double> highArray = new ArrayList<Double>();
		ArrayList<Double> lowArray = new ArrayList<Double>();
		ArrayList<Double> closeArray = new ArrayList<Double>();
		ArrayList<Double> volumeArray = new ArrayList<Double>();
		ArrayList<Double> adjCloseArray = new ArrayList<Double>();	
		
	    dateArray = readFromFile.GetDateArray();
	    openArray = readFromFile.GetOpenArray();
	    highArray = readFromFile.GetHighArray();
	    lowArray = readFromFile.GetLowArray();
		closeArray = readFromFile.GetCloseArray();
		volumeArray = readFromFile.GetVolumeArray();
		adjCloseArray = readFromFile.GetAdjCloseArray();
		
		//final int dataAmount = closeArray.size();
		
		// Basic window
		window = new JFrame("PROFITS R' US STOCK ANALYSIS");
		window.setSize(699, 699);
		window.getContentPane().setBackground(Color.GRAY);
		window.getContentPane().setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		// Stock Searcher
		search = new JTextField();
		search.setColumns(15);
		search.setSize(search.getPreferredSize());
		search.setLocation(418, 11);
		search.setToolTipText("Enter stock");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user = search.getText();

				// ignore case
				if (user.equalsIgnoreCase("Apple")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else if (user.equalsIgnoreCase("Amazon")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else if (user.equalsIgnoreCase("Microsoft")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else if (user.equalsIgnoreCase("Google")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else 
				{
					JOptionPane.showMessageDialog(window, "Invalid stock!");
				}

			}
		});
		window.getContentPane().add(search);

		// Search button
		searchBut = new JButton("Search");
		searchBut.setSize(searchBut.getPreferredSize());
		searchBut.setLocation(603, 10);
		searchBut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user = search.getText();

				// ignore case
				if (user.equalsIgnoreCase("Apple")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else if (user.equalsIgnoreCase("Amazon")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else if (user.equalsIgnoreCase("Microsoft")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else if (user.equalsIgnoreCase("Google")) 
				{
					JOptionPane.showMessageDialog(window, "Stock found!");
				} 
				else 
				{
					JOptionPane.showMessageDialog(window, "Invalid stock!");
				}

			}
		});
		window.getContentPane().add(searchBut);

		// Information about stock

		// Have another JTabbedPane the main one will encap the one below
		JTabbedPane main = new JTabbedPane(JTabbedPane.TOP);
		main.setBounds(20, 52, 653, 607);
		window.getContentPane().add(main);

		JPanel main2 = new JPanel();
		main.addTab("Home", null, main2, null);
		main2.setLayout(null);

		JPanel main1 = new JPanel();
		main.addTab("Stock Data", null, main1, null);
		main1.setLayout(null);

		JPanel main3 = new JPanel();
		main.addTab("Credits", null, main3, null);
		main3.setLayout(null);
		JLabel label = new JLabel("");
		label.setBounds(189, 127, 262, 300);
		main3.add(label);

		// Tabs
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(10, 0, 628, 568);
		main1.add(tabs);

		JPanel tab1 = new JPanel();
		tabs.addTab("Current", null, tab1, null);
		JPanel insideTab1 = new JPanel();

		insideTab1.setBorder(new TitledBorder(null, "Graph", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		insideTab1.setBounds(0, 0, 619, 496);
		
		 Graph gPanel = new Graph(g.createAndShowGui(0,0, closeArray, dateArray));
		 //System.out.println(g.createAndShowGui(0,0)); 
		 gPanel.setBounds(10, 20, 600, 450);
		 insideTab1.add(window.getContentPane().add(gPanel));
		 
		
		JComboBox<String> days = new JComboBox();
		days.addItem("None");
		days.addItem("20");
		days.addItem("50");
		days.addItem("100");
		days.addItem("200");
		days.setBounds(115, 501, 108, 20);
		tab1.add(days);
		
		JComboBox<String> rangeBox = new JComboBox();
		rangeBox.addItem("All");
		rangeBox.addItem("Past year");
		rangeBox.addItem("Past 2 years");
		rangeBox.addItem("Past 5 years");
		rangeBox.setBounds(0, 501, 108, 20);
		tab1.add(rangeBox);
		
		graphButton = new JButton("Generate graph");
		graphButton.setSize(graphButton.getPreferredSize());
		graphButton.setLocation(470, 495);

		graphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/*Main readFromFile = null;
				try 
				{
					readFromFile = new Main();
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
				}*/
			    
				ArrayList<Date> dateArray = new ArrayList<Date>();
				ArrayList<Double> openArray = new ArrayList<Double>();
				ArrayList<Double> highArray = new ArrayList<Double>();
				ArrayList<Double> lowArray = new ArrayList<Double>();
				ArrayList<Double> closeArray = new ArrayList<Double>();
				ArrayList<Double> volumeArray = new ArrayList<Double>();
				ArrayList<Double> adjCloseArray = new ArrayList<Double>();	
				
			    dateArray = readFromFile.GetDateArray();
			    openArray = readFromFile.GetOpenArray();
			    highArray = readFromFile.GetHighArray();
			    lowArray = readFromFile.GetLowArray();
				closeArray = readFromFile.GetCloseArray();
				volumeArray = readFromFile.GetVolumeArray();
				adjCloseArray = readFromFile.GetAdjCloseArray();
				
			    
			    int mvd = 0;
			    //int day;
				 if(days.getSelectedItem().equals("20"))
				 {
					 mvd = 20;
				 }
				 if(days.getSelectedItem().equals("50"))
				 {
					 mvd = 50;
				 }
				 if(days.getSelectedItem().equals("100"))
				 {
					 mvd = 100;
				 }
				 if(days.getSelectedItem().equals("200"))
				 {
					 mvd = 200;
				 }
				 
				 String rangeSelection = (String)rangeBox.getSelectedItem();
				 
				 int range = 0;
				
				 if(rangeSelection.equals("All"))
				 {
					 range = closeArray.size();
				 }
				 if(rangeSelection.equals("Past year"))
				 {
					 range = 365;
				 }
				 if(rangeSelection.equals("Past 2 years"))
				 {
					 range = 730;
				 }
				 if(rangeSelection.equals("Past 5 years"))
				 {
					 range = 1825;
				 }
				 
				Graph gPanel = null;
				try 
				{
					gPanel = new Graph(g.createAndShowGui(range, mvd, closeArray, dateArray));
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
				gPanel.setBounds(10, 20, 600, 450);
				insideTab1.add(window.getContentPane().add(gPanel));
				
				//insideTab1.repaint();
				gPanel.repaint();
				gPanel.revalidate();
				//insideTab1.revalidate();
			}

		});
		tab1.add(graphButton);

		tab1.add(insideTab1);
		insideTab1.setLayout(null);

		tab1.setLayout(null);

		JPanel tab2 = new JPanel();
		tabs.addTab("History", null, tab2, null);
		tab2.setLayout(null);

		userName = new JLabel();//"*graph here");
		userName.setLocation(10, 5);
		userName.setSize(userName.getPreferredSize());
		tab2.add(userName);

		JComboBox<String> comboBox = new JComboBox();
		comboBox.addItem("Apple");
		comboBox.addItem("Amazon");
		comboBox.addItem("Microsoft");
		comboBox.addItem("Google");
		comboBox.setBounds(20, 11, 108, 20);
		window.getContentPane().add(comboBox);

		window.setResizable(false);

	}


	// Main method
	public static void main(String[] args) throws NumberFormatException, IOException, ParseException { // TODO Auto-generated

		new guiMain();

	}
	
}
