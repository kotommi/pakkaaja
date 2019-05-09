package compress.domain;

/**
 * Class representing a Huffman codeword.
 * Uses an int to represent a bitstring.
 * The codewords are <= 20 bits long so
 * int32 is a good compromise between ease
 * of use and size. Array of 3 bytes would be
 * most optimal in size but the bitwise operations
 * in Java return always ints so that implementation
 * would suffer from casts between the primitives and
 * it would use ints for the intermediate results.
 */
public class Codeword {

    private int bits;
    // logical size of the set
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
     * @throws IndexOutOfBoundsException if i > 31 or i < 0.
     *                                   The set only holds an int's worth of bits
     */
    public boolean get(int i) {
        if (i > 31 || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        //shift right i positions and & the bit value
        final int bit = bits >> i & 1;
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
        if (index > 0)
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

}
