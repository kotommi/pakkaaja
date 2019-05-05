package compress.domain;

import java.util.Arrays;
import java.util.Objects;

/**
 * Dynamic byte-array for convenience purposes
 */
public class ByteList {
    private byte[] bytes;
    private int size;

    /**
     * Default constructor. Uses 256 as
     * initial array size for no particular reason
     */
    public ByteList() {
        this.bytes = new byte[256];
        this.size = 0;
    }

    /**
     * Create a List with initial array length
     *
     * @param initLength length of the array inside
     */
    public ByteList(int initLength) {
        this.bytes = new byte[initLength];
        this.size = 0;
    }

    /**
     * Copies the parameter array as initial contents of the list.
     * Pure
     *
     * @param initialBytes initial bytes for the list.
     */
    public ByteList(byte... initialBytes) {
        this.bytes = new byte[initialBytes.length];
        for (int i = 0; i < initialBytes.length; i++) {
            this.bytes[i] = initialBytes[i];
        }
        this.size = initialBytes.length;
    }


    /**
     * Appends byte b to the list.
     *
     * @param b byte
     */
    public void add(byte b) {
        if (this.size == bytes.length) {
            this.resize();
        }
        bytes[size] = b;
        size++;
    }


    public void addAll(byte... bytes) {
        for (byte b : bytes) {
            this.add(b);
        }
    }

    public void addAll(ByteList bl) {
        for (int i = 0; i < bl.size(); i++) {
            this.add(bl.get(i));
        }
    }

    /**
     * Get byte at index i
     *
     * @param i index
     * @return
     * @throws IndexOutOfBoundsException if i>= size
     */
    public byte get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("got: " + i + " size: " + size);
        }
        return bytes[i];
    }

    /**
     * Sets byte at index i to byte b.
     *
     * @param i index
     * @param b byte to set
     * @throws IndexOutOfBoundsException if i >= size
     */
    public void set(int i, byte b) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("got: " + i + " size: " + size);
        }
        bytes[i] = b;
    }

    /**
     * Resize the array that the list uses.
     * Growth factor 3/2 stolen from arraylist
     * and other commonly used implementations
     */
    private void resize() {
        int newLength = (int) (bytes.length * 1.5) + 1;
        byte[] newBytes = new byte[newLength];
        for (int i = 0; i < bytes.length; i++) {
            newBytes[i] = bytes[i];
        }
        this.bytes = newBytes;
    }

    /**
     * Returns an array with elements of the list in order.
     *
     * @return Array with .length == size
     */
    public byte[] toArray() {
        final byte[] ret = new byte[size];
        for (int i = 0; i < size; i++) {
            ret[i] = bytes[i];
        }
        return ret;
    }

    /**
     * Returns the logical size of the list.
     *
     * @return amount of bytes in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Clears the list.
     */
    public void clear() {
        this.bytes = new byte[256];
        this.size = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ByteList byteList = (ByteList) o;
        return size == byteList.size &&
                Arrays.equals(bytes, byteList.bytes);
    }

    @Override
    public int hashCode() {
        int result = size * 599;
        for (int i = 0; i < size; i++) {
            result += (i + 1) * bytes[i] * 31;
        }
        return result;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += bytes[i];
            if (i < size - 1) {
                s += ", ";
            }
        }


        return s;
    }
}
