package compress.encode;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Huffman {

    /**
     * Builds a Huffman-tree from orphan nodes.
     *
     * @param nodes array of lone TreeNodes
     * @return A Huffman-tree where leaves are sorted by count.
     */
    public static TreeNode buildTree(TreeNode[] nodes) {
        PriorityQueue<TreeNode> heap = new PriorityQueue<>(Arrays.asList(nodes));
        System.out.println("heap: " + heap);
        while (heap.size() > 1) {
            TreeNode left = heap.poll();
            TreeNode right = heap.poll();
            TreeNode combined = combineNode(left, right);
            heap.add(combined);
        }
        return heap.poll();
    }

    /**
     * Builds TreeNodes from a frequency list.
     *
     * @param freqs count of occurences for each byte
     * @return an array of TreeNodes
     */
    public static TreeNode[] buildNodes(long[] freqs) {
        final int size = ArrayUtils.nonZeroes(freqs);
        System.out.println(size + "/256 bytes used");
        final TreeNode[] treeNodes = new TreeNode[size];
        int j = 0;

        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] != 0) {
                treeNodes[j] = new TreeNode(freqs[i], (byte) i);
                j++;
            }
        }
        return treeNodes;
    }

    /**
     * Combines two nodes as one with sum count, and left and right as children.
     *
     * @param left  TreeNode with count > right.count
     * @param right TreeNode with count < left.count
     * @return A node with count.right + count.left and empty bytes since they aren't needed
     */
    private static TreeNode combineNode(TreeNode left, TreeNode right) {
        if (left.getCount() < right.getCount()) {
            final TreeNode temp = left;
            left = right;
            right = temp;
        }
        final TreeNode combined = new TreeNode(left.getCount() + right.getCount());
        combined.setLeft(left);
        combined.setRight(right);
        return combined;
    }

    /**
     * Recursive inorder treewalk-method that mutates parameter table.
     *
     * @param node  TreeNode to process
     * @param bits  Codeword representing the shortform version for a particular byte
     * @param table lookup-table for Huffman codes
     */
    private static void inOrderTreeWalk(TreeNode node, Codeword bits, Codeword[] table) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            bits.reverse();
            final int offset = 128;
            table[(int) node.getId()[0] + offset] = bits;
            return;
        }
        // could probably reuse original bits for one of these
        final Codeword left = bits.getCopy();
        left.setNext(false);
        final Codeword right = bits.getCopy();
        right.setNext(true);
        inOrderTreeWalk(node.getLeft(), left, table);
        inOrderTreeWalk(node.getRight(), right, table);
    }


    /**
     * Builds a lookup-table for Huffman codes.
     *
     * @param treeRoot root of a Huffman-tree
     * @return array where arr[byte + 128] is the short code for that byte
     */
    public static Codeword[] buildLookupTable(TreeNode treeRoot) {
        final int BYTEMAX = 256;
        Codeword[] table = new Codeword[BYTEMAX];
        inOrderTreeWalk(treeRoot, new Codeword(), table);
        return table;
    }
}
