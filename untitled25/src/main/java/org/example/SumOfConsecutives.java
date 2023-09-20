package org.example;

import java.util.ArrayList;
import java.util.List;

public class SumOfConsecutives {
  public static List<Integer> getSumConsecutives(List<Integer> list) {
    if (list.size() < 2) {
      return list;
    }
    List<Integer> integers = new ArrayList<>();
    int sum = 0;
    for (int i = 0; i < list.size(); i++) {
      if (i != list.size() - 1) {
        if (list.get(i).equals(list.get(i + 1))) {
          if (sum == 0) {
            sum += list.get(i);
          }
          sum += list.get(i + 1);
          continue;
        }
      }
      if (sum != 0) {
        integers.add(sum);
      } else {
        integers.add(list.get(i));
      }
      sum = 0;
    }
    return integers;
  }
}