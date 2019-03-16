package compress.encode;

import static compress.utils.ArrayUtils.nonZeroes;

import compress.domain.TreeNode;
import compress.utils.ArrayUtils;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Huffman {
    public static TreeNode buildTree(TreeNode[] freqs) {
        PriorityQueue<TreeNode> heap = new PriorityQueue<>(Arrays.asList(freqs));
        while (heap.size() > 1) {
            TreeNode left = heap.poll();
            TreeNode right = heap.poll();
            if (left.getCount() < right.getCount()) {
                TreeNode temp = left;
                left = right;
                right = temp;
            }
            byte[] newBytes = ArrayUtils.concat(left.getId(), right.getId());
            TreeNode combined = new TreeNode((left.getCount() + right.getCount()), newBytes);
            combined.setLeft(left);
            combined.setRight(right);
            heap.add(combined);
        }
        return heap.poll();
    }

    public static TreeNode[] buildNodes(long[] freqs) {
        final int size = nonZeroes(freqs);
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
}
