package compress.domain;

/**
 * Trie data structure for fast-ish searching.
 * Used for LZW-compression.
 */
public class TrieNode {
    private TrieNode[] children;
    private int code;

    /**
     * Creates new node with no children and code as -1 == no code.
     */
    public TrieNode() {
        this.children = new TrieNode[256];
        this.code = -1;
    }

    /**
     * Adds a code to the end of the chain of bytes
     * in bytelist.
     * arr[0]->arr[1]->arr[2]->code
     *
     * @param bytes Trie-node chain to follow.
     * @param code  Code being added.
     */
    public void put(ByteList bytes, int code) {
        TrieNode current = this;
        for (int i = 0; i < bytes.size(); i++) {
            final int byteIndex = bytes.get(i) + 128;
            // don't reset the child node if it already exists
            if (current.children[byteIndex] == null) {
                TrieNode newNode = new TrieNode();
                current.children[byteIndex] = newNode;
                current = newNode;
            } else {
                current = current.children[byteIndex];
            }

            if (i == bytes.size() - 1) {
                current.code = code;
            }
        }
    }

    /**
     * Finds a code at the end of bytechain.
     *
     * @param bytes Bytechain to follow.
     * @return Code at the end of bytechain. -1 if not found.
     */
    public int get(ByteList bytes) {
        TrieNode current = this;
        for (int i = 0; i < bytes.size(); i++) {
            final int byteIndex = bytes.get(i) + 128;
            final TrieNode child = current.children[byteIndex];
            // Terminate search as early as possible
            if (child == null) {
                return -1;
            }
            current = child;
            if (i == bytes.size() - 1) {
                return current.code;
            }
        }
        return -1;
    }

    /**
     * Convenience wrapper for readability.
     *
     * @param bytes chain to follow
     * @return true if already in trie, false otherwise.
     */
    public boolean contains(ByteList bytes) {
        return this.get(bytes) != -1;
    }
}
