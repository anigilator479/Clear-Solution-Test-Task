package org.example;

public class Triangular {
  public static int getTriangular(int n) {
    if (n <= 0) {
      return 0;
    }
    int result = 0;
    for (int i = 1; i < n + 1; i++) {
      result += i;
    }
    return result;
  }
}
