package compress.encode;

import compress.domain.HeaderReader;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

public class Decode {

    public static byte[] decodeHuffman(byte[] encodedBytes) {
        //build tree from header
        HeaderReader headerReader = new HeaderReader(encodedBytes);
        TreeNode treeRoot = HufHeader.decodeTree(headerReader);

        // slice the header out
        byte[] data = ArrayUtils.slice(encodedBytes, headerReader.getIndex(), encodedBytes.length);

        return Huffman.decode(data, treeRoot);
    }
}
