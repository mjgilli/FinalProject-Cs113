package edu.miracosta.cs113;

/**
 * This class is used as the representation
 * of a char object with added instance variables
 * necessary for the encryption operation
 *
 * @author Group G - Final Project - CS113 SP2017
 * @version 1.0
 * @date 5/19/2017
 */
public class ModCharacter
{
    private boolean upperCase = false;
    
    private Character data;
    private String encryptionCode = "";

    /**
     * Full Constructor for ModCharacter class
	 * Sets all instance variable to given values
	 * PRECONDITION: All arguments passed through must have valid values
	 * POSTCONDITION: Creates instance of EMail class with given values
     * @param c
     * @param encryptionCode
     */
    public ModCharacter(char c, String encryptionCode)
    {
        data = c;
        this.encryptionCode = encryptionCode;

        if(Character.isUpperCase(data));
        {
            upperCase = true;
        }
    }

    /**
     * Returns if character or upper case
     * PRECONDITION:  N/A
	 * POSTCONDITION: true, if upper, false otherwise
     * @return boolean true, if upper, false otherwise
     */
    public boolean isUpperCase()
    {
        return upperCase;
    }

    /**
     * Returns the char object of the data
     * PRECONDITION: All instance variables(subject) have valid values
     * @return The data
     */
    public Character getData()
    {
        return data;
    }

    /**
     * Returns the representation of this char
     * in encrypted character
     * PRECONDITION: All instance variables(subject) have valid values
     * @return encrypted string representation of character
     */
    public String getEncryptionCode()
    {
        return encryptionCode;
    }

    /**
     * Returns boolean is "this" ModCharacter is equal to parameter
	 * PRECONDITION:	Instance variables have valid values for both ModCharacter objects
     * POSTCONDITION:	Returns true if both are exactly the same, false otherwise.
     * @param other Object to compare
     * @return boolean representing if equal
     */
    @Override
    public boolean equals(Object o)
    {
        if( o == null )
        {
            return false;
        }

        if(o.getClass() != this.getClass())
        {
            return false;
        }

        ModCharacter other = (ModCharacter) o;

        return (char) this.data == (char) other.getData();
    }


	/**
	 * Converts object to String representation
	 * PRECONDITON:  All instance variables have valid values
	 * POSTCONDITON: Returns String containing all instance variables
	 */
    @Override
    public String toString()
    {
        return data.toString() + " " + encryptionCode;
    }

}
