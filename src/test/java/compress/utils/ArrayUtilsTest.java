package compress.utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ArrayUtilsTest {


    @Test
    public void nonZeroTest() {
        int[] l = {1, 2, 3};
        int nonZeroes = ArrayUtils.nonZeroes(l);
        assertEquals(3, nonZeroes);
        nonZeroes = ArrayUtils.nonZeroes(new int[0]);
        assertEquals(0, nonZeroes);
        int[] l2 = {0, 1, 0, 1, 0};
        nonZeroes = ArrayUtils.nonZeroes(l2);
        assertEquals(2, nonZeroes);
    }

    @Test
    public void getFreqsTest() {
        final int OFFSET = 128;
        byte[] bytes = {0, 1, 1, 2, 3, 3, 3};
        int[] freqs = ArrayUtils.getFreqs(bytes);
        assertEquals(1, freqs[0 + OFFSET]);
        assertEquals(2, freqs[1 + OFFSET]);
        assertEquals(1, freqs[2 + OFFSET]);
        assertEquals(3, freqs[3 + OFFSET]);
    }

    @Test
    public void getFreqsEmptyTest() {
        byte[] bytes = {};
        int[] freqs = ArrayUtils.getFreqs(bytes);
        assertEquals(0, Arrays.stream(freqs).sum());
    }

    @Test
    public void concatTest() {
        byte[] first = {0, 1};
        byte[] second = {2, 3};
        byte[] result = ArrayUtils.concat(first, second);
        final byte[] expected = {0, 1, 2, 3};
        assertArrayEquals(expected, result);
    }

    @Test
    public void concatTest2() {
        byte[] first = {0, 1};
        byte[] second = {2, 3, 5, 6};
        byte[] result = ArrayUtils.concat(first, second);
        final byte[] expected = {0, 1, 2, 3, 5, 6};
        assertArrayEquals(expected, result);
    }

    @Test
    public void sliceTest() {
        final byte[] bytes = new byte[256];
        byte b = Byte.MIN_VALUE;
        for (int i = 0; i < 256; i++) {
            bytes[i] = b;
            b++;
        }
        byte[] slice = ArrayUtils.slice(bytes, 15, bytes.length);
        byte[] expected = Arrays.copyOfRange(bytes, 15, bytes.length);
        assertArrayEquals(expected, slice);

    }

}