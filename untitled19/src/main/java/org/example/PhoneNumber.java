package org.example;

public class PhoneNumber {
  public static boolean isValid(String phoneNumber) {
    String regex = "^\\(\\d{3}\\)\\s\\d{3}-\\d{4}$";
    return phoneNumber.matches(regex);
  }
}
