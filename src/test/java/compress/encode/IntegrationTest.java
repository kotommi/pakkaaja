package compress.encode;

import compress.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Test class for making sure the data stays intact
 * after a round of compression and decompression.
 */
public class IntegrationTest {

    @Test
    public void HuffmanEncodeDecodeTest() throws IOException {
        final String shortFile = "testi.txt";
        testFile(shortFile);

        final String textFile = "tarinat.txt";
        testFile(textFile);

        final String pdfFile = "testi.pdf";
        testFile(pdfFile);

    }

    private void testFile(String filename) throws IOException {
        String testFileName = "src/test/resources/" + filename;
        final byte[] originalFileBytes = FileUtils.readFile(testFileName);
        System.out.println("Filename: " + testFileName);
        System.out.println("Original length: " + originalFileBytes.length + " bytes");

        //Huffman
        final byte[] hufEncoded = Encode.encodeHuffman(originalFileBytes);
        System.out.println("Huffman length: " + hufEncoded.length + " bytes");
        final byte[] hufDecoded = Decode.decodeHuffman(hufEncoded);
        Assert.assertArrayEquals(originalFileBytes, hufDecoded);

        //LZW
        final byte[] lzwEncoded = LZW.encode(originalFileBytes);
        System.out.println("LZW length: " + lzwEncoded.length + " bytes");
        final byte[] lzwDecoded = LZW.decode(lzwEncoded);
        Assert.assertArrayEquals(originalFileBytes, lzwDecoded);
    }
}
