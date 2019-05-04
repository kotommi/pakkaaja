package compress.encode;

import compress.domain.ByteList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class LZWTest {

    private byte[] stringBytes = {B, A, B, A, A, B, A, A, A};
    private int[] decodedCodes = {98, 97, 128, 129, 97, 132};

    private final static byte A = 97;
    private final static byte B = 98;

    @Test
    public void encode() {
        System.out.println("input: " + Arrays.toString(stringBytes));
        ArrayList<Integer> output = LZW.encode(stringBytes);
        Stream<Integer> integerStream = output.stream().map((Integer i) -> i - 128);
        List<Integer> collect = integerStream.collect(Collectors.toList());
        System.out.println("encoded output: " + collect);
    }

    @Test
    public void decode() {
        ArrayList<Integer> input = new ArrayList();
        for (int i : decodedCodes) {
            input.add(i);
        }
        System.out.println("encoded input: " + input);

        byte[] decode = LZW.decode(input);
        for (int i = 0; i < decode.length; i++) {
            decode[i] += 128;
        }
        System.out.println("decoded output: " + Arrays.toString(decode));
    }
}