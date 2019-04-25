package compress;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.encode.Encode;
import compress.encode.Huffman;
import compress.utils.ArrayUtils;
import compress.utils.FileUtils;

import java.io.IOException;
import java.util.Arrays;


public class Main {

    /**
     * Main class for testing, for now, for ever.
     *
     * @param args commandline arguments
     * @throws IOException errorhandling soon:tm:
     */
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("No arguments given");
            return;
        }
        System.out.println(Arrays.toString(args));
        final String filename = args[0];
        final byte[] fileBytes = FileUtils.readFile(filename);
        byte[] encodedBytes = Encode.encodeHuffman(fileBytes);
        FileUtils.writeFile(filename, encodedBytes);
    }

}
