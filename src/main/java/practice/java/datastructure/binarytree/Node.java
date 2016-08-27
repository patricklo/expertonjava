package practice.java.datastructure.binarytree;

/**
 a tree node structure
 */
public class Node {
    Node left;
    Node right;
    int data;

    Node(int newData) {
        left = null;
        right = null;
        data = newData;
    }
}
