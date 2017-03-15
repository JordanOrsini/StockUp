import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class guiLogin {

	JFrame window;
	JTextField userField;
	JTextField passField;
	JLabel userName;
	JLabel passWord;
	JButton loginButton;
	
	guiLogin() {

		// Basic window
		window = new JFrame("PROFITS R' US LOGIN");
		window.setSize(500, 250);
		window.getContentPane().setBackground(Color.GRAY);
		window.setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Labels
		userName = new JLabel("Username:");
		passWord = new JLabel("Password:");
		userName.setLocation(80, 60);
		passWord.setLocation(80, 100);
		userName.setSize(userName.getPreferredSize());
		passWord.setSize(passWord.getPreferredSize());
		window.add(userName);
		window.add(passWord);

		// Textfields
		userField = new JTextField();
		userField.setColumns(15);
		userField.setSize(userField.getPreferredSize());
		userField.setLocation(180, 60);
		userField.setToolTipText("Enter username");
		userField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user = userField.getText();
				String pass = passField.getText();
				
				//check var user with text file user( same for pass)
				String filename = "user.txt";
				String filename2 = "pass.txt";
				ArrayList<String> userList = new ArrayList<String>();
				ArrayList<String> passList = new ArrayList<String>();
				
				FileReader file;
				FileReader file2;
				try {
					file = new FileReader(filename);
					file2 = new FileReader(filename2);
					BufferedReader buff = new BufferedReader(file);
					BufferedReader buff2 = new BufferedReader(file2);
					
					String line = null;
					String line2 = null;

					while (((line = buff.readLine()) != null)) 
					{
						userList.add(line);	
					}
					while((line2 = buff2.readLine()) != null)
					{
						passList.add(line2);
					}
					
					buff.close();
					buff2.close();
					//System.out.println(userList);
					//System.out.println(passList);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean userPassFound = false;
				//NEW LOG IN makes sure the user name corresponds to the password and not just is in the list
				for(int y = 0; y < userList.size(); y++)
				{
					if(userList.get(y).equals(user) && passList.get(y).equals(pass))
					{
						userPassFound = true;
						JOptionPane.showMessageDialog(window, "Welcome " + user + "!");
						window.dispose();
						try 
						{
							guiMain main = new guiMain();
						} 
						catch (NumberFormatException | IOException | ParseException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(userPassFound == false)
				{
					JOptionPane.showMessageDialog(window, "Invalid login credentials!");
				}
				
				//OLD LOG IN
				/*if (userList.contains(user) && passList.contains(pass)) 
				{
					JOptionPane.showMessageDialog(window, "Welcome!");
					window.dispose();
					try {
						guiMain main = new guiMain();
					} catch (NumberFormatException | IOException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(window, "Invalid login credentials!");
				}*/

			}
		});
		window.add(userField);

		passField = new JPasswordField();
		passField.setColumns(15);
		passField.setSize(passField.getPreferredSize());
		passField.setLocation(180, 100);
		passField.setToolTipText("Password");
		passField.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user = userField.getText();
				String pass = passField.getText();
				
				//check var user with text file user( same for pass)
				String filename = "user.txt";
				String filename2 = "pass.txt";
				ArrayList<String> userList = new ArrayList<String>();
				ArrayList<String> passList = new ArrayList<String>();
				
				FileReader file;
				FileReader file2;
				try {
					file = new FileReader(filename);
					file2 = new FileReader(filename2);
					BufferedReader buff = new BufferedReader(file);
					BufferedReader buff2 = new BufferedReader(file2);
					
					String line = null;
					String line2 = null;

					while (((line = buff.readLine()) != null)) 
					{
						userList.add(line);	
					}
					while((line2 = buff2.readLine()) != null)
					{
						passList.add(line2);
					}
					
					buff.close();
					buff2.close();
					//System.out.println(userList);
					//System.out.println(passList);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean userPassFound = false;
				//NEW LOG IN makes sure the user name corresponds to the password and not just is in the list
				for(int y = 0; y < userList.size(); y++)
				{
					if(userList.get(y).equals(user) && passList.get(y).equals(pass))
					{
						userPassFound = true;
						JOptionPane.showMessageDialog(window, "Welcome " + user + "!");
						window.dispose();
						try 
						{
							guiMain main = new guiMain();
						} 
						catch (NumberFormatException | IOException | ParseException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(userPassFound == false)
				{
					JOptionPane.showMessageDialog(window, "Invalid login credentials!");
				}
				
				//OLD LOG IN
				/*if (userList.contains(user) && passList.contains(pass)) 
				{
					JOptionPane.showMessageDialog(window, "Welcome!");
					window.dispose();
					try {
						guiMain main = new guiMain();
					} catch (NumberFormatException | IOException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(window, "Invalid login credentials!");
				}*/

			}
		});
		window.add(passField);

		// Button
		loginButton = new JButton("Login");
		loginButton.setSize(loginButton.getPreferredSize());
		loginButton.setLocation(230, 140);
		loginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user = userField.getText();
				String pass = passField.getText();
				
				//check var user with text file user( same for pass)
				String filename = "user.txt";
				String filename2 = "pass.txt";
				ArrayList<String> userList = new ArrayList<String>();
				ArrayList<String> passList = new ArrayList<String>();
				
				FileReader file;
				FileReader file2;
				try {
					file = new FileReader(filename);
					file2 = new FileReader(filename2);
					BufferedReader buff = new BufferedReader(file);
					BufferedReader buff2 = new BufferedReader(file2);
					
					String line = null;
					String line2 = null;

					while (((line = buff.readLine()) != null)) 
					{
						userList.add(line);	
					}
					while((line2 = buff2.readLine()) != null)
					{
						passList.add(line2);
					}
					
					buff.close();
					buff2.close();
					//System.out.println(userList);
					//System.out.println(passList);

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				boolean userPassFound = false;
				//NEW LOG IN makes sure the user name corresponds to the password and not just is in the list
				for(int y = 0; y < userList.size(); y++)
				{
					if(userList.get(y).equals(user) && passList.get(y).equals(pass))
					{
						userPassFound = true;
						JOptionPane.showMessageDialog(window, "Welcome " + user + "!");
						window.dispose();
						try 
						{
							guiMain main = new guiMain();
						} 
						catch (NumberFormatException | IOException | ParseException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				if(userPassFound == false)
				{
					JOptionPane.showMessageDialog(window, "Invalid login credentials!");
				}
				
				//OLD LOG IN
				/*if (userList.contains(user) && passList.contains(pass)) 
				{
					JOptionPane.showMessageDialog(window, "Welcome!");
					window.dispose();
					try {
						guiMain main = new guiMain();
					} catch (NumberFormatException | IOException | ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				else 
				{
					JOptionPane.showMessageDialog(window, "Invalid login credentials!");
				}*/

			}
		});
		window.add(loginButton);

		window.setResizable(false);
		window.setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new guiLogin();


	}

}
