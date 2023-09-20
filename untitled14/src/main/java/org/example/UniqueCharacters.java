package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class UniqueCharacters {
  public static List<Character> getUniqueCharacters(String sourceString) {
    return sourceString.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
  }
}