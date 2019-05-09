package compress.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TrieNodeTest {

    @Test
    public void putSingle() {
        TrieNode trieRoot = new TrieNode();
        ByteList[] arr = new ByteList[256];
        for (int i = 0; i <= 255; i++) {
            // shift the int to byte range
            byte[] b = {(byte) (i - 128)};
            ByteList byteList = new ByteList(b);
            arr[i] = byteList;
            trieRoot.put(byteList, i);
        }

        for (int i = 0; i <= 255; i++) {
            int code = trieRoot.get(arr[i]);
            assertEquals(i, code);
        }

        // failcase
        byte[] bytes = {97, 98, 99};
        ByteList bl = new ByteList();
        bl.addAll(bytes);
        assertEquals(-1, trieRoot.get(bl));

    }

    @Test
    public void putMultipleBytes() {
        byte[] bytes = {97, 98, 99};
        ByteList bl = new ByteList();
        bl.addAll(bytes);
        TrieNode treeRoot = new TrieNode();
        int code = 0;
        treeRoot.put(bl, code);
        int found = treeRoot.get(bl);
        assertEquals(code, found);
    }

}