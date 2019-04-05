package compress.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeTest {

    @Test
    public void constAndGetTest() {
        TreeNode node = new TreeNode(5L, (byte) 0);
        assertEquals(0, node.getDepth());
        assertEquals(5, node.getCount());
        assertTrue(node.isLeaf());
    }

    @Test
    public void depthTest() {
        final byte b = 0;
        TreeNode root = new TreeNode(0, b);
        TreeNode child = new TreeNode(0, b);
        //the tree doesn't handle all nulls
        //because a node has either 0 or 2 children
        root.setLeft(child);
        root.setRight(child);
        assertEquals(1, root.getDepth());
        assertFalse(root.isLeaf());
        assertEquals(1, root.getDepth());
        //don't recurse infinitely
        TreeNode child2 = new TreeNode(0, b);
        root.getLeft().setRight(child2);
        root.getLeft().setLeft(child2);
        assertEquals(2, root.getDepth());
    }

    @Test
    public void compareToTest() {
        // this is used for the min-heap to compare counts
        // so the order is rising since we're building
        // the tree from bottom-up
        final byte b = 0;
        TreeNode node1 = new TreeNode(3, b);
        TreeNode node2 = new TreeNode(15, b);
        int diff = node1.compareTo(node2);
        assertTrue(diff < 0);
        diff = node1.compareTo(node1);
        assertEquals(0, diff);
    }


}
