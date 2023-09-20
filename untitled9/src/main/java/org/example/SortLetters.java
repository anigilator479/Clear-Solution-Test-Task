package org.example;

import java.util.Set;
import java.util.TreeSet;

public class SortLetters {
    public static String getUniqueCharacters(String text) {
        String regex = "[^a-zA-Z]";
        int maxSize = 5;
        text = text.replaceAll(regex, "");
        char[] chars = text.toCharArray();
        Set<Character> uniqueChars = new TreeSet<>();
        for (char c : chars) {
            uniqueChars.add(Character.toLowerCase(c));
        }
        StringBuilder result = new StringBuilder();
        for (Character c : uniqueChars) {
            result.append(c);
            if (result.length() == maxSize) {
                break;
            }
        }
        return result.toString();
    }
}
