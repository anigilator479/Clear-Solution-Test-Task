package org.example;

import java.util.Arrays;

public class SumOfArray {
  public static int getSumOfIntegers(String[] numbers) {
    return Arrays.stream(numbers)
            .mapToInt(Integer::valueOf)
            .sum();
  }
}