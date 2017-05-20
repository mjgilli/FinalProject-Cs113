package edu.miracosta.cs113;

import java.util.Scanner;

public class Date implements Comparable<Date>
{
    private String month;
    private int day;
    private int year; //a four digit number.

    /**
     * Default Constructor for Date class
	 * Sets all instance variables to default values
	 * PRECONDITION:  N/A
	 * POSTCONDITION: Creates instance of Date class
     */
    public Date( )
    {
        month = "January";
        day = 1;
        year = 1000;
    }

    /**
     * Full Constructor for Date Class
     * @param monthInt int representing month
     * @param day int representing day
     * @param year int representing year
     */
    public Date(int monthInt, int day, int year)
    {
        setDate(monthInt, day, year);
    }

    /**
     * Full Constructor for Date Class
     * @param monthInt String representing month
     * @param day int representing day
     * @param year int representing year
     */
    public Date(String monthString, int day, int year)
    {
        setDate(monthString, day, year);
    }

    /**
     * Full Constructor for Date Class starting on new year
     * @param year int representing year
     */
    public Date(int year)
    {
        setDate(1, 1, year);
    }

    /**
     * Copy Constructor for Date Class starting on given Date
     * @param aDate Date object to create Date from
     */
    public Date(Date aDate)
    {
        if (aDate == null)//Not a real date.
        {
             System.out.println("Fatal Error.");
             System.exit(0);
        }
        month = aDate.month;
        day = aDate.day;
        year = aDate.year;
    }

    /**
     * Sets the date of Date to given values
	 * PRECONDITION:  Variable monthInt, day, year has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
     * @param monthInt int representation of month
     * @param day int representation of day
     * @param year int representation of year
     */
    public void setDate(int monthInt, int day, int year)
    {
        if (dateOK(monthInt, day, year))
        {
            this.month = monthString(monthInt);
            this.day = day;
            this.year = year;
        }
        else
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }

    /**
     * Sets the date of Date to given values
	 * PRECONDITION:  Variable monthInt, day, year has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
     * @param monthInt String representation of month
     * @param day int representation of day
     * @param year int representation of year
     */
    public void setDate(String monthString, int day, int year)
    {
        if (dateOK(monthString, day, year))
        {
            this.month = monthString;
            this.day = day;
            this.year = year;
        }
        else
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
    }

    /**
     * Sets the date of Date to given values on new year
	 * PRECONDITION:  Variable year has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
     * @param year int representation of year
     */
    public void setDate(int year)
    {
        setDate(1, 1, year);
    }

    /**
     * Sets the year of Date to given values
	 * PRECONDITION:  Variable year has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
     * @param year int representation of year
     */
    public void setYear(int year)
    {
        if ( (year < 1000) || (year > 9999) )
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            this.year = year;
    }

    /**
     * Sets the month of Date to given values
	 * PRECONDITION:  Variable month has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
     * @param month int representation of month
     */
    public void setMonth(int monthNumber)
    {
        if ((monthNumber <= 0) || (monthNumber > 12))
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            month = monthString(monthNumber);
    }

    /**
     * Sets the day of Date to given values
	 * PRECONDITION:  Variable day has valid value
	 * POSTCONDITION: Sets instance variable to parameter value
     * @param month int representation of day
     */
    public void setDay(int day)
    {
        if ((day <= 0) || (day > 31))
        {
            System.out.println("Fatal Error");
            System.exit(0);
        }
        else
            this.day = day;
    }

    /**
	 * Gets the month of the Date
	 * PRECONDITION: All instance variables(month) have valid values
	 * @return int representation of month
	 */
    public int getMonth( )
    {
        if (month.equals("January"))
            return 1;
        else if (month.equals("February"))
            return 2;
        else if (month.equals("March"))
            return 3;
       else if (month.equalsIgnoreCase("April"))
           return 4;
       else if (month.equalsIgnoreCase("May"))
           return 5;
       else if (month.equals("June"))
           return 6;
       else if (month.equalsIgnoreCase("July"))
           return 7;
       else if (month.equalsIgnoreCase("August"))
           return 8;
       else if (month.equalsIgnoreCase("September"))
           return 9;
       else if (month.equalsIgnoreCase("October"))
           return 10;
        else if (month.equals("November"))
            return 11;
        else if (month.equals("December"))
            return 12;
        else
        {
            System.out.println("Fatal Error");
            System.exit(0);
            return 0; //Needed to keep the compiler happy
        }
    }

    /**
   	 * Gets the day of the Date
   	 * PRECONDITION: All instance variables(day) have valid values
   	 * @return int representation of day
   	 */
    public int getDay( )
    {
        return day;
    }

    /**
   	 * Gets the year of the Date
   	 * PRECONDITION: All instance variables(year) have valid values
   	 * @return int representation of year
   	 */
    public int getYear( )
    {
        return year;
    }

    /**
	 * Converts object to String representation
	 * PRECONDITON:  All instance variables have valid values
	 * POSTCONDITON: Returns String containing all instance variables
	 */
    public String toString( )
    {
        return (month + " " + day + ", " + year);
    }

    /**
     * Returns boolean is "this" Date is equal to parameter
	 * PRECONDITION:	Instance variables have valid values for both Date objects
     * POSTCONDITION:	Returns true if both are exactly the same, false otherwise.
     * @param other Date object to compare
     * @return boolean representing if equal
     */
    public boolean equals(Object other)
    {
    	Date otherDate;

		if(other == null || this.getClass() != other.getClass())
		{
			return false;
		}
		else
		{
			otherDate = (Date) other;
			
			return ( (month.equals(otherDate.month))
	                  && (day == otherDate.day) && (year == otherDate.year) );
		}
    }

    /**
     * Returns boolean is "this" Date precedes to parameter
	 * PRECONDITION:	Instance variables have valid values for both Date objects
     * POSTCONDITION:	Returns true if this precedes otherDate, false otherwise.
     * @param other Date object to compare
     * @return boolean representing if precedes
     */
    public boolean precedes(Date otherDate)
    {
        return ( (year < otherDate.year) ||
           (year == otherDate.year && getMonth( ) < otherDate.getMonth( )) ||
           (year == otherDate.year && month.equals(otherDate.month)
                                         && day < otherDate.day) );
    }

    /**
     * Reads input from user to set Date variables
	 * PRECONDITION:	N/A
     * POSTCONDITION:	Date object has now set values
     */
    public void readInput( )
    {
        boolean tryAgain = true;
        Scanner keyboard = new Scanner(System.in);
        while (tryAgain)
        {
            System.out.println("Enter month, day, and year.");
              System.out.println("Do not use a comma.");
            String monthInput = keyboard.next( );
            int dayInput = keyboard.nextInt( );
            int yearInput =  keyboard.nextInt( );
            if (dateOK(monthInput, dayInput, yearInput) )
            {
                setDate(monthInput, dayInput, yearInput);
                tryAgain = false;
            }
            else
                System.out.println("Illegal date. Reenter input.");
         }
        keyboard.close();
    }

    /**
     * checks if date is valid
     * @param monthInt int representation of month
     * @param dayInt int representation of day
     * @param yearInt int representation of year
     * @return true if valid, false otherwise
     */
    private boolean dateOK(int monthInt, int dayInt, int yearInt)
    {
        return ( (monthInt >= 1) && (monthInt <= 12) &&
                 (dayInt >= 1) && (dayInt <= 31) &&
                 (yearInt >= 1000) && (yearInt <= 9999) );
    }

    /**
     * checks if date is valid
     * @param monthString string representation of month
     * @param dayInt int representation of day
     * @param yearInt int representation of year
     * @return true if valid, false otherwise
     */
    private boolean dateOK(String monthString, int dayInt, int yearInt)
    {
        return ( monthOK(monthString) &&
                 (dayInt >= 1) && (dayInt <= 31) &&
                 (yearInt >= 1000) && (yearInt <= 9999) );
    }

    /**
     * Checks if month is valid
     * @param month string representation
     * @return true if valid, false otherwise
     */
    private boolean monthOK(String month)
    {
        return (month.equals("January") || month.equals("February") ||
                month.equals("March") || month.equals("April") ||
                month.equals("May") || month.equals("June") ||
                month.equals("July") || month.equals("August") ||
                month.equals("September") || month.equals("October") ||
                month.equals("November") || month.equals("December") );
    }

    /**
     * changes int representation of month to String
     * @param monthNumber int representing month
     * @return string representation of month
     */
    private String monthString(int monthNumber)
    {
        switch (monthNumber)
        {
        case 1:
            return "January";
        case 2:
            return "February";
        case 3:
            return "March";
        case 4:
            return "April";
        case 5:
            return "May";
        case 6:
            return "June";
        case 7:
            return "July";
        case 8:
            return "August";
         case 9:
            return "September";
        case 10:
            return "October";
        case 11:
            return "November";
        case 12:
            return "December";
        default:
            System.out.println("Fatal Error");
            System.exit(0);
            return "Error"; //to keep the compiler happy
        }
    }

    /**
	 * Compares two Dates if precedes, equal, or after
	 * PRECODNDITON: Date instance variables have valid values
	 * @param Date object other to be compared to
	 * @return int value < 0 if this comes before other, > 0 if this comes after other, = 0 if both EMails are equal
	 */
	public int compareTo(Date obj) 
	{
		if(this.precedes(obj))
		{
			return -1;
		}
		else if (this.equals(obj))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	/**
	 * Sorts Date array inside of fullAr
	 * @param fullAr Date array to be sorted
	 */
	public static void mergeSort(Date[] fullAr)
	{
		int halfSize;
		if(fullAr.length > 1)
		{
			halfSize = fullAr.length / 2;
			int size1 = halfSize;
			int size2 = fullAr.length - halfSize;
			Date[] leftAr  = new Date[size1];
			Date[] rightAr = new Date[size2];
			
			for(int i = 0; i < halfSize; i ++)
			{
				leftAr[i] = fullAr[i];
			}
			for(int i = 0; i < size2; i++)
			{
				rightAr[i] = fullAr[i + size1];
			}
			mergeSort(leftAr);
			mergeSort(rightAr);
			merge(fullAr, leftAr, rightAr);
		}
	}
	
	/**
	 * Merges the given left and right arrays into the given result array.
	 * PRECONDITION:  Result is empty; left/right are sorted
	 * POSTCONDITION: Result contains result of merging sorted lists;
	 * @param result  Date array containing array to be merged
	 * @param left    Left half of Date array
	 * @param right	  Right half of Date array
	 */
    private static void merge(Date[] result, Date[] left, Date[] right) 
    {
        int i1 = 0;   // index into left array
        int i2 = 0;   // index into right array
        
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
