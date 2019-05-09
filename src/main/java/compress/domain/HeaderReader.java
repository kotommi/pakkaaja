package compress.domain;

/**
 * Java Scanner style reader-class for encoded headers.
 * This is used to hide dirty state from the decoding functions.
 */
public class HeaderReader {
    private final byte[] arr;
    private int leaves;
    private final int offset = 129;
    private int i;

    /**
     * arr[0] is the number of leaves in the huffman-tree
     * as a signed byte, hence the offset.
     *
     * @param arr data bytes with header in front.
     */
    public HeaderReader(byte[] arr) {
        this.arr = arr;
        this.leaves = arr[0] + offset;
        this.i = 1;
    }

    /**
     * Reads until all leaves are read.
     * After that the header ends.
     *
     * @return true if there's still header to read.
     */
    public boolean hasNext() {
        return leaves > 0;
    }

    /**
     * Reads next byte and maybe reduces leafcount.
     * Algorithm knows when to call this.
     * These are used to infer the structure of the tree.
     *
     * @return 1 for leaf node, 0 for parent.
     * @see compress.encode.HufHeader
     */
    public byte getType() {
        final byte b = arr[i];
        i++;
        if (b == 1) {
            leaves--;
        }
        return b;
    }

    /**
     * Reads next byte.
     *
     * @return Value of a leaf node.
     * @see compress.encode.HufHeader
     */
    public byte getValue() {
        final byte b = arr[i];
        i++;

        return b;
    }

    /**
     * Used after the header has been read
     * to infer where data starts.
     *
     * @return Array index of first actual data byte.
     */
    public int getIndex() {
        if (leaves > 0) {
            //fail case
            return -1;
        }
        return i;
    }

}
