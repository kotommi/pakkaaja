package compress;

import compress.encode.Decode;
import compress.encode.Encode;
import compress.utils.FileUtils;

import java.io.IOException;


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
            System.out.println("Usage: -[c/x/h] [huf/lzw] filename");
            return;
        }
        final String mode = args[0];
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
        System.out.println("Compressing " + filename + ", original size " + fileBytes.length + " bytes.");
        int compressedSize = 0;
        switch (algo) {
            case "huf":
                byte[] hufBytes = Encode.encodeHuffman(fileBytes);
                compressedSize = hufBytes.length;
                FileUtils.writeFile(filename + ".huf", hufBytes);
                System.out.println("Compressed to " + filename + ".huf");
                break;
            case "lzw":
                byte[] lzwBytes = Encode.encodeLZW(fileBytes);
                compressedSize = lzwBytes.length;
                FileUtils.writeFile(filename + ".lzw", lzwBytes);
                System.out.println("Compressed to " + filename + ".lzw");
                break;
            default:
                System.out.println("No algorithm selected\n Choose huf or lzw");
        }
        double ratio = (double) compressedSize / (double) fileBytes.length * 100;
        String format = String.format("%.2f", ratio);
        System.out.println("Compressed size " + compressedSize + " bytes.");
        System.out.println(format + "% of the original");

    }

    private static void extractFile(byte[] fileBytes, String filename, String algo) {
        System.out.println("Decompressing " + filename + ", compressed size "
                + fileBytes.length + " bytes.");
        String slicedName = filename.substring(0, filename.length() - 4);
        int decompressedBytes = 0;
        switch (algo) {
            case "huf":
                byte[] decodedHufBytes = Decode.decodeHuffman(fileBytes);
                decompressedBytes = decodedHufBytes.length;
                FileUtils.writeFile(slicedName, decodedHufBytes);
                break;
            case "lzw":
                byte[] decodedLZWBytes = Decode.decodeLZW(fileBytes);
                decompressedBytes = decodedLZWBytes.length;
                FileUtils.writeFile(slicedName, decodedLZWBytes);
                break;
            default:
                System.out.println("No algorithm selected\n Choose huf or lzw");
        }
        System.out.println("Decompressed file to " + slicedName
                + ", decompressed size " + decompressedBytes + " bytes");
    }

}
