package org.example;

import java.util.Arrays;

public class Words {
  public static String getWinnerWord(String words) {
    String[] strings = words.split(" ");
    int[] ints = new int[strings.length];
    for (int i = 0; i < strings.length; i++) {
      char[] chars = strings[i].toCharArray();
      int score = 0;
      for (char achar : chars) {
        score += (int) achar % 26;
      }
      ints[i] = score;
    }
    int value = 0;
    for (int anInt : ints) {
      if (anInt > value) {
        value = anInt;
      }
    }
    for (int i = 0; i < ints.length; i++) {
      if (ints[i] == value) {
        return strings[i];
      }
    }
    return "";
  }
}