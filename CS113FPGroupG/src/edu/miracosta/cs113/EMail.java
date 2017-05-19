package edu.miracosta.cs113;

public class EMail implements Comparable<EMail>{
	protected String sender;
	protected String subject;
	protected Date date;
	protected String msg;
	protected String saveFileName;
	
	
	/**
	 * Default Constructor for EMail class
	 * Sets all instance variables to default values
	 * PRECONDITION:  N/A
	 * POSTCONDITION: Creates instance of EMail class
	 */
	public EMail(){
		this.sender = "";
		this.subject = "";
		this.date = null;
		this.msg = "";
		this.saveFileName = "encrypted"+subject+".txt";
	}
	
	/**
	 * Full Constructor for EMail class
	 * sets all instance variable to given values
	 * PRECONDITION: All arguments passed through must have valid values
	 * POSTCONDITION: Creates instance of EMail class with given values
	 * @param sender String representing name of sender
	 * @param subject String representing subject on EMail
	 * @param date Date Object representing date EMail was sent
	 * @param msg String representing contents of EMail
	 */
	public EMail(String sender, String subject, Date date, String msg)
	{
		this.sender= sender;
		this.subject = subject;
		this.date = date;
		this.msg = msg;
		saveFileName = "encrypted" + this.subject + ".txt";
	}
	
	/**
	 * Sets the sender of EMail to given value 
	 * PRECONDITION:  Variable sender has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
	 * @param sender String representation of sender
	 */
	public void setSender(String sender){
		this.sender = sender;
	}
	
	/**
	 * Gets the sender of EMail
	 * PRECONDITION: All instance variables(sender) have valid values
	 * @return String representing sender of EMail
	 */
	public String getSender(){
		return sender;
	}
	
	/**
	 * Sets the subject of EMail to given value
	 * PRECONDITION:  Variable subject has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
	 * @param subject String representation of subject
	 */
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	/**
	 * Gets the subject of EMail
	 * PRECONDITION: All instance variables(subject) have valid values
	 * @return String representing subject of EMail
	 */
	public String getSubject(){
		return subject;
	}
	
	/**
	 * Sets the date of EMail to given value
	 * PRECONDITION:  Variable date has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
	 * @param date Date Object representing when EMail was sent
	 */
	public void setDate(Date date){
		this.date = date;
	}
	
	/**
	 * Gets the date of EMail
	 * PRECONDITION: All instance variables(date) have valid values
	 * @return Date representation of date email sent
	 */
	public Date getDate(){
		return date;
	}
	
	/**
	 * Sets the contents of what was written in EMail
	 * PRECONDITION:  Variable msg has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
	 * @param msg String representing what email says
	 */
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	/**
	 * Gets the msg of EMail
	 * PRECONDITION: All instance variables(msg) have valid values
	 * @return String representation of what email says
	 */
	public String getMsg(){
		return msg;
	}
	
	public void setSaveFileName(String saveFileName){
		this.saveFileName = saveFileName;
	}
	
	/**
	 * Gets the fileName of how EMail will be saved
	 * PRECONDITION: All instance variables(saveFileName) have valid values
	 * @return String representing what EMail is saved under
	 */
	public String getSaveFileName(){
		return saveFileName;
	}
	// DESCRIPTION:   converts object to String representation
		// PRECONDITION:  all instance variables have valid values
		// POSTCONDITION: returns String containing all instance variables
	/**
	 * Converts object to String representation
	 * PRECONDITON:  All instance variables have valid values
	 * POSTCONDITON: Returns String containing all instance variables
	 */
	public String toString()
	{
		return "From: " + this.sender + "\t on " + this.date.toString() + "\n Subject:" + this.subject
				+ "\n Message:" + this.msg;
	}

	/**
	 * Compares two EMails by date > sender's name > subject
	 * PRECODNDITON: EMails instance variables have valid values
	 * @param EMail object other to be compared to
	 * @return int value < 0 if this comes before other, > 0 if this comes after other, = 0 if both EMails are equal
	 */
	public int compareTo(EMail other) {
		int n;
		n = this.date.compareTo(other.date);
		if(n != 0)
		{
			return n;
		}
		n = this.sender.compareTo(other.sender);
		if (n != 0)
		{
			return n;
		}
		return this.subject.compareTo(other.subject);
	}
	
	/**
	 * Compares two EMails by date
	 * PRECODNDITON: EMails instance variables have valid values
	 * @param EMail object other to be compared to
	 * @return int value < 0 if this comes before other, > 0 if this comes after other, = 0 if both EMails are equal
	 */
	public int compareToDate(EMail other)
	{
		return this.date.compareTo(other.date);
	}
	
	/**
	 * Compares two EMails by sender's name
	 * PRECODNDITON: EMails instance variables have valid values
	 * @param EMail object other to be compared to
	 * @return int value < 0 if this comes before other, > 0 if this comes after other, = 0 if both EMails are equal
	 */
	public int compareToSender(EMail other)
	{
		return this.sender.compareTo(other.sender);
	}
	
	/**
	 * Compares two EMails by subject
	 * PRECODNDITON: EMails instance variables have valid values
	 * @param EMail object other to be compared to
	 * @return int value < 0 if this comes before other, > 0 if this comes after other, = 0 if both EMails are equal
	 */
	public int compareToSubject(EMail other)
	{
		return this.subject.compareTo(other.subject);
	}
	
	/**
	 * Sorts EMail array inside of fullAr
	 * @param fullAr EMail array to be sorted
	 * @param choice char to pick how to sort
	 */
	public static void mergeSort(EMail[] fullAr, char choice)
	{
		int halfSize;
		if(fullAr.length > 1)
		{
			halfSize = fullAr.length / 2;
			int size1 = halfSize;
			int size2 = fullAr.length - halfSize;
			EMail[] leftAr  = new EMail[size1];
			EMail[] rightAr = new EMail[size2];
			
			for(int i = 0; i < halfSize; i ++)
			{
				leftAr[i] = fullAr[i];
			}
			for(int i = 0; i < size2; i++)
			{
				rightAr[i] = fullAr[i + size1];
			}
			mergeSort(leftAr, choice);
			mergeSort(rightAr, choice);
			merge(fullAr, leftAr, rightAr, choice);
		}
	}
	
	/**
	 * Merges the given left and right arrays into the given result array.
	 * PRECONDITION:  Result is empty; left/right are sorted
	 * POSTCONDITION: Result contains result of merging sorted lists;
	 * @param result  EMail array containing array to be merged
	 * @param left    Left half of EMail array
	 * @param right	  Right half of EMail array
	 * @param choice  char representing what type of sort
	 */
    private static void merge(EMail[] result, EMail[] left, EMail[] right, char choice) 
    {
        int i1 = 0;   // index into left array
        int i2 = 0;   // index into right array
        
        if(choice == 'A') // by sender alphabetically
        {
        	for (int i = 0; i < result.length; i++) {
                if (i2 >= right.length || (i1 < left.length && left[i1].compareToSender(right[i2]) < 0))
                {
                    result[i] = left[i1];    // take from left
                    i1++;
                } else {
                    result[i] = right[i2];   // take from right
                    i2++;
                }
            }
        }
        else if (choice == 'B') // by subject alphabetically
        {
        	for (int i = 0; i < result.length; i++) {
                if (i2 >= right.length || (i1 < left.length && left[i1].compareToSubject(right[i2]) < 0))
                {
                    result[i] = left[i1];    // take from left
                    i1++;
                } else {
                    result[i] = right[i2];   // take from right
                    i2++;
                }
            }
        }
        else if(choice == 'C' ) // by date chronologically
        {
        	for (int i = 0; i < result.length; i++) {
                if (i2 >= right.length || (i1 < left.length && left[i1].compareToDate(right[i2]) < 0))
                {
                    result[i] = left[i1];    // take from left
                    i1++;
                } else {
                    result[i] = right[i2];   // take from right
                    i2++;
                }
            }
        }
        else // by default--> date > sender > subject
        {
        	 for (int i = 0; i < result.length; i++) {
                 if (i2 >= right.length || (i1 < left.length && left[i1].compareTo(right[i2]) < 0))
                 {
                     result[i] = left[i1];    // take from left
                     i1++;
                 } else {
                     result[i] = right[i2];   // take from right
                     i2++;
                 }
             }
        	
        }
          
    }
	
}
