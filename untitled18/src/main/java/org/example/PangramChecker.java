package org.example;

public class PangramChecker {
  public static boolean isPangram(String sentence) {
    String alphabet = "abcdefghijklmnopqrstuvwxyz";
    if (sentence.length() < alphabet.length()) {
      return false;
    }
    
    for (char letter : alphabet.toCharArray()) {
      if (sentence.toLowerCase().indexOf(letter) == -1) {
        return false;
      }
    }
    
    return true;
  }
}