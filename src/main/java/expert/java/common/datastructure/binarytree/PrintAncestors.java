package expert.java.common.datastructure.binarytree;

/**
 * Created by patrick on 4/29/15.
 */
public class PrintAncestors {

    public static void main(String[] args){
        /* Construct the following binary tree
                      1
                    /   \
                  2      3
                /  \
              4     5
             /
            7
          */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right =  new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.left.left = new Node(7);

        printTheAncestors(root, 7);

    }


    public static boolean printTheAncestors(Node root, int target){
        if(root == null){
            return false;
        }

        if(root.data==target){
            return true;
        }

        if(printTheAncestors(root.left,target) || printTheAncestors(root.right,target)){
            System.out.println(root.data);
            return true;
        }
        return false;
    }
}
