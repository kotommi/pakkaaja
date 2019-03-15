package compress.domain;

public class Tuple implements Comparable<Tuple> {
    private byte id;
    private long count;

    public Tuple(byte id, long count) {
        this.id = id;
        this.count = count;
    }

    public byte getId() {
        return id;
    }

    public long getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "byte:" + Byte.toString(id) + ", count: " + count;
    }

    //ascending order
    @Override
    public int compareTo(Tuple tuple) {
        return Long.compare(tuple.count, this.count);
    }
}
