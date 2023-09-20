package org.example;

import java.util.function.IntUnaryOperator;

public class Addition {
  public static IntUnaryOperator add(int n) {
    return operand -> operand + n;
  }
}
