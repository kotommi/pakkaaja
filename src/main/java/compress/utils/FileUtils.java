package compress.utils;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

/**
 * Class for handling files.
 */
public class FileUtils {

    /**
     * Reads a file from current working directory as array of bytes.
     *
     * @param filename Name of the file to open
     * @return - file as array of bytes
     * @throws IOException              If the file is changed while it is read              -
     * @throws IllegalArgumentException If the file is bigger
     *                                  than the largest possible byte array (2^31 - 1)
     */
    public static byte[] readFile(String filename) throws IOException, IllegalArgumentException {
        final String userDir = System.getProperty("user.dir");
        final String filepath = userDir + "/" + filename;
        System.out.println("reading file: " + filepath);
        return Files.toByteArray(new File(filepath));
    }
}
