package org.example;

public class ExesAndOhs {
  public static boolean isTheSameAmountOfXO(String string) {
    return (string.toLowerCase().replaceAll("o", "").length() - string.toLowerCase().replaceAll("x","").length()) == 0;
  }
}