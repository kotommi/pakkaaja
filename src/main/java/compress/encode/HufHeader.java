package compress.encode;

import compress.domain.ByteList;
import compress.domain.TreeNode;

public class HufHeader {

    //encode
    private int currentByte = 0;
    private int bitIndex = 0;
    private final int mask = 0xff;
    private final int offset = 128;
    private ByteList encodedBytes;

    // decode
    private byte[] headerBytes;
    private int byteIndex;
    private int position;


    public byte[] encodeHeader(TreeNode root) {
        encodedBytes = new ByteList();
        // reserve 4 + 1 encodedBytes for metadata
        byte b = 0;
        encodedBytes.addAll(b, b, b, b, b);

        headerTreewalk(root);

        // write last "unfinished" byte
        // write encodedBytes.length - 5 to indexes 0-3
        int len = encodedBytes.size() - 5;
        encodedBytes.set(0, (byte) (len >> 24 & mask)); // offset needed?
        encodedBytes.set(1, (byte) (len >> 16 & mask));
        encodedBytes.set(2, (byte) (len >> 8 & mask));
        encodedBytes.set(3, (byte) (len & mask));
        // write bitIndex to index 4
        encodedBytes.set(4, (byte) (bitIndex + offset));

        return encodedBytes.toArray();
    }

    private void headerTreewalk(TreeNode node) {
        currentByte = 0;
        bitIndex = 0;
        if (!node.isLeaf()) {
            // write 0 to mark a parent node
            write(false);
            headerTreewalk(node.getLeft());
            headerTreewalk(node.getRight());
        } else {
            // write 1 to mark a leaf node
            write(true);
            int byteToWrite = node.getId() + offset;
            // then write byte backwards
            for (int i = 7; i >= 0; i--) {
                int bit = (byteToWrite >> i);
                write(bit == 1);
            }
        }
    }

    private void write(boolean bit) {
        if (bitIndex < 8) {
            if (bit) {
                // if bit == 1 flip last bit
                currentByte = currentByte | 0b00000001;
            }
            if (bitIndex != 7) {
                //if not last bit in byte shift left one pos
                currentByte = currentByte << 1;
            }
            bitIndex++;
        } else {
            // byte "full"
            // get the last 8 bits out of n int
            encodedBytes.add((byte) ((currentByte + offset)));
            currentByte = 0;
            bitIndex = 0;
            // do this byte again
            write(bit);
        }
    }

    public TreeNode decodeHeader(byte[] headerBytes) {

        //offsets?????????????
        this.headerBytes = headerBytes;
        int bytesUsed = headerBytes[0] << 24 | headerBytes[1] << 16 | headerBytes[2] << 8 | headerBytes[3];
        int bitsUsed = headerBytes[4];

        //set the global indexes
        byteIndex = 5;
        position = 0;

        TreeNode root = decodeWalk();

        return root;
    }

    private TreeNode decodeWalk() {
        boolean isLeaf = readNext();
        if (!isLeaf) {
            TreeNode parent = new TreeNode(0, Byte.MIN_VALUE);
            parent.setLeft(decodeWalk());
            parent.setRight(decodeWalk());
        } else {
            // read 8 bits for value
            byte id = 0;
            return new TreeNode(0, id);
        }
        return null;

    }

    private boolean readNext() {
        position++;
        if (position >= 8) {
            byteIndex++;
            position = 0;
        }
        byte readByte = headerBytes[byteIndex];
        int readBit = (readByte >> position) & 1;
        return readBit == 1;
    }

}
