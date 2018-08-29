import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WelcomeMenu 
{

	// class fields
	/** GridBagConstraints constraints */
	public static final double WEIGHTX = 1.0;
	/** GridBagConstraints constraints */
	public static final double WEIGHTX_NONE = 0;

	/** GridBagConstraints constraints */
	public static final double WEIGHTY = 1.0;

	/** GridBagConstraints constraints */
	public static final int FIRST_COLUMN = 0;
	/** GridBagConstraints constraints */
	public static final int SECOND_COLUMN = 1;

	/** GridBagConstraints constraints */
	public static final int FIRST_ROW = 0;
	/** GridBagConstraints constraints */
	public static final int SECOND_ROW = 1;
	/** GridBagConstraints constraints */
	public static final int THIRD_ROW = 2;
	/** GridBagConstraints constraints */
	public static final int FOURTH_ROW = 3;

	// instance fields
	private File file;
	private BufferedReader bufferedReader;
	private FileReader fileReader;

	private JFrame frame;
	private JPanel loginContentPane;
	private Container mainContentPane;

	private JPanel titleContentPane;
	private JLabel titleLabel;

	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;

	private JPanel buttonContentPane;
	private JButton loginButton;
	private JButton checkAvailableClassButton;

	private JLabel wrongCredentials;

	/**
	 * Creates a new WelcomeMenu object.
	 */
	public static void main(String[] args)
	{
		WelcomeMenu welcomeMenu = new WelcomeMenu();
	}

	/**
	 * Constructs a new WelcomeMenu with default values.
	 */
	public WelcomeMenu() 
	{
		paintFrame();
	}

	/**
	 * Paints the frame of this WelcomeMenu.
	 */
	public void paintFrame() 
	{
		frame = new JFrame("School Database Program");
		GridBagConstraints constraints = new GridBagConstraints();

		titleContentPane = new JPanel();
		titleContentPane.setLayout(new BoxLayout(titleContentPane, BoxLayout.Y_AXIS));

		titleLabel = new JLabel("School Database Program", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		titleContentPane.add(titleLabel);

		loginContentPane = new JPanel();
		loginContentPane.setLayout(new GridBagLayout());

		usernameLabel = new JLabel("Username: ");
		constraints.insets = new Insets(5, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.weighty = WEIGHTY;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		loginContentPane.add(usernameLabel, constraints);

		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(50, 25));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		loginContentPane.add(usernameField, constraints);

		passwordLabel = new JLabel("Password: ");
		constraints.insets = new Insets(0, 0, 7, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		loginContentPane.add(passwordLabel, constraints);

		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(50, 25));
		passwordField.addFocusListener(new FocusFieldReset());
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = SECOND_ROW;
		loginContentPane.add(passwordField, constraints);

		buttonContentPane = new JPanel();
		buttonContentPane.setLayout(new GridBagLayout());

		loginButton = new JButton("Login");
		loginButton.addActionListener(new LoginCheck());
		loginButton.setPreferredSize(new Dimension(100, 25));
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = THIRD_ROW;
		buttonContentPane.add(loginButton, constraints);

		checkAvailableClassButton = new JButton("Check Grade Availability");
		checkAvailableClassButton.addActionListener(new CheckClassAvailability());
		checkAvailableClassButton.setPreferredSize(new Dimension(200, 25));
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FOURTH_ROW;
		buttonContentPane.add(checkAvailableClassButton, constraints);

		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		mainContentPane.add(titleContentPane, BorderLayout.NORTH);
		mainContentPane.add(loginContentPane, BorderLayout.CENTER);
		mainContentPane.add(buttonContentPane, BorderLayout.SOUTH);

		frame.setContentPane(mainContentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Displays this message if the credentials are wrong.
	 */
	private void displayMessage() 
	{
		if (wrongCredentials != null) return;
		GridBagConstraints constraints = new GridBagConstraints();

		wrongCredentials = new JLabel("Wrong username/password");
		titleContentPane.add(wrongCredentials);
		frame.validate();
		frame.repaint();
	}

	/**
	 * Gets the login file.
	 * 
	 * @return <code>true</code> if the operation is successful, otherwise
	 *         <code>false</code>
	 */
	private boolean getLoginFile() 
	{
		try 
		{
			String filename = "login.txt";
			file = new File("resources" + File.separator + filename);
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			return true;
		} 
		catch (FileNotFoundException e)
		{
			return false;
		}
	}

	/* private class */

	private class FocusFieldReset implements FocusListener 
	{

		/**
		 * Resets the field upon focus.
		 */
		public void focusGained(FocusEvent focusEvent) 
		{
			JTextField textField = (JTextField) focusEvent.getSource();
			textField.setText("");
		}

		public void focusLost(FocusEvent arg0) {}
	}

	// checks login credentials when login button is clicked
	private class LoginCheck implements ActionListener 
	{
		
		/**
		 * Checks the login credentials.
		 */
		public void actionPerformed(ActionEvent actionEvent) 
		{
			if (getLoginFile()) 
			{
				try 
				{
					String str = bufferedReader.readLine();
					if (str == null || str.isEmpty()) 
					{
						CreateAccountMenu createAccountMenu = new CreateAccountMenu();
						usernameField.setText("");
						passwordField.setText("");
						return;
					}
					String[] line = str.split(",", 2);
					if (line[0].equals(String.valueOf(usernameField.getText()))
							&& line[1].equals(String.valueOf(passwordField.getPassword()))) 
					{
						MainMenu mainMenu = new MainMenu();
						fileReader.close();
						bufferedReader.close();
						frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					} 
					else 
					{
						displayMessage();
					}
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} 
				finally 
				{
					try 
					{
						if (bufferedReader != null && fileReader != null) 
						{
							bufferedReader.close();
							fileReader.close();
						}
					} 
					catch (IOException ex) 
					{
						ex.printStackTrace();
					}
				}
			} 
			else
			{
				CreateAccountMenu createAccountMenu = new CreateAccountMenu();
				usernameField.setText("");
				passwordField.setText("");
			}
		}
	}

	private class CheckClassAvailability implements ActionListener 
	{
		
		/**
		 * Checks the availability of a class for registration.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			CheckClassAvailabilityMenu menu = new CheckClassAvailabilityMenu();
		}
	}
}
