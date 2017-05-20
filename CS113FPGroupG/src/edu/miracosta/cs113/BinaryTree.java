package edu.miracosta.cs113;

import java.io.*;
import java.util.Scanner;

@SuppressWarnings("serial")
public class BinaryTree<E> implements Serializable
{
    public Node<E> root;


    /**
     * Default Constructor for BinaryTree class
	 * Sets all instance variables to default values
	 * PRECONDITION:  N/A
	 * POSTCONDITION: Creates instance of BinaryTree class
     */
    public BinaryTree()
    {
        root = null;
    }

    /**
	 * Full Constructor for BinaryTree class
	 * sets all instance variable to given values
	 * PRECONDITION: All arguments passed through must have valid values
	 * POSTCONDITION: Creates instance of BinaryTree class with given values
     * @param node sets the root node
     */
    public BinaryTree(Node<E> node)
    {
        root = node;
    }

    /**
	 * Full Constructor for BinaryTree class, merges two trees
	 * sets all instance variable to given values
	 * PRECONDITION: All arguments passed through must have valid values
	 * POSTCONDITION: Creates instance of BinaryTree class with given values
     * @param data sets root of tree
     * @param leftTree left side of root
     * @param rightTree right side of root
     */
    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree)
    {
        root = new Node<E>(data);

        if( leftTree  != null)
        {
            root.left = leftTree.root;
        }
        else
        {
            root.left = null;
        }

        if( rightTree != null)
        {
            root.right = rightTree.root;
        }
        else
        {
            root.right = null;
        }
    }

    /**
	 * Gets the left sub tree of BinaryTree
	 * PRECONDITION: N/A
	 * @return BinaryTree representing left side of tree
	 */
    public BinaryTree<E> getLeftSubtree()
    {
        if(root != null && root.left != null)
        {
            return new BinaryTree<E>(root.left);
        }
        else
        {
            return null;
        }

    }

    /**
	 * Gets the right sub tree of BinaryTree
	 * PRECONDITION: N/A
	 * @return BinaryTree representing right side of tree
	 */
    public BinaryTree<E> getRightSubtree()
    {
        if(root != null && root.right != null)
        {
            return new BinaryTree<E>(root.right);
        }
        else
        {
            return null;
        }
    }

    /**
	 * Gets the data of root
	 * PRECONDITION: All instance variables(root.data) have valid values
	 * @return data of root node
	 */
    public E getData()
    {
        return root.data;
    }

    /**
	 * Checks if root is a leaf node
	 * PRECONDITION: All instance variables(rootroot) have valid values
	 * @return boolean representing if root has any children
	 */
    public boolean isLeaf()
    {
        return root.left == null && root.right == null;
    }

    /**
	 * Converts object to String representation
	 * PRECONDITON:  All instance variables have valid values
	 * POSTCONDITON: Returns String containing all instance variables
	 */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);

        return sb.toString();
    }

    /**
     * Recursive method Traverses tree in pre order fashion, called by to string
     * @param node current node
     * @param depth levels of tree
     * @param sb collects string represntation of tree
     */
    public void preOrderTraverse(Node<E> node, int depth, StringBuilder sb)
    {
        for(int i = 1; i < depth; i++)
        {
            sb.append(" ");
        }

        if( node == null)
        {
            sb.append("null\n");
        }
        else
        {
            sb.append(node.toString() + "\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }

    /**
     * reads in a binary tree from text file
     * @param scan scanner to get nodes
     * @return Fully constructed BinaryTree of strings
     */
    public static BinaryTree<String> readBinaryTree(Scanner scan)
    {
        String data = scan.next();
        if(data.equals("null"))
        {
            return null;
        }
        else
        {
            BinaryTree<String> leftTree = readBinaryTree(scan);
            BinaryTree<String> rightTree = readBinaryTree(scan);
            return new BinaryTree<String>(data, leftTree, rightTree);
        }

    }


    //Inner node class
	protected static class Node<E>
    {
        protected E data;
        protected Node<E> left;
        protected Node<E> right;

        public Node()
        {
            this.left = null;
            this.right = null;
        }

        public Node(E data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString()
        {
            if(data == null)
            {
                return null;
            }
            return data.toString();
        }
    }
}
