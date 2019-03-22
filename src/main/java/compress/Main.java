package compress;

import static compress.encode.Huffman.buildNodes;
import static compress.encode.Huffman.buildTree;
import static compress.utils.ArrayUtils.getFreqs;
import static compress.utils.FileUtils.readFile;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.encode.Huffman;

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
        final byte[] fileBytes = readFile(filename);
        long[] freqs = getFreqs(fileBytes);
        TreeNode[] treeNodes = buildNodes(freqs);
        TreeNode treeRoot = buildTree(treeNodes);
        System.out.println("tree: " + treeRoot);
        System.out.println("tree depth: " + treeRoot.getDepth());
        Codeword[] lookupTable = Huffman.buildLookupTable(treeRoot);
        System.out.println(Arrays.toString(lookupTable));
        for (int i = 0; i < lookupTable.length; i++) {
            if (lookupTable[i] == null) {
                continue;
            }
            byte b = (byte) (i + 128);
            System.out.println("Byte: " + b + " char: " + Character.getName((char) (b + 128)) + " string: " + lookupTable[i].toString());
        }

    }

}
