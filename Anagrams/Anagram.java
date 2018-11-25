// Keerthana Routhu
// krouthu
// 5/20/18
// PA 3 - Anagrams
// Anagram.java -- contains constructors of Anagram type and methods to find the anagrams of a String

import java.util.*;

public class Anagram {

    String word = null;

    // constructs an Anagram object from a String	
    public Anagram (String w) {
        word = w;
        for (int i = 0; i < w.length(); i++) {
            if (w.charAt(i) >= 65 && w.charAt(i) <= 90)
                word = word.substring(0, i) + Character.toString((char)(w.charAt(i) + ' ')) + word.substring(i+1);
        }
    }

    // constructs an Anagram object from an array of characters
    public Anagram (char [] w) {
        word = "";
        for (int i = 0; i < w.length; i++) {
            if (w[i] >= 65 && w[i] <= 90)
                word = word + Character.toString((char)(w[i] + ' '));
            else
                word = word + Character.toString(w[i]);
        }
    }

    // print method to print the anagram
    public String print() {
        return word;
    }

    // checks if two Strings are anagrams of each other by finding and comparing the codes of both
    public boolean isAnagram (Anagram B) {
        String a = word;
        String b = B.word;
		
        char [] tempA = a.toCharArray();
        char [] tempB = b.toCharArray();
		
        Arrays.sort(tempA);
        Arrays.sort(tempB);
		
        a = new String (tempA);
        b = new String (tempB);
		
        return (a.equals(b));
    }

    // method to find the list that is hashed to a particular code of the word -- finds all anagrams of a given String in the wordList.txt file
    public List<String> anagramsOf () {
        char [] temp = word.toCharArray();
        Arrays.sort(temp);
        String code = new String(temp);
        List<String> list = null;
        if (FindAnagrams.hash.containsKey(code))  list = FindAnagrams.hash.get(code);
        return list;	
    }	
}
