import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A frame that manipulates a Student.
 */
public class ManipulateStudentMenu 
{
	
	// class fields
	/** GridBagConstraints constraints */
	public static final double WEIGHTX = 1.0;
	/** GridBagConstraints constraints */
	public static final double WEIGHTX_NONE = 0;
	
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
	/** GridBagConstraints constraints */
	public static final int FIFTH_ROW = 4;
	/** GridBagConstraints constraints */
	public static final int SIXTH_ROW = 5;
	/** GridBagConstraints constraints */
	public static final int SEVENTH_ROW = 6;
	/** GridBagConstraints constraints */
	public static final int EIGHTH_ROW = 7;
	/** GridBagConstraints constraints */
	public static final int NINTH_ROW = 8;
	/** GridBagConstraints constraints */
	public static final int TENTH_ROW = 9;
	/** GridBagConstraints constraints */
	public static final int ELEVENTH_ROW = 10;
	/** GridBagConstraints constraints */
	public static final int TWELFTH_ROW = 11;
	/** GridBagConstraints constraints */
	public static final int THIRTEENTH_ROW = 12;
	
	// protected fields
	protected GridBagConstraints constraints;
	
	protected JFrame frame;
	protected Container mainContentPane;
	
	protected JPanel titleContentPane;
	protected JLabel titleLabel;
	
	protected JPanel menuContentPane;
	
	protected JLabel addressLabel;
	protected JTextField addressField;
	protected JLabel ageLabel;
	protected JTextField ageField;
	protected JLabel birthDateLabel;
	protected JTextField birthDateField;
	protected JLabel emergencyNameLabel;
	protected JTextField emergencyNameField;
	protected JLabel emergencyPhoneLabel;
	protected JTextField emergencyPhoneField;
	protected JLabel gradeLabel;
	protected JTextField gradeField;
	protected JLabel healthCardLabel;
	protected JTextField healthCardField;
	protected JLabel isMorningClassLabel;
	protected JTextField isMorningClassField;
	protected JLabel nameLabel;
	protected JTextField nameField;
	protected JLabel oenNumberLabel;
	protected JTextField oenNumberField;
	protected JLabel parentNameLabel;
	protected JTextField parentNameField;
	protected JLabel phoneLabel;
	protected JTextField phoneField;
	protected JLabel specificHealthProblemLabel;
	protected JTextArea specificHealthProblemField;
	
	protected JPanel buttonContentPane;
	
	/**
	 * Creates a new ManipulateStudentMenu with default values.
	 */
	public ManipulateStudentMenu() 
	{
		paintFrame();
	}
	
	/**
	 * Paints the frame of this ManipulateStudentMenu.
	 */
	private void paintFrame() 
	{
		frame = new JFrame("Student Menu");
		constraints = new GridBagConstraints();
		
		titleContentPane = new JPanel();
		titleContentPane.setLayout(new BoxLayout(titleContentPane, BoxLayout.Y_AXIS));
		
		titleLabel = new JLabel("Student Menu", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		titleContentPane.add(titleLabel);
		
		menuContentPane = new JPanel();
		menuContentPane.setLayout(new GridBagLayout());
		
		nameLabel = new JLabel("Name (Last First): ");
		constraints.insets = new Insets(5, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		menuContentPane.add(nameLabel, constraints);
		
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		menuContentPane.add(nameField, constraints);
		
		birthDateLabel = new JLabel("Date of Birth (YYYY-MM-DD): ");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		menuContentPane.add(birthDateLabel, constraints);
		
		birthDateField = new JTextField();
		birthDateField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = SECOND_ROW;
		menuContentPane.add(birthDateField, constraints);
		
		ageLabel = new JLabel("Age: ");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = THIRD_ROW;
		menuContentPane.add(ageLabel, constraints);
		
		ageField = new JTextField();
		ageField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = THIRD_ROW;
		menuContentPane.add(ageField, constraints);
		
		oenNumberLabel = new JLabel("OEN: ");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FOURTH_ROW;
		menuContentPane.add(oenNumberLabel, constraints);
		
		oenNumberField = new JTextField();
		oenNumberField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FOURTH_ROW;
		menuContentPane.add(oenNumberField, constraints);
		
		gradeLabel = new JLabel("Grade (1-8): ");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIFTH_ROW;
		menuContentPane.add(gradeLabel, constraints);
		
		gradeField = new JTextField();
		gradeField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIFTH_ROW;
		menuContentPane.add(gradeField, constraints);
		
		isMorningClassLabel = new JLabel("Morning Class (Y/N): ");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SIXTH_ROW;
		menuContentPane.add(isMorningClassLabel, constraints);
		
		isMorningClassField = new JTextField();
		isMorningClassField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = SIXTH_ROW;
		menuContentPane.add(isMorningClassField, constraints);
		
		parentNameLabel = new JLabel("Parent Name:");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SEVENTH_ROW;
		menuContentPane.add(parentNameLabel, constraints);
		
		parentNameField = new JTextField();
		parentNameField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = SEVENTH_ROW;
		menuContentPane.add(parentNameField, constraints);
		
		addressLabel = new JLabel("Address:");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = EIGHTH_ROW;
		menuContentPane.add(addressLabel, constraints);
		
		addressField = new JTextField();
		addressField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = EIGHTH_ROW;
		menuContentPane.add(addressField, constraints);
		
		phoneLabel = new JLabel("Phone Number:");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = NINTH_ROW;
		menuContentPane.add(phoneLabel, constraints);
		
		phoneField = new JTextField();
		phoneField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = NINTH_ROW;
		menuContentPane.add(phoneField, constraints);
		
		healthCardLabel = new JLabel("Health Card Number:");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = TENTH_ROW;
		menuContentPane.add(healthCardLabel, constraints);
		
		healthCardField = new JTextField();
		healthCardField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = TENTH_ROW;
		menuContentPane.add(healthCardField, constraints);
		
		emergencyNameLabel = new JLabel("Emergency Contact Name:");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = ELEVENTH_ROW;
		menuContentPane.add(emergencyNameLabel, constraints);
		
		emergencyNameField = new JTextField();
		emergencyNameField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = ELEVENTH_ROW;
		menuContentPane.add(emergencyNameField, constraints);
		
		emergencyPhoneLabel = new JLabel("Emergency Contact Phone:");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = TWELFTH_ROW;
		menuContentPane.add(emergencyPhoneLabel, constraints);
		
		emergencyPhoneField = new JTextField();
		emergencyPhoneField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = TWELFTH_ROW;
		menuContentPane.add(emergencyPhoneField, constraints);
		
		specificHealthProblemLabel = new JLabel("Specific Health Problem(s):");
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = THIRTEENTH_ROW;
		menuContentPane.add(specificHealthProblemLabel, constraints);
		
		specificHealthProblemField = new JTextArea(20, 20);
		specificHealthProblemField.setMinimumSize(new Dimension(50, 100));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = THIRTEENTH_ROW;
		menuContentPane.add(specificHealthProblemField, constraints);
		
		buttonContentPane = new JPanel();
		buttonContentPane.setLayout(new GridBagLayout());
		
		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		mainContentPane.add(titleContentPane, BorderLayout.NORTH);
		mainContentPane.add(menuContentPane, BorderLayout.CENTER);
		mainContentPane.add(buttonContentPane, BorderLayout.SOUTH);
		
		frame.setContentPane(mainContentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(550, 550));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}