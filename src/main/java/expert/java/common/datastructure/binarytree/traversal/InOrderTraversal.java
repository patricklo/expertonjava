package expert.java.common.datastructure.binarytree.traversal;

/**
 * Created by Patrick on 14/1/17.
 * Inorder Traversal:

 Algorithm Inorder(tree)
 1. Traverse the left subtree, i.e., call Inorder(left-subtree)
 2. Visit the root.
 3. Traverse the right subtree, i.e., call Inorder(right-subtree)
 Uses of Inorder
 In case of binary search trees (BST), Inorder traversal gives nodes in non-decreasing order. To get nodes of BST in non-increasing order, a variation of Inorder traversal where Inorder itraversal s reversed, can be used.
 Example: Inorder traversal for the above given figure is 4 2 5 1 3.

 中序遍历:
 特点:
 1.首先遍历左子数结点
 2.然后遍历根结点
 3.最后遍历右子数据结点

 如:

           1
          / \
         2   3
        / \
       4   5


 输出结点: 42513
    */

public class InOrderTraversal {
}
