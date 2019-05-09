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
    public static int[] getFreqs(byte[] arr) {

        final int[] freqs = new int[BYTE_VALUES];
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
    public static int nonZeroes(int[] array) {
        int count = 0;
        for (int i : array) {
            if (i != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Concatenate two arrays into one.
     *
     * @param first  head of new array
     * @param second tail of new array
     * @return Concatenated array first+second.
     */
    public static byte[] concat(byte[] first, byte[] second) {
        final int total = first.length + second.length;
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

    /**
     * Get a sub-array from input.
     *
     * @param bytes     Array to slice
     * @param fromIndex Index to start, inclusive
     * @param toIndex   Index to stop, exclusive
     * @return Subarray arr[from]...arr[to]
     */
    public static byte[] slice(byte[] bytes, int fromIndex, int toIndex) {
        final byte[] result = new byte[toIndex - fromIndex];
        for (int i = fromIndex; i < toIndex; i++) {
            result[i - fromIndex] = bytes[i];
        }
        return result;
    }

}
