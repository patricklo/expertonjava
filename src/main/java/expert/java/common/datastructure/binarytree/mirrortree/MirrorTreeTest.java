package expert.java.common.datastructure.binarytree.mirrortree;

/**
 * Created by patrick on 1/29/15.
 */
public class MirrorTreeTest {

    /* A binary tree node has data, pointer to left child
   and a pointer to right child */


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
    private static void mirror(Node node)
    {
        if (node== null)
            return;
        else
        {
            Node temp = new Node();

            /* do the subtrees */
            mirror(node.left);
            mirror(node.right);

           /* swap the pointers in this node */
            temp = node.left;
            node.left  = node.right;
            node.right = temp;
        }
    }



    /* Driver program to test mirror() */
    public static void main(String[] args)
    {
        Node root = newNode(1);
        root.left        = newNode(2);
        root.right       = newNode(3);
        root.left.left  =  newNode(4);
        root.left.right =  newNode(5);

  /* Print inorder traversal of the input tree */
        System.out.printf("\n Inorder traversal of the constructed tree is \n");
        inOrder(root);

  /* Convert tree to its mirror */
        mirror(root);

  /* Print inorder traversal of the mirror tree */
        System.out.printf("\n Inorder traversal of the mirror tree is \n");
        inOrder(root);

       // getchar();
       // return 0;
    }


    /* Helper function that allocates a new node with the
       given data and NULL left and right pointers. */
    private static Node newNode(int data)

    {
        Node node = new Node();

        node.data = data;
        node.left = null;
        node.right = null;

        return(node);
    }



    /* Helper function to test mirror(). Given a binary
       search tree, print out its data elements in
       increasing sorted order.*/
    private static void inOrder(Node node)
    {
        if (node == null)
            return;

        inOrder(node.left);
        System.out.printf("%d ", node.data);

        inOrder(node.right);
    }


}



