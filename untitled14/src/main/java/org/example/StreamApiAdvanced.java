package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Comparator;
public class StreamApiAdvanced {
  /**
   * Given array of numbers, your task is to sort them in the reverse order and return only those
   * numbers that can be divided by 5 without a remainder.
   */
  public List<Integer> filterAndReverse(int[] inputNumbers) {
    return Arrays.stream(inputNumbers)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .filter(num -> num % 5 == 0)
            .collect(Collectors.toList());
  }

  /**
   * We want to gather some statistics: we have list of people and we want to know
   * distribution among the age of women who have cats and are older than 18
   * Result should have the following view: Map.of(19 - List.of(person1, person2, ...),
   * 21 - List.of(person3, person7, ...);
   */
  public Map<Integer, List<Person>> groupByAge(List<Person> people) {
    return people.stream()
            .filter(human -> human.getSex().equals(Person.Sex.WOMAN) && human.getCatList() != null && human.getAge() > 18)
            .collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(person -> person, Collectors.toList())));
  }

  /**
   * Given a list of random strings, group all of them by the last letter from the
   * string. If last char is a number or punctuation - skip the word.
   */
  public Map<Character, List<String>> groupWordsByLastChar(List<String> words) {
    String invalid = "1234567890,;!-";
    return words.stream()
            .filter(word -> !invalid.contains(String.valueOf(word.charAt(word.length() - 1))))
            .collect(Collectors.groupingBy(word -> word.charAt(word.length() - 1)));
  }
}
