package expert.java.common.datastructure.binarytree;

/**
 * Created by patrick on 6/17/2015.
 */
public class IdenticalTree {

    public static boolean isSameTree(Node tree1, Node tree2){

        if(tree1 == null &&tree2 == null){
            return true;
        }

        if(tree1 == null || tree2 == null){
            return false;
        }

        return (tree1.data == tree2.data) && (isSameTree(tree1.left,tree2.left)) && (isSameTree(tree1.right,tree2.right));
    }


}
