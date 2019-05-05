package compress.encode;

import compress.domain.ByteList;
import compress.domain.Codeword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LZW {

    private final static int CODE_LENGTH = 12;

    public static ArrayList<Codeword> encode(byte[] inputBytes) {

        if (inputBytes.length == 0) {
            return null;
        }

        HashMap<ByteList, Codeword> dictionary = initEncodeDict();
        int nextCode = 256;
        ArrayList<Codeword> output = new ArrayList<>();


        ByteList current = new ByteList(1);
        current.add(inputBytes[0]);

        //System.out.println(current);
        for (int i = 1; i < inputBytes.length; i++) {
            byte next = inputBytes[i];
            ByteList test = new ByteList(current.toArray());
            test.add(next);
            if (dictionary.containsKey(test)) {
                //System.out.println(i);
                //System.out.println("cur: " + current + ",test: " + test);
                current = test;
            } else {
                dictionary.put(test, new Codeword(nextCode, CODE_LENGTH));
                nextCode++;
                //System.out.println("added to dict: " + test);
                output.add(dictionary.get(current));
                ByteList newlist = new ByteList(1);
                newlist.add(next);
                current = newlist;
            }
        }
        output.add(dictionary.get(current));
        return output;

    }

    private static HashMap<ByteList, Codeword> initEncodeDict() {
        HashMap<ByteList, Codeword> dictionary = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            byte[] b = {(byte) (i - 128)};
            dictionary.put(new ByteList(b), new Codeword(i, CODE_LENGTH));
        }
        return dictionary;
    }

    private static HashMap<Integer, ByteList> initDecodeDict() {
        HashMap<Integer, ByteList> dictionary = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            byte[] b = {(byte) (i - 128)};
            dictionary.put(i, new ByteList(b));
        }
        return dictionary;
    }

    public static byte[] decode(List<Integer> input) {
        // create initial dictionary since
        // we know what was used in encoding
        HashMap<Integer, ByteList> dictionary = initDecodeDict();
        int nextcode = 256;

        ByteList output = new ByteList();

        // handle first code by hand
        Integer oldCode = input.get(0);
        ByteList nextOutput = dictionary.get(oldCode);
        output.addAll(nextOutput.toArray());


        for (int i = 1; i < input.size(); i++) {
            Integer newCode = input.get(i);

            // if in dict all is good
            if (dictionary.containsKey(newCode)) {
                nextOutput = dictionary.get(newCode);
            } else {
                //
                nextOutput = new ByteList();
                nextOutput.addAll(dictionary.get(oldCode).toArray());
                nextOutput.add(dictionary.get(oldCode).get(0));

            }
            output.addAll(nextOutput.toArray());

            ByteList newDictEntry = new ByteList();
            newDictEntry.addAll(dictionary.get(oldCode).toArray());
            newDictEntry.add(nextOutput.get(0));
            dictionary.put(nextcode, newDictEntry);
            nextcode++;

            oldCode = newCode;
        }
        System.out.println(dictionary);

        return output.toArray();
    }

}
