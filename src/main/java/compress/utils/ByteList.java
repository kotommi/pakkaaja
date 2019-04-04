package compress.utils;

public class ByteList {
    private byte[] bytes;
    private int size;

    public ByteList() {
        this.bytes = new byte[256];
        this.size = 0;
    }

    public ByteList(int initLength) {
        this.bytes = new byte[initLength];
        this.size = 0;
    }

    public ByteList(byte[] initialBytes) {
        this.bytes = new byte[initialBytes.length];
        for (int i = 0; i < initialBytes.length; i++) {
            this.bytes[i] = initialBytes[i];
        }
        this.size = initialBytes.length;
    }

    public void add(byte b) {
        if (this.size == bytes.length) {
            this.reSize();
        }
        bytes[size] = b;
        size++;
    }

    public byte get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("got: " + i + " size: " + size);
        }
        return bytes[i];
    }

    public void set(int i, byte b) {
        if (i >= size) {
            throw new IndexOutOfBoundsException("got: " + i + " size: " + size);
        }
        bytes[i] = b;
    }

    private void reSize() {
        //resizing factor stolen from arraylist
        int newLength = (int) (bytes.length * 1.5) + 1;
        byte[] newBytes = new byte[newLength];
        for (int i = 0; i < bytes.length; i++) {
            newBytes[i] = bytes[i];
        }
        this.bytes = newBytes;
    }

    public byte[] toArray() {
        byte[] ret = new byte[size];
        for (int i = 0; i < size; i++) {
            ret[i] = bytes[i];
        }
        return ret;
    }

    public int size() {
        return size;
    }

    public void clear() {
        this.bytes = new byte[256];
        this.size = 0;
    }


}
