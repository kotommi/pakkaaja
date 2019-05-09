package compress.encode;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

/**
 * Wrapper class for encoding functions.
 */
public class Encode {

    public static byte[] encodeHuffman(byte[] fileBytes) {
        //build tree
        final int[] freqs = ArrayUtils.getFreqs(fileBytes);
        final TreeNode[] treeNodes = Huffman.buildNodes(freqs);
        final TreeNode treeRoot = Huffman.buildTree(treeNodes);
        // encode header
        final byte[] headerBytes = HufHeader.encodeTree(treeRoot);
        // encode data
        final Codeword[] lookupTable = Huffman.buildLookupTable(treeRoot);
        final byte[] dataBytes = Huffman.encodeData(fileBytes, lookupTable);
        // combine
        final byte[] encodedBytes = ArrayUtils.concat(headerBytes, dataBytes);

        return encodedBytes;
    }

    public static byte[] encodeLZW(byte[] fileBytes) {
        return LZW.encode(fileBytes);
    }
}
