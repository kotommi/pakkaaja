package compress.encode;

import compress.domain.ByteList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LZW {

    public static ArrayList<Integer> encode(byte[] inputBytes) {

        if (inputBytes.length == 0) {
            return null;
        }

        HashMap<ByteList, Integer> dictionary = initDict();
        int nextCode = 256;
        ArrayList<Integer> output = new ArrayList<>();


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
                dictionary.put(test, nextCode);
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

    private static HashMap<ByteList, Integer> initDict() {
        HashMap<ByteList, Integer> dictionary = new HashMap<>();
        for (int i = 0; i <= 255; i++) {
            byte[] b = {(byte) (i - 128)};
            dictionary.put(new ByteList(b), i);
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
        output.addAll(dictionary.get(oldCode).toArray());
        byte single = 0;

        for (int i = 1; i < input.size(); i++) {
            Integer newCode = input.get(i);
            ByteList toWrite;
            // if in dict all is good
            if (dictionary.containsKey(newCode)) {
                toWrite = dictionary.get(newCode);
            } else {
                //
                toWrite = new ByteList();
                toWrite.addAll(dictionary.get(oldCode).toArray());
                toWrite.add(single);

            }
            output.addAll(toWrite.toArray());
            single = toWrite.get(0);

            ByteList old = dictionary.get(oldCode);
            old.add(single);
            if (dictionary.containsValue(old)) {
                System.out.println("fuq");
                System.out.println(old);
            }
            dictionary.put(nextcode, old);
            nextcode++;

            oldCode = newCode;
        }

        return output.toArray();
    }

}
