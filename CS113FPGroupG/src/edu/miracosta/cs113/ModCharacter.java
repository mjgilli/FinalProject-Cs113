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
    private Character encrypt;

    //constructor
    public ModCharacter(char c)
    {
        data = c;

        if(Character.isUpperCase(data));
        {
            upperCase = true;
        }
    }

    /**
     * Returns the char object of the data
     *
     * @return The data
     */

    public Character getData()
    {
        return data;
    }

    /**
     * Returns the representation of this char
     * in encrypted character
     * @return
     */
    public char getEncrypt()
    {
        return encrypt;
    }

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

    @Override
    public String toString()
    {
        return data.toString();
    }

}
