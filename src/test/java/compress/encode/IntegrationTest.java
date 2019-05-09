package compress.encode;

import compress.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

/**
 * Test class for making sure the data stays intact
 * after a round of compression and decompression.
 */
public class IntegrationTest {

    @Test
    public void compressDecompressTest() throws IOException {
        final String shortFile = "testi.txt";
        testFile(shortFile);

        final String textFile = "tarinat.txt";
        testFile(textFile);

        final String pdfFile = "testi.pdf";
        testFile(pdfFile);

        // bugged file from issue #2 in gh
        final String bugiFile = "bugi.txt";
        testFile(bugiFile);
    }

    @Test
    public void testRandomData() {
        for (int i = 0; i < 100; i++) {
            final Random random = new Random();
            final byte[] bytes = new byte[1000];
            random.nextBytes(bytes);

            //Huffman
            testHuffman(bytes, false);

            //LZW
            testLZW(bytes, false);
        }
    }

    private void testFile(String filename) throws IOException {
        String testFileName = "src/test/resources/" + filename;
        final byte[] originalFileBytes = FileUtils.readFile(testFileName);
        System.out.println("Filename: " + testFileName);
        System.out.println("Original length: " + originalFileBytes.length + " bytes");

        //Huffman
        testHuffman(originalFileBytes, true);
        System.out.println("------------------------------------");

        //LZW
        testLZW(originalFileBytes, true);
        System.out.println("------------------------------------");

    }

    private void testLZW(byte[] originalFileBytes, boolean print) {
        long start, finish, diff;
        start = System.currentTimeMillis();
        final byte[] lzwEncoded = LZW.encode(originalFileBytes);
        finish = System.currentTimeMillis();
        diff = finish - start;
        diff = diff == 0 ? 1 : diff;
        if (print) {
            System.out.println("LZW encode took " + diff + " ms");
            System.out.println("speed: " + originalFileBytes.length / diff + " bytes / ms");
            System.out.println("LZW length: " + lzwEncoded.length + " bytes");

        }
        start = System.currentTimeMillis();
        final byte[] lzwDecoded = LZW.decode(lzwEncoded);
        finish = System.currentTimeMillis();
        diff = finish - start;
        diff = diff == 0 ? 1 : diff;
        if (print) {
            System.out.println("LZW decode took " + diff + " ms");
        }
        Assert.assertArrayEquals(originalFileBytes, lzwDecoded);
    }

    private void testHuffman(byte[] originalFileBytes, boolean print) {
        long start, finish, diff;

        start = System.currentTimeMillis();
        final byte[] hufEncoded = Encode.encodeHuffman(originalFileBytes);
        finish = System.currentTimeMillis();
        diff = finish - start;
        diff = diff == 0 ? 1 : diff;
        if (print) {
            System.out.println("Huffman encode took " + diff + " ms");
            System.out.println("speed: " + originalFileBytes.length / diff + " bytes / ms");
            System.out.println("Huffman length: " + hufEncoded.length + " bytes");
        }

        start = System.currentTimeMillis();
        final byte[] hufDecoded = Decode.decodeHuffman(hufEncoded);
        finish = System.currentTimeMillis();
        diff = finish - start;
        diff = diff == 0 ? 1 : diff;
        if (print) {
            System.out.println("Huffman decode took " + diff + " ms");
        }
        Assert.assertArrayEquals(originalFileBytes, hufDecoded);
    }
}
