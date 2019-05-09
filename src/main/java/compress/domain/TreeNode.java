package compress.domain;

/**
 * Object reference-style binary tree for Huffman-code generation.
 */
public class TreeNode {

    //the byte this node represents
    private byte id;
    // weight of this node or the nodes below this
    private int count;
    //either both are null or defined
    private TreeNode left;
    private TreeNode right;

    /**
     * Creates new treenode.
     *
     * @param count the amount of id bytes
     * @param id    byte-value of the node
     */
    public TreeNode(int count, byte id) {
        this.id = id;
        this.count = count;
        this.left = null;
        this.right = null;
    }

    public byte getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Gets recursively the amount of leaves in the tree.
     * Used in creating a header for huffmantree.
     *
     * @return Amount of leaves.
     */
    public int getLeafCount() {
        if (this.isLeaf()) {
            return 1;
        }
        return this.left.getLeafCount() + this.right.getLeafCount();
    }

    public void setId(byte newId) {
        this.id = newId;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }


    @Override
    public String toString() {
        String s = "id: " + id + ", count: " + count;
        if (left != null) {
            s = s + "\n" + left.toString();
        }
        if (right != null) {
            s = s + "\n" + right.toString();
        }
        return s;
    }
}
