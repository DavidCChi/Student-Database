import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * A frame for displaying and editing the list of absent students of each grade.
 */
public class AbsentStudentMenu 
{
	// class fields
	/**
	 * Max capacity of a class.
	 */
	public static final int MAX_CLASS_CAPACITY = 50;
	/**
	 * Max amount of classes in the school.
	 */
	public static final int MAX_AMOUNT_OF_CLASS = 16;
	
	// instance fields
	private ArrayList<Queue[]> presentStudentList;
	private Queue[] attendancePresentStudents;
	private Queue classPresentStudents;
	private ArrayList<Student> studentList;
	
	private int attendanceIndex;
	private int queueIndex;
	private int grade;
	private boolean isMorningClass;
	private boolean enableEditing;
	
	private JButton button;
	
	private JFrame frame;
	private Container mainContentPane;
	
	private JLabel titleLabel;
	
	private JPanel studentListPane;
	private JScrollPane studentListScrollPane;
	
	private JCheckBox[] studentListCheckBox;
	
	private JButton doneButton;
	
	/**
	 * Constructs a new AbsentStudentMenu with default values.
	 */
	public AbsentStudentMenu() 
	{
		presentStudentList = new ArrayList<Queue[]>();
		attendancePresentStudents = new Queue[MAX_AMOUNT_OF_CLASS];
		classPresentStudents = new Queue(MAX_CLASS_CAPACITY);
		studentList = new ArrayList<Student>();
		attendanceIndex = 0;
		grade = 0;
		isMorningClass = false;
		button = new JButton();
		enableEditing = false;
		paintFrame();
	}
	
	/**
	 * Constructs a new AbsentStudentMenu with specified values.
	 * 
	 * @param presentStudentList the list of present students
	 * @param studentList the list of students
	 * @param attendanceIndex the index of the attendance in the list of attendances
	 * @param grade the grade of students to be listed
	 * @param isMorningClass the time of day of the class of students
	 * @param button the button that constructed this AbsentStudentMenu object
	 * @param enableEditing whether or not the user can edit the list of present students
	 */
	public AbsentStudentMenu(ArrayList<Queue[]> presentStudentList, ArrayList<Student> studentList, 
			int attendanceIndex, int grade, boolean isMorningClass, JButton button, boolean enableEditing) 
	{
		this.presentStudentList = presentStudentList;
		this.studentList = studentList;
		this.attendanceIndex = attendanceIndex;
		this.grade = grade;
		this.isMorningClass = isMorningClass;
		this.button = button;
		this.enableEditing = enableEditing;
		paintFrame();
	}
	
	/**
	 * Paints the frame of this AbsentStudentMenu.
	 */
	public void paintFrame() 
	{
		frame = new JFrame("Absent Students");
		
		titleLabel = new JLabel("Absent Students");
		titleLabel.setFont(new Font("Helvetica", Font.BOLD, 15));
		
		studentListPane = new JPanel();
		studentListPane.setLayout(new BoxLayout(studentListPane, BoxLayout.Y_AXIS));
		
		displayPresentStudents();
		
		studentListScrollPane = new JScrollPane(studentListPane);
		studentListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		studentListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		studentListScrollPane.setPreferredSize(new Dimension(300, 300));
		
		doneButton = new JButton("Done");
		doneButton.addActionListener(new UpdatePresentStudentList());
		
		mainContentPane = frame.getContentPane();
		mainContentPane.setLayout(new BorderLayout());
		mainContentPane.add(titleLabel, BorderLayout.NORTH);
		mainContentPane.add(studentListScrollPane, BorderLayout.CENTER);
		mainContentPane.add(doneButton, BorderLayout.SOUTH);
		
		frame.setContentPane(mainContentPane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.addWindowListener(new UpdatePresentStudentList());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	/**
	 * Displays the message that no students are enrolled in this class.
	 */
	public void noStudents()
	{
		JLabel lbl = new JLabel("No students are enrolled in this class.");
		studentListPane.add(lbl);
		
		frame.validate();
		frame.repaint();
	}
	
	/**
	 * Displays the list of present students.
	 */
	public void displayPresentStudents() 
	{
		int counter = 0;
		queueIndex = 0;
		if (isMorningClass) 
		{
			queueIndex = grade - 1;
		}
		else
		{
			queueIndex = (grade - 1) + 8;
		}
		
		attendancePresentStudents = presentStudentList.get(attendanceIndex);
		classPresentStudents = attendancePresentStudents[queueIndex];
		studentListCheckBox = new JCheckBox[MAX_CLASS_CAPACITY];
		
		for (int i = 0; i < studentList.size(); i++) 
		{
			if (studentList.get(i).getGrade() == grade && studentList.get(i).isMorningClass() == isMorningClass) 
			{
				studentListCheckBox[counter] = new JCheckBox(studentList.get(i).getName());
				studentListCheckBox[counter].setAlignmentX(Component.LEFT_ALIGNMENT);
				studentListCheckBox[counter].setEnabled(enableEditing);
				if (studentList.get(i).getName().equals(classPresentStudents.peek()))
				{
					classPresentStudents.dequeue();
				}
				else
				{
					studentListCheckBox[counter].setSelected(true);
				}
				studentListPane.add(studentListCheckBox[counter]);
				counter++;
			}
		}
		if (studentListPane.getComponentCount() == 0) noStudents();
		repopulateQueue();
	}
	
	/**
	 * Re-populates the Queue that was previously emptied when the present students were displayed.
	 */
	public void repopulateQueue()
	{
		int counter = 0;
		while (!classPresentStudents.isEmpty())
		{
			classPresentStudents.dequeue();
		}
		for (int i = 0; i < studentListCheckBox.length; i++)
		{
			if (studentListCheckBox[i] == null)
			{
				break;
			}
			else if (!studentListCheckBox[i].isSelected())
			{
				counter++;
				classPresentStudents.enqueue(studentListCheckBox[i].getText());
			}
		}
	}
	
	
	private class UpdatePresentStudentList implements ActionListener, WindowListener
	{	
		/**
		 * Updates the list of present students.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			repopulateQueue();
			if (enableEditing)
			{
				button.setText("" + classPresentStudents.getSize());
			}
			if (arg0 != null) frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
		
		public void windowClosing(WindowEvent arg0) 
		{
			actionPerformed(null);
		}
		public void windowActivated(WindowEvent arg0) {}
		public void windowClosed(WindowEvent arg0) {}
		public void windowDeactivated(WindowEvent arg0) {}
		public void windowDeiconified(WindowEvent arg0) {}
		public void windowIconified(WindowEvent arg0) {}
		public void windowOpened(WindowEvent arg0) {}
	}
}