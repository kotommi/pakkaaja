package compress.utils;

public final class ArrayUtils {
    private static final int BYTE_VALUES = 256;
    private static final int OFFSET = 128;

    /**
     * Counts the frequency of each byte value 0-255.
     *
     * @param arr array of bytes
     * @return - an array where for byte b: array[b] is its frequency/count.
     */
    public static long[] getFreqs(byte[] arr) {

        final long[] freqs = new long[BYTE_VALUES];
        for (byte b : arr) {
            int i = (int) b + OFFSET;
            freqs[i]++;
        }
        return freqs;
    }


    /**
     * Counts the amount of nonzero values in an array.
     *
     * @param array array of random long values
     * @return count of indexes with nonzero value.
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

    public static byte[] concat(byte[] first, byte[] second) {
        int total = first.length + second.length;
        final byte[] result = new byte[total];
        int i;
        for (i = 0; i < first.length; i++) {
            result[i] = first[i];
        }
        for (int j = i; j < total; j++) {
            result[j] = second[j - i];
        }

        return result;
    }


}
