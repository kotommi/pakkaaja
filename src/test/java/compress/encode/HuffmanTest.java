package compress.encode;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class HuffmanTest {

    // String used
    byte[] stringBytes = {C, B, B, A, A, A};

    final static byte A = 97;
    final static byte B = 98;
    final static byte C = 99;

    final static int OFF = 128;

    final long[] freqs = ArrayUtils.getFreqs(stringBytes);


    @Test
    public void buildnodesTest() {

        final TreeNode[] treeNodes = Huffman.buildNodes(freqs);
        assertEquals(3, treeNodes.length);
        Arrays.sort(treeNodes);

        TreeNode tn1 = treeNodes[0];
        assertEquals(1, tn1.getCount());
        assertEquals(C, tn1.getId()[0]);

        TreeNode tn2 = treeNodes[1];
        assertEquals(2, tn2.getCount());
        assertEquals(B, tn2.getId()[0]);

        TreeNode tn3 = treeNodes[2];
        assertEquals(3, tn3.getCount());
        assertEquals(A, tn3.getId()[0]);

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
        assertEquals(A, treeRoot.getLeft().getId()[0]);
        assertEquals(B, treeRoot.getRight().getLeft().getId()[0]);
        assertEquals(C, treeRoot.getRight().getRight().getId()[0]);
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
        assertEquals(true, cwC.getBits().get(0));
        assertEquals(true, cwC.getBits().get(1));
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
        byte[] encodedBytes = Huffman.encode(stringBytes, codewords);

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
        final byte[] encodedBytes = Huffman.encode(stringBytes, codewords);
        final byte[] decoded = Huffman.decode(encodedBytes, treeRoot);
        assertArrayEquals(stringBytes, decoded);
    }
}
