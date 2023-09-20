package org.example;

public class ExtraPerfectNumbers {
  public static int[] getExtraPerfectNumbers(int number) {
    int counter = 0;
    while (number > 0) {
      number -= 2;
      counter++;
    }
    int[] result = new int[counter];
    for (int i = 0; i < counter; i++) {
      result[i] = 1 + (2 * i);
    }
    return result;
  }
}
