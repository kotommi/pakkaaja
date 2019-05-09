package compress.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeNodeTest {

    final byte b = 0;

    @Test
    public void constAndGetTest() {
        TreeNode node = new TreeNode(5, b);
        assertEquals(5, node.getCount());
        assertTrue(node.isLeaf());
    }

    @Test
    public void getLeafCountTest() {
        final TreeNode root = new TreeNode(1, b);
        assertEquals(1, root.getLeafCount());
        final TreeNode node = new TreeNode(0, b);
        root.setLeft(node);
        root.setRight(node);
        assertEquals(2, root.getLeafCount());
    }
}
