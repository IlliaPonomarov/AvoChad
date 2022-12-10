package com.example;

public class BinaryTree {
    
    static class Node {


        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

    }

    public Node root;

    public BinaryTree() {
        this.root = null;
    }


    public Node removeNode(Node node, int data) {
        
        if (node == null)
            return null;
        
        if (data < node.getData())
            node.left = removeNode(node.left, data);
        else if (data > node.getData())
            node.right = removeNode(node.right, data);
        else {
            if (node.left == null && node.right == null)
                return null;
            else if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                int min = findMin(node.right);
                node.data = min;
                node.right = removeNode(node.right, min);
            }
        }

        return node;
    }

    public Node insert(Node node, int data) {
        if (node == null)
            return new Node(data);
        else {
            if (data <= node.getData())
                node.left = insert(node.left, data);
            else
                node.right = insert(node.right, data);
        }

        return node;
    }

    public void insert(int data) {
        this.root = insert(this.root, data);
    }

    public void printTree(Node node) {
        if (node == null)
            return;
        
        printTree(node.getLeft());
        System.out.println(node.getData());
        printTree(node.getRight());
    }

    public void printTree() {
        printTree(this.root);
    }


}

