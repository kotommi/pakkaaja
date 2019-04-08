package compress;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.encode.Huffman;
import compress.utils.ArrayUtils;
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
            return;
        }
        final String filename = args[0];
        final byte[] fileBytes = FileUtils.readFile(filename);
        final int fileBytesCount = fileBytes.length;
        final long[] freqs = ArrayUtils.getFreqs(fileBytes);
        final TreeNode[] treeNodes = Huffman.buildNodes(freqs);
        final TreeNode treeRoot = Huffman.buildTree(treeNodes);
        final Codeword[] lookupTable = Huffman.buildLookupTable(treeRoot);
        final byte[] encodedBytes = Huffman.encode(fileBytes, lookupTable);
        FileUtils.writeFile(filename, encodedBytes);
        byte[] asdasd = FileUtils.readFile(filename + ".huf");
        byte[] decodedBytes = Huffman.decode(asdasd, treeRoot);
        FileUtils.writeFile("decoded.file", decodedBytes);
    }

}
