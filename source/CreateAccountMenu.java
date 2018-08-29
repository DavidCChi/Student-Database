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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A frame that creates a new user account.
 */
public class CreateAccountMenu 
{

    // class fields
	/** GridBagConstraints values */
    public static final double WEIGHTX = 1.0;
    /** GridBagConstraints values */
    public static final double WEIGHTX_NONE = 0;

    /** GridBagConstraints values */
    public static final int FIRST_COLUMN = 0;
    /** GridBagConstraints values */
    public static final int SECOND_COLUMN = 1;

    /** GridBagConstraints values */
    public static final int FIRST_ROW = 0;
    /** GridBagConstraints values */
    public static final int SECOND_ROW = 1;
    /** GridBagConstraints values */
    public static final int THIRD_ROW = 2;
    /** GridBagConstraints values */
    public static final int FOURTH_ROW = 3;

    // instance fields
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private File newAccount;

    private JFrame frame;
    private Container mainContentPane;

    private JPanel titleContentPane;
    private JLabel titleLabel;
    private JLabel errorLabel;

    private JPanel createContentPane;
    private JLabel usernameLabel;
    private JTextField usernameField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JButton createButton;

    /**
     * Initializes this CreateAccountMenu with default values.
     */
    public CreateAccountMenu() 
    {
        paintFrame();
    }

    /**
     * Paints the frame of this CreateAccountMenu.
     */
    private void paintFrame() 
    {
        frame = new JFrame("Create A New Account");
        GridBagConstraints constraints = new GridBagConstraints();

        createContentPane = new JPanel();
        createContentPane.setLayout(new GridBagLayout());

        titleContentPane = new JPanel();
        titleContentPane.setLayout(new BoxLayout(titleContentPane, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Create A New Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
        titleContentPane.add(titleLabel);

        usernameLabel = new JLabel("Username: ");
        constraints.insets = new Insets(5, 0, 0, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX_NONE;
        constraints.gridx = FIRST_COLUMN;
        constraints.gridy = FIRST_ROW;
        createContentPane.add(usernameLabel, constraints);

        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(50, 25));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX;
        constraints.gridx = SECOND_COLUMN;
        constraints.gridy = FIRST_ROW;
        createContentPane.add(usernameField, constraints);

        passwordLabel = new JLabel("Password: ");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX_NONE;
        constraints.gridx = FIRST_COLUMN;
        constraints.gridy = SECOND_ROW;
        createContentPane.add(passwordLabel, constraints);

        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(50, 25));
        passwordField.addFocusListener(new FocusFieldReset());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX;
        constraints.gridx = SECOND_COLUMN;
        constraints.gridy = SECOND_ROW;
        createContentPane.add(passwordField, constraints);

        confirmPasswordLabel = new JLabel("Confirm Password: ");
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX_NONE;
        constraints.gridx = FIRST_COLUMN;
        constraints.gridy = THIRD_ROW;
        createContentPane.add(confirmPasswordLabel, constraints);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(50, 25));
        confirmPasswordField.addFocusListener(new FocusFieldReset());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX;
        constraints.gridx = SECOND_COLUMN;
        constraints.gridy = THIRD_ROW;
        createContentPane.add(confirmPasswordField, constraints);

        createButton = new JButton("Create Account");
        createButton.addActionListener(new CreateAccount());
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = WEIGHTX;
        constraints.gridwidth = 2;
        constraints.gridx = FIRST_COLUMN;
        constraints.gridy = FOURTH_ROW;
        createContentPane.add(createButton, constraints);

        mainContentPane = frame.getContentPane();
        mainContentPane.setLayout(new BorderLayout());
        mainContentPane.add(createContentPane, BorderLayout.CENTER);
        mainContentPane.add(titleContentPane, BorderLayout.NORTH);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.setContentPane(mainContentPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /* private class */

    private class FocusFieldReset implements FocusListener 
    {
    	/**
    	 * Resets the password field upon focus.
    	 */
        public void focusGained(FocusEvent focusEvent) 
        {
            JPasswordField passwordField = (JPasswordField)focusEvent.getSource();
            passwordField.setText("");
        }

        public void focusLost(FocusEvent arg0) {}
    }

    private class CreateAccount implements ActionListener {
    	
    	/**
    	 * Creates a new user account.
    	 */
        public void actionPerformed(ActionEvent arg0) 
        {
            if (newAccount == null) 
            {
            	String file = "login.txt";
        		newAccount = new File("resources" + File.separator + file);
            }
            if (!newAccount.exists()) 
            {
                try 
                {
                	newAccount.getParentFile().mkdirs();
                    newAccount.createNewFile();
                } catch (IOException e) {}
            }
            try 
            {
                fileWriter = new FileWriter(newAccount);
                bufferedWriter = new BufferedWriter(fileWriter);
                //login.txt contains name of person and the password, delimited by ","
                if (usernameField.getText().isEmpty() || passwordField.getPassword().length == 0) 
                {
                    if (errorLabel == null) 
                    {
                        errorLabel = new JLabel("Username/Password cannot be empty.");
                        titleContentPane.add(errorLabel);
                    } 
                    else 
                    {
                        errorLabel.setText("Username/Password cannot be empty.");
                    }
                    frame.pack();
                    frame.repaint();
                    return;
                } 
                else if (!Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) 
                {
                    if (errorLabel == null) 
                    {
                        errorLabel = new JLabel("Passwords don't match.");
                        titleContentPane.add(errorLabel);
                    } 
                    else 
                    {
                        errorLabel.setText("Passwords don't match.");
                    }
                    frame.pack();
                    frame.repaint();
                    return;
                }
                else 
                {
                    bufferedWriter.write(usernameField.getText() + "," + String.valueOf(passwordField.getPassword()));
                    JOptionPane.showMessageDialog(frame, "Account successfully created.");
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
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
                    bufferedWriter.close();
                    fileWriter.close();
                }
                catch (IOException e1) {}
            }
        }
    }
}