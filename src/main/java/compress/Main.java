package compress;

import static compress.encode.Huffman.buildNodes;
import static compress.encode.Huffman.buildTree;
import static compress.utils.ArrayUtils.getFreqs;
import static compress.utils.FileUtils.readFile;

import compress.domain.TreeNode;

import java.io.IOException;


public class Main {

    /**
     * Main class for testing, for now.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final String filename = args[0];
        final byte[] fileBytes = readFile(filename);
        long[] freqs = getFreqs(fileBytes);
        TreeNode[] treeNodes = buildNodes(freqs);
        TreeNode treeRoot = buildTree(treeNodes);
        System.out.println(treeRoot);
    }

}
