/*
 * Kevin Seng
 * CS-499-T3254
 * Computer Science Capstone 21EW3
 * 02/07/2021
 *
*/

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.security.MessageDigest; // Import Library for MD5 utility
import java.security.NoSuchAlgorithmException;
import java.io.FileNotFoundException;
import java.io.IOException; // Import IOException needed by FileInputStream
import java.math.BigInteger;

import java.sql.*;

public class AuthenticationSystem extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	// Create JFrame objects that will be referenced later
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;
	private JTextField usernameForm = new JTextField(20);
	private JTextField passwordForm = new JTextField(20);
	

	public static void main(String[] args) throws Exception, IOException, NoSuchAlgorithmException, FileNotFoundException {
		
		AuthenticationSystem authenticationSystem = new AuthenticationSystem();
		authenticationSystem.prepareGUI(); // Call prepareGUI to create main frame
		authenticationSystem.createPanel(); // Call createPanel to create panel that has username and password fields with buttons
		
	}
	
	private void prepareGUI() {
		
		mainFrame = new JFrame(); // Create new frame that will house the header, status, and panel that has username and password fields as well as buttons
		mainFrame.setSize(500, 500); // Set size of frame
		mainFrame.setLayout(new GridLayout(3,1)); // Configure grid with 3 rows and 1 column
		mainFrame.setResizable(false);	// Do not allow window to be resized
		
		headerLabel = new JLabel("Zoo Authentication System", JLabel.CENTER); // Set header label that will have initial message
		statusLabel = new JLabel("", JLabel.CENTER); // Create status label that will have status of login attempt
		
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); // Set default behavior of frame being closed is to exit
		
		controlPanel = new JPanel(); // Create panel that will house objects from createPanel()
		controlPanel.setLayout(new FlowLayout()); // Set layout of JFrame
		
		// Add all objects to mainFrame
		mainFrame.add(headerLabel); 
		mainFrame.add(statusLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true); // Make the frame visible
		
	}
	
	public void createPanel() {
		
		// Title of JFrame
		//super("Authentication System");
				
		JPanel panel = new JPanel(); // Create panel that will house label, fields, and buttons
		panel.setSize(300, 300); // Size of window. Common defaults are 1024x768, 800x600
		
		panel.setBackground(Color.lightGray);
		GridLayout layout = new GridLayout(3,2); // Create layout
		layout.setHgap(10);
		layout.setVgap(10);
		
		panel.setLayout(layout); // Set layout of panel
				
		// Create labels
		JLabel usernameField = new JLabel("Username:", SwingConstants.CENTER); // Create username label and text
		JLabel passwordField = new JLabel("Password:", SwingConstants.CENTER); // Create password label and text
		
		// Create buttons
		JButton enterButton = new JButton("Enter");
		enterButton.addActionListener(this);
				
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);

		// Add JFrame objects to panel
		panel.add(usernameField);
		panel.add(passwordField);
		panel.add(usernameForm);
		panel.add(passwordForm);
		panel.add(enterButton);
		panel.add(cancelButton);
				
		// Add panel to mainFrame through the controlPanel
		controlPanel.add(panel);
		mainFrame.setVisible(true); // Set mainFrame to visible
		
	}
	
	public static String usernameHash(String s) throws NoSuchAlgorithmException {
		
		// Method to convert input s to MD5
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5"); // getInstance method called with hashing as MD5
			
			byte[] usernameDigest = md.digest(s.getBytes()); // digest() called to calculate digest and returned as array of bytes
			
			BigInteger no = new BigInteger(usernameDigest); // Convert byte array into signum representation
			
			// Convert message digest into hex value
			String hashText = no.toString(16);
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			
			return hashText; // Return hash as string
			
		}
			// Exception handling
			catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
			
		}
	
	public static String passwordHash(String s) throws NoSuchAlgorithmException {
		
		// Method to convert input s to MD5

		try {
			
			MessageDigest md = MessageDigest.getInstance("MD5"); // getInstance method called with hashing as MD5
			
			byte[] passwordDigest = md.digest(s.getBytes()); // digest() called to calculate digest and returned as array of bytes
			
			BigInteger no = new BigInteger(passwordDigest); // Convert byte array into signum representation
			
			// Convert message digest into hex value
			String hashText = no.toString(16); 
			while (hashText.length() < 32) {
				hashText = "0" + hashText;
			}
			
			return hashText; // Return hash as string
			
		}
			// Exception handling
			catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
			
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Connection c = null;
		Statement stmt = null;
		
		//int i = 0;
		String name = e.getActionCommand();
		
		//FileInputStream fileByteStream = null; // Create FileInputStream to read files
		//Scanner inFS = null; // Create scanner to read files
		
		// If enter button is clicked print
		if(name.equals("Enter")) {
			
			System.out.println("Enter pressed"); // Used to test, works when code below is commented out
			
			// Decided to just test with one username and password with MD5 to prepare for reading from DB
			// Credentials:
			
			// Username = "username"
			// Password = "password"
			
		    // Testing password MD5 == password MD5
		    /*
		       Password hash generated by MD5 for 'password' is: 5f4dcc3b5aa765d61d8327deb882cf99
			   Password hash generated by MD5 for user input is: 5f4dcc3b5aa765d61d8327deb882cf99
			
			
			try {
				System.out.println("Password hash generated by MD5 for 'password' is: " + passwordHash("password"));
			}
			
			catch (NoSuchAlgorithmException x) {
				throw new RuntimeException(x);
			}
			
			try {
				System.out.println("Password hash generated by MD5 for user input is: " + passwordHash(passwordForm.getText()));
			}
			
			catch (NoSuchAlgorithmException x) {
				throw new RuntimeException(x);
			}
			
			*/
			
			try {
				
				Class.forName("org.postgresql.Driver");
				
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "password"); // Create connection to psql database
				System.out.println("Successfully Connected."); // Print to confirm successfully connected
				stmt = c.createStatement(); // Method to process SQL statement with JDBC
				
				
				// ResultSet rs = stmt.executeQuery("select * from public.\"credentials\" ;"); // Testing to read all from table				
				
				ResultSet rs = stmt.executeQuery("select * from public.credentials where credentials.username = '" + usernameForm.getText() + "';"); // Query to get record by username entered
				
				// Need to loop for rs.next or will throw PSQLException: ResultSet not positioned properly, perhaps you need to call next
				while (rs.next()) {
					
					// Did not return password as it is probably not good to store password from db, rather check against hash
					// In future could make method to create hash and insert into table, but for now hashes are entered manually
					String dbUsername = rs.getString("username"); // Set dbUsername to username returned from db
					String dbHashedPassword = rs.getString("hashed_password"); // Set dbHashedPassword to hashed_password from db
					String dbRole = rs.getString("role"); // Set dbRole to role from db
					
					// Check if hashes for both username AND password match against "username"/"password". If true, print message to status label
					try { 
						if (dbHashedPassword.equals(passwordHash(passwordForm.getText())) && usernameHash(dbUsername).equals(usernameHash(usernameForm.getText()))) {
							statusLabel.setText("Username and Password hashes match! Role is: " + dbRole);
						}
						else {
							statusLabel.setText("Username and/or Password do not match!");
						}
							
					}
					
					catch (NoSuchAlgorithmException x) {
						throw new RuntimeException(x);
					}
					
				}
				
				// Close connections
				rs.close();		
				stmt.close();
				c.close();
			}
			
			catch (Exception x) {
				
				System.err.println(x.getClass().getName()+": "+ x.getMessage());
				System.exit(0);
				
			}
		}
			
		// If cancel button is clicked, close window. Works if code above is commented out
		else if (name.equals("Cancel")) {
			System.out.println("Exited");
			System.exit(0);
		}
	}

}
