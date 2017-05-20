package edu.miracosta.cs113;
/**
 * Encryption Binary Tree
 */


import java.util.*;
import java.io.*;

@SuppressWarnings({ "serial" })
public class EncryptionTree<E> extends BinaryTree<ModCharacter>
{
    private final String FILE = "encryptionMap.txt";
    private final String CODE = "encryptionCode.txt";
    //Constructor

	public EncryptionTree()
    {
        super();
        root = new Node<ModCharacter>();
        readFile(FILE);
    }

    /**
     * Read passed filename and open it in a Scanner object.
     * This method will build a Morse Code binary tree
     * by assigning all alphabet characters in its proper
     * place
     *
     * @param fileName file name prompted in the Driver class.
     */

	public void readFile(String fileName)
    {
        Scanner scan = null;
        try
        {
            scan = new Scanner(new File(fileName));
            while(scan.hasNextLine())
            {
                String data = scan.nextLine();
                Character letter = data.charAt(0);
                String path = data.substring(1);
                build(letter, path, this.root);
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
            System.exit(0);
        }
        finally
        {
            scan.close();
        }
    }

    /**
     * Returns the Encryption Tree traversal path of the character
     *
     * @param c The character
     * @return The path
     */
    public String getPath(Character c)
    {
        Scanner scan = null;
        String path = null;
        

        try
        {
            scan = new Scanner(new File(FILE));

            while (scan.hasNextLine())
            {
                String data = scan.nextLine();
                if(data.toUpperCase().charAt(0) == c.charValue())
                {
                    path = data.substring(1);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return path;
    }

    /**
     * Returns the encryption code of the character
     *
     * @param c The character
     * @return
     */
    private String getCode(Character c)
    {
        Scanner scan = null;
        String encryptionCode = "";

        try
        {
            scan = new Scanner(new File(CODE));

            while(scan.hasNextLine())
            {
                String data = scan.nextLine();

                if( c == data.charAt(0))
                {
                    encryptionCode = data.substring(1);
                }
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        return encryptionCode;
    }

    /**
     * Helper method: Recursively traverse the tree based on given path
     * and collects encrypted representation of node
     *
     * @param path The traversal path through the tree
     * @param current Current position in the tree
     * @param sb StringBuilder object used to collect the encryption code
     * @return The encryption representation
     */
    private String getEncryption(String path, Node<ModCharacter> current, StringBuilder sb)
    {
        //this operation wont work until the generic is changed to ModData
        if(path.length() == 0)
        {
        	return sb.toString()+" ";
            //do nothing
        }
        else
        {
            if (path.charAt(0) == '-')
            {
                current = current.left;
                sb.append(current.data.getEncryptionCode()+"|");
            } else
            {
                current = current.right;
                sb.append(current.data.getEncryptionCode()+"|");
            }
        }
        return getEncryption(path.substring(1), current, sb);
    }

    /**
     * Returns the encrypted version of the character
     *
     * @param c The character
     * @return Encrypted Representation
     */
	public String encode(Character c)
    {
        String path = getPath(c);
        StringBuilder sb = new StringBuilder();
        Node<ModCharacter> theNode = root;
        String encryptedChar = getEncryption(path, theNode, sb);

        return encryptedChar;
    }
    
    /**
     * Returns the Decrypted version of character
     * @param input encryption to be decoded
     * @return decrypted representation
     */
	public Character decode(String input){
    	ModCharacter aa = (ModCharacter) decode(root, input);
    	return ((ModCharacter)(aa)).getData();
    }
    
    /**
     * Helper Method: Recursively traverse the tree based on given strings
     * and substrings until finds end letter and decrypts
     * @param node node to find and decrypt
     * @param str encoded representation of letter
     * @return decoded letter
     */
    private ModCharacter decode(Node<ModCharacter> node, String str){

    	String nextWord = "";
    	if(str.length() != 0){
    		nextWord = str.substring(0, str.indexOf("|"));
    	}
    	
    	if(str.length() == 0 || str == null){
    		return node.data;
    	}    	
    	if(node.left.data.getEncryptionCode().equals(nextWord)){
    		node = node.left;
    	}
    	else if(node.right.data.getEncryptionCode().equals(nextWord)){
    		node = node.right;
    	}
    	return decode(node, str.substring(str.indexOf("|")+1));
    }

    /**
     * This method recursively traverse the binary tree
     * Once all morse characters are accounted for
     * will assign the letter to the data of the current node
     *
     * @param c Alphabet letter represented by the morse code
     * @param code Argument morse code
     * @param current Current node in the recursion(Start from root)
     */
    private void build(Character c, String code, Node<ModCharacter> current)
    {
        //Terminating case. Executed once no more morse character used to traverse in the code
        if(code.isEmpty())
        {
            current.data = new ModCharacter(c, getCode(c));
        }
        else
        {
            if(code.charAt(0) == '-')
            {
                if(current.left == null)
                {
                    current.left = new Node<>();
                }
                build(c, code.substring(1), current.left);
            }
            else if(code.charAt(0) == '+')
            {
                if(current.right == null)
                {
                    current.right = new Node<>();
                }
                build(c, code.substring(1), current.right);
            }
        }
    }
	@Override
    public String toString()
    {
        StringBuilder letter = new StringBuilder();
        StringBuilder code = new StringBuilder();
        traverse(root, letter, code);

        return letter.toString();
    }

    /**
     * Traverse the tree in preorder fashion an append the StringBuilder
     * with the alphabetic character as well as its corresponding
     * morse code
     *
     * @param node Current node traversed. Starts with root.
     * @param letter String builder for Alphabet char and its morse code.
     * @param code Used to keep track of the morse code
     */
    private void traverse(Node<ModCharacter> node, StringBuilder letter, StringBuilder code)
    {
        if (node == null)
        {
            //DO NOTHING.
        }
        else {
            letter.append(node.toString() + "\t" + code.toString() + "\n");
            traverse(node.left, letter, code.append(node.data.getEncryptionCode()));
            code.deleteCharAt(code.length() - 1);
            traverse(node.right, letter, code.append(node.data.getEncryptionCode()));
            code.deleteCharAt(code.length() - 1);
        }
    }
}
