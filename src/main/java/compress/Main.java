package compress;


import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("asd");
        System.out.println(Arrays.toString(args));
        String property = System.getProperty("user.dir");
        String filepath = property + "/" + args[0];
        byte[] array = Files.toByteArray(new File(filepath));
        // java tavu on signed integer \in [-128,127]
        // test writing bytes back Files.write(array, new File(property + "writefile.png"));
        long[] freqs = new long[256];
        for (byte b : array) {
            int i = (int) b + 128;
            freqs[i]++;
        }
        //Arrays.sort(freqs);
        System.out.println(Arrays.toString(freqs));
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] == 7186) {
                System.out.println("index:" + i);
            }
        }
    }
}
