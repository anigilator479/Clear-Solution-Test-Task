package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TwoToOne {
  public static String getUniqueLetters(String string1, String string2) {
    if (string1.equals(string2)) {
    return Arrays.stream(string1.split("")).sorted().distinct().collect(Collectors.joining());
    }
    String replacedString = string1.replaceAll(string2, "") + string2.replaceAll(string1, "");
    String collect = Arrays.stream(replacedString.split("")).sorted().distinct().collect(Collectors.joining());
    return collect;
  }
}
