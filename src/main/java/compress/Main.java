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
            return;
        }
        String mode = args[0];
        if (mode.charAt(0) != '-') {
            throw new IllegalArgumentException("first argument must be mode [-e/-x/-a/-h]");
        }

        final String filename = args[1];
        final byte[] fileBytes = FileUtils.readFile(filename);


        switch (mode) {
            case "-c":
                compressFile(fileBytes, filename);
                break;
            case "-x":
                extractFile(fileBytes, filename);
                break;
            case "-a":
                doItAll(fileBytes, filename);
                break;
            default:
                System.out.println("Unsupported operation");
                break;
        }
    }

    private static void doItAll(byte[] fileBytes, String filename) throws IOException {
        compressFile(fileBytes, filename);
        byte[] tempBytes = FileUtils.readFile(filename);
        extractFile(tempBytes, filename);
    }

    private static void compressFile(byte[] fileBytes, String filename) {
        byte[] bytes = Encode.encodeHuffman(fileBytes);
        FileUtils.writeFile(filename + ".huf", bytes);
    }

    private static void extractFile(byte[] fileBytes, String filename) {
        byte[] decodedBytes = Decode.decodeHuffman(fileBytes);
        FileUtils.writeFile(filename.substring(0, filename.length() - 4), decodedBytes);
    }

}
