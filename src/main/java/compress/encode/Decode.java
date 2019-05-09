package compress.encode;

import compress.domain.HeaderReader;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

/**
 * Wrapper from decompressing byte arrays.
 */
public class Decode {

    public static byte[] decodeHuffman(byte[] encodedBytes) {
        //build tree from header
        final HeaderReader headerReader = new HeaderReader(encodedBytes);
        final TreeNode treeRoot = HufHeader.decodeTree(headerReader);

        // slice the header out
        final byte[] data = ArrayUtils.slice(encodedBytes, headerReader.getIndex(), encodedBytes.length);

        return Huffman.decode(data, treeRoot);
    }

    public static byte[] decodeLZW(byte[] encodedBytes) {
        return LZW.decode(encodedBytes);
    }
}
