package compress.domain;

/**
 * Class representing a Huffman codeword.
 * Uses an int to represent a bitstring.
 */
public class Codeword {

    private int bits;
    // first unused bit
    private int index;

    public Codeword() {
        this.bits = 0;
        this.index = 0;
    }

    private Codeword(int bits, int index) {
        this.bits = bits;
        this.index = index;
    }

    /**
     * Returns a bit value at position i.
     *
     * @param i index, range 0-31
     * @return value of the bit
     * @throws IndexOutOfBoundsException if i > 31.
     *                                   The set only holds an int's worth of bits
     */
    public boolean get(int i) {
        if (i > 31 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        int bit = bits >> i & 1;
        return bit == 1;
    }

    /**
     * Sets bit at position index to b.
     *
     * @param b bit value
     * @throws IndexOutOfBoundsException if the internal int would overflow.
     */
    public void setNext(boolean b) {
        if (index == 32) {
            throw new IndexOutOfBoundsException("int overflow");
        }
        if (index == 1)
            bits = bits << 1;
        if (b) {
            bits = bits | 1;
        }
        index++;
    }

    /**
     * Returns how many bits are in use in the set.
     *
     * @return amount of bits used.
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns a "deep" copy of this Codeword.
     *
     * @return Copy of this codeword
     */
    public Codeword getCopy() {
        return new Codeword(this.bits, this.index);
    }

    /**
     * Too lazy to impl leading zeros.
     *
     * @return bits in a string
     */
    @Override
    public String toString() {
        return index == 0 ? "" : Integer.toBinaryString(bits);
    }


    /**
     * Standard equals method. Checks for class and contents.
     *
     * @param o Object to compare with
     * @return true if both objects' contents are the same
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Codeword codeword = (Codeword) o;
        return index == codeword.index
                && bits == codeword.bits;
    }
}
