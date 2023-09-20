package org.example;

public class ShiftLeft {
  public static int calculateMoves(String word1, String word2) {
    if (word1.endsWith(word2)) {
      return word1.length() - word2.length();
    }
    if (word2.endsWith(word1)) {
      return word2.length() - word1.length();
    }
    for (int i = 0; i < word1.length(); i++) {
      if (word2.endsWith(word1.substring(i))) {
        return (word2.length() - word1.substring(i).length()) * 2;
      }
    }
    for (int i = 0; i < word2.length(); i++) {
      if (word1.endsWith(word2.substring(i))) {
        return (word1.length() - word2.substring(i).length()) * 2;
      }
    }
    return word1.length() + word2.length();
  }
}