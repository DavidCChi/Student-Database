import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A frame that asks for the exact name of the student whose information is to be displayed.
 */
public class FindStudentMenu 
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
	
	// instance fields
	private String[] studentNameList;
	private ArrayList<Student> studentList;
	private MainMenu menu;
	
	private GridBagConstraints constraints;
	
	private JFrame frame;
	private Container mainContentPane;

	private JLabel titleLabel;

	private JPanel findContentPane;
	private JLabel studentLabel;
	private JTextField studentField;
	private JButton findStudentButton;
	
	/**
	 * Constructs a new FindStudentMenu with default values.
	 */
	public FindStudentMenu() 
	{
		studentNameList = new String[0];
		studentList = new ArrayList<Student>();
		menu = new MainMenu();
		paintFrame();
	}
	
	/**
	 * Constructs a new FindStudentMenu with specified values.
	 * 
	 * @param nameList the list of student names
	 * @param list the list of students
	 * @param menu the menu that created this FindStudentMenu
	 */
	public FindStudentMenu(String[] nameList, ArrayList<Student> list, MainMenu menu) 
	{
		studentNameList = nameList;
		studentList = list;
		this.menu = menu;
		paintFrame();
	}
	
	/**
	 * Paints the frame of this FindStudentMenu.
	 */
	public void paintFrame() 
	{
		frame = new JFrame("Find Student Information");
		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		constraints = new GridBagConstraints();
		
		titleLabel = new JLabel("Find Student Information");
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		mainContentPane.add(titleLabel, BorderLayout.NORTH);

		findContentPane = new JPanel();
		findContentPane.setLayout(new GridBagLayout());

		studentLabel = new JLabel("Name (Last First): ");
		constraints.insets = new Insets(5, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		findContentPane.add(studentLabel, constraints);
		
		studentField = new JTextField();
		studentField = new JTextField();
		studentField.setPreferredSize(new Dimension(50, 25));
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		findContentPane.add(studentField, constraints);

		findStudentButton = new JButton("Find Student");
		findStudentButton.addActionListener(new FindStudent());
		constraints.gridwidth = 2;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		findContentPane.add(findStudentButton, constraints);

		mainContentPane.add(findContentPane, BorderLayout.CENTER);

		frame.setContentPane(mainContentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/* private classes */
	
	private class FindStudent implements ActionListener 
	{
		
		/**
		 * Finds the specified student and displays its information.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			if (!Arrays.asList(studentNameList).contains(studentField.getText())) 
			{
				studentField.setText("");
				JOptionPane.showMessageDialog(frame, "Student does not exist.");
			} 
			else 
			{
				int index = Arrays.asList(studentNameList).indexOf(studentField.getText());
				studentField.setText("");
				StudentInfoMenu studentInfoMenu = new StudentInfoMenu(studentList, menu, (index + 1));
			}
		}
	}
}
