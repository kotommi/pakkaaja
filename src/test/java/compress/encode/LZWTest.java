package compress.encode;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

public class LZWTest {

    private byte[] stringBytes = {B, A, B, A, A, B, A, A, A};

    private final static byte A = 97;
    private final static byte B = 98;
    private final static byte C = 99;

    @Test
    public void encode() {
        byte[] output = LZW.encode(stringBytes);
        Assert.assertNotNull(output);
        Assert.assertTrue("Output size should be even",
                output.length % 2 == 0);

        // convert output to ints so its easier to
        // compare to a precalculated result.
        int[] codes = new int[output.length / 2];
        int j = 0;
        for (int i = 0; i < output.length - 1; i += 2) {
            byte first = output[i];
            byte second = output[i + 1];
            int result = ((first & 0xFF) << 8) | (second & 0xFF);
            codes[j] = result;
            j++;
        }

        // result calculated by hand
        //[226, 225, 256, 257, 225, 260]
        final int[] expected = {226, 225, 256, 257, 225, 260};
        Assert.assertArrayEquals(expected, codes);
    }

    @Test
    public void decode() {
        // taken from encode test after it was working
        final byte[] inputs = {0, -30, 0, -31, 1, 0, 1, 1, 0, -31, 1, 4};

        byte[] decode = LZW.decode(inputs);

        Assert.assertArrayEquals(stringBytes, decode);
    }

    @Test
    public void fullRoundLongInput() {
        // generate array of random A's and B's
        Random random = new Random();
        byte[] input = new byte[1000];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (A + random.nextInt(2));
        }
        //compress and decompress
        byte[] encode = LZW.encode(input);
        byte[] decode = LZW.decode(encode);
        assertArrayEquals(input, decode);
    }

    @Test
    public void fullRoundLongInputMoreChars() {
        // generate array of random A's and B's
        final Random random = new Random();
        final byte[] input = new byte[10000];
        for (int i = 0; i < input.length; i++) {
            input[i] = (byte) (A + random.nextInt(10));
        }
        //compress and decompress
        final byte[] encode = LZW.encode(input);
        final byte[] decode = LZW.decode(encode);
        assertArrayEquals(input, decode);
    }

}