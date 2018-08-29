
/**
 * An attendance that knows its date, the amount of students of each grade, and the total amount of students.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Attendance
{
    // instance variables
    private String date;
    private int morningStudentCount[] = new int[8];
    private int afternoonStudentCount[] = new int[8];
    private int total;

    /**
     * Constructs a new attendance with default values.
     */
    public Attendance()
    {
    	date = "";
        for (int i = 0; i < morningStudentCount.length; i++) 
        {
            morningStudentCount[i] = 0;
            afternoonStudentCount[i] = 0;
        }
        total = 0;
    }
    
    /**
     * Constructs a new attendance with specified values.
     * 
     * @param date the date of this attendance.
     * @param morningStudentCount the morning student count of this attendance.
     * @param afternoonStudentCount the afternoon student count of this attendance.
     * @param total the total count of students of this attendance.
     */
    public Attendance(String date, int[] morningStudentCount, int[] afternoonStudentCount, int total)
    {
    	this.date = date;
        this.morningStudentCount = morningStudentCount;
        this.afternoonStudentCount = afternoonStudentCount;
        this.total = total;
    }
    
    // accessors
    
    /**
     * Returns the date of this attendance.
     * 
     * @return the date of this attendance
     */
    public String getDate()
    {
        return date;
    }
    
    /**
     * Returns the morning student count of this attendance.
     * 
     * @return the morning student count
     */
    public int[] getMorningStudentCount()
    {
        return morningStudentCount;
    }
    
    /**
     * Returns the afternoon student count of this attendance.
     * 
     * @return the afternoon student count
     */
    public int[] getAfternoonStudentCount()
    {
        return afternoonStudentCount;
    }
    
    /**
     * Returns the total student count of this attendance.
     * 
     * @return the total student count of this attendance.
     */
    public int getTotal() {
    	return total;
    }
    
    // mutators
    
    /**
     * Sets the date of this attendance.
     * 
     * @param date the new date of this attendance
     */
    public void setDate(String date)
    {
        this.date = date;
    }
    
    /**
     * Sets the morning student count of this attendance.
     * 
     * @param morningStudentCount the new morning student count of this attendance
     */
    public void setMorningStudentCount(int[] morningStudentCount)
    {
        this.morningStudentCount = morningStudentCount;
    }
    
    /**
     * Sets the afternoon student count of this attendance.
     * 
     * @param afternoonStudentCount the new afternoon student count of this attendance
     */
    public void setAfternoonStudentCount(int[] afternoonStudentCount)
    {
        this.afternoonStudentCount = afternoonStudentCount;
    }
    
    /**
     * Sets the total student count of this attendance.
     * 
     * @param total the total student count of this attendance
     */
    public void setTotal(int total) {
    	this.total = total;
    }
    
    /**
     * Returns the string representation of this attendance.
     * 
     * @return the string representation of this attendance
     */
    public String toString() 
    {
    	String morningCount = "";
    	String afternoonCount = "";
    	
    	for (int i = 0; i < morningStudentCount.length; i++) 
    	{
    		morningCount  = morningCount + morningStudentCount[i] + "~";
    		afternoonCount  = afternoonCount + afternoonStudentCount[i] + "~";
    	}
    	
    	return morningCount
    		   + afternoonCount
    		   + date;
    }
}
