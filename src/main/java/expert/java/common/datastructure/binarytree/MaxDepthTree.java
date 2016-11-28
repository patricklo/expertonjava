package expert.java.common.datastructure.binarytree;

/**
 * Created by patrick on 6/17/2015.
 */
public class MaxDepthTree {
    public static void main(String [] args){
        //MaxDepthTree maxDepthtree = new MaxDepthTree();
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.left.left = newNode(9);
        root.left.right = newNode(5);

        System.out.println("maxDepth= "+ maxDepthTree(root));


    }

    public static int maxDepthTree(Node root){
        if( root == null){
            return 0;
        }else{
            int lDepth = maxDepthTree(root.left);
            int rDepth = maxDepthTree(root.right);
            return (lDepth>rDepth?lDepth:rDepth) +1 ;
        }

    }


    public static Node newNode(int data) {
        Node temp = new Node(data);
        temp.left = null;
        temp.right = null;
        return temp;
    }

}
