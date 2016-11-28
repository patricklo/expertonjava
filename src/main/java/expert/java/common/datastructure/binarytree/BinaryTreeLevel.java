package expert.java.common.datastructure.binarytree;

/**
 * Created by patrick on 4/29/15.
 */
public class BinaryTreeLevel {

    public static int getNodeLevel(Node root,int target,int level){
        if(root == null){
            System.out.println("the tree is empty");
            return 0;
        }
        if(root.data == target){
            return level;
        }

        int currentLevel = getNodeLevel(root.left,target,level+1);
        if(currentLevel != 0 ){
            return currentLevel;
        }
        currentLevel = getNodeLevel(root.right,target,level+1);

        if(currentLevel != 0){
            return currentLevel;
        }
        return level;
    }



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
        root.left.left.left.left = new Node(9);

        int level = getNodeLevel(root, 9,0);

        System.out.println("target level = "+level);
    }


}
