package compress.domain;

import compress.domain.TreeNode;

/**
 * MinimumHeap implementation based on CLRS
 */
public class MinHeap {
    private TreeNode[] heap;
    private int size;

    public MinHeap() {
        //1-indexing simple index math
        this.heap = new TreeNode[257];
        this.size = 0;
    }

    public void addAll(TreeNode[] nodes) {
        for (TreeNode t : nodes) {
            add(t);
        }
    }

    private void heapify(int i) {
        if (i > size || i < 1) {
            return;
        }
        final int left = 2 * i;
        final int right = (2 * i) + 1;
        int smallest = i;

        if ((left <= size) && (heap[left].getCount() < heap[smallest].getCount())) {
            smallest = left;
        }
        if ((right <= size) && (heap[right].getCount() < heap[smallest].getCount())) {
            smallest = right;
        }

        if (smallest != i) {
            swap(i, smallest);
            heapify(smallest);
        }

    }

    private void swap(int i, int j) {
        final TreeNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    public TreeNode poll() {
        if (size == 0) {
            return null;
        }
        final TreeNode ret = heap[1];
        heap[1] = heap[size];
        size--;
        heapify(1);
        return ret;
    }

    public void add(TreeNode node) {
        size++;
        heap[size] = node;
        int i = size;
        while (i > 1 && heap[parent(i)].getCount() > heap[i].getCount()) {
            swap(parent(i), i);
            i = parent(i);
        }
    }

    private int parent(int i) {
        return i / 2;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        String arr = "";
        for (int i = 1; i <= size; i++) {
            arr += heap[i].toString() + " ";
        }
        return "MinHeap{" +
                "heap=" + arr +
                ", size=" + size +
                '}';
    }
}
