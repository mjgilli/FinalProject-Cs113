/**
 * Encryption Binary Tree
 */

import java.util.*;
import java.io.*;

public class EncryptionTree<E> extends BinaryTree
{
    private final String FILE = "encryptionMap.txt";
    //Constructor
    public EncryptionTree()
    {
        super();
        root = new Node<Character>(null);
        readFile("morsecode.txt");
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
            while(scan.hasNext())
            {
                Character letter = scan.next().charAt(0);
                String code = scan.next();
                build(letter, code, this.root);
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
        StringBuilder sb = new StringBuilder();
        Scanner scan = null;
        String path = null;

        try
        {
            scan = new Scanner(new File(FILE));

            while (scan.hasNextLine())
            {
                String data = scan.nextLine();
                if(data.toLowerCase().charAt(0) == c)
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
     * Helper method: Recursively traverse the tree based on given path
     * and collects encrypted representation of node
     *
     * @param path The traversal path through the tree
     * @param current Current position in the tree
     * @param sb StringBuilder object used to collect the encryption code
     * @return The encryption representation
     */
    private String getEncryption(String path,Node<ModCharacter> current, StringBuilder sb)
    {
        //this operation wont work until the generic is changed to ModData
        sb.append(current.data.getEncrypt());
        if(path.length() == 0)
        {
            //do nothing
        }
        else
        {
            if (path.charAt(0) == '-')
            {
                current = current.left;
            } else
            {
                current = current.right;
            }
        }
        return getEncryption(path.substring(1), current, sb);
    }

    public String encode(Character c)
    {
        String path = getPath(c);
        StringBuilder sb = new StringBuilder();

        String encryptedChar = getEncryption(path, root, sb);

        return encryptedChar;
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
    private void build(Character c, String code, Node<Character> current)
    {
        //Terminating case. Executed once no more morse character used to traverse in the code
        if(code.isEmpty())
        {
            current.data = c;
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
    private void traverse(Node<E> node,  StringBuilder letter, StringBuilder code)
    {
        if (node == null)
        {
            //DO NOTHING.
        }
        else {
            letter.append(node.toString() + "\t" + code.toString() + "\n");
            traverse(node.left, letter, code.append('*'));
            code.deleteCharAt(code.length() - 1);
            traverse(node.right, letter, code.append('-'));
            code.deleteCharAt(code.length() - 1);
        }
    }
}
