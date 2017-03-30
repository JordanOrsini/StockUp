import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
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
	JButton createAcc;
	
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
				String filename = "loginCredentials/user.txt";
				String filename2 = "loginCredentials/pass.txt";
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
							guiMain main = new guiMain(user);
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
				
				//System.out.println(user);
				//System.out.println(pass);
				
				//check var user with text file user( same for pass)
				String filename = "loginCredentials/user.txt";
				String filename2 = "loginCredentials/pass.txt";
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
							guiMain main = new guiMain(user);
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
		loginButton = new JButton("Log in");
		loginButton.setSize(loginButton.getPreferredSize());
		loginButton.setLocation(176, 140);
		loginButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String user = userField.getText();
				String pass = passField.getText();
				
				//check var user with text file user( same for pass)
				String filename = "loginCredentials/user.txt";
				String filename2 = "loginCredentials/pass.txt";
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
							guiMain main = new guiMain(user);
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
		
		
		createAcc = new JButton("Register");
		createAcc.setSize(createAcc.getPreferredSize());
		createAcc.setLocation(253, 140);
		createAcc.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				String user = userField.getText();
				String pass = passField.getText();
				boolean duplicateFound = false;
				boolean nullFound = false;
				
				if(user.equals("") || pass.equals(""))
				{
					nullFound = true;
				}
				
				if(nullFound == false)
				{
					ArrayList<String> userList = new ArrayList<String>();
					FileReader file;
					
					try 
					{
						String filename = "loginCredentials/user.txt";
						file = new FileReader(filename);
						
						BufferedReader buff = new BufferedReader(file);	
						
						String line = null;
						
						while (((line = buff.readLine()) != null)) 
						{
							userList.add(line);	
						}
	
						buff.close();
					}
					catch (FileNotFoundException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					for(int t = 0; t < userList.size(); t++)
					{
						if(userList.get(t).equals(user))
						{
							duplicateFound = true;
						}
					}
				}
						
				
				if(duplicateFound == false && nullFound == false)
				{
					try
					{
						PrintWriter userWrite = new PrintWriter(new BufferedWriter(new FileWriter("loginCredentials/user.txt",true)));
						PrintWriter passWrite = new PrintWriter(new BufferedWriter(new FileWriter("loginCredentials/pass.txt",true)));
						//userWrite.println();
						//passWrite.println();
						userWrite.print("\n" + user);
						passWrite.print("\n" + pass);
						userWrite.close();
						passWrite.close();
						JOptionPane.showMessageDialog(window, "Registration successful!");
						
						PrintStream outputWriter;
						outputWriter = new PrintStream("userProfiles/" + user + ".txt");
						window.dispose();
						//new guiLogin();
						
						try 
						{
							guiMain main = new guiMain(user);
						} 
						catch (NumberFormatException | IOException | ParseException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					catch(IOException e){
						JOptionPane.showMessageDialog(window, "Registration error!");
					}
				}
				else if(duplicateFound == true)
				{
					JOptionPane.showMessageDialog(window, "Username already exists!");
				}
				else
				{
					JOptionPane.showMessageDialog(window, "Null entry!");
				}
				
			}
		});
		window.add(createAcc);

		window.setResizable(false);
		window.setVisible(true);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new guiLogin();


	}

}
