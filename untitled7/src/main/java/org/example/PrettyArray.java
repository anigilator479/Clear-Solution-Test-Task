package org.example;

public class PrettyArray {
  public static boolean isArrayPretty(int[] array) {
    int count = 0;
    for (int i = 0; i < array.length; i++) {
      if (count == array.length) {
        return true;
      }
      for (int j = 0; j < array.length; j++) {
        if (array[i] == array[i] - 1 || array[i] == array[i] + 1) {
          count++;
        }
      }
    }
    return false;
  }
}