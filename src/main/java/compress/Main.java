package compress;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.encode.Decode;
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

        System.out.println(Arrays.toString(args));
        if (args.length == 0) {
            System.out.println("No arguments given");
            return;
        }
        String mode = args[0];
        if (mode.charAt(0) != '-') {
            throw new IllegalArgumentException("asd");
        }

        final String filename = args[1];
        final byte[] fileBytes = FileUtils.readFile(filename);


        switch (mode) {
            case "-c":
                byte[] bytes = Encode.encodeHuffman(fileBytes);
                FileUtils.writeFile(filename + ".huf", bytes);
                break;
            case "-x":
                byte[] decodedBytes = Decode.decodeHuffman(fileBytes);
                FileUtils.writeFile(filename.substring(0, filename.length() - 4) + ".dec", decodedBytes);
                break;
            case "-a":
                //do both
                break;
            default:
                System.out.println("Unsupported operation");
                break;
        }


        //byte[] encodedBytes = Encode.encodeHuffman(fileBytes);
        //FileUtils.writeFile(filename, encodedBytes);
    }

}
