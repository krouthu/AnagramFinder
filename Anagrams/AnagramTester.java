// Keerthana Routhu
// krouthu
// 5/20/18
// PA 3 - Anagrams
// AnagramTester.java -- tests all of the methods contained in Anagram.java; tester class for Anagram.java

import java.util.*;

public class AnagramTester {
    public static void main (String [] args) {
        FindAnagrams.populateArray(args[0]);
        FindAnagrams.createCodes();

        // checks String to Anagram constructor		
        String a = "abcDeFg";
        Anagram b = new Anagram (a);

        // checks character array to Anagram constructor
        char [] c = {'a', 'b', 'C', 'd', 'E', 'Z', 'h', 'y'};
        Anagram d = new Anagram (c);
		
        System.out.println("String constructor tester: " +b.print());
        System.out.println("String constructor tester: " +d.print());

        // checks if two Strings are anagrams of each other -- should print true
        Anagram e = new Anagram ("charm");
        Anagram f = new Anagram ("march");
        System.out.println(f.print()+ " is an anagram of "+e.print()+ " (true/false): " +e.isAnagram(f));

        // checks if two Strings are anagrams of each other -- should print false
        Anagram g = new Anagram ("tommarvoloriddle");
        Anagram h = new Anagram ("iamlordvoldemort");
        System.out.println(h.print()+ " is an anagram of "+g.print()+ " (true/false): " +g.isAnagram(h));

        // finds all anagrams of "items"
        Anagram i = new Anagram("items");
        List<String> list = new LinkedList<String>();
        list = i.anagramsOf();
        int n = list.size();
        System.out.println("anagrams of " + i.print() + ": ");
        for (int j = 0; j < n; j++) {
            String temp = list.remove(0);
            if (!(i.word).equals(temp))  System.out.println(temp);
        }	
    }
}
