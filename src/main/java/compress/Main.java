package compress;


import com.google.common.io.Files;
import compress.domain.Tuple;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("asd");
        System.out.println(Arrays.toString(args));
        String property = System.getProperty("user.dir");
        String filepath = property + "/" + args[0];
        byte[] array = Files.toByteArray(new File(filepath));
        // java tavu on signed integer \in [-128,127]
        // test writing bytes back Files.write(array, new File(property + "writefile.png"));
        ArrayList<Tuple> freqs = getFreqs(array);
        System.out.println(freqs);
    }

    public static ArrayList<Tuple> getFreqs(byte[] arr) {
        long[] freqs = new long[256];
        for (byte b : arr) {
            int i = (int) b + 128;
            freqs[i]++;
        }
        //toss zeroed bytes away
        // TODO implement arraylist
        ArrayList<Tuple> tuples = new ArrayList<>();
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] != 0) {
                tuples.add(new Tuple((byte) i, freqs[i]));
            }
        }
        // TODO implement sort, n=256 at worst, insertion good enough?
        Collections.sort(tuples);
        return tuples;
    }
}
