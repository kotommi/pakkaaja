package compress.encode;

import compress.domain.ByteList;
import compress.domain.Codeword;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LZWTest {

    private byte[] stringBytes = {B, A, B, A, A, B, A, A, A};
    private int[] decodedCodes = {226, 225, 256, 257, 225, 260};

    private final static byte A = 97;
    private final static byte B = 98;

    @Test
    public void encode() {
        System.out.println("input: " + Arrays.toString(stringBytes));
        ArrayList<Codeword> output = LZW.encode(stringBytes);

        //[226, 225, 256, 257, 225, 260]
        System.out.println("encoded output: " + output);
    }

    @Test
    public void decode() {
        ArrayList<Integer> input = new ArrayList<>();
        for (int i : decodedCodes) {
            input.add(i);
        }
        System.out.println("encoded input: " + input);

        byte[] decode = LZW.decode(input);
        for (int i = 0; i < decode.length; i++) {
            //decode[i] += 128;
        }
        System.out.println("decoded output: " + Arrays.toString(decode));
        Assert.assertArrayEquals(stringBytes, decode);
    }
}