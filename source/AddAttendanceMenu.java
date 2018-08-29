import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * A frame for adding Attendance objects.
 */
public class AddAttendanceMenu extends ManipulateAttendanceMenu 
{
	// class fields
	/**
	 * Max capacity of a class.
	 */
	public static final int MAX_CAPACITY = 50;
	
	/**
	 * Max amount of classes in the school
	 */
	public static final int MAX_AMOUNT_OF_CLASSES = 16;
	
	// instance fields
	private ArrayList<Queue[]> presentStudentList;
	private Queue[] attendancePresentStudentList;
	private ArrayList<Attendance> attendanceList;
	private ArrayList<Student> studentList;
	
	private int[] morningStudentCount;
	private int[] afternoonStudentCount;
	private int attendanceIndex;
	
	private JButton addAttendanceButton;
	
	private JLabel errorLabel;
	private boolean errorLabelAdded;
	
	// menu that created an object of this class
	private MainMenu menu;
	
	/**
	 * Constructs a new AddAttendanceMenu with default values.
	 */
	public AddAttendanceMenu() 
	{
		super();
		presentStudentList = new ArrayList<Queue[]>();
		attendancePresentStudentList = new Queue[MAX_AMOUNT_OF_CLASSES];
		attendanceList = new ArrayList<Attendance>();
		studentList = new ArrayList<Student>();
		addAttendanceButton = new JButton();
		errorLabel = new JLabel();
		errorLabelAdded = false;
		attendanceIndex = 0;
		menu = new MainMenu();
		paintFrame();
	}
	
	/**
	 * Constructs a new AddAttendanceMenu with specified values.
	 * 
	 * @param presentStudentList the list of present students
	 * @param attendanceList the list of Attendance to manipulate
	 * @param menu the menu that created an object of this class
	 */
	public AddAttendanceMenu(ArrayList<Queue[]> presentStudentList, ArrayList<Attendance> attendanceList, MainMenu menu) 
	{
		super();
		this.presentStudentList = presentStudentList;
		attendancePresentStudentList = new Queue[MAX_AMOUNT_OF_CLASSES];
		this.presentStudentList.add(attendancePresentStudentList);
		attendanceIndex = this.presentStudentList.indexOf(attendancePresentStudentList);
		this.attendanceList = attendanceList;
		this.menu = menu;
		studentList = menu.getStudentList();
		errorLabel = new JLabel();
		errorLabelAdded = false;
		morningStudentCount = new int[8];
		afternoonStudentCount = new int[8];
		populateQueueList();
		paintFrame();
	}
	
	/**
	 * Paints the frame of this AddAttendanceMenu.
	 */
	public void paintFrame() 
	{
		frame.setTitle("Add an Attendance");
		titleLabel.setText("Add an Attendance");
		
		dateLabel.setText("Date (YYYY-MM-DD) *:");
		
		addAttendanceButton = new JButton("Add Attendance");
		addAttendanceButton.addActionListener(new AddAttendance());
		addAttendanceButton.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.insets = new Insets(0, 100, 13, 100);
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		buttonContentPane.add(addAttendanceButton, constraints);
		
		for (int i = 0; i < morningStudentCountField.length; i++) 
		{
			morningStudentCountField[i].addActionListener(new DisplayPresentStudents());
			morningStudentCountField[i].setEnabled(true);
			
			afternoonStudentCountField[i].addActionListener(new DisplayPresentStudents());
			afternoonStudentCountField[i].setEnabled(true);
		}
		
		frame.validate();
		frame.repaint();
	}
	
	/**
	 * Populates the queue list with default values.
	 */
	public void populateQueueList() {
		Queue queue = null;
		for (int i = 0; i < morningStudentCount.length; i++) 
		{
			if (morningStudentCountField[i].getText().equals("Click to select absent students")) 
			{
				queue = new Queue(MAX_CAPACITY);
				int counter = 0;
				for (int j = 0; j < studentList.size(); j++) 
				{
					if (studentList.get(j).isMorningClass() && studentList.get(j).getGrade() == (i + 1))
					{
						queue.enqueue(studentList.get(j).getName());
						counter++;
					}
				}
				morningStudentCount[i] = counter;
				attendancePresentStudentList[i] = queue;
			}
		}
		
		for (int i = 0; i < afternoonStudentCount.length; i++) 
		{
			if (afternoonStudentCountField[i].getText().equals("Click to select absent students")) 
			{
				queue = new Queue(MAX_CAPACITY);
				int counter = 0;
				for (int j = 0; j < studentList.size(); j++) 
				{
					if (!studentList.get(j).isMorningClass() && studentList.get(j).getGrade() == (i + 1))
					{
						queue.enqueue(studentList.get(j).getName());
						counter++;
					}
				}
				afternoonStudentCount[i] = counter;
				attendancePresentStudentList[i + 8] = queue;
			} 
		}
	}
	
	/* private classes */
	
	private class AddAttendance implements ActionListener 
	{
		
		/**
		 * Adds the Attendance with specified values from the frame.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			if (!errorLabelAdded) 
			{
				titleContentPane.add(errorLabel);
				errorLabelAdded = true;
			}
			
			// ensures the date field is not empty.
			if (dateField.getText().isEmpty()) 
			{
				errorLabel.setText("Please enter a date.");
				
				frame.validate();
				frame.repaint();
				
				return;
			}
			
			// ensures the date is in the valid format.
			DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
			try 
			{
				date.parse(dateField.getText());
				
				String[] str = dateField.getText().split("-", 3);
				
				if (str[0].length() != 4 || str[1].length() != 2 || str[2].length() != 2) 
				{
					errorLabel.setText(dateField.getText() + " is in the wrong format.");

					frame.validate();
					frame.repaint();

					return;
				}
				
				// ensures an attendance of the same date is not already in the database.
				for (int i = 0; i < attendanceList.size(); i++)
				{
					if (attendanceList.get(i).getDate().equals(dateField.getText()))
					{
						errorLabel.setText("An attendance of this date was already added.");

						frame.validate();
						frame.repaint();

						return;
					}
				}
			} 
			catch (ParseException e) 
			{
				errorLabel.setText(dateField.getText() + " is in the wrong format.");

				frame.validate();
				frame.repaint();

				return;
			}
			
			for (int i = 0; i < morningStudentCount.length; i++) 
			{
				if (morningStudentCountField[i].getText().equals("Click to select absent students")) 
				{
					morningStudentCount[i] = attendancePresentStudentList[i].getSize();
				}
				else
				{
					morningStudentCount[i] = Integer.parseInt(morningStudentCountField[i].getText());
				}
			}
			
			for (int i = 0; i < afternoonStudentCount.length; i++) 
			{
				if (afternoonStudentCountField[i].getText().equals("Click to select absent students")) 
				{
					afternoonStudentCount[i] = attendancePresentStudentList[i + 8].getSize();
				} 
				else
				{
					afternoonStudentCount[i] = Integer.parseInt(afternoonStudentCountField[i].getText());
				}
			}
			
			int sum = IntStream.of(morningStudentCount).sum() + IntStream.of(afternoonStudentCount).sum();
			String attendanceDate = dateField.getText();
			
			attendanceList.add(new Attendance(attendanceDate, morningStudentCount, afternoonStudentCount, sum));
			morningStudentCount = null;
			afternoonStudentCount = null;
			menu.updateAttendanceProperties();
			menu.orderDates();
			menu.updateAttendanceList();
			
			for (int i = 0; i < attendanceList.size(); i++) {
				if (attendanceList.get(i).getDate().equals(attendanceDate)) {
					presentStudentList.remove(attendanceIndex);
					presentStudentList.add(i, attendancePresentStudentList);
					break;
				}
			}
			
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			JOptionPane.showMessageDialog(menu.getFrame(), "Attendance successfully added.");
		}
	}
	
	private class DisplayPresentStudents implements ActionListener
	{
		/**
		 * Displays the list of present students.
		 */
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			int buttonIndex = Arrays.asList(morningStudentCountField).indexOf(button);
			if (buttonIndex == -1)
			{
				buttonIndex = Arrays.asList(afternoonStudentCountField).indexOf(button);
				AbsentStudentMenu absentStudentMenu = new AbsentStudentMenu(presentStudentList, 
						menu.getStudentList(), attendanceIndex, (buttonIndex + 1), false, button, true);
			}
			else
			{
				AbsentStudentMenu absentStudentMenu = new AbsentStudentMenu(presentStudentList, 
						menu.getStudentList(), attendanceIndex, (buttonIndex + 1), true, button, true);
			}
		}
	}
}
