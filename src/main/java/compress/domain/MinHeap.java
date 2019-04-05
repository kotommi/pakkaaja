package compress.domain;

/**
 * MinimumHeap implementation based on CLRS
 */
public class MinHeap {
    private TreeNode[] heap;
    private int size;

    /**
     * Create empty heap with size 256.
     */
    public MinHeap() {
        //1-indexing for simple index math
        this.heap = new TreeNode[257];
        this.size = 0;
    }

    /**
     * Adds all nodes to the heap.
     *
     * @param nodes Collection of treenodes to add
     */
    public void addAll(TreeNode... nodes) {
        for (TreeNode t : nodes) {
            add(t);
        }
    }

    /**
     * Maintains the min-heap property.
     *
     * @param i index where to start
     */
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


    /**
     * Swap two nodes in the array.
     *
     * @param i index
     * @param j index
     */
    private void swap(int i, int j) {
        final TreeNode temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Remove and return smallest element from heap.
     *
     * @return "smallest" node in heap, null if empty
     */
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

    /**
     * Adds a node to the heap and heapifies it to right position.
     *
     * @param node node to add
     */
    public void add(TreeNode node) {
        size++;
        heap[size] = node;
        int i = size;
        while (i > 1 && heap[parent(i)].getCount() > heap[i].getCount()) {
            swap(parent(i), i);
            i = parent(i);
        }
    }


    /**
     * Get the parent's index for element at index i
     *
     * @param i index
     * @return index of parent
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Logical size of the heap.
     *
     * @return Amount of elements in the heap
     */
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
