package compress.encode;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class HuffmanTest {

    // String used
    private byte[] stringBytes = {C, B, B, A, A, A};

    private final static byte A = 97;
    private final static byte B = 98;
    private final static byte C = 99;

    private final static int OFF = 128;

    private final long[] freqs = ArrayUtils.getFreqs(stringBytes);


    @Test
    public void buildnodesTest() {

        final TreeNode[] treeNodes = Huffman.buildNodes(freqs);
        assertEquals(3, treeNodes.length);
        Arrays.sort(treeNodes);

        TreeNode tn1 = treeNodes[0];
        assertEquals(1, tn1.getCount());
        assertEquals(C, tn1.getId());

        TreeNode tn2 = treeNodes[1];
        assertEquals(2, tn2.getCount());
        assertEquals(B, tn2.getId());

        TreeNode tn3 = treeNodes[2];
        assertEquals(3, tn3.getCount());
        assertEquals(A, tn3.getId());

    }

    @Test
    public void buildTreeTest() {
        /* Puun pitäisi näyttää tältä
                 R
                0  1
               A    R
                   0 1
                  B   C
         */
        final TreeNode treeRoot = Huffman.buildTree(Huffman.buildNodes(freqs));
        assertEquals(false, treeRoot.isLeaf());
        assertEquals(A, treeRoot.getLeft().getId());
        assertEquals(B, treeRoot.getRight().getLeft().getId());
        assertEquals(C, treeRoot.getRight().getRight().getId());
    }

    @Test
    public void buildLookupTable() {
        /*
        A = 0
        B = 10
        C = 11
         */
        final Codeword[] codewords = Huffman.buildLookupTable(Huffman.buildTree(Huffman.buildNodes(freqs)));
        assertEquals("0", codewords[A + OFF].toString());
        assertEquals("10", codewords[B + OFF].toString());
        assertEquals("11", codewords[C + OFF].toString());

        Codeword cwC = codewords[C + OFF];
        assertTrue(cwC.get(0));
        assertTrue(cwC.get(1));
    }

    @Test
    public void encode() {
        /*
           cbbaaa -> 11 10 10 0 0 0 -> 11101000 00000000
           int conversion 00000000 -> -128
           11101000 = 232 - 128 = 104
           00000000 = 0 - 128 = -128
           first byte  = how many bits of the last byte are in use
         */

        final Codeword[] codewords = Huffman.buildLookupTable(Huffman.buildTree(Huffman.buildNodes(freqs)));
        byte[] encodedBytes = Huffman.encodeData(stringBytes, codewords);

        final byte usedBits = encodedBytes[0];
        final byte fst = 104; // 1110 1000
        final byte snd = -128; // 0000 0000

        assertEquals(1, usedBits);
        assertEquals(fst, encodedBytes[1]);
        assertEquals(snd, encodedBytes[2]);
    }

    @Test
    public void decode() {
        TreeNode treeRoot = Huffman.buildTree(Huffman.buildNodes(freqs));
        final Codeword[] codewords = Huffman.buildLookupTable(treeRoot);
        final byte[] encodedBytes = Huffman.encodeData(stringBytes, codewords);
        final byte[] decoded = Huffman.decode(encodedBytes, treeRoot);
        assertArrayEquals(stringBytes, decoded);
    }
}
