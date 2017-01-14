package expert.java.common.datastructure.binarytree.traversal;

/**
 * Created by Patrick on 14/1/17.
 Postorder Traversal:

 Algorithm Postorder(tree)
 1. Traverse the left subtree, i.e., call Postorder(left-subtree)
 2. Traverse the right subtree, i.e., call Postorder(right-subtree)
 3. Visit the root.
           1
          / \
         2   3
        / \
       4   5

 输出:   4 5 2 3 1
    */

public class PostOrderTraversal {


    // Root of Binary Tree
    Node root;

    PostOrderTraversal()
    {
        root = null;
    }

    void printPostorder(Node node)
    {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.key + " ");
    }


    private class Node
    {
        int key;
        Node left, right;

        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }
}
