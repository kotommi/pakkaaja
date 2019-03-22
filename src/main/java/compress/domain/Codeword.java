package compress.domain;

import java.util.BitSet;

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

    public void setNext(boolean b) {
        bits.set(index, b);
        index++;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public Codeword clone() {
        return new Codeword((BitSet) this.bits.clone(), this.index);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = index - 1; i >= 0; i--) {
            s += bits.get(i) ? "1" : "0";
        }
        return s + bits.toString();
    }

}
