import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class main {

    public static void main(String[] args) {

        /*
        Jeg har redigeret teksten ned til kun Alice i Wonderland minus alt det der står i introduktionen og i appendix.
         */

        System.out.println("Total number of letters in the text: " + numberOfLetters());
        System.out.println("Total number of words in the text: " + numberOfWords());
        wordsByCount();

    }

    public static int numberOfLetters() {

        int counter = 0;

        try {
            Scanner word = new Scanner(new File("AliceWonderland.txt"));

            while (word.hasNext()) {
                String singleWord = word.next();

                StringBuilder sb = new StringBuilder(singleWord);

                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) == ',' || sb.charAt(i) == '.' || sb.charAt(i) == ':' || sb.charAt(i) == '(' || sb.charAt(i) == ')' || sb.charAt(i) == '\'' || sb.charAt(i) == '?' ||
                            sb.charAt(i) == '*') {
                        sb.deleteCharAt(i);
                        singleWord = sb.toString();
                        i = i-1;
                    }
                }

                int tokens = singleWord.length();
                counter += tokens;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return counter;
    }

    public static int numberOfWords() {

        int counter = 0;

        try {
            Scanner read = new Scanner(new File("AliceWonderland.txt"));



            while (read.hasNext()) {
                counter++;
                String current = read.next();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return counter;
    }

    public static void wordsByCount() {

        HashMap<String, Integer> words = new HashMap<>();
        int count = 1;

        try {
            Scanner read = new Scanner(new File("AliceWonderland.txt"));

            while (read.hasNext()) {
                String key = read.next().toLowerCase();
                StringBuilder sb = new StringBuilder(key);

                for (int i = 0; i < sb.length(); i++) {
                    if (sb.charAt(i) == ',' || sb.charAt(i) == '.' || sb.charAt(i) == ':' || sb.charAt(i) == '(' || sb.charAt(i) == ')' || sb.charAt(i) == '\'' || sb.charAt(i) == '?') {
                        sb.deleteCharAt(i);
                        key = sb.toString();
                        i = i-1;
                    }
                }

                if (words.get(key) == null) {
                    words.put(key, count);
                } else {
                    words.put(key, words.get(key)+1);
                }
            }
            // Tree Map to sort the results
            Map<String, Integer> treeMap = new TreeMap<>(words);

            for (Map.Entry<String,Integer> entry : treeMap.entrySet()) {
                if (entry.getValue() > 20) {
                    if (entry.getKey().length() < 2) {
                        System.out.println("Key = " + entry.getKey() + "\t\t\t\t Value = " + entry.getValue());
                    } else if (entry.getKey().length() > 5) {
                        if (entry.getKey().length() > 8) {
                            System.out.println("Key = " + entry.getKey() + "\t Value = " + entry.getValue());
                        } else {
                            System.out.println("Key = " + entry.getKey() + "\t\t Value = " + entry.getValue());
                        }
                    } else
                    System.out.println("Key = " + entry.getKey() + "\t\t\t Value = " + entry.getValue());
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
