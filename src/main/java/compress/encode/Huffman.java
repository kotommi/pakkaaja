package compress.encode;

import static compress.utils.ArrayUtils.nonZeroes;

import compress.domain.Codeword;
import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Huffman {

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

    public static TreeNode[] buildNodes(long[] freqs) {
        final int size = nonZeroes(freqs);
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

    public static TreeNode combineNode(TreeNode left, TreeNode right) {
        if (left.getCount() < right.getCount()) {
            TreeNode temp = left;
            left = right;
            right = temp;
        }
        TreeNode combined = new TreeNode((left.getCount() + right.getCount()), new byte[0]);
        combined.setLeft(left);
        combined.setRight(right);
        return combined;
    }

    public static void inOrderTreeWalk(TreeNode node, Codeword bits, Codeword[] table) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            //taulukko[node.getBytes[0] =
            bits.reverse();
            table[(int) node.getId()[0] + 128] = bits;
            return;
        }
        // could probably reuse original bits for one of these
        Codeword left = bits.clone();
        left.setNext(false);
        Codeword right = bits.clone();
        right.setNext(true);
        inOrderTreeWalk(node.getLeft(), left, table);
        inOrderTreeWalk(node.getRight(), right, table);
    }

    public static Codeword[] buildLookupTable(TreeNode treeRoot) {
        Codeword[] table = new Codeword[256];
        inOrderTreeWalk(treeRoot, new Codeword(), table);
        return table;
    }
}
