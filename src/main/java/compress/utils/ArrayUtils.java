package compress.utils;

public class ArrayUtils {
    private final static int BYTE_VALUES = 256;

    /**
     * Counts the frequency of each byte value 0-255.
     *
     * @param arr array of bytes
     * @return - an array where for byte b: array[b] is its frequency
     */
    public static long[] getFreqs(byte[] arr) {

        long[] freqs = new long[BYTE_VALUES];
        for (byte b : arr) {
            int i = (int) b + 128;
            freqs[i]++;
        }
        return freqs;
    }


    /**
     * Counts the amount of nonzero values in an array.
     *
     * @param array
     * @return
     */
    public static int nonZeroes(long[] array) {
        int count = 0;
        for (long l : array) {
            if (l != 0) {
                count++;
            }
        }
        return count;
    }


    /**
     * Concatenates two arrays.
     *
     * @param a byte[]
     * @param b byte[]
     * @return byte[]
     */
    public static byte[] concat(byte[] a, byte[] b) {
        byte[] ret = new byte[a.length + b.length];
        int i = 0;
        while (i < a.length) {
            ret[i] = a[i];
            i++;
        }
        while (i < a.length + b.length) {
            ret[i] = b[i - a.length];
            i++;
        }
        return ret;
    }
}
