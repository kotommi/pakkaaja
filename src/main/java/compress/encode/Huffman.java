package compress.encode;

import static compress.utils.ArrayUtils.nonZeroes;

import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

import java.util.Arrays;
import java.util.BitSet;
import java.util.PriorityQueue;

public class Huffman {

    public static TreeNode buildTree(TreeNode[] nodes) {
        PriorityQueue<TreeNode> heap = new PriorityQueue<>(Arrays.asList(nodes));
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
        byte[] newBytes = ArrayUtils.concat(left.getId(), right.getId());
        TreeNode combined = new TreeNode((left.getCount() + right.getCount()), newBytes);
        combined.setLeft(left);
        combined.setRight(right);
        return combined;
    }

    public static void inOrderTreeWalk(TreeNode node, BitSet bits, BitSet[] table) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            //taulukko[node.getBytes[0] =
            table[node.getId()[0]] = bits;
        }
        int len = bits.length();
        BitSet left = (BitSet) bits.clone();
        left.set(len + 1, false);
    }
}
