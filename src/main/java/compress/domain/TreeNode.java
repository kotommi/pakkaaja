package compress.domain;

import java.util.Arrays;

public class TreeNode implements Comparable<TreeNode> {
    private byte[] id;
    private long count;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(long count, byte... id) {
        this.id = id;
        this.count = count;
        this.left = null;
        this.right = null;
    }

    public byte[] getId() {
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

    @Override
    public String toString() {
        String s = "bytes:" + Arrays.toString(id) + ", c: " + count;
        if (left != null) {
            s = s + "\n" + left.toString();
        }
        if (right != null) {
            s = s + "\n" + right.toString();
        }
        return s;
    }

    //ascending order
    @Override
    public int compareTo(TreeNode treeNode) {
        return Long.compare(this.count, treeNode.count);
    }
}
