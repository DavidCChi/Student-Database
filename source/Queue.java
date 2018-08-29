/**
 * A queue of strings implemented via an array.
 * 
 * @author
 * @version
 */
public class Queue
{
    /* class variables */
    /**
     * The default maximum size of a queue.
     */
    public static final int DEFAULT_MAX_SIZE = 10;
    
    private static final int EMPTY_QUEUE = -1;
    
    /*instance fields */
    private String[] queue;
    private int mostRecentString;
    
    /**
    * Constructs a queue of characters with the specified maximum size. 
    * If an invalid value is received, the {@link Queue#DEFAULT_MAX_SIZE} is used.
    * <br>precondition: maximumSize > 0</br>
    *
    * @param maximumSize maximum number of characters which this queue can hold.
    */
    public Queue(int maximumSize)
    {
        if (maximumSize > 0)
        {
            queue = new String[maximumSize];
        }
        else
        {
            queue = new String[DEFAULT_MAX_SIZE];
        } // end of if (maximumSize > 0)
        mostRecentString = EMPTY_QUEUE;
    } // end of constructor Queue(int maximumSize)

    /* accessors */
    
    /**
     * Returns the number of elements on this queue.
     * 
     * @return the number of elements on this queue
     */
    public int getSize()
    {
        return mostRecentString + 1;
    } // end of method getSize()
    
    /**
     * Without dequeuing, returns the first element of this queue. <br>pre-condition: the queue is not empty</br>
     * 
     * @return the first element in this queue, otherwise <code>null</code>
     */
    public String peek()
    {
        if (!isEmpty()) return queue[0];
        return null;
    } // end of method peek()
    
    /**
     * Returns <code>true</code> if the is empty, otherwise <code>false</code>.
     * 
     * @return <code>true</code> if the queue is empty, otherwise <code>false</code>
     */
    public boolean isEmpty()
    {
        if (mostRecentString == EMPTY_QUEUE) return true;
        return false;
    } // end of method isEmpty()
    
    /**
     * Returns <code>true</code> if the is full, otherwise <code>false</code>.
     * 
     * @return <code>true</code> if the queue is full, otherwise <code>false</code>
     */
    public boolean isFull()
    {
        if (mostRecentString == queue.length - 1) return true;
        return false;
    } // end of method isFull()
    
    /**
     * Adds the specified character to the rear of the queue. <br>precondition: the queue is not full</br>
     * 
     * @param character the character to be enqueued
     */
    public void enqueue(String string)
    {
        if (!isFull()) 
        {
            queue[++mostRecentString] = string;
        }
        else
        {
            return;
        } // end of if (!isFull()) 
    } // end of method enqueue(String string)
    
    /**
     * Removes the character at the front of the queue. <br>precondition: the queue is not empty</br>
     * 
     * @return the character at the front of the queue
     */
    public String dequeue()
    {
    	String string = queue[0];
        for (int i = 0; i < mostRecentString; i++)
        {
            queue[i] = queue[i+1];
        } // end of for (int i = 0; i < mostRecentString; i++)
        mostRecentString--;
        return string;
    } // end of method dequeue()
    
    /**
     * Returns a string representation of this queue.
     *
     * @return a string representing this queue
     */
    public String toString()
    {
    	if (mostRecentString == EMPTY_QUEUE) return null;
        String returnString = "" + queue[0];
        for (int i = 1; i <= mostRecentString; i++)
        {
            returnString = returnString + "," + queue[i];
        } // end of for (int i = 1; i <= mostRecentString; i++)
        
        return returnString;
    } // end of method toString()
} // end of class Queue
