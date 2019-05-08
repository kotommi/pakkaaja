package compress.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
            trieRoot.put(byteList, new Codeword(i, 16));
        }

        for (int i = 0; i <= 255; i++) {
            Codeword codeword = trieRoot.get(arr[i]);
            assertEquals(new Codeword(i, 16), codeword);
        }

    }

    @Test
    public void putMultipleBytes() {
        byte[] bytes = {97, 98, 99};
        ByteList bl = new ByteList();
        bl.addAll(bytes);
        TrieNode treeRoot = new TrieNode();
        Codeword code = new Codeword(0, 0);
        treeRoot.put(bl, code);
        Codeword found = treeRoot.get(bl);
        assertNotNull(found);
        assertEquals(code, found);
    }

}