package compress.utils;

import compress.domain.TreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MinHeapTest {
    private TreeNode[] nodes;
    private MinHeap heap;

    @Before
    public void setup() {
        final int amount = 10;
        this.nodes = new TreeNode[amount];
        for (int i = 0; i < amount; i++) {
            nodes[i] = new TreeNode((long) amount - i, (byte) i);
        }
        heap = new MinHeap();
        heap.addAll(nodes);
    }

    @Test
    public void newHeapIsHeapified() {
        int expected = 1;
        while (heap.size() > 0) {
            TreeNode polled = heap.poll();
            assertEquals(expected, polled.getCount());
            expected++;
        }
        assertEquals(0, heap.size());
    }

    @Test
    public void addingTest() {
        //smallest value goes to the front
        long smallvalue = -1;
        heap.add(new TreeNode(smallvalue, Byte.MIN_VALUE));
        TreeNode polled = heap.poll();
        assertEquals(smallvalue, polled.getCount());
        assertEquals(Byte.MIN_VALUE, polled.getId());
        //biggest value goes to the back
        long bigvalue = 999;
        heap.add(new TreeNode(bigvalue, Byte.MAX_VALUE));
        while (heap.size() > 0) {
            polled = heap.poll();
        }
        assertEquals(bigvalue, polled.getCount());
        assertEquals(Byte.MAX_VALUE, polled.getId());
        //empty heap returns null
        assertNull(heap.poll());
    }


}
