import java.awt.GridBagConstraints;
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
 * A frame that displays and manipulates the information of an Attendance.
 */
public class AttendanceInfoMenu extends ManipulateAttendanceMenu 
{

	// instance fields
	private ArrayList<Queue[]> presentStudentList;
	private Queue[] attendancePresentStudentList;
	private ArrayList<Attendance> attendanceList;
	private Attendance attendance;
	private int attendanceIndex;
	private String date;

	// menu that created an object of this class
	private MainMenu menu;

	private JButton deleteAttendanceButton;
	private JButton editAttendanceButton;
	private JLabel errorLabel;
	private boolean errorLabelAdded;
	private GridBagConstraints constraints = new GridBagConstraints();

	/**
	 * Constructs a new AttendanceInfoMenu with default values.
	 */
	public AttendanceInfoMenu() 
	{
		super();
		presentStudentList = new ArrayList<Queue[]>();
		attendanceList = new ArrayList<Attendance>();
		attendance = new Attendance();
		attendanceIndex = 0;
		date = "";
		menu = new MainMenu();
		errorLabel = new JLabel();
		errorLabelAdded = false;
		paintFrame();
		getAttendanceInfo(attendance);
	}
	
	/**
	 * Constructs a new AttendanceInfoMenu with specified values.
	 * 
	 * @param presentStudentList the list of present students
	 * @param attendanceList the list of Attendance to manipulate
	 * @param menu the menu that created this AttendanceInfoMenu
	 * @param date the date of the Attendance
	 */
	public AttendanceInfoMenu(ArrayList<Queue[]> presentStudentList, ArrayList<Attendance> attendanceList, 
			MainMenu menu, String date) 
	{
		super();
		this.presentStudentList = presentStudentList;
		this.attendanceList = attendanceList;
		this.menu = menu;
		this.date = date;
		errorLabel = new JLabel();
		errorLabelAdded = false;
		attendance = searchAttendance(date);
		paintFrame();
		getAttendanceInfo(attendance);
	}
	
	/**
	 * Paints the frame of this AttendanceInfoMenu.
	 */
	public void paintFrame() 
	{
		frame.setTitle("Attendance Information");
		titleLabel.setText("Attendance Information");

		editAttendanceButton = new JButton("Edit");
		editAttendanceButton.addActionListener(new EditAttendanceInfo());
		editAttendanceButton.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.insets = new Insets(0, 20, 13, 30);
		constraints.gridwidth = 1;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		buttonContentPane.add(editAttendanceButton, constraints);

		deleteAttendanceButton = new JButton("Delete");
		deleteAttendanceButton.addActionListener(new DeleteAttendance());
		deleteAttendanceButton.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.insets = new Insets(0, 30, 13, 20);
		constraints.gridwidth = 1;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		buttonContentPane.add(deleteAttendanceButton, constraints);

		frame.validate();
		frame.repaint();
	}
	
	// searches the Attendance list for specified Attendance
	private Attendance searchAttendance(String date) 
	{
		for (int i = 0; i < attendanceList.size(); i++) 
		{
			if (attendanceList.get(i).getDate().equals(date)) 
			{
				attendanceIndex = i;
				return attendanceList.get(i);
			}
		}
		return new Attendance();
	}

	/**
	 * Gets the value of the Attendance.
	 * 
	 * @param attendance the attendance whose values are to be displayed
	 */
	public void getAttendanceInfo(Attendance attendance) 
	{
		dateField.setText(attendance.getDate());
		dateField.setEditable(false);

		for (int i = 0; i < attendance.getMorningStudentCount().length; i++) 
		{
			morningStudentCountField[i].setText("" + attendance.getMorningStudentCount()[i]);
			afternoonStudentCountField[i].setText("" + attendance.getAfternoonStudentCount()[i]);
			
			morningStudentCountField[i].addActionListener(new DisplayPresentStudents());
			afternoonStudentCountField[i].addActionListener(new DisplayPresentStudents());
		}
	}

	/**
	 * Checks the frame for valid values and adds a new Attendance accordingly.
	 */
	public void checkValidValues() 
	{
		int[] morningStudentCount = new int[8];
		int[] afternoonStudentCount = new int[8];

		// ensures the date field is not empty.
		if (dateField.getText().isEmpty()) 
		{
			errorLabel.setText("Please enter a date.");

			frame.validate();
			frame.repaint();

			return;
		}

		// ensures the date is entered in the valid format.
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
			if (!dateField.getText().equals(this.date))
			{
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
			if (morningStudentCountField[i].getText().isEmpty()) 
			{
				morningStudentCount[i] = 0;
			} 
			else 
			{
				morningStudentCount[i] = Integer.parseInt(morningStudentCountField[i].getText());
			}
		}

		for (int i = 0; i < afternoonStudentCount.length; i++) 
		{
			if (afternoonStudentCountField[i].getText().isEmpty()) 
			{
				afternoonStudentCount[i] = 0;
			} 
			else 
			{
				afternoonStudentCount[i] = Integer.parseInt(afternoonStudentCountField[i].getText());
			}
		}

		int sum = IntStream.of(morningStudentCount).sum() + IntStream.of(afternoonStudentCount).sum();
		String attendanceDate = dateField.getText();
		attendancePresentStudentList = presentStudentList.get(attendanceIndex);
		
		attendanceList.set(attendanceIndex,
				new Attendance(attendanceDate, morningStudentCount, afternoonStudentCount, sum));
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
		JOptionPane.showMessageDialog(menu.getFrame(), "Attendance successfully edited.");
	}

	/* private classes */

	private class EditAttendanceInfo implements ActionListener 
	{
		
		/**
		 * Enables editing of the Attendance, and updates accordingly.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			if (editAttendanceButton.getText().equals("Edit")) 
			{
				editAttendanceButton.setText("Done");

				dateField.setEditable(true);

				frame.validate();
				frame.repaint();
			} 
			else if (editAttendanceButton.getText().equals("Done")) 
			{
				if (!errorLabelAdded) 
				{
					titleContentPane.add(errorLabel);
					errorLabelAdded = true;
				}
				checkValidValues();
			}
		}
	}

	private class DeleteAttendance implements ActionListener 
	{
		
		/**
		 * Deletes the Attendance.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			int reply = JOptionPane.showConfirmDialog(frame, "Are you sure you\n" + 
					"want to delete this attendance?", "Warning", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) 
			{
				attendanceList.remove(attendanceIndex);
				presentStudentList.remove(attendanceIndex);
				menu.updateAttendanceProperties();
				menu.orderDates();
				menu.updateAttendanceList();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				JOptionPane.showMessageDialog(menu.getFrame(), "Attendance successfully deleted.");
			}
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
						menu.getStudentList(), attendanceIndex, (buttonIndex + 1), false, button, 
						dateField.isEditable());
			}
			else
			{
				AbsentStudentMenu absentStudentMenu = new AbsentStudentMenu(presentStudentList, 
						menu.getStudentList(), attendanceIndex, (buttonIndex + 1), true, button, 
						dateField.isEditable());
			}
		}
	}
}
