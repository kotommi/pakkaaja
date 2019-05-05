package compress;

import compress.encode.Decode;
import compress.encode.Encode;
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
            System.out.println("Usage: -[c/x/h] [huf/lzw] filename");
            return;
        }
        String mode = args[0];
        if (mode.charAt(0) != '-') {
            throw new IllegalArgumentException("first argument must be mode [-c/-x/-h]");
        }

        final String algo = args[1];

        final String filename = args[2];
        final byte[] fileBytes = FileUtils.readFile(filename);


        switch (mode) {
            case "-c":
                compressFile(fileBytes, filename, algo);
                break;
            case "-x":
                extractFile(fileBytes, filename, algo);
                break;
            default:
                System.out.println("Unsupported operation, try [-c/-x/-h]");
                break;
        }
    }

    private static void compressFile(byte[] fileBytes, String filename, String algo) {
        switch (algo) {
            case "huf":
                byte[] hufBytes = Encode.encodeHuffman(fileBytes);
                FileUtils.writeFile(filename + ".huf", hufBytes);
                break;
            case "lzw":
                byte[] lzwBytes = Encode.encodeHuffman(fileBytes);
                FileUtils.writeFile(filename + ".lzw", lzwBytes);
                break;
            default:
                System.out.println("No algorithm selected\n Choose huf or lzw");
        }

    }

    private static void extractFile(byte[] fileBytes, String filename, String algo) {
        switch (algo) {
            case "huf":
                byte[] decodedHufBytes = Decode.decodeHuffman(fileBytes);
                FileUtils.writeFile(filename.substring(0, filename.length() - 4), decodedHufBytes);
                break;
            case "lzw":
                byte[] decodedLZWBytes = Decode.decodeHuffman(fileBytes);
                FileUtils.writeFile(filename.substring(0, filename.length() - 4), decodedLZWBytes);
                break;
            default:
                System.out.println("No algorithm selected\n Choose huf or lzw");
        }

    }

}
