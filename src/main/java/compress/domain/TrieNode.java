package compress.domain;

/**
 * Trie data structure for fast-ish searching.
 * Used for LZW-compression.
 */
public class TrieNode {
    private TrieNode[] children;
    private Codeword code;

    public TrieNode() {
        this.children = new TrieNode[256];
    }

    public void put(ByteList bytes, Codeword code) {
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

    public Codeword get(ByteList bytes) {
        TrieNode current = this;
        for (int i = 0; i < bytes.size(); i++) {
            int byteIndex = bytes.get(i) + 128;
            TrieNode child = current.children[byteIndex];
            // Terminate search as early as possible
            if (child == null) {
                return null;
            }
            current = child;
            if (i == bytes.size() - 1) {
                return current.code;
            }
        }
        return null;
    }

    public boolean contains(ByteList bytes) {
        return this.get(bytes) != null;
    }
}
