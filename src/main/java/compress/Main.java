package compress;

import compress.domain.Codeword;
import compress.domain.TreeNode;
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
        final String filename = args[0];
        final byte[] fileBytes = FileUtils.readFile(filename);
        System.out.println("eka" + fileBytes[0]);
        System.out.println(Arrays.toString(fileBytes));
        final long[] freqs = ArrayUtils.getFreqs(fileBytes);
        final TreeNode[] treeNodes = Huffman.buildNodes(freqs);
        final TreeNode treeRoot = Huffman.buildTree(treeNodes);
        System.out.println("tree: " + treeRoot);
        System.out.println("tree depth: " + treeRoot.getDepth());
        final Codeword[] lookupTable = Huffman.buildLookupTable(treeRoot);
        System.out.println(Arrays.toString(lookupTable));
        //printLookupTable(lookupTable);
        final byte[] encodedBytes = Huffman.encode(fileBytes, lookupTable);
        FileUtils.writeFile(filename, encodedBytes);
    }

    public static void printLookupTable(Codeword[] lookupTable) {
        for (int i = 0; i < lookupTable.length; i++) {
            if (lookupTable[i] == null) {
                continue;
            }
            final int offset = 128;
            final byte b = (byte) (i + offset);
            System.out.println("Byte: " + b + " char: "
                    + Character.getName((char) (b + offset))
                    + " string: "
                    + lookupTable[i].toString());
        }
    }

}
