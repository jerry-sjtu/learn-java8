package org.dcharm.java.dnn;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

/**
 * Create by qiangwang on 2018/10/16
 */
public class PreProcessor {
    private static Map<Character, Integer> CHAR_TO_INT = getCharToInt();

    public static void main(String[] args) {

    }

    private static List<String> getShakespeareAsList(int sequenceLength) throws IOException {
        //The Complete Works of William Shakespeare
        //5.3MB file in UTF-8 Encoding, ~5.4 million characters
        //https://www.gutenberg.org/ebooks/100
//        String url = "https://s3.amazonaws.com/dl4j-distribution/pg100.txt";
//        String tempDir = System.getProperty("java.io.tmpdir");
//        String fileLocation = tempDir + "/Shakespeare.txt";    //Storage location from downloaded file
        String fileLocation = "/tmp/Shakespeare.txt";
        File f = new File(fileLocation);
        if (!f.exists()) {
            FileUtils.copyURLToFile(new URL(""), f);
            System.out.println("File downloaded to " + f.getAbsolutePath());
        } else {
            System.out.println("Using existing text file at " + f.getAbsolutePath());
        }

        if (!f.exists()) throw new IOException("File does not exist: " + fileLocation);    //Download problem?

        String allData = getDataAsString(fileLocation);

        List<String> list = new ArrayList<>();
        int length = allData.length();
        int currIdx = 0;
        while (currIdx + sequenceLength < length) {
            int end = currIdx + sequenceLength;
            String substr = allData.substring(currIdx, end);
            currIdx = end;
            list.add(substr);
        }
        return list;
    }

    private static String getDataAsString(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(new File(filePath).toPath(), Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (CHAR_TO_INT.containsKey(chars[i])) sb.append(chars[i]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public static Map<Character, Integer> getCharToInt() {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = getValidCharacters();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], i);
        }
        return map;
    }

    private static char[] getValidCharacters() {
        List<Character> validChars = new LinkedList<>();
        for (char c = 'a'; c <= 'z'; c++) validChars.add(c);
        for (char c = 'A'; c <= 'Z'; c++) validChars.add(c);
        for (char c = '0'; c <= '9'; c++) validChars.add(c);
        char[] temp = {'!', '&', '(', ')', '?', '-', '\'', '"', ',', '.', ':', ';', ' ', '\n', '\t'};
        for (char c : temp) validChars.add(c);
        char[] out = new char[validChars.size()];
        int i = 0;
        for (Character c : validChars) out[i++] = c;
        return out;
    }
}
