package compress.encode;

import compress.domain.ByteList;
import compress.domain.Codeword;

import java.util.HashMap;

public class LZW {

    private final static int CODE_LENGTH = 16;
    private final static int MAX_CODES = 65536;

    /**
     * Compresses data with LZW, fixed-length 16-bit codes.
     *
     * @param inputBytes
     * @return Compressed data.
     */
    public static byte[] encode(byte[] inputBytes) {


        if (inputBytes.length == 0) {
            return new byte[0];
        }

        HashMap<ByteList, Codeword> dictionary = initEncodeDict();
        int nextCode = 256;
        ByteList outputBytes = new ByteList();


        ByteList current = new ByteList(10);
        current.add(inputBytes[0]);

        // main loop
        for (int i = 1; i < inputBytes.length; i++) {
            final byte next = inputBytes[i];
            current.add(next);

            // if in dict, try a longer string of bytes
            if (!dictionary.containsKey(current)) {
                // if not in  dict, add to dict and
                ByteList newKey = new ByteList(current.size());
                newKey.addAll(current);
                dictionary.put(newKey, new Codeword(nextCode, CODE_LENGTH));
                nextCode++;
                // remove the last byte before writing
                current.remove();
                // write the bytestring that was in dict
                writeCodeword(dictionary.get(current), outputBytes);

                // start new string from the last byte that
                // wasn't added.
                ByteList newlist = new ByteList(10);
                newlist.add(next);
                current = newlist;
                // reset dict if full
                if (dictionary.size() == MAX_CODES) {
                    dictionary = initEncodeDict();
                    nextCode = 256;
                }
            }
        }
        // write the last byte(s)
        writeCodeword(dictionary.get(current), outputBytes);

        return outputBytes.toArray();

    }

    /**
     * Writes codewords as bytes to the end of the list.
     * Currently only supports 16-bit codes.
     *
     * @param code   Codeword to write.
     * @param output List being written to.
     */
    private static void writeCodeword(Codeword code, ByteList output) {
        int bits = code.getIntValue();
        byte first = (byte) ((bits >> 8) & 0xFF);
        byte second = (byte) (bits & 0xFF);
        output.addAll(first, second);
    }

    /**
     * Creates an initial dictionary for encoding.
     * Contains all single byte-values.
     *
     * @return Initial dictionary for encoding.
     */
    private static HashMap<ByteList, Codeword> initEncodeDict() {
        HashMap<ByteList, Codeword> dictionary = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            // shift the int to byte range
            byte[] b = {(byte) (i - 128)};
            dictionary.put(new ByteList(b), new Codeword(i, CODE_LENGTH));
        }
        return dictionary;
    }

    /**
     * Creates an initial dictionary for decoding.
     * Contains all single byte-values.
     *
     * @return Initial dictionary for decoding.
     */
    private static HashMap<Codeword, ByteList> initDecodeDict() {
        HashMap<Codeword, ByteList> dictionary = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            // shift the int to byte range
            byte[] b = {(byte) (i - 128)};
            Codeword c = new Codeword(i, CODE_LENGTH);
            dictionary.put(c, new ByteList(b));
        }
        return dictionary;
    }

    /**
     * Converts encoded bytes to codewords.
     * Currently supports fixed-length 16-bit codes.
     *
     * @param inputBytes compressed data.
     * @return Array of codewords in original order.
     */
    private static Codeword[] bytesToCodes(byte[] inputBytes) {
        Codeword[] codes = new Codeword[inputBytes.length / 2];
        int j = 0;
        for (int i = 0; i < inputBytes.length - 1; i += 2) {
            byte first = inputBytes[i];
            byte second = inputBytes[i + 1];
            int result = ((first & 0xFF) << 8) | (second & 0xFF);
            codes[j] = new Codeword(result, CODE_LENGTH);
            j++;
        }
        return codes;

    }

    /**
     * Decodes LZW-compressed data.
     *
     * @param inputBytes
     * @return uncompressed bytes.
     */
    public static byte[] decode(byte[] inputBytes) {

        // parse codewords used from the input
        Codeword[] input = bytesToCodes(inputBytes);

        // create initial dictionary since
        // we know what was used in encoding
        HashMap<Codeword, ByteList> dictionary = initDecodeDict();
        int nextCode = 256;

        ByteList output = new ByteList();

        // Handle first code by hand
        // to init oldCode

        Codeword oldCode = input[0];
        ByteList nextOutput = dictionary.get(oldCode);
        output.addAll(nextOutput);


        for (int i = 1; i < input.length; i++) {
            Codeword newCode = input[i];

            // if in dict all is good
            if (dictionary.containsKey(newCode)) {
                nextOutput = dictionary.get(newCode);
            } else {
                // Codeword wasn't in dict so it must
                // be
                nextOutput = new ByteList();
                ByteList temp = dictionary.get(oldCode);
                nextOutput.addAll(temp);
                nextOutput.add(temp.get(0));

            }
            output.addAll(nextOutput);

            ByteList newDictEntry = new ByteList();
            newDictEntry.addAll(dictionary.get(oldCode));
            newDictEntry.add(nextOutput.get(0));
            if (dictionary.size() == MAX_CODES) {
                dictionary = initDecodeDict();
                nextCode = 256;
            }

            dictionary.put(new Codeword(nextCode, CODE_LENGTH), newDictEntry);
            nextCode++;

            oldCode = newCode;
        }

        return output.toArray();
    }

}
