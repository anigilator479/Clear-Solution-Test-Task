package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MajorityElement {
  public static int findMajor(int[] nums) {
    String strArr = Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining());

    return Arrays.stream(nums)
            .mapToObj(String::valueOf)
            .filter(str -> strArr.replaceAll(str,"").length() <= strArr.length() / 2)
            .map(Integer::parseInt)
            .findFirst()
            .orElse(0);
  }
}