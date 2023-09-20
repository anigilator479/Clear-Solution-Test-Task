package org.example;

public class SpecialNumber {
  public static String isSpecialNumber(int number) {
    String notSpecialNums = "6789";
    for (int i = 0; i < String.valueOf(number).length(); i++) {
      if(notSpecialNums.indexOf(String.valueOf(number).charAt(i)) != -1) {
        return "NOT!!";
      }
    }
    return "Special!!";
  }
}
