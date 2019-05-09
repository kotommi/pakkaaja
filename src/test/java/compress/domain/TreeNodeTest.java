package compress.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeNodeTest {

    @Test
    public void constAndGetTest() {
        TreeNode node = new TreeNode(5L, (byte) 0);
        assertEquals(5, node.getCount());
        assertTrue(node.isLeaf());
    }
}
