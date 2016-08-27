package expert.java.common.datastructure.binarytree;

/**
 * Created by patrick on 6/27/2015.
 */
public class SameTree {
    public static void main(String[] args){


        Node root1 = newNode(1);
        root1.left = newNode(2);
        root1.right = newNode(3);
        root1.left.left = newNode(4);
        root1.left.right = newNode(5);


        Node root2 = newNode(1);
        root2.left = newNode(2);
        root2.right = newNode(3);
        root2.left.left = newNode(4);
        root2.left.right = newNode(5);

        SameTree sameTree = new SameTree();

        System.out.println(sameTree.isSameTree(root1,root2));
    }

    public boolean isSameTree(Node root1, Node root2){

        if(root1 == null && root2 == null){
            return true;
        }else if(root1.data == root2.data && isSameTree(root1.left,root2.left) && isSameTree(root1.right,root2.right)){
            return true;
        }else{
            return false;
        }
    }

    public static Node newNode(int data) {
        Node temp = new Node(data);
        temp.left = null;
        temp.right = null;
        return temp;
    }
}
