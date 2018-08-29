import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A frame that manipulates an Attendance.
 */
public class ManipulateAttendanceMenu 
{

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
	
	protected JLabel dateLabel;
	protected JTextField dateField;
	protected JLabel[] morningStudentCountLabel;
	protected JButton[] morningStudentCountField;
	protected JLabel[] afternoonStudentCountLabel;
	protected JButton[] afternoonStudentCountField;
	
	protected JPanel buttonContentPane;
	
	/**
	 * Creates a new ManipulateAttendanceMenu with default values.
	 */
	public ManipulateAttendanceMenu() 
	{
		morningStudentCountLabel = new JLabel[8];
		morningStudentCountField = new JButton[8];
		afternoonStudentCountLabel = new JLabel[8];
		afternoonStudentCountField = new JButton[8];
		
		paintFrame();
	}
	
	/**
	 * Paints the frame of this ManipulateAttendanceMenu.
	 */
	private void paintFrame() 
	{
		frame = new JFrame("Attendance Menu");
		constraints = new GridBagConstraints();
		
		titleContentPane = new JPanel();
		titleContentPane.setLayout(new BoxLayout(titleContentPane, BoxLayout.Y_AXIS));
		
		titleLabel = new JLabel("Attendance Menu", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		titleContentPane.add(titleLabel);
		
		menuContentPane = new JPanel();
		menuContentPane.setLayout(new GridBagLayout());
		
		dateLabel = new JLabel("Date (YYYY-MM-DD): ");
		constraints.insets = new Insets(5, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		menuContentPane.add(dateLabel, constraints);
		
		dateField = new JTextField();
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		menuContentPane.add(dateField, constraints);
		
		for (int i = 0; i < morningStudentCountLabel.length; i++)
		{
			morningStudentCountLabel[i] = new JLabel("Morning Grade " + (i + 1) + ":");
			constraints.weightx = WEIGHTX_NONE;
			constraints.gridx = FIRST_COLUMN;
			constraints.gridy = (i + 1);
			menuContentPane.add(morningStudentCountLabel[i], constraints);
		}
		
		for (int i = 0; i < morningStudentCountField.length; i++) 
		{
			morningStudentCountField[i] = new JButton("Click to select absent students");
			constraints.weightx = WEIGHTX;
			constraints.gridx = SECOND_COLUMN;
			constraints.gridy = (i + 1);
			menuContentPane.add(morningStudentCountField[i], constraints);
		}
		
		for (int i = 0; i < afternoonStudentCountLabel.length; i++)
		{
			afternoonStudentCountLabel[i] = new JLabel("Afternoon Grade " + (i + 1) + ":");
			constraints.weightx = WEIGHTX_NONE;
			constraints.gridx = FIRST_COLUMN;
			constraints.gridy = (i + 9);
			menuContentPane.add(afternoonStudentCountLabel[i], constraints);
		}
		
		for (int i = 0; i < afternoonStudentCountField.length; i++) 
		{
			afternoonStudentCountField[i] = new JButton("Click to select absent students");
			constraints.weightx = WEIGHTX;
			constraints.gridx = SECOND_COLUMN;
			constraints.gridy = (i + 9);
			menuContentPane.add(afternoonStudentCountField[i], constraints);
		}
		
		buttonContentPane = new JPanel();
		buttonContentPane.setLayout(new GridBagLayout());
		
		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		mainContentPane.add(titleContentPane, BorderLayout.NORTH);
		mainContentPane.add(menuContentPane, BorderLayout.CENTER);
		mainContentPane.add(buttonContentPane, BorderLayout.SOUTH);
		
		frame.setContentPane(mainContentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(new Dimension(350, 665));
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
