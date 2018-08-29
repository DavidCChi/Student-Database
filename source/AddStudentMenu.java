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
 * A frame for adding Student objects.
 */
public class AddStudentMenu extends ManipulateStudentMenu 
{

	// class fields
	/**
	 * Max capacity of a class.
	 */
	public static final int MAX_CAPACITY = 50;
	
	// instance fields
	private ArrayList<Student> studentList;

	private JButton addStudentButton;
	private JLabel errorLabel = new JLabel();
	private boolean errorLabelAdded;
	private GridBagConstraints constraints;

	// menu that created an object of this class
	private MainMenu menu;
	
	/**
	 * Constructs a new AddStudentMenu with default values.
	 */
	public AddStudentMenu() 
	{
		super();
		studentList = new ArrayList<Student>();
		addStudentButton = new JButton();
		errorLabel = new JLabel();
		errorLabelAdded = false;
		paintFrame();
	}

	/**
	 * Constructs a new AddStudentMenu with specified values.
	 * 
	 * @param list the list of Student to be manipulated
	 * @param menu the menu that created this AddStudentmenu
	 */
	public AddStudentMenu(ArrayList<Student> list, MainMenu menu) 
	{
		super();
		studentList = list;
		this.menu = menu;
		errorLabel = new JLabel();
		errorLabelAdded = false;
		constraints = new GridBagConstraints();
		paintFrame();
	}

	/**
	 * Paints the frame of this AddStudentMenu.
	 */
	public void paintFrame() 
	{
		frame.setTitle("Add a Student");
		titleLabel.setText("Add a Student");

		addressLabel.setText("Address *: ");
		ageLabel.setText("Age *: ");
		birthDateLabel.setText("Date of Birth (YYYY-MM-DD) *: ");
		emergencyNameLabel.setText("Emergency Contact Name *: ");
		emergencyPhoneLabel.setText("Emergency Contact Phone *: ");
		gradeLabel.setText("Grade (1-8) *: ");
		healthCardLabel.setText("Health Card Number *: ");
		isMorningClassLabel.setText("Morning Class (Y/N) *: ");
		nameLabel.setText("Name (Last First) *: ");
		oenNumberLabel.setText("OEN *: ");
		parentNameLabel.setText("Parent Name *: ");
		phoneLabel.setText("Phone Number *: ");

		addStudentButton = new JButton("Add Student");
		addStudentButton.addActionListener(new AddStudent());
		addStudentButton.setHorizontalAlignment(SwingConstants.CENTER);
		constraints.insets = new Insets(0, 0, 20, 0);
		constraints.gridx = FIRST_COLUMN;
		constraints.gridy = FIRST_ROW;
		buttonContentPane.add(addStudentButton, constraints);

		frame.validate();
		frame.repaint();
	}

	/* private class */

	private class AddStudent implements ActionListener 
	{
		
		/**
		 * Adds a Student with specified values from the frame.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{
			if (!errorLabelAdded) 
			{
				titleContentPane.add(errorLabel);
				errorLabelAdded = true;
			}
			
			// ensures all required fields are not empty.
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

			// ensures the date is in the valid format.
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
			
			// ensures the proper values are entered for the isMorningClassField.
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

			// ensures that the given class has not reached max capacity.
			int grade = Integer.parseInt(gradeField.getText());
			
			// ensures the grade that was entered is valid.
			if (grade < 1 || grade > 8)
			{
				errorLabel.setText(grade + " is not valid. (1-8)");

				frame.validate();
				frame.repaint();

				return;
			}
			
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
			
			// ensures that the fields that are int/long have valid values.
			try 
			{
				studentList.add(new Student(addressField.getText().trim(), Integer.parseInt(ageField.getText()),
						birthDateField.getText().trim(), emergencyNameField.getText().trim(),
						Long.parseLong(emergencyPhoneField.getText()), Integer.parseInt(gradeField.getText()),
						healthCardField.getText().trim(), isMorningClass, nameField.getText().trim(),
						oenNumberField.getText().trim(), parentNameField.getText().trim(),
						Long.parseLong(phoneField.getText()), specificHealthProblemField.getText().trim()));
			} 
			catch (NumberFormatException e) 
			{
				String[] str = e.getMessage().split("\"", 3);
				errorLabel.setText(str[1] + " is not a valid integer.");

				frame.validate();
				frame.repaint();

				return;
			}
			menu.updateStudentProperties();
			menu.orderStudents();
			menu.updateStudentList();
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			JOptionPane.showMessageDialog(menu.getFrame(), "Student successfully added.");
		}
	}
}