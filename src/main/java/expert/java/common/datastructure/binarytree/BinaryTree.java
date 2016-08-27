package expert.java.common.datastructure.binarytree;

/**
 * Created by Patrick on 12/30/2014.
 */
public class BinaryTree {
    // Root node pointer. Will be null for an empty tree.
    private Node root;

    /**
     * Creates an empty binary tree -- a null root pointer.
     */
    public void BinaryTree() {
        root = null;
    }

    /**
     * Returns true if the given target is in the binary tree. Uses a recursive
     * helper.
     */
    public boolean lookup(int data) {
        return (lookup(root, data));
    }

    /**
     * Recursive lookup -- given a node, recur down searching for the given
     * data.
     */
    private boolean lookup(Node node, int data) {
        if (node == null) {
            return (false);
        }

        if (data == node.data) {
            return (true);
        } else if (data < node.data) {
            return lookup(node.left, data);
        } else {
            return (lookup(node.right, data));
        }
    }

    /**
     * Inserts the given data into the binary tree. Uses a recursive helper.
     */
    public void insert(int data) {
        root = insert(root, data);
    }

    /**
     * Recursive insert -- given a node pointer, recur down and insert the given
     * data into the tree. Returns the new node pointer (the standard way to
     * communicate a changed pointer back to the caller).
     */
    private Node insert(Node node, int data) {
        if (node == null) {
            node = new Node(data);
        } else {
            if (data <= node.data) {
                node.left = insert(node.left, data);
            } else {
                node.right = insert(node.right, data);
            }
        }

        return (node); // in any case, return the new pointer to the caller
    }

    // //////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
	 * print the Node value in "inorder" order uses a recursive helper to do the
	 * traversal
	 */
    public void printInorder() {
        printInorder(root);
        System.out.println();
    }

    private void printInorder(Node root) {
        if (root == null) {
            return;
        }
        // left , node itself , right
        printInorder(root.left);
        System.out.print(root.data + "  ");
        printInorder(root.right);

    }

    // /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Prints the node values in the "postorder" order. Uses a recursive helper
     * to do the traversal.
     */
    public void printPostorder() {
        printPostorder(root);
        System.out.println();
    }

    public void printPostorder(Node node) {
        if (node == null)
            return;

        // first recur on both subtrees
        printPostorder(node.left);
        printPostorder(node.right);

        // then deal with the node
        System.out.print(node.data + "  ");
    }

    public void printPreorder() {
        printPreorder(root);
        System.out.println();
    }

    private void printPreorder(Node node) {
        // TODO Auto-generated method stub
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);

    }

    // ///////////////////////////////////////////////////////////////////////////////////////////////////////

    // ////////////////////建树//////////////////////////////////////

    /**
     * Build 123 using three pointer variables.
     */
    public void build123a() {
        root = new Node(3);
        Node lChlid = new Node(1);
        Node rChild = new Node(3);
        root.left = lChlid;
        root.right = rChild;

    }

    /**
     * Build 123 using only one pointer variable.
     */
    public void build123b() {
        root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
    }

    /**
     * Build 123 by calling insert() three times. Note that the '2' must be
     * inserted first.
     */
    public void build123c() {
        root = null;
        root = insert(root, 2);
        root = insert(root, 1);
        root = insert(root, 3);
        root = insert(root, 5);
        root = insert(root, 11);
        root = insert(root, 4);
        root = insert(root, 23);
        root = insert(root, 11);
        root = insert(root, 22);

    }

    /**
     * Returns the number of nodes in the tree. Uses a recursive helper that
     * recurs down the tree and counts the nodes.
     */
    public int size() {
        return (size(root));
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        } else {
            return (size(root.left) + size(root.right) + 1);
        }
    }

    // //////////////////////////////max depth/////////////////////////////////

    /**
     * Returns the max root-to-leaf depth of the tree. Uses a recursive helper
     * that recurs down to find the max depth.
     */
    public int maxDepth() {
        return (maxDepth(root));
    }

    private int maxDepth(Node root) {
        if (root == null) {
            return 0;
        } else {
            int lMaxDepth = maxDepth(root.left);
            int rMaxDepth = maxDepth(root.right);
            // use the larger + 1
            return Math.max(lMaxDepth, rMaxDepth) + 1;
        }
    }

    // ////////////////////////////////////////////////

    /**
     * Finds the min value in a non-empty binary search tree.
     */
    public int minValue() {
        return (minValue(root));
    }

    // 最小值在最左子树最左的孩子
    private int minValue(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;

    }

    /**
     * Finds the min value in a non-empty binary search tree.
     */
    public int maxValue() {
        return (maxValue(root));
    }

    // 最大值在最右子树最右的孩子
    private int maxValue(Node root) {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;

    }

    // ///////////////////////

    // /////////////////////////////////////////////////////////////////

    /**
     * Given a binary tree, prints out all of its root-to-leaf paths, one per
     * line. Uses a recursive helper to do the work.
     */
    public void printPaths() {
        int[] path = new int[1000];
        printPaths(root, path, 0);
    }

    /**
     * Recursive printPaths helper -- given a node, and an array containing the
     * path from the root node up to but not including this node, prints out all
     * the root-leaf paths.
     */
    private void printPaths(Node node, int[] path, int pathLen) {
        if (node == null)
            return;

        // append this node to the path array
        path[pathLen] = node.data;
        pathLen++;

        // it's a leaf, so print the path that led to here
        if (node.left == null && node.right == null) {
            printArray(path, pathLen);
        } else {
            // otherwise try both subtrees
            printPaths(node.left, path, pathLen);
            printPaths(node.right, path, pathLen);
        }
    }

    /**
     * Utility that prints ints from an array on one line.
     */
    private void printArray(int[] ints, int len) {
        for (int i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
    }

    // ////////////////////////////////////////////////////////////////

    /**
     * Changes the tree into its mirror image.
     * <p/>
     * So the tree... 4 / / 2 5 / / 1 3
     * <p/>
     * is changed to... 4 / / 5 2 / / 3 1
     * <p/>
     * Uses a recursive helper that recurs over the tree, swapping the
     * left/right pointers.
     */
    public void mirror() {
        mirror(root);
    }

    private void mirror(Node node) {
        if (node != null) {
            // do the sub-trees
            mirror(node.left);
            mirror(node.right);

            // swap the left/right pointers
            Node temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }

    // ///////////////////////////////////////////////////////

    /**
     * Changes the tree by inserting a duplicate node on each nodes's .left.
     * <p/>
     * <p/>
     * So the tree... 2 / / 1 3
     * <p/>
     * Is changed to... 2 / / 2 3 / / 1 3 / 1
     * <p/>
     * Uses a recursive helper to recur over the tree and insert the duplicates.
     */
    public void doubleTree() {
        doubleTree(root);
    }

    private void doubleTree(Node node) {
        Node oldLeft;

        if (node == null)
            return;

        // do the subtrees
        doubleTree(node.left);
        doubleTree(node.right);

        // duplicate this node to its left
        oldLeft = node.left;
        node.left = new Node(node.data);
        node.left.left = oldLeft;
    }

    // ///////////////////////////////////////////////////////////////////
	/*
	 * Compares the receiver to another tree to see if they are structurally
	 * identical.
	 */
    public boolean sameTree(BinaryTree other) {
        return (sameTree(root, other.root));
    }

    /**
     * Recursive helper -- recurs down two trees in parallel, checking to see if
     * they are identical.
     */
    boolean sameTree(Node a, Node b) {
        // 1. both empty.true
        if (a == null && b == null)
            return (true);

            // 2. both non-empty.compare them
        else if (a != null && b != null) {
            return (a.data == b.data && sameTree(a.left, b.left) && sameTree(
                    a.right, b.right));
        }
        // 3. one empty, one not.false
        else
            return (false);
    }

    // ////////////////////////////////////////////////////////////////////////

    /**
     * For the key values 1...numKeys, how many structurally unique binary
     * search trees are possible that store those keys? Strategy: consider that
     * each value could be the root. Recursively find the size of the left and
     * right subtrees.
     */
    public static int countTrees(int numKeys) {
        if (numKeys <= 1) {
            return (1);
        } else {
            // there will be one value at the root, with whatever remains
            // on the left and right each forming their own subtrees.
            // Iterate through all the values that could be the root...
            int sum = 0;
            int left, right, root;

            for (root = 1; root <= numKeys; root++) {
                left = countTrees(root - 1);
                right = countTrees(numKeys - root);

                // number of possible trees with this root == left*right
                sum += left * right;
            }
            return (sum);
        }
    }

    // ////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests if a tree meets the conditions to be a binary search tree (BST).
     */
    public boolean isBST() {
        return (isBST(root));
    }

    /**
     * Recursive helper -- checks if a tree is a BST using minValue() and
     * maxValue() (not efficient).
     */
    private boolean isBST(Node node) {
        if (node == null)
            return (true);
        // do the subtrees contain values that do not
        // agree with the node?
        if (node.left != null && maxValue(node.left) > node.data)
            return (false);
        if (node.right != null && minValue(node.right) <= node.data)
            return (false);

        // check that the subtrees themselves are ok
        return (isBST(node.left) && isBST(node.right));
    }

    // ////////////////////////////////////////////////14. isBST2() Solution
    // (Java)

    /**
     * Tests if a tree meets the conditions to be a binary search tree (BST).
     * Uses the efficient recursive helper.
     */
    public boolean isBST2() {
        return (isBST2(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    /**
     * Efficient BST helper -- Given a node, and min and max values, recurs down
     * the tree to verify that it is a BST, and that all its nodes are within
     * the min..max range. Works in O(n) time -- visits each node only once.
     */
    private boolean isBST2(Node node, int min, int max) {
        if (node == null) {
            return (true);
        } else {
            // left should be in range min...node.data
            boolean leftOk = isBST2(node.left, min, node.data);

            // if the left is not ok, bail out
            if (!leftOk)
                return (false);

            // right should be in range node.data+1..max
            boolean rightOk = isBST2(node.right, node.data + 1, max);

            return (rightOk);
        }
    }

    public boolean contain(int data) {
        return contain(root, data);
    }

    private boolean contain(Node node, int data) {
        if (node == null) {
            return false;
        }
        Node current = node;
        //用迭代
        while (current != null) {
            if (current.data == data) {
                return true;
            } else if (data <= current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            }
        }
        return false;
    }

    public int getLevelUnit(Node node, int data, int level) {
        if (node == null) {
            return 0;
        }

        if (node.data == data) {
            return level;
        }

        int downLevel = getLevelUnit(node.left, data, level + 1);
        if (downLevel != 0) {
            return downLevel;
        }

        downLevel = getLevelUnit(node.right, data, level + 1);
        if (downLevel != 0) {
            return downLevel;
        } else {
            return 0;
        }
    }

    public int getLevel(Node node, int data) {
        return getLevelUnit(node, data, 1);
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        int x;
        Node root = new Node(3);
        root.left = newNode(2);
        root.right = newNode(5);
        root.left.left = newNode(1);
        root.left.right = newNode(4);

        for (x = 1; x <= 5; x++) {
            int level = binaryTree.getLevel(root, x);
            if (level > 0)
                System.out.printf(" Level of %d is %d\n", x, binaryTree.getLevel(root, x));
            else
                System.out.printf(" %d is not present in tree \n", x);
        }

        binaryTree.printAncestors(root,4);

    }

    public static Node newNode(int data) {
        Node temp = new Node(data);
        temp.left = null;
        temp.right = null;
        return temp;
    }

    public boolean printAncestors(Node root, int target) {
        if (root == null) {
            return false;
        }
        if (root.data == target) {
            return true;
        }

        if(printAncestors(root.left,target) || printAncestors(root.right,target)){
            System.out.println("root:"+root.data);
            return true;
        }
        return false;
    }

}

