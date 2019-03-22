package compress.domain;

import java.util.BitSet;

/**
 * Highly kesken.
 */
public class Codeword {

    private BitSet bits;
    // first unused bit
    private int index;

    public Codeword() {
        this.bits = new BitSet();
        this.index = 0;
    }

    private Codeword(BitSet bits, int index) {
        this.bits = bits;
        this.index = index;
    }

    public void setBit(int i, boolean b) {
        bits.set(i, b);
        index++;
    }

    public BitSet getBits() {
        return this.bits;
    }

    public void setNext(boolean b) {
        bits.set(index, b);
        index++;
    }

    public int getIndex() {
        return index;
    }

    public void reverse() {
        BitSet rev = new BitSet();
        for (int i = index; i >= 0; i--) {
            rev.set(i, bits.get(index - i));
        }
        this.bits = rev;
    }

    @Override
    public Codeword clone() {
        return new Codeword((BitSet) this.bits.clone(), this.index);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = index - 1; i >= 0; i--) {
            s.append(bits.get(i) ? "1" : "0");
        }
        return s.toString();
    }

}
