package compress.domain;

public class Tuple implements Comparable<Tuple>{
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
        return "byte:" + id + ", count: " + count;
    }


    @Override
    public int compareTo(Tuple tuple) {
        return (int)(this.count - tuple.count);
    }
}
