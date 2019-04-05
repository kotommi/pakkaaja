package compress.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ByteListTest {

    private ByteList list;

    @Before
    public void setup() {
        list = new ByteList();
    }

    @Test
    public void createTest() {
        assertEquals(0, list.size());
        byte[] init = {0, 0, 0, 0};
        list = new ByteList(init);
        assertEquals(4, list.size());
        assertArrayEquals(init, list.toArray());
        list = new ByteList(1);
    }

    @Test
    public void listGrowsProperlyWithInitialArray() {
        byte[] init = {0, 0, 0, 0};
        list = new ByteList(init);
        for (int i = 0; i < 100000; i++) {
            list.add(Byte.MAX_VALUE);
        }
        assertEquals(100000 + init.length, list.size());
        assertEquals(Byte.MAX_VALUE, list.get(100000 + init.length - 1));
    }

    @Test
    public void listGrowsProperlyWithInitialArrayLength() {
        list = new ByteList(10);
        for (int i = 0; i < 100000; i++) {
            list.add(Byte.MAX_VALUE);
        }
        assertEquals(100000, list.size());
        assertEquals(Byte.MAX_VALUE, list.get(100000 - 1));

    }

    @Test
    public void addTest() {
        list.add(Byte.MIN_VALUE);
        assertEquals(1, list.size());
        for (int i = 0; i < 100; i++) {
            list.add((byte) i);
        }
        assertEquals(101, list.size());
    }

    @Test
    public void resizeTest() {
        for (int i = 0; i < 10000; i++) {
            list.add(Byte.MAX_VALUE);
        }
        assertEquals(10000, list.size());
        assertEquals(Byte.MAX_VALUE, list.get(list.size() - 1));
    }

    @Test
    public void getTest() {
        try {
            list.get(0);
            fail("got index 0 from empty list");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
        list.add(Byte.MIN_VALUE);
        assertEquals(Byte.MIN_VALUE, list.get(0));
    }

    @Test
    public void toArrayTest() {
        list.add(Byte.MIN_VALUE);
        list.add(Byte.MAX_VALUE);
        byte[] bytes = list.toArray();
        assertEquals(2, bytes.length);
        assertEquals(Byte.MIN_VALUE, bytes[0]);
        assertEquals(Byte.MAX_VALUE, bytes[1]);
    }

    @Test
    public void clearTest() {
        list.add(Byte.MAX_VALUE);
        list.clear();
        assertEquals(0, list.size());
        try {
            list.get(0);
            fail("got index 0 from empty list");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }

    @Test
    public void setTest() {
        list.add(Byte.MIN_VALUE);
        list.set(0, Byte.MAX_VALUE);
        assertEquals(Byte.MAX_VALUE, list.get(0));
        try {
            list.set(15, Byte.MIN_VALUE);
            fail("set out of bounds");
        } catch (Exception e) {
            assertEquals(IndexOutOfBoundsException.class, e.getClass());
        }
    }


}
