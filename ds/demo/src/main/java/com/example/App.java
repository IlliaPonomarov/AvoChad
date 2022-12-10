package com.example;

import com.example.BinaryTree.Node;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BinaryTree tree = new BinaryTree();

        tree.root = new BinaryTree.Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);

        tree.printTree();

    }
}
