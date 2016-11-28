package expert.java.common.datastructure.binarytree;

/**
 * Created by patrick on 12/30/14.
 */
public class MirrorTree {

    /* Change a tree so that the roles of the  left and
        right pointers are swapped at every node.

     So the tree...
           4
          / \
         2   5
        / \
       1   3

     is changed to...
           4
          / \
         5   2
            / \
           3   1
    */
    public void mirror(Node node) {
        if (node == null)
            return;
        else {
            Node temp;

            /* do the subtrees */
            mirror(node.left);
            mirror(node.right);

            /* swap the pointers in this node */
            temp = node.left;
            node.left    = node.right;
            node.right = temp;
        }
    }


    /* Helper function to test mirror(). Given a binary
       search tree, print out its data elements in
       increasing sorted order.*/
    public void inOrder(Node node) {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.println("data="+node.data);

        inOrder(node.right);
    }


    /* Driver program to test mirror() */
    public static void main(String[] args) {
        MirrorTree mirrorTree = new MirrorTree();
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.right = newNode(5);

        /* Print inorder traversal of the input tree */
        System.out.println("\n Inorder traversal of the constructed tree is \n");
        mirrorTree.inOrder(root);

        /* Convert tree to its mirror */
        mirrorTree.mirror(root);

        /* Print inorder traversal of the mirror tree */
        System.out.println("\n Inorder traversal of the mirror tree is \n");
        mirrorTree.inOrder(root);

    }


    public static Node newNode(int data) {
        Node temp = new Node(data);
        temp.left = null;
        temp.right = null;
        return temp;
    }
}
