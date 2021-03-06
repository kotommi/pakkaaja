package compress.encode;

import compress.domain.ByteList;
import compress.domain.Codeword;
import compress.domain.MinHeap;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

public class Huffman {

    private static final int offset = 128;

    /**
     * Builds a Huffman-tree from orphan nodes.
     *
     * @param nodes array of lone TreeNodes
     * @return A Huffman-tree where leaves are sorted by count.
     */
    public static TreeNode buildTree(TreeNode[] nodes) {
        MinHeap heap = new MinHeap();
        heap.addAll(nodes);

        while (heap.size() > 1) {
            TreeNode left = heap.poll();
            TreeNode right = heap.poll();
            TreeNode combined = combineNode(left, right);
            heap.add(combined);
        }
        return heap.poll();
    }

    /**
     * Builds TreeNodes from a frequency list.
     *
     * @param freqs count of occurences for each byte
     * @return an array of TreeNodes
     */
    public static TreeNode[] buildNodes(int[] freqs) {
        final int size = ArrayUtils.nonZeroes(freqs);
        final TreeNode[] treeNodes = new TreeNode[size];
        int j = 0;

        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] != 0) {
                treeNodes[j] = new TreeNode(freqs[i], (byte) (i + offset));
                j++;
            }
        }
        return treeNodes;
    }

    /**
     * Combines two nodes as one with sum count, and left and right as children.
     *
     * @param left  TreeNode with count > right.count
     * @param right TreeNode with count < left.count
     * @return A node with count.right + count.left and empty bytes since they aren't needed
     */
    private static TreeNode combineNode(TreeNode left, TreeNode right) {
        if (left.getCount() < right.getCount()) {
            final TreeNode temp = left;
            left = right;
            right = temp;
        }
        // combine counts and use a dummy value
        final TreeNode combined = new TreeNode(left.getCount() + right.getCount(), Byte.MIN_VALUE);
        combined.setLeft(left);
        combined.setRight(right);
        return combined;
    }

    /**
     * Recursive preorder-treewalk-method that mutates parameter table.
     *
     * @param node  TreeNode to process
     * @param bits  Codeword representing the shortform version for a particular byte
     * @param table lookup-table for Huffman codes
     */
    private static void preOrderTreeWalk(TreeNode node, Codeword bits, Codeword[] table) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            //bits.reverse();
            table[(int) node.getId() + offset] = bits;
            return;
        }
        // could probably reuse original bits for one of these
        final Codeword left = bits.getCopy();
        left.setNext(false);
        final Codeword right = bits.getCopy();
        right.setNext(true);
        preOrderTreeWalk(node.getLeft(), left, table);
        preOrderTreeWalk(node.getRight(), right, table);
    }


    /**
     * Builds a lookup-table for Huffman codes.
     *
     * @param treeRoot root of a Huffman-tree
     * @return array where arr[byte + 128] is the short code for that byte
     */
    public static Codeword[] buildLookupTable(TreeNode treeRoot) {
        final int BYTEMAX = 256;
        Codeword[] table = new Codeword[BYTEMAX];
        preOrderTreeWalk(treeRoot, new Codeword(), table);
        return table;
    }


    /**
     * Decodes an array of bytes with a Huffman-tree
     * into the originals. Expects bytes[0] to be in
     * range 0-7 and to indicate the leftover bits in
     * last byte.
     *
     * @param bytes    Huffman-encoded bytes
     * @param treeRoot Huffmantree with codewords used for encoding
     * @return Original file contents as array of bytes
     */
    public static byte[] decode(byte[] bytes, TreeNode treeRoot) {
        ByteList decodedBytes = new ByteList();

        //int index = 0; // where to write in decoded array
        TreeNode current = treeRoot;
        // start from bytes[1], end at second to last
        for (int i = 1; i < bytes.length - 1; i++) {
            int currentByte = bytes[i] + offset;
            // j = bit in byte
            // read backwards
            for (int j = 7; j >= 0; j--) {
                //shift right j times to get position
                //and last bit with 1 to get real bit
                int bit = (currentByte >> j) & 1;
                // if 0 zero left else right
                current = (bit == 0) ? current.getLeft() : current.getRight();
                if (current.isLeaf()) {
                    // write to array and reset the node we're on
                    decodedBytes.add(current.getId());
                    current = treeRoot;
                }
            }
        }
        // handle the last byte
        int last = bytes[bytes.length - 1] + offset;
        int used = bytes[0];
        for (int j = used; j > 0; j--) {
            int bit = (last >> j) & 1;
            current = bit == 0 ? current.getLeft() : current.getRight();
            if (current.isLeaf()) {
                decodedBytes.add(current.getId());
                current = treeRoot;
            }
        }
        return decodedBytes.toArray();
    }


    public static byte[] encodeData(byte[] bytes, Codeword[] lookup) {
        final ByteList encodedBytes = new ByteList();
        encodedBytes.add((byte) 0);

        int bitIndex = 0;
        int currentByte = 0;


        for (int i = 0; i < bytes.length; i++) {
            Codeword codeword = lookup[bytes[i] + offset];
            for (int j = codeword.getIndex() - 1; j >= 0; j--) {
                if (bitIndex < 8) {
                    boolean isTrue = codeword.get(j);
                    if (isTrue) {
                        // if bit == 1 flip last bit
                        currentByte = currentByte | 0b00000001;
                    }
                    if (bitIndex != 7) {
                        //if not last bit in byte shift left one pos
                        currentByte = currentByte << 1;
                    }
                    bitIndex++;
                } else {
                    // byte "full"
                    // get the last 8 bits out of n int
                    encodedBytes.add((byte) ((currentByte + offset)));
                    currentByte = 0;
                    bitIndex = 0;
                    // do this byte again
                    j++;
                }
            }
        }
        // if all bits are used add a dummy byte in the end
        // and write 0 in front.
        if (bitIndex == 8) {
            encodedBytes.add((byte) ((currentByte + offset)));
            encodedBytes.add((byte) 0);
            encodedBytes.set(0, (byte) 0);
        } else {
            //add the last byte
            encodedBytes.add((byte) ((currentByte + offset)));
            //set the header byte to used bits in the last byte
            encodedBytes.set(0, (byte) bitIndex);
        }
        return encodedBytes.toArray();
    }

}
