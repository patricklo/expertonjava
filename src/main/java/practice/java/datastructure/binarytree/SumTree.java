package practice.java.datastructure.binarytree;

/**
 * Created by patrick on 6/17/2015.
 */
public class SumTree {

    public static boolean isSumTree(Node root){

        int leftSum, rightSum;

        if(root == null && root.right == null  && root.left == null){
            return true;
        }

        leftSum = sum(root.left);
        rightSum = sum(root.right);

        if((root.data==(leftSum+rightSum)) && isSumTree(root.left) && isSumTree(root.right)){
            return true;
        }
        return false;
    }

    public static int sum(Node tree){
        if(tree == null){
            return 0;
        }
        return sum(tree.left)+ tree.data + sum(tree.right);
    }

    public static void main(String[] args){
        Node root = newNode(1);
        root.left = newNode(2);
        root.right = newNode(3);
        root.left.left = newNode(4);
        root.left.left.left = newNode(9);
        root.left.right = newNode(5);

       System.out.println(isSumTree(root));


        Node root2 = newNode(26);
        root2.left         = newNode(10);
        root2.right        = newNode(3);
        root2.left.left   = newNode(4);
        root2.left.right  = newNode(6);
        root2.right.right = newNode(3);
       if(isSumTree(root2)){
           System.out.println("the given tree is a sumtree");
       }else{
           System.out.println("the given tree is not a sumtree");
       }

    }

    public static Node newNode(int data) {
        Node temp = new Node(data);
        temp.left = null;
        temp.right = null;
        return temp;
    }
}
