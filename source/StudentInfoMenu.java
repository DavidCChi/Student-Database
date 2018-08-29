import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 * A frame that displays the information of a Student.
 */
public class StudentInfoMenu extends ManipulateStudentMenu 
{
	// class fields
	public static final int MAX_CAPACITY = 50;

	// instance fields
	private ArrayList<Student> studentList;
	private Student student;
	private int studentNumber;
	private String initialName;
	private int originalGrade;
	private boolean originalMorningClass;

	// menu that created an object of this class
	private MainMenu menu;

	private JButton deleteStudentButton;
	private JButton editStudentButton;
	private JLabel errorLabel;
	private boolean errorLabelAdded;
	private GridBagConstraints constraints = new GridBagConstraints();

	/**
	 * Constructs a new StudentInfoMenu with specified values.
	 */
	public StudentInfoMenu() 
	{
		super();
		studentList = new ArrayList<Student>();
		student = new Student();
		studentNumber = 0;
		initialName = "";
		menu = new MainMenu();
		paintFrame();
		getStudentInfo(studentNumber);
	}

	/**
	 * Constructs a new StudentInfoMenu with specified values.
	 * 
	 * @param list
	 *            the list of students
	 * @param menu
	 *            the menu that created this StudentInfoMenu
	 * @param studentNumber
	 *            the number of the student whose information is to be displayed
	 */
	public StudentInfoMenu(ArrayList<Student> list, MainMenu menu, int studentNumber) 
	{
		super();
		studentList = list;
		this.menu = menu;
		student = studentList.get(studentNumber - 1);
		this.studentNumber = studentNumber;
		errorLabel = new JLabel();
		errorLabelAdded = false;
		paintFrame();
		getStudentInfo(studentNumber);
	}

	/**
	 * Paints the frame of this StudentInfoMenu.
	 */
	public void paintFrame() 
	{
		frame.setTitle("Student Information");
		titleLabel.setText("Student Information");

		editStudentButton = new JButton("Edit");
		editStudentButton.addActionListener(new EditStudentInfo());
		editStudentButton.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.insets = new Insets(0, 20, 20, 30);
		constraints.gridwidth = 1;
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		buttonContentPane.add(editStudentButton, constraints);

		deleteStudentButton = new JButton("Delete");
		deleteStudentButton.addActionListener(new DeleteStudent());
		deleteStudentButton.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.insets = new Insets(0, 30, 20, 20);
		constraints.gridwidth = 1;
		constraints.gridx = SECOND_COLUMN;
		constraints.gridy = FIRST_ROW;
		buttonContentPane.add(deleteStudentButton, constraints);

		frame.validate();
		frame.repaint();
	}

	/**
	 * Displays the information of the Student.
	 * 
	 * @param studentNumber
	 *            the number of the student
	 */
	public void getStudentInfo(int studentNumber) 
	{
		addressField.setText(student.getAddress());
		addressField.setEditable(false);

		ageField.setText("" + student.getAge());
		ageField.setEditable(false);

		birthDateField.setText(student.getBirthDate());
		birthDateField.setEditable(false);

		emergencyNameField.setText(student.getEmergencyName());
		emergencyNameField.setEditable(false);

		emergencyPhoneField.setText("" + student.getEmergencyPhone());
		emergencyPhoneField.setEditable(false);

		gradeField.setText("" + student.getGrade());
		gradeField.setEditable(false);
		originalGrade = student.getGrade();

		healthCardField.setText(student.getHealthCard());
		healthCardField.setEditable(false);

		if (student.isMorningClass()) 
		{
			isMorningClassField.setText("Y");
		} 
		else 
		{
			isMorningClassField.setText("N");
		}
		isMorningClassField.setEditable(false);
		originalMorningClass = student.isMorningClass();

		nameField.setText(student.getName());
		nameField.setEditable(false);

		oenNumberField.setText(student.getOenNumber());
		oenNumberField.setEditable(false);

		parentNameField.setText(student.getParentName());
		parentNameField.setEditable(false);

		phoneField.setText("" + student.getPhone());
		phoneField.setEditable(false);

		specificHealthProblemField.setText(student.getSpecificHealthProblem());
		specificHealthProblemField.setEditable(false);

		frame.validate();
		frame.repaint();
	}

	/**
	 * Checks the frame for valid values.
	 */
	public void checkValidValues() {
		
		// ensures all the required fields are not empty.
		if (addressField.getText().isEmpty() || ageField.getText().isEmpty() || birthDateField.getText().isEmpty()
				|| emergencyNameField.getText().isEmpty() || emergencyPhoneField.getText().isEmpty()
				|| gradeField.getText().isEmpty() || healthCardField.getText().isEmpty()
				|| isMorningClassField.getText().isEmpty() || nameField.getText().isEmpty()
				|| oenNumberField.getText().isEmpty() || parentNameField.getText().isEmpty()
				|| phoneField.getText().isEmpty()) 
		{
			errorLabel.setText("Please Enter Valid Values for All Fields");

			frame.validate();
			frame.repaint();

			return;
		}

		// ensures the date is entered in the right format.
		DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		try 
		{
			date.parse(birthDateField.getText());

			String[] str = birthDateField.getText().split("-", 3);

			if (str[0].length() != 4 || str[1].length() != 2 || str[2].length() != 2) 
			{
				errorLabel.setText(birthDateField.getText() + " is in the wrong format.");

				frame.validate();
				frame.repaint();

				return;
			}
		} 
		catch (ParseException e)
		{
			errorLabel.setText(birthDateField.getText() + " is in the wrong format.");

			frame.validate();
			frame.repaint();

			return;
		}

		// ensures the entered value for isMorningClassField is valid.
		boolean isMorningClass = false;
		if (isMorningClassField.getText().equalsIgnoreCase("Y")) 
		{
			isMorningClass = true;
		}
		else if (isMorningClassField.getText().equalsIgnoreCase("N")) 
		{
			isMorningClass = false;
		} 
		else 
		{
			errorLabel.setText(isMorningClassField.getText() + " is not valid. (Y/N)");

			frame.validate();
			frame.repaint();

			return;
		}
		
		// ensures the desired class has not reached max capacity.
		int grade = Integer.parseInt(gradeField.getText());
		
		// ensures the grade that was entered is valid.
		if (grade < 1 || grade > 8)
		{
			errorLabel.setText(grade + " is not valid. (1-8)");

			frame.validate();
			frame.repaint();

			return;
		}
		
		if (originalGrade != grade || originalMorningClass != isMorningClass)
		{
			int counter = 0;
	
			for (int i = 0; i < studentList.size(); i++) 
			{
				if (studentList.get(i).getGrade() == grade 
						&& studentList.get(i).isMorningClass() == isMorningClass) counter++;
				if (counter == MAX_CAPACITY) 
				{
					errorLabel.setText("The selected class is currently full.");
	
					frame.validate();
					frame.repaint();
	
					return;
				}
			}
		}

		// ensures the fields that are int/long have valid values.
		try 
		{
			student = new Student(addressField.getText().trim(), Integer.parseInt(ageField.getText()),
					birthDateField.getText().trim(), emergencyNameField.getText().trim(),
					Long.parseLong(emergencyPhoneField.getText()), Integer.parseInt(gradeField.getText()),
					healthCardField.getText().trim(), isMorningClass, nameField.getText().trim(),
					oenNumberField.getText().trim(), parentNameField.getText().trim(),
					Long.parseLong(phoneField.getText()), specificHealthProblemField.getText().trim());
		}
		catch (NumberFormatException e) 
		{
			String[] str = e.getMessage().split("\"", 3);
			errorLabel.setText(str[1] + " is not a valid integer.");

			frame.validate();
			frame.repaint();

			return;
		}

		studentList.set(studentNumber - 1, student);

		menu.updateStudentProperties();
		if (!initialName.equals(nameField.getText())) menu.orderStudents();
		menu.updateStudentList();
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		JOptionPane.showMessageDialog(menu.getFrame(), "Student successfully edited.");
	}

	/* private class */

	private class EditStudentInfo implements ActionListener 
	{

		/**
		 * Enables editing of the student's information
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (editStudentButton.getText().equals("Edit")) 
			{
				initialName = nameField.getText();

				editStudentButton.setText("Done");

				addressField.setEditable(true);
				ageField.setEditable(true);
				birthDateField.setEditable(true);
				emergencyNameField.setEditable(true);
				emergencyPhoneField.setEditable(true);
				gradeField.setEditable(true);
				healthCardField.setEditable(true);
				isMorningClassField.setEditable(true);
				nameField.setEditable(true);
				oenNumberField.setEditable(true);
				parentNameField.setEditable(true);
				phoneField.setEditable(true);
				specificHealthProblemField.setEditable(true);

				frame.validate();
				frame.repaint();
			}
			else if (editStudentButton.getText().equals("Done")) 
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

	private class DeleteStudent implements ActionListener 
	{

		/**
		 * Deletes the student.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			int reply = JOptionPane.showConfirmDialog(frame, "Are you sure you\n" + "want to delete this student?",
					"Warning", JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) 
			{
				studentList.remove(studentNumber - 1);
				menu.updateStudentProperties();
				menu.orderStudents();
				menu.updateStudentList();
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				JOptionPane.showMessageDialog(menu.getFrame(), "Student successfully deleted.");
			}
		}
	}
}