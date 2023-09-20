package org.example;

public class CamelCase {
  public static String convertToCamelCase(String string) {
    if (string.length() < 2) {
      return string;
    }
    String[] str = string.split("[-_]");
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(str[0]);
    for (int i = 1; i < str.length; i++) {
      String result = str[i].substring(0,1).toUpperCase() + str[i].substring(1);
      stringBuilder.append(result);
    }
    return stringBuilder.toString();
  }
}
