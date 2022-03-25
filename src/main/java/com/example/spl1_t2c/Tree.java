package com.example.spl1_t2c;
import java.util.*;

public class Tree extends Type_Var{

    static class Node
    {
        String key;
        Vector<Node > child;
    };

    // Utility function to create a new tree node
    static Node newNode(String key)
    {
        Node temp = new Node();
        temp.key = key;
        temp.child = new Vector<Node>();
        return temp;
    }

    // Function that will return the depth
// of the tree
    static void LevelOrderTraversal(Node root)
    {
        if (root == null)
            return;
        Queue<Node > q = new LinkedList<>(); // Create a queue
        q.add(root); // Enqueue root
        while (!q.isEmpty())
        {
            int n = q.size();
            while (n > 0)
            {
                Node p = q.peek();
                q.remove();
                System.out.print(p.key + " ");
                for (int i = 0; i < p.child.size(); i++)
                    q.add(p.child.get(i));
                n--;
            }
            System.out.println();
        }
    }

    static int depthOfTree(Node ptr)
    {
        if (ptr == null)
            return 0;
        int maxdepth = 0;
        for (Node it : ptr.child)
            maxdepth = Math.max(maxdepth,
                    depthOfTree(it));

        return maxdepth + 1 ;
    }

    public static void main(String[] args)
    {
            Node root = newNode("Start");
            (root.child).add(newNode("Var"));
            (root.child).add(newNode("If"));
            (root.child).add(newNode("Loop"));
            (root.child).add(newNode("Enter"));
            (root.child.get(0).child).add(newNode("Variable Type & Name Declare"));
            (root.child.get(1).child).add(newNode("Condtion Check"));
            (root.child.get(1).child).add(newNode("else if"));
            (root.child.get(1).child).add(newNode("else"));
            (root.child.get(2).child).add(newNode("for"));
            (root.child.get(2).child).add(newNode("while"));
            (root.child.get(2).child).add(newNode("do-while"));
            (root.child.get(0).child.get(0).child).add(newNode("Variable Type Declare"));
            (root.child.get(0).child.get(0).child.get(0).child).add(newNode("Value Assign"));
            (root.child.get(0).child.get(0).child.get(0).child).add(newNode("Operation"));
            (root.child.get(0).child.get(0).child.get(0).child).add(newNode("Return"));

            System.out.println("Level order traversal " +
                    "Before Mirroring ");
            LevelOrderTraversal(root);
        System.out.print(depthOfTree(root) + "\n");
        }
    }
