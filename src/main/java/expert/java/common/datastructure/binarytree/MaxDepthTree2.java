package expert.java.common.datastructure.binarytree;

import expert.java.common.datastructure.binarytree.nodes.TreeNode;

/**
 * Created by Patrick on 14/1/17.
 */
public class MaxDepthTree2 {
    public static void main(String[] args){

    }

    public int getMaxDepth(TreeNode treeNode){
        if(treeNode == null){
            return 0;
        }else{
            int leftDepth = getMaxDepth(treeNode.left);
            int rightDepth = getMaxDepth(treeNode.right);
            return (leftDepth>rightDepth?leftDepth:rightDepth)+1;
        }
    }


    public static TreeNode getNode(int data){
        TreeNode treeNode = new TreeNode(data);
        treeNode.right = null;
        treeNode.left = null;
        return treeNode;
    }
}
