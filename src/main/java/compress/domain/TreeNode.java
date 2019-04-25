package compress.domain;

/**
 * Object reference-style binary tree for Huffman-code generation.
 */
public class TreeNode implements Comparable<TreeNode> {

    //the byte this node represents
    private byte id;
    // weight of this node or the nodes below this
    private long count;
    //either both are null or defined
    private TreeNode left;
    private TreeNode right;

    public TreeNode(long count, byte id) {
        this.id = id;
        this.count = count;
        this.left = null;
        this.right = null;
    }

    public byte getId() {
        return id;
    }

    public long getCount() {
        return count;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    /**
     * Recursive depth-method.
     *
     * @return depth of tree. root = depth 0.
     */
    public int getDepth() {
        if (this.isLeaf()) {
            return 0;
        }
        return 1 + Math.max(this.left.getDepth(), this.right.getDepth());
    }

    public int getTotalCount() {
        if (this.isLeaf()) {
            return (int) this.count;
        }
        return this.left.getTotalCount() + this.right.getTotalCount();
    }

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

    @Override
    public int compareTo(TreeNode treeNode) {
        return Long.compare(this.count, treeNode.count);
    }
}
