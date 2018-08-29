/**
 * Write a description of class Student here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Student
{
    // class fields
    public static final int DEFAULT_AGE = 6;
    public static final int DEFAULT_GRADE = 1;

    // instance fields
    private String address;
    private int age;
    private String birthDate;
    private String emergencyName;
    private long emergencyPhone;
    private int grade;
    private String healthCard;
    private boolean isMorningClass;
    private String name;
    private String oenNumber;
    private String parentName;
    private long phone;
    private String specificHealthProblem;

    // constructors
    
    /**
     * Constructs a new student with default values.
     */
    public Student()
    {
        address = "";
        age = 0;
        birthDate = "";
        emergencyName = "";
        emergencyPhone = 0;
        grade = 0;
        healthCard = "";
        isMorningClass = false;
        name = "";
        oenNumber = "";
        parentName = "";
        phone = 0;
        specificHealthProblem = "";
    } // end of constructor Student()

    /**
     * Constructs a new student with specified values.
     * 
     * @param address the address of this student
     * @param age the age of this student <br><i>pre-condition:</i> cannot be less than or equal to 0.
     * @param birthDate the birth date of this student.
     * @param emergencyName the name of the emergency contact of this student.
     * @param emergencyPhone the phone number of the emergency contact of this student.
     * @param grade the grade of this student <br><i>pre-condition:</i> cannot be less than 1.
     * @param healthCard the health card of this student.
     * @param isMorningClass whether or not this student is in the morning class, otherwise afternoon class.
     * @param name the name of this student.
     * @param oenNumber the OEN number of this student <br><i>pre-condition:</i> cannot be less than 0.
     * @param parentName the name of the parent of this student.
     * @param phone the phone number to contact this student.
     * @param specificHealthProblem the specific health problem of the student
     */
    public Student(String address, int age, String birthDate, String emergencyName, long emergencyPhone, int grade, String healthCard, boolean isMorningClass, String name, String oenNumber, String parentName, long phone, String specificHealthProblem)
    {
        this.address = address;
        if (age >= 0) this.age = age;
        else this.age = DEFAULT_AGE;
        this.birthDate = birthDate;
        this.emergencyName = emergencyName;
        this.emergencyPhone = emergencyPhone;
        if (grade > 0) this.grade = grade;
        else this.grade = DEFAULT_GRADE;
        this.healthCard = healthCard;
        this.isMorningClass = isMorningClass;
        this.name = name;
        this.oenNumber = oenNumber;
        this.parentName = parentName;
        this.phone = phone;
        this.specificHealthProblem = specificHealthProblem;
    } // end of constructor Student(String address, int age, String birthDate, String emergencyName, long emergencyPhone, int grade, String healthCard, boolean isMorningClass, String name, int oenNumber, String parentName, long phone, String specificHealthProblem)
    
    // accessors
    
    /**
     * Returns the address of this student.
     * 
     * @return the address of this student
     */
    public String getAddress()
    {
        return address;
    } // end of method getAddress()
    
    /**
     * Returns the age of this student.
     * 
     * @return the age of this student
     */
    public int getAge()
    {
        return age;
    } // end of method getAge()
    
    /**
     * Returns the birth date of this student.
     * 
     * @return the birth date of this student
     */
    public String getBirthDate()
    {
        return birthDate;
    } // end of method getBirthDate()
    
    /**
     * Returns the name of the emergency contact of this student.
     * 
     * @return the emergency contact name of this student
     */
    public String getEmergencyName()
    {
        return emergencyName;
    } // end of method getEmergencyName()
    
    /**
     * Returns the phone number of the emergency contact of this student.
     * 
     * @return the emergency contact phone number of this student.
     */
    public long getEmergencyPhone()
    {
        return emergencyPhone;
    } // end of method getEmergencyPhone()
    
    /**
     * Returns the grade of this student.
     * 
     * @return the grade of this student
     */
    public int getGrade()
    {
    	return grade;
    } // end of method getGrade()
    
    /**
     * Returns the health card of this student.
     * 
     * @return the health card of this student
     */
    public String getHealthCard()
    {
    	return healthCard;
    } // endn of method getHealthCard()
    
    /**
     * Returns whether or not this student is in the morning class.
     * 
     * @return <code>true</code> if this student is in the morning class, otherwise <code>false</code>
     */
    public boolean isMorningClass()
    {
    	return isMorningClass;
    } // end of method isMorningClass()
    
    /**
     * Returns the name of this student.
     * 
     * @return the name of this student
     */
    public String getName()
    {
    	return name;
    } // end of method getName()
    
    /**
     * Returns the OEN number of this student.
     * 
     * @return the OEN number of this student
     */
    public String getOenNumber()
    {
    	return oenNumber;
    } // end of method getOenNumber()
    
    /**
     * Returns the name of this student's parent.
     * 
     * @return the name of this student's parent
     */
    public String getParentName()
    {
    	return parentName;
    } // end of method getParentName()
    
    /**
     * Returns the phone number of this student.
     * 
     * @return the phone number of this student
     */
    public long getPhone()
    {
    	return phone;
    } // end of method getPhone()
    
    /**
     * Returns the specific health problem(s) of this student.
     * 
     * @return the specific health problem(s) of this student
     */
    public String getSpecificHealthProblem()
    {
    	return specificHealthProblem;
    } // end of method getSpecificHealthProblem()
    
    // mutators
    
    /**
     * Sets the address of this student.
     * 
     * @param address the new address of this student
     */
    public void setAddress(String address)
    {
    	this.address = address;
    } // end of method setAddress(String address)
    
    /**
     * Sets the age of this student.
     * 
     * @param age the new age of this student
     */
    public void setAge(int age)
    {
    	this.age = age;
    } // end of method setAge(int age)
    
    /**
     * Sets the birth date of this student.
     * 
     * @param birthDate the new birth date of this student
     */
    public void setBirthDate(String birthDate) 
    {
    	this.birthDate = birthDate;
    } // end of method setBirthDate(String birthDate)
    
    /**
     * Sets the emergency contact name of this student.
     * 
     * @param emergencyName the new emergency contact name of this student
     */
    public void setEmergencyName(String emergencyName)
    {
    	this.emergencyName = emergencyName;
    } // end of method setEmergencyName(String emergencyName)
    
    /**
     * Sets the emergency contact phone number of this student.
     * 
     * @param emergencyPhone the emergency contact phone number of this student
     */
    public void setEmergencyPhone(int emergencyPhone)
    {
    	this.emergencyPhone = emergencyPhone;
    } // end of method setEmergencyPhone(int emergencyPhone)
    
    /**
     * Sets the grade of this student.
     * 
     * @param grade the new grade of this student
     */
    public void setGrade(int grade)
    {
    	this.grade = grade;
    } // end of method setGrade(int grade)
    
    /**
     * Sets the health card of this student.
     * 
     * @param healthCard the new health card of this student
     */
    public void setHealthCard(String healthCard)
    {
    	this.healthCard = healthCard;
    } // end of method setHealthCard(String healthCard)
    
    /**
     * Sets whether or not this student is in the morning class.
     * 
     * @param isMorningClass whether or not this student is in the morning class
     */
    public void setIsMorningClass(boolean isMorningClass)
    {
    	this.isMorningClass = isMorningClass;
    } // end of method setIsMorningClass(boolean isMorningClass)
    
    /**
     * Sets the name of this student.
     * 
     * @param name the new name of this student
     */
    public void setName(String name)
    {
    	this.name = name;
    } // end of method setName(String name)
    
    /**
     * Sets the OEN number of this student.
     * 
     * @param oenNumber the new OEN number of this student
     */
    public void setOenNumber(String oenNumber)
    {
    	this.oenNumber = oenNumber;
    } // end of method setOenNumber(String oenNumber)
    
    /**
     * Sets the name of this student's parent.
     * 
     * @param parentName the new name of this student's parent
     */
    public void setParentName(String parentName)
    {
    	this.parentName = parentName;
    } // end of method setParentName(String parentName)
    
    /**
     * Sets the phone number of this student.
     * 
     * @param phone the new phone number of this student
     */
    public void setPhone(int phone)
    {
    	this.phone = phone;
    } // end of method setPhone(int phone)
    
    /**
     * Sets the specific health problem of this student.
     * 
     * @param specificHealthProblem the new specific health problem of this student
     */
    public void setSpecificHealthProblem(String specificHealthProblem)
    {
    	this.specificHealthProblem = specificHealthProblem;
    } // end of method setSpecificHealthProblem(String specificHealthProblem)
    
    /**
     * Returns the string representation of this student.
     */
    public String toString() {
    	// format is used so that the return statement can be easily tokenized.
    	return address + "~"
    		   + age + "~"
    		   + birthDate + "~"
    		   + emergencyName + "~"
    		   + emergencyPhone + "~"
    		   + grade + "~"
    		   + healthCard + "~"
    		   + isMorningClass + "~"
    		   + name + "~"
    		   + oenNumber + "~"
    		   + parentName + "~"
    		   + phone + "~"
    		   + specificHealthProblem;
    }
} // end of class Student