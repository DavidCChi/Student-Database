import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.stream.IntStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 * The main frame of this program.
 */
public class MainMenu 
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
	/**
	 * Max capacity of a class.
	 */
	public static final int MAX_CAPACITY = 50;

	// instance fields
	private FileReader studentFileReader;
	private FileWriter studentFileWriter;
	private File studentFile;
	private Properties studentProperties;
	private ArrayList<Student> studentList;
	private String[] orderedStudentNames;

	private FileReader attendanceFileReader;
	private FileWriter attendanceFileWriter;
	private FileReader presentStudentFileReader;
	private FileWriter presentStudentFileWriter;
	private File attendanceFile;
	private File presentStudentFile;
	private Properties attendanceProperties;
	private ArrayList<Attendance> attendanceList;
	private Properties presentStudentProperties;
	private ArrayList<Queue[]> presentStudentList;

	private GridBagConstraints constraints;

	private JFrame frame;
	private Container mainContentPane;
	private JTabbedPane tabbedContentPane;
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem findStudentItem;
	private JMenuItem checkClassAvailabilityItem;
	private JMenuItem editAccountItem;

	private JLabel welcomeLabel;

	private JPanel studentPane;
	private JButton addStudentButton;
	private JButton deleteAllStudentsButton;

	private JPanel studentListPane;
	private JScrollPane studentListScrollPane;

	private JPanel attendancePane;
	private JButton addAttendanceButton;
	private JButton deleteAllAttendancesButton;

	private JPanel attendanceListPane;
	private JScrollPane attendanceListScrollPane;

	/**
	 * Constructs a new MainMenu with default values.
	 */
	public MainMenu() 
	{
		paintFrame();
		retrieveStudentList();
		retrieveAttendanceList();
	}

	/**
	 * Paints the frame of this MainMenu.
	 */
	private void paintFrame() 
	{
		frame = new JFrame("Main Menu");
		constraints = new GridBagConstraints();
		
		menuBar = new JMenuBar();
		
		menu = new JMenu("Options");
		menuBar.add(menu);
		
		findStudentItem = new JMenuItem("Find a Specific Student");
		findStudentItem.addActionListener(new FindStudent());
		menu.add(findStudentItem);
		
		checkClassAvailabilityItem = new JMenuItem("Check Class Availability");
		checkClassAvailabilityItem.addActionListener(new CheckClassAvailability());
		menu.add(checkClassAvailabilityItem);
		
		editAccountItem = new JMenuItem("Edit Credentials");
		editAccountItem.addActionListener(new EditAccount());
		menu.add(editAccountItem);

		welcomeLabel = new JLabel("Main Menu");
		welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 15));

		// student pane tab
		studentPane = new JPanel();
		studentPane.setLayout(new GridBagLayout());

		studentListPane = new JPanel();
		studentListPane.setLayout(new BoxLayout(studentListPane, BoxLayout.Y_AXIS));

		studentListScrollPane = new JScrollPane(studentListPane);
		studentListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		studentListScrollPane.setPreferredSize(new Dimension(500, 500));
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		studentPane.add(studentListScrollPane, constraints);

		addStudentButton = new JButton("Add a Student");
		addStudentButton.addActionListener(new AddStudent());
		constraints.insets = new Insets(5, 0, 0, 200);
		constraints.weightx = WEIGHTX;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		studentPane.add(addStudentButton, constraints);

		deleteAllStudentsButton = new JButton("Delete All Students");
		deleteAllStudentsButton.addActionListener(new DeleteAllStudents());
		constraints.insets = new Insets(5, 200, 0, 0);
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		studentPane.add(deleteAllStudentsButton, constraints);
		
		constraints.insets = new Insets(0, 0, 0, 0);

		// attendance pane tab
		attendancePane = new JPanel();
		attendancePane.setLayout(new GridBagLayout());

		attendanceListPane = new JPanel();
		attendanceListPane.setLayout(new BoxLayout(attendanceListPane, BoxLayout.Y_AXIS));

		attendanceListScrollPane = new JScrollPane(attendanceListPane);
		attendanceListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		attendanceListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		attendanceListScrollPane.setPreferredSize(new Dimension(500, 500));
		constraints.weightx = WEIGHTX_NONE;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		attendancePane.add(attendanceListScrollPane, constraints);

		addAttendanceButton = new JButton("Add an Attendance");
		addAttendanceButton.addActionListener(new AddAttendance());
		constraints.insets = new Insets(5, 0, 0, 200);
		constraints.weightx = WEIGHTX;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		attendancePane.add(addAttendanceButton, constraints);

		deleteAllAttendancesButton = new JButton("Delete All Attendances");
		deleteAllAttendancesButton.addActionListener(new DeleteAllAttendances());
		constraints.insets = new Insets(5, 200, 0, 0);
		constraints.weightx = WEIGHTX;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = SECOND_ROW;
		attendancePane.add(deleteAllAttendancesButton, constraints);
		
		constraints.insets = new Insets(0, 0, 0, 0);

		// main frame section
		tabbedContentPane = new JTabbedPane();
		tabbedContentPane.addTab("Students", studentPane);
		tabbedContentPane.addTab("Attendance", attendancePane);

		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		mainContentPane.add(welcomeLabel, BorderLayout.NORTH);
		mainContentPane.add(tabbedContentPane, BorderLayout.CENTER);

		frame.setJMenuBar(menuBar);
		frame.setContentPane(mainContentPane);
		frame.addWindowListener(new StoreInformation());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// accessors
	
	/**
	 * Returns the frame of this MainMenu.
	 * 
	 * @return the frame of this MainMenu
	 */
	public JFrame getFrame() 
	{
		return frame;
	}
	
	/**
	 * Returns the student list of this MainMenu.
	 * 
	 * @return the student list
	 */
	public ArrayList<Student> getStudentList() 
	{
		return studentList;
	}

	/* student section */

	/**
	 * Returns whether or not there were any stored students in the saved student file.
	 * 
	 * @return <code>true</code> if there are stored students, otherwise <code>false<code>
	 */
	public boolean getStudentFile() 
	{
		try 
		{
			String file = "student.properties";
			studentFile = new File("resources" + File.separator + file);
			if (!studentFile.exists()) studentFile.createNewFile();
			studentFileReader = new FileReader(studentFile);
			studentProperties = new Properties();
			studentProperties.load(studentFileReader);
			studentList = new ArrayList<Student>();
			if (studentProperties.isEmpty()) return false;
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			studentProperties = new Properties();
			studentList = new ArrayList<Student>();
			return false;
		} 
		catch (IOException e1) 
		{
			studentProperties = new Properties();
			studentList = new ArrayList<Student>();
			return false;
		}
	}
	
	/**
	 * Initiates the file writer for the saved student file and returns whether or not
	 * the operation was successful
	 * 
	 * @return <code>true</code> if the file writer was initiated successfully, otherwise <code>false<code>
	 */
	public boolean initiateStudentWriter() 
	{
		if (!studentFile.exists()) 
		{
			try 
			{
				if (!studentFile.createNewFile()) 
				{
					return false;
				}
			} 
			catch (IOException e) 
			{
				return false;
			}
		}
		try 
		{
			studentFileWriter = new FileWriter(studentFile);
		} 
		catch (IOException e) 
		{
			return false;
		}
		return true;
	}

	/**
	 * Adds students to the student list pane component.
	 */
	public void retrieveStudentList() 
	{
		if (!getStudentFile()) 
		{
			studentsNotListed();
			return;
		}

		Enumeration<Object> enumeration = studentProperties.keys();
		String str = "";
		String[] studentInfo = null;
		JLabel lbl = null;
		while (enumeration.hasMoreElements()) 
		{
			str = (String) enumeration.nextElement();
			studentInfo = studentProperties.getProperty(str).split("~", 13);

			studentList.add(new Student(studentInfo[0], Integer.parseInt(studentInfo[1]), studentInfo[2],
					studentInfo[3], Long.parseLong(studentInfo[4]), Integer.parseInt(studentInfo[5]), studentInfo[6],
					Boolean.parseBoolean(studentInfo[7]), studentInfo[8], studentInfo[9], studentInfo[10],
					Long.parseLong(studentInfo[11]), studentInfo[12]));
		}

		orderStudents();

		for (int i = 0; i < studentList.size(); i++) 
		{
			lbl = new JLabel(("" + (i + 1) + ". " + studentList.get(i).getName()));
			lbl.addMouseListener(new DisplayStudentInfo());
			lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

			studentListPane.add(lbl);
		}
	}

	/**
	 * Sets "No students." on the student list pane component when the file is empty or missing.
	 */
	public void studentsNotListed() 
	{
		JLabel studentField = new JLabel("No students.");
		// prevents the JTextField from expanding
		studentField.setMaximumSize(studentField.getPreferredSize());
		studentField.setAlignmentX(Component.LEFT_ALIGNMENT);
		studentListPane.add(studentField);

		deleteAllStudentsButton.setEnabled(false);

		frame.validate();
		frame.repaint();
	}

	/**
	 * Orders students in alphabetical order.
	 */
	public void orderStudents() 
	{
		if (studentList.isEmpty()) return;

		orderedStudentNames = new String[studentList.size()];

		for (int i = 0; i < studentList.size(); i++) 
		{
			orderedStudentNames[i] = studentList.get(i).getName();
		}

		Arrays.sort(orderedStudentNames, String.CASE_INSENSITIVE_ORDER);
		studentList.clear();

		String[] studentInfo = null;

		for (int i = 0; i < orderedStudentNames.length; i++) 
		{
			studentInfo = studentProperties.getProperty(orderedStudentNames[i]).split("~", 13);

			studentList.add(new Student(studentInfo[0], Integer.parseInt(studentInfo[1]), studentInfo[2],
					studentInfo[3], Long.parseLong(studentInfo[4]), Integer.parseInt(studentInfo[5]), studentInfo[6],
					Boolean.parseBoolean(studentInfo[7]), studentInfo[8], studentInfo[9], studentInfo[10],
					Long.parseLong(studentInfo[11]), studentInfo[12]));
		}
	}

	/**
	 * Updates the students listed in student list pane component.
	 */
	public void updateStudentList() 
	{
		studentListPane.removeAll();

		JLabel lbl = null;

		for (int i = 0; i < studentList.size(); i++) 
		{
			lbl = new JLabel(("" + (i + 1) + ". " + studentList.get(i).getName()));
			lbl.addMouseListener(new DisplayStudentInfo());
			lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
			studentListPane.add(lbl);
		}

		if (studentList.isEmpty()) 
		{
			deleteAllStudentsButton.setEnabled(false);
			studentsNotListed();
		} 
		else 
		{
			deleteAllStudentsButton.setEnabled(true);
		}

		frame.validate();
		frame.repaint();
	}

	/**
	 * Updates the Properties that store the list of students.
	 */
	public void updateStudentProperties() 
	{
		if (studentProperties != null) 
		{
			studentProperties.clear();
		}
		else 
		{
			studentProperties = new Properties();
		}
		Student student = null;
		int duplicateCounter = 2;

		for (int i = 0; i < studentList.size(); i++) 
		{
			student = studentList.get(i);
			if (studentProperties.getProperty(student.getName()) == null) 
			{
				studentProperties.setProperty(student.getName(), student.toString());
				duplicateCounter = 2; // reset duplicate counter
			} 
			else 
			{
				student.setName(student.getName() + " (" + duplicateCounter + ")");
				studentProperties.setProperty(student.getName(), student.toString());
				duplicateCounter++; // since it's alpha order, the same names
									// will appear in succession
			}
		}
	}

	/**
	 * Stores the Properties of students in a file.
	 */
	public void storeStudentList() 
	{
		updateStudentProperties();
		if (initiateStudentWriter()) 
		{
			try 
			{
				studentProperties.store(studentFileWriter, "" + new Timestamp(new Date().getTime()));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (studentFileWriter != null) 
					{
						studentFileWriter.close();
					}
				}
				catch (IOException e1) {}
			}
		}
	}

	/* attendance section */

	/**
	 * Returns whether or not there were any stored attendances in the saved attendance file.
	 * 
	 * @return <code>true</code> if there are stored attendances, otherwise <code>false<code>
	 */
	public boolean getAttendanceFile() 
	{
		try 
		{
			String file = "attendance.properties";
			String file2 = "presentStudent.properties";
			attendanceFile = new File("resources" + File.separator + file);
			presentStudentFile = new File("resources" + File.separator + file2);
			if (!attendanceFile.exists())
			{
				attendanceFile.createNewFile();
				presentStudentFile.createNewFile();
			}
			attendanceFileReader = new FileReader(attendanceFile);
			presentStudentFileReader = new FileReader(presentStudentFile);
			attendanceProperties = new Properties();
			presentStudentProperties = new Properties();
			attendanceProperties.load(attendanceFileReader);
			presentStudentProperties.load(presentStudentFileReader);
			attendanceList = new ArrayList<Attendance>();
			presentStudentList = new ArrayList<Queue[]>();
			if (attendanceProperties.isEmpty()) return false;
			return true;
		} 
		catch (FileNotFoundException e) 
		{
			attendanceProperties = new Properties();
			presentStudentProperties = new Properties();
			attendanceList = new ArrayList<Attendance>();
			presentStudentList = new ArrayList<Queue[]>();
			return false;
		} 
		catch (IOException e1) 
		{
			attendanceProperties = new Properties();
			presentStudentProperties = new Properties();
			attendanceList = new ArrayList<Attendance>();
			presentStudentList = new ArrayList<Queue[]>();
			return false;
		}
	}

	/**
	 * Initiates the file writer for the saved attendance file and returns whether or not
	 * the operation was successful
	 * 
	 * @return <code>true</code> if the file writer was initiated successfully, otherwise <code>false<code>
	 */
	public boolean initiateAttendanceWriter() 
	{
		if (!attendanceFile.exists()) 
		{
			try 
			{
				if (!attendanceFile.createNewFile()) 
				{
					return false;
				}
			}
			catch (IOException e) 
			{
				return false;
			}
		}
		try
		{
			attendanceFileWriter = new FileWriter(attendanceFile);
			presentStudentFileWriter = new FileWriter(presentStudentFile);
		} 
		catch (IOException e) 
		{
			return false;
		}
		return true;
	}

	/**
	 * Adds attendances to the attendance list pane component.
	 */
	public void retrieveAttendanceList() 
	{
		if (!getAttendanceFile()) 
		{
			attendancesNotListed();
			return;
		}

		Enumeration<Object> enumeration = attendanceProperties.keys();
		String str = "";
		String[] attendanceInfo = null;
		JLabel lbl = null;
		int[] morningStudentCount = null;
		int[] afternoonStudentCount = null;
		int sum = 0;
		while (enumeration.hasMoreElements()) 
		{
			str = (String) enumeration.nextElement();
			attendanceInfo = attendanceProperties.getProperty(str).split("~", 17);
			morningStudentCount = new int[8];
			afternoonStudentCount = new int[8];

			for (int i = 0; i < morningStudentCount.length; i++) 
			{
				morningStudentCount[i] = Integer.parseInt(attendanceInfo[i]);
				afternoonStudentCount[i] = Integer.parseInt(attendanceInfo[i + 8]);
			}
			sum = IntStream.of(morningStudentCount).sum() + IntStream.of(afternoonStudentCount).sum();
			
			attendanceList.add(new Attendance(attendanceInfo[16], morningStudentCount, afternoonStudentCount, sum));
		}

		orderDates();

		for (int i = 0; i < attendanceList.size(); i++) 
		{
			lbl = new JLabel(attendanceList.get(i).getDate() + ", Total Student Count: " + 
					attendanceList.get(i).getTotal());
			lbl.addMouseListener(new DisplayAttendanceInfo());
			lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
			attendanceListPane.add(lbl);
		}
		
		String[] classes = null;
		String[] classStudentList = null;
		Queue[] queueList = null;
		Queue queue = null;
		for (int i = 0; i < attendanceList.size(); i++) 
		{
			queueList = new Queue[16];
			classes = presentStudentProperties.getProperty(attendanceList.get(i).getDate()).split("~", 16);
			for (int j = 0; j < classes.length; j++)
			{
				queue = new Queue(MAX_CAPACITY);
				classStudentList = classes[j].split(",");
				for (int k = 0; k < classStudentList.length; k++)
				{
					queue.enqueue(classStudentList[k]);
				}
				queueList[j] = queue;
			}
			presentStudentList.add(queueList);
		}
	}

	/**
	 * Sets "No attendances." on the attendance list pane component when the file is empty or missing.
	 */
	public void attendancesNotListed() 
	{
		JLabel attendanceField = new JLabel("No attendances.");
		attendanceField.setMaximumSize(attendanceField.getPreferredSize());
		attendanceField.setAlignmentX(Component.LEFT_ALIGNMENT);
		attendanceListPane.add(attendanceField);

		deleteAllAttendancesButton.setEnabled(false);

		frame.validate();
		frame.repaint();
	}

	/**
	 * Orders the dates of the attendances in order.
	 */
	public void orderDates() 
	{
		if (attendanceList.isEmpty())
			return;

		String[] orderedAttendanceDates = new String[attendanceList.size()];

		for (int i = 0; i < attendanceList.size(); i++) 
		{
			orderedAttendanceDates[i] = attendanceList.get(i).getDate();
		}

		Arrays.sort(orderedAttendanceDates, new DateComparator());
		attendanceList.clear();

		String[] attendanceInfo = null;
		int[] morningStudentCount = null;
		int[] afternoonStudentCount = null;
		int sum = 0;

		for (int i = 0; i < orderedAttendanceDates.length; i++) 
		{
			morningStudentCount = new int[8];
			afternoonStudentCount = new int[8];
			attendanceInfo = attendanceProperties.getProperty(orderedAttendanceDates[i]).split("~", 17);

			for (int j = 0; j < morningStudentCount.length; j++) 
			{
				morningStudentCount[j] = Integer.parseInt(attendanceInfo[j]);
				afternoonStudentCount[j] = Integer.parseInt(attendanceInfo[j + 8]);
			}
			sum = IntStream.of(morningStudentCount).sum() + IntStream.of(afternoonStudentCount).sum();
			
			attendanceList.add(new Attendance(attendanceInfo[16], morningStudentCount, afternoonStudentCount, sum));
		}
	}

	/**
	 * Updates the attendances displayed in the attendance list pane component.
	 */
	public void updateAttendanceList() 
	{
		attendanceListPane.removeAll();

		JLabel lbl = null;

		for (int i = 0; i < attendanceList.size(); i++) 
		{
			lbl = new JLabel(attendanceList.get(i).getDate() + ", Total Student Count: " + 
					attendanceList.get(i).getTotal());
			lbl.addMouseListener(new DisplayAttendanceInfo());
			lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
			attendanceListPane.add(lbl);
		}

		if (attendanceList.isEmpty()) 
		{
			deleteAllAttendancesButton.setEnabled(false);
			attendancesNotListed();
		} 
		else 
		{
			deleteAllAttendancesButton.setEnabled(true);
		}

		frame.validate();
		frame.repaint();
	}

	/**
	 * Updates the Properties that stores the list of attendances.
	 */
	public void updateAttendanceProperties() 
	{
		if (attendanceProperties != null) 
		{
			attendanceProperties.clear();
			presentStudentProperties.clear();
		} 
		else
		{
			attendanceProperties = new Properties();
			presentStudentProperties = new Properties();
		}

		Attendance attendance = null;
		Queue[] queueList = null;
		String str = "";
		for (int i = 0; i < attendanceList.size(); i++) 
		{
			attendance = attendanceList.get(i);
			attendanceProperties.setProperty(attendance.getDate(), attendance.toString());
			// only runs if the method is called from storeAttendanceList() method.
			if (presentStudentList.size() == attendanceList.size())
			{
				str = "";
				queueList = presentStudentList.get(i);
				str = "" + queueList[0].toString();
				for (int j = 1; j < queueList.length; j++)
				{
					str = str + "~" + queueList[j].toString();
				}
				presentStudentProperties.setProperty(attendance.getDate(), str);
			}
		}
	}

	/**
	 * Stores the Properties of attendances in a file.
	 */
	public void storeAttendanceList() 
	{
		updateAttendanceProperties();
		if (initiateAttendanceWriter()) 
		{
			try 
			{
				attendanceProperties.store(attendanceFileWriter, "" + new Timestamp(new Date().getTime()));
				presentStudentProperties.store(presentStudentFileWriter, "" + new Timestamp(new Date().getTime()));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			} 
			finally 
			{
				try 
				{
					if (attendanceFileWriter != null) 
					{
						attendanceFileWriter.close();
						presentStudentFileWriter.close();
					}
				} 
				catch (IOException e1) {}
			}
		}
	}

	/* private classes */

	private class DisplayStudentInfo implements MouseListener 
	{

		public void mouseClicked(MouseEvent arg0) {}

		public void mouseEntered(MouseEvent arg0) {}

		public void mouseExited(MouseEvent arg0) {}

		/**
		 * Displays the selected student's information.
		 */
		public void mousePressed(MouseEvent arg0) 
		{
			JLabel clicked = (JLabel) arg0.getSource();
			String[] info = clicked.getText().split(". ", 2);
			StudentInfoMenu studentInfoMenu = new StudentInfoMenu(studentList, MainMenu.this,
					Integer.parseInt(info[0]));
		}

		public void mouseReleased(MouseEvent arg0) {}
	}

	private class AddStudent implements ActionListener 
	{

		/**
		 * Creates a frame to add a student.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			if (studentList == null) studentList = new ArrayList<Student>();
			AddStudentMenu addStudentMenu = new AddStudentMenu(studentList, MainMenu.this);
		}
	}

	private class DeleteAllStudents implements ActionListener 
	{
		
		/**
		 * Deletes all the stored students.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			int reply = JOptionPane.showConfirmDialog(frame,
					"Are you sure you want to\n" + "delete all the stored students?", "Warning",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) 
			{
				studentList.clear();
				updateStudentProperties();
				updateStudentList();
				frame.validate();
				frame.repaint();
				JOptionPane.showMessageDialog(frame, "All students were successfully deleted");
			}
		}
	}

	private class DateComparator implements Comparator<String> 
	{
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		/**
		 * Compares two given dates.
		 */
		public int compare(String arg0, String arg1) {
			try {
				return date.parse(arg0).compareTo(date.parse(arg1));
			} catch (ParseException e) {
				return -1;
			}
		}
	}

	private class DisplayAttendanceInfo implements MouseListener 
	{

		public void mouseClicked(MouseEvent arg0) {}

		public void mouseEntered(MouseEvent arg0) {}

		public void mouseExited(MouseEvent arg0) {}

		/**
		 * Displays the selected attendance's information.
		 */
		public void mousePressed(MouseEvent arg0) 
		{
			JLabel clicked = (JLabel) arg0.getSource();
			String[] str = clicked.getText().split(",", 2);
			AttendanceInfoMenu attendanceInfoMenu = new AttendanceInfoMenu(presentStudentList, attendanceList, 
					MainMenu.this, str[0]);
		}

		public void mouseReleased(MouseEvent arg0) {}
	}

	private class AddAttendance implements ActionListener 
	{
		
		/**
		 * Creates a frame to add an attendance.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			AddAttendanceMenu addAttendanceMenu = new AddAttendanceMenu(presentStudentList, attendanceList, 
					MainMenu.this);
		}
	}

	private class DeleteAllAttendances implements ActionListener 
	{
		
		/**
		 * Deletes all the stored attendances.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			int reply = JOptionPane.showConfirmDialog(frame,
					"Are you sure you want to\n" + "delete all the stored attendances?", "Warning",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) 
			{
				attendanceList.clear();
				presentStudentList.clear();
				updateAttendanceProperties();
				updateAttendanceList();
				frame.validate();
				frame.repaint();
				JOptionPane.showMessageDialog(frame, "All attendances were successfully deleted");
			}
		}
	}

	private class StoreInformation implements WindowListener 
	{

		public void windowActivated(WindowEvent arg0) {}

		public void windowClosed(WindowEvent arg0) {}

		/**
		 * Stores the students and attendances before this MainMenu is closed.
		 */
		public void windowClosing(WindowEvent arg0) {
			storeStudentList();
			storeAttendanceList();}

		public void windowDeactivated(WindowEvent arg0) {}

		public void windowDeiconified(WindowEvent arg0) {}

		public void windowIconified(WindowEvent arg0) {}

		public void windowOpened(WindowEvent arg0) {}
	}
	
	private class EditAccount implements ActionListener
	{
		
		/**
		 * Creates a frame to edit the user account information.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			EditAccountMenu menu = new EditAccountMenu();
		}
	}
	
	private class FindStudent implements ActionListener 
	{
		
		/**
		 * Creates a frame to find a specific student.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			FindStudentMenu menu = new FindStudentMenu(orderedStudentNames, studentList, MainMenu.this);
		}
	}
	
	private class CheckClassAvailability implements ActionListener
	{
		/**
		 * Creates a frame to find a specific student.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			CheckClassAvailabilityMenu menu = new CheckClassAvailabilityMenu(studentList);
		}
	}
}
