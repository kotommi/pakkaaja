package compress.domain;

public class BitArray {
    private byte b;
    private short index;

    public BitArray() {
        this.b = Byte.MIN_VALUE;
        this.index = 0;
    }

    public void shiftLeft() {
        b = (byte) (b << 1);
    }

}
