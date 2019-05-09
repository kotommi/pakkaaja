package compress.encode;

import compress.domain.HeaderReader;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HufHeaderTest {

    private final byte A = 97;
    private final byte B = 98;
    private final byte C = 99;
    private byte[] stringBytes = {C, B, B, A, A, A};
    private TreeNode originalRoot;


    private final int ConversionOffset = 129;

    @Before
    public void setUp() {

    }

    @Test
    public void encodeTree() {
        byte[] bytes = buildHeader(stringBytes);
        /*
          Expected: 3 leaves so bytes[0] == 3 (- 128 - 1)
          Then 0 means parent node and 1 means leaf followed by value
          The expected result is handcrafted.
         */
        final byte[] expected = {(3 - ConversionOffset), 0, 1, 97, 0, 1, 98, 1, 99};
        assertEquals(3, bytes[0] + ConversionOffset);
        assertArrayEquals(expected, bytes);
    }

    @Test
    public void encodeHeaderValueDoesntOverflow() {
        final byte[] originalBytes = new byte[256];
        byte b = Byte.MIN_VALUE;
        for (int i = 0; i < 256; i++) {
            originalBytes[i] = b;
            b++;
        }
        byte[] bytes = buildHeader(originalBytes);
        assertEquals(256, bytes[0] + ConversionOffset);
    }

    @Test
    public void decodeComplexTree() {
        final byte[] originalBytes = new byte[256];
        byte b = Byte.MIN_VALUE;
        for (int i = 0; i < 256; i++) {
            originalBytes[i] = b;
            b++;
        }
        byte[] bytes = buildHeader(originalBytes);
        HeaderReader headerReader = new HeaderReader(bytes);
        TreeNode decodedRoot = HufHeader.decodeTree(headerReader);
        treeWalkTest(originalRoot, decodedRoot);
        assertEquals(256, decodedRoot.getLeafCount());
    }

    @Test
    public void decodeTest() {
        byte[] bytes = buildHeader(stringBytes);
        HeaderReader headerReader = new HeaderReader(bytes);
        TreeNode treeRoot = HufHeader.decodeTree(headerReader);
        treeWalkTest(originalRoot, treeRoot);

    }

    private void treeWalkTest(TreeNode original, TreeNode decoded) {
        if (original.isLeaf()) {
            assertEquals(original.getId(), decoded.getId());
        } else {
            treeWalkTest(original.getLeft(), decoded.getLeft());
            treeWalkTest(original.getRight(), decoded.getRight());
        }
    }

    private byte[] buildHeader(byte[] originalBytes) {
        final int[] freqs = ArrayUtils.getFreqs(originalBytes);
        TreeNode[] treeNodes = Huffman.buildNodes(freqs);
        TreeNode root = Huffman.buildTree(treeNodes);
        this.originalRoot = root;

        return HufHeader.encodeTree(root);
    }
}