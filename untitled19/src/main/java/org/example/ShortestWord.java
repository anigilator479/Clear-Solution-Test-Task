package org.example;

import java.util.Arrays;

public class ShortestWord {
  public static int findShortest(String string) {
    return Arrays.stream(string.split(" "))
            .mapToInt(String::length)
            .min()
            .orElse(0);
  }
}
