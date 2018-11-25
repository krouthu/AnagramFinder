// Keerthana Routhu
// krouthu
// 5/20/18
// PA 3 - Anagrams
// FindAnagrams.java -- contains main method that allows user to find all possible anagrams of a certain String; allows user to input a certain String and prints out anagrams of the word in the dictionary

import java.io.*;
import java.util.*;

public class FindAnagrams {
    
    static String [] dictionary = null;
    static Map<String, List<String>> hash = new Hashtable<String, List<String>>();

    // reads in wordList.txt file and fills in dictionary array	
    public static void populateArray(String fileName) {
        try {
            File f = new File (fileName);
            Scanner sc = new Scanner (f);
            int dictionaryLen = sc.nextInt();
            dictionary = new String [dictionaryLen];
            for (int i = 0; i < dictionaryLen; i++) {
                dictionary[i] = sc.next();
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    // creates codes by sorting the letters of each word in the dictionary -- this is the code; then, the code will be a hash key, and we hash dictionary words with the same code to the same key; we do this by hashtable chaining
    public static void createCodes () {
        String [] codes = new String[dictionary.length];
        for (int i = 0; i < dictionary.length; i++) {
            char [] temp = dictionary[i].toCharArray();
            Arrays.sort(temp);
            codes[i] = new String(temp);
            List <String> list = null;

            if (!hash.containsKey(codes[i])) {
                list = new LinkedList<String>();
                hash.put(codes[i], list);
            }
            list = hash.get(codes[i]);
            list.add(dictionary[i]);	
        }
    }

    // main method; allows user to choose which Strings they want anagrams of
    public static void main (String [] args) {
        // only need to fill dictionary array, find codes, and hash each word to the respective key once
        FindAnagrams.populateArray(args[0]);
        FindAnagrams.createCodes();

        Scanner sc = new Scanner(System.in);
        String inp = null;
        String doAnother = null;
        do {
            System.out.println("type a string of letters");
            inp = sc.next();
            Anagram i = new Anagram(inp);
            List<String> list = new LinkedList<String>();
            list = i.anagramsOf();
            int n = list.size();
            for (int j = 0; j < n; j++) {
                String temp = list.remove(0);
                if (!(i.word).equals(temp))  System.out.println(temp);
            }
            System.out.println("Do another (y/n)?");
            doAnother = sc.next();
        } while (doAnother.equals("y"));
    }	
}
