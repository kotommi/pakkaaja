package compress.domain;

import java.util.BitSet;

/**
 * Highly kesken. Wrapperi BitSetille.
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
        for (int i = index - 1; i >= 0; i--) {
            rev.set((index - 1) - i, bits.get(i));
        }
        this.bits = rev;
    }

    public Codeword getCopy() {
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
                && bits.equals(codeword.bits);
    }
}
