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
        byte[] originalFileBytes = FileUtils.readFile(testFileName);

        byte[] encodedBytes = Encode.encodeHuffman(originalFileBytes);
        byte[] decodedBytes = Decode.decodeHuffman(encodedBytes);

        Assert.assertArrayEquals(originalFileBytes, decodedBytes);
    }
}
