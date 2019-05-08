package compress.domain;

/**
 * Trie data structure for fast-ish searching.
 * Used for LZW-compression.
 */
public class TrieNode {
    private TrieNode[] children;
    private int code;

    public TrieNode() {

        this.children = new TrieNode[256];
        this.code = -1;
    }

    public void put(ByteList bytes, int code) {
        TrieNode current = this;
        for (int i = 0; i < bytes.size(); i++) {
            int byteIndex = bytes.get(i) + 128;
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

    public int get(ByteList bytes) {
        TrieNode current = this;
        for (int i = 0; i < bytes.size(); i++) {
            int byteIndex = bytes.get(i) + 128;
            TrieNode child = current.children[byteIndex];
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

    public boolean contains(ByteList bytes) {
        return this.get(bytes) != -1;
    }
}
