package org.example;

import java.util.List;

public class ListFilter {
  public List<Person> filter(List<Person> personList) {
    List<Person> maleList = null;
    for (Person person : personList) {
      if (person.getSex().equals("male") && (person.getAge() >= 18 && person.getAge() <= 27)) {
        maleList.add(person);
      }
    }
    return maleList;
  }
}