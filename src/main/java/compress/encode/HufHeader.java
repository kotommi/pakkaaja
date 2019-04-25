package compress.encode;

import compress.domain.ByteList;
import compress.domain.HeaderReader;
import compress.domain.TreeNode;

public class HufHeader {

    private static int OFF = 128;
    private static byte EMPTY = 0;
    private static byte LEAF = 1;

    public static byte[] encodeTree(TreeNode root) {
        ByteList bytes = new ByteList();
        // mark how many leafNodes to expect.
        // gotta do some scaling to make the int to byte conversion.
        int leafCount = root.getLeafCount() - 1;
        leafCount -= OFF;
        bytes.add((byte) leafCount);

        //recursively encode the tree
        encodeTree(root, bytes);
        return bytes.toArray();
    }

    /**
     * Schema: 1 marks a leaf and is followed by a byte with the value.
     * 0 marks an empty parent node and is followed by nothing.
     *
     * @param node
     * @param bytes
     */
    private static void encodeTree(TreeNode node, ByteList bytes) {
        if (node == null) {
            throw new NullPointerException("something went wrong in the recursion");
        }
        if (!node.isLeaf()) {
            bytes.add((byte) 0);
            encodeTree(node.getLeft(), bytes);
            encodeTree(node.getRight(), bytes);
        } else {
            bytes.add((byte) 1);
            bytes.add(node.getId());
        }

    }

    public static TreeNode decodeTree(HeaderReader headerReader) {
        TreeNode root = new TreeNode(0, Byte.MIN_VALUE);
        decodeNode(root, headerReader);

        return root;
    }

    private static void decodeNode(TreeNode node, HeaderReader headerReader) {
        if (headerReader.hasNext()) {
            byte type = headerReader.getType();
            if (type == EMPTY) {
                node.setLeft(new TreeNode(0, Byte.MIN_VALUE));
                node.setRight(new TreeNode(0, Byte.MIN_VALUE));
                decodeNode(node.getLeft(), headerReader);
                decodeNode(node.getRight(), headerReader);
            } else if (type == LEAF) {
                byte value = headerReader.getValue();
                node.setId(value);
            } else {
                throw new IllegalArgumentException(
                        "Something went wrong traveling header.\n" +
                                "expected 0 or 1, got: " + type);
            }
        }
    }


}
