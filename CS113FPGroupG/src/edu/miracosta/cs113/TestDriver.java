package edu.miracosta.cs113;

/**
 * Created by User on 5/19/2017.
 */
public class TestDriver
{
    public static void main(String[] args)
    {
        EncryptionTree<ModCharacter> et = new EncryptionTree<>();
        System.out.println(((ModCharacter)(et.root.right.right.left.left.data)).getData());


    }
}
