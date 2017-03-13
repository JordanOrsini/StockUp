import java.awt.Color;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

	guiMain() {

		// Basic window
		window = new JFrame("Main Window");
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
				if (user.equals("Apple")) {
					JOptionPane.showMessageDialog(window, "Success");
				} else {
					JOptionPane.showMessageDialog(window, "No");
				}

			}
		});
		window.getContentPane().add(searchBut);

		// Information about stock

		// Have another JTabbedPane the main one will encap the one below
		JTabbedPane main = new JTabbedPane(JTabbedPane.TOP);
		main.setBounds(20, 52, 653, 607);
		window.getContentPane().add(main);

		JPanel main1 = new JPanel();
		main.addTab("Graphs", null, main1, null);
		main1.setLayout(null);

		JPanel main2 = new JPanel();
		main.addTab("NoobGuide", null, main2, null);
		main2.setLayout(null);

		JPanel main3 = new JPanel();
		main.addTab("DevTeam", null, main3, null);
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
		
		 Graph gPanel = new Graph(g.createAndShowGui(0));
		 System.out.println(g.createAndShowGui(0)); 
		 gPanel.setBounds(10, 20,600, 450);
		 insideTab1.add(window.getContentPane().add(gPanel));
		 
		
		JComboBox<Integer> days = new JComboBox();
		days.addItem(5);
		days.addItem(10);
		days.addItem(50);
		days.addItem(100);
		days.setBounds(168, 501, 108, 20);
		tab1.add(days);
		
		
		graphButton = new JButton("Generate graph");
		graphButton.setSize(graphButton.getPreferredSize());
		graphButton.setLocation(364, 500);

		graphButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int day = (int) days.getSelectedItem();
				Graph gPanel = new Graph(g.createAndShowGui(day));
				System.out.println(g.createAndShowGui(day));
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

		userName = new JLabel("*graph here");
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
	public static void main(String[] args) { // TODO Auto-generated

		new guiMain();

	}
}
