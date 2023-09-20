package org.example;

import java.util.Arrays;

public class IPAddresses {
  public static long countIPBetween(String start, String end) {
    String[] ip1 = start.split("\\.");
    String[] ip2 = end.split("\\.");
    int sum1 = (Integer.parseInt(ip1[0]) * 256 * 256 * 256)
            + (Integer.parseInt(ip1[1]) * 256 * 256) + (Integer.parseInt(ip1[3]) * 256)
            + (Integer.parseInt(ip1[3]));
    int sum2 = (Integer.parseInt(ip2[0]) * 256 * 256 * 256)
            + (Integer.parseInt(ip2[1]) * 256 * 256) + (Integer.parseInt(ip2[3]) * 256)
            + (Integer.parseInt(ip2[3]));
    return sum2 - sum1;
  }
}