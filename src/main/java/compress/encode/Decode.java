package compress.encode;

import compress.domain.HeaderReader;
import compress.domain.TreeNode;

public class Decode {

    public byte[] decodeHuffman(byte[] encodedBytes) {
        HeaderReader headerReader = new HeaderReader(encodedBytes);
        TreeNode treeRoot = HufHeader.decodeTree(headerReader);

        // slice the header out
        final byte[] decodedBytes = Huffman.decode(encodedBytes, treeRoot);

        return decodedBytes;
    }
}
