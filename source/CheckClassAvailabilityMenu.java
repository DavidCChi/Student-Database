import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A frame that checks the availability of a class for registration.
 */
public class CheckClassAvailabilityMenu 
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

	/**
	 * Max capacity of a class.
	 */
	public static final int MAX_CAPACITY = 50;

	// instance fields
	private File studentFile;
	private FileReader studentFileReader;
	private Properties studentProperties;
	private ArrayList<Student> studentList;

	private GridBagConstraints constraints;

	private JFrame frame;
	private Container mainContentPane;

	private JLabel titleLabel;

	private JPanel checkContentPane;
	private JLabel gradeLabel;
	private JComboBox<String> gradeBox;
	private JLabel isMorningClassLabel;
	private JComboBox<String> isMorningClassBox;
	private JButton checkGradeButton;
	
	private boolean accessedWithinMainMenu;

	/**
	 * Constructs a new CheckClassAvailabilityMenu with default values.
	 */
	public CheckClassAvailabilityMenu() 
	{
		accessedWithinMainMenu = false;
		getStudentList();
		paintFrame();
	}
	
	/**
	 * Constructs a new CheckClassAvailabilityMenu with specified values.
	 * 
	 * @param studentList the list of students.
	 */
	public CheckClassAvailabilityMenu(ArrayList<Student> studentList)
	{
		this.studentList = studentList;
		accessedWithinMainMenu = true;
		paintFrame();
	}

	/**
	 * Paints the frame of this CheckClassAvailabilityMenu.
	 */
	public void paintFrame() 
	{
		if (accessedWithinMainMenu)
		{
			frame = new JFrame("Check Class Student Count");
		}
		else
		{
			frame = new JFrame("Check Class Availability");
		}
		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		constraints = new GridBagConstraints();
		
		if (accessedWithinMainMenu)
		{
			titleLabel = new JLabel("Check Class Student Count");
		}
		else
		{
			titleLabel = new JLabel("Check Class Availability");
		}
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 25));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		mainContentPane.add(titleLabel, BorderLayout.NORTH);

		checkContentPane = new JPanel();
		checkContentPane.setLayout(new GridBagLayout());

		gradeLabel = new JLabel("Grade: ");
		constraints.insets = new Insets(5, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		checkContentPane.add(gradeLabel, constraints);

		String[] possibleValues = { "" + 1, "" + 2, "" + 3, "" + 4, "" + 5, "" + 6, "" + 7, "" + 8 };
		gradeBox = new JComboBox<String>(possibleValues);
		gradeBox.setSelectedIndex(0);
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		checkContentPane.add(gradeBox, constraints);
		
		isMorningClassLabel = new JLabel("Class: ");
		constraints.insets = new Insets(5, 0, 0, 0);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		checkContentPane.add(isMorningClassLabel, constraints);
		
		String[] possibleClasses = {"Morning", "Afternoon"};
		isMorningClassBox = new JComboBox<String>(possibleClasses);
		isMorningClassBox.setSelectedIndex(0);
		constraints.weightx = WEIGHTX;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = SECOND_ROW;
		checkContentPane.add(isMorningClassBox, constraints);
		
		checkGradeButton = new JButton("Check Grade");
		checkGradeButton.addActionListener(new CheckGrade());
		constraints.gridwidth = 2;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = THIRD_ROW;
		checkContentPane.add(checkGradeButton, constraints);

		mainContentPane.add(checkContentPane, BorderLayout.CENTER);

		frame.setContentPane(mainContentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	/**
	 * Gets the student list from the saved file.
	 */
	public void getStudentList() 
	{
		String file = "student.properties";
		studentFile = new File("resources" + File.separator + file);
		if (!studentFile.exists()) 
		{
			try 
			{
				studentFile.createNewFile();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		studentProperties = new Properties();
		try 
		{
			studentFileReader = new FileReader(studentFile);
			studentProperties.load(studentFileReader);
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		studentList = new ArrayList<Student>();

		Enumeration<Object> enumeration = studentProperties.keys();
		String str = "";
		String[] studentInfo = null;
		while (enumeration.hasMoreElements()) 
		{
			str = (String) enumeration.nextElement();
			studentInfo = studentProperties.getProperty(str).split("~", 13);

			studentList.add(new Student(studentInfo[0], Integer.parseInt(studentInfo[1]), studentInfo[2],
					studentInfo[3], Long.parseLong(studentInfo[4]), Integer.parseInt(studentInfo[5]), studentInfo[6],
					Boolean.parseBoolean(studentInfo[7]), studentInfo[8], studentInfo[9], studentInfo[10],
					Long.parseLong(studentInfo[11]), studentInfo[12]));
		}
	}
	
	// checks availability in grade above specified grade
	private boolean checkUpperGrade(int grade) 
	{
		if ((grade + 1) > 8) return false;
		int counter = 0;
		for (int i = 0; i < studentList.size(); i++) 
		{
			if (studentList.get(i).getGrade() == (grade + 1) && studentList.get(i).isMorningClass()) counter++;
			if (counter == MAX_CAPACITY) 
			{
				return false;
			}
		}
		return true;
	}
	
	// checks availability in grade below specified grade
	private boolean checkLowerGrade(int grade) 
	{
		if ((grade - 1) < 1) return false;
		int counter = 0;
		for (int i = 0; i < studentList.size(); i++) 
		{
			if (studentList.get(i).getGrade() == (grade - 1) && studentList.get(i).isMorningClass()) counter++;
			if (counter == MAX_CAPACITY) 
			{
				return false;
			}
		}
		return true;
	}

	/* private class */

	private class CheckGrade implements ActionListener 
	{
		
		/**
		 * Checks the specified grade for availability for registration.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			boolean isMorningClass = false;
			int grade = Integer.parseInt((String) gradeBox.getSelectedItem());
			if (isMorningClassBox.getSelectedIndex() == 0) isMorningClass = true;
			int counter = 0;

			for (int i = 0; i < studentList.size(); i++) 
			{
				if (studentList.get(i).getGrade() == grade 
						&& studentList.get(i).isMorningClass() == isMorningClass) counter++;
				if (counter == MAX_CAPACITY) break;
			}
			
			if (counter < MAX_CAPACITY && accessedWithinMainMenu)
			{
				JOptionPane.showMessageDialog(frame, "There are " + counter + " students in this class.");
			}
			else if (accessedWithinMainMenu)
			{
				JOptionPane.showMessageDialog(frame, "This class has reached the max capacity (" + 
						MAX_CAPACITY + ") of students.");
			}

			if (counter < MAX_CAPACITY && !accessedWithinMainMenu) 
			{
				JOptionPane.showMessageDialog(frame, "This grade is currently available for registration.\n"
						+ "There are " + (MAX_CAPACITY - counter) + " spots left.");
			}
			else if (!accessedWithinMainMenu)
			{
				if (checkUpperGrade(grade) && checkLowerGrade(grade)) 
				{
					JOptionPane.showMessageDialog(frame, "This grade is unavailable for registration.\n"
							+ "Please register for either grade " + (grade - 1) + " or " + (grade + 1) + ".");
				} 
				else if (checkUpperGrade(grade)) 
				{
					JOptionPane.showMessageDialog(frame, "This grade is unavailable for registration.\n"
							+ "Please register for grade " + (grade + 1) + ".");
				} 
				else if (checkLowerGrade(grade)) 
				{
					JOptionPane.showMessageDialog(frame, "This grade is unavailable for registration.\n"
							+ "Please register for grade " + (grade - 1) + ".");
				} 
				else 
				{
					JOptionPane.showMessageDialog(frame, "This grade is unavailable for registration.\n");
				}
			}
		}
	}
}
