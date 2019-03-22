package compress.utils;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ArrayUtilsTest {


    @Test
    public void nonZeroTest() {
        long[] l = {1, 2, 3};
        int nonZeroes = ArrayUtils.nonZeroes(l);
        assertEquals(3, nonZeroes);
        nonZeroes = ArrayUtils.nonZeroes(new long[0]);
        assertEquals(0, nonZeroes);
        long[] l2 = {0, 1, 0, 1, 0};
        nonZeroes = ArrayUtils.nonZeroes(l2);
        assertEquals(2, nonZeroes);
    }

    @Test
    public void getFreqsTest() {
        final int OFFSET = 128;
        byte[] bytes = {0, 1, 1, 2, 3, 3, 3};
        long[] freqs = ArrayUtils.getFreqs(bytes);
        assertEquals(1, freqs[0 + OFFSET]);
        assertEquals(2, freqs[1 + OFFSET]);
        assertEquals(1, freqs[2 + OFFSET]);
        assertEquals(3, freqs[3 + OFFSET]);
    }

    @Test
    public void getFreqsEmptyTest() {
        byte[] bytes = {};
        long[] freqs = ArrayUtils.getFreqs(bytes);
        assertEquals(0, Arrays.stream(freqs).sum());
    }

}
