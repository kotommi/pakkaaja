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

    public HeaderReader(byte[] arr) {
        this.arr = arr;
        this.leaves = arr[0] + offset;
        this.i = 1;
    }

    public boolean hasNext() {
        return leaves > 0;
    }

    public byte getType() {
        byte b = arr[i];
        i++;
        if (b == 1) {
            leaves--;
        }
        return b;
    }

    public byte getValue() {
        byte b = arr[i];
        i++;

        return b;
    }

    public int getIndex() {
        return i;
    }

}
