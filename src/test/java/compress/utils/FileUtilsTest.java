package compress.utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FileUtilsTest {
    private static File file;
    final static String filename = "test.test";

    @BeforeClass
    public static void setUp() throws IOException {
        file = new File(filename);
        if (file.exists()) {
            file.delete();
            file = new File(filename);
        }
        file.createNewFile();
        String content = "ab";
        FileWriter fw = new FileWriter(filename);
        fw.write(content);
        fw.close();
    }

    @Test
    public void readFileTest() throws IOException {
        byte[] bytes = FileUtils.readFile(filename);
        assertEquals(97, bytes[0]);
        assertEquals(98, bytes[1]);
    }

    @Test
    public void readEmptyFileTest() throws IOException {
        final String fn = "empty";
        File f = new File(fn);
        f.createNewFile();
        byte[] bytes = FileUtils.readFile(fn);
        assertEquals(0, bytes.length);
        f.delete();
    }

    // What to do with nonexistent file?


    @AfterClass
    public static void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }
}
