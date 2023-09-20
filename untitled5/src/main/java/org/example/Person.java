package org.example;

import java.util.Objects;

public class Person {
  private String name;
  private int age;
  private String sex;

  public Person(String name, int age, String sex) {
    this.name = name;
    this.age = age;
    this.sex = sex;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getSex() {
    return sex;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return age == person.age
      && Objects.equals(name, person.name)
      && Objects.equals(sex, person.sex);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, age, sex);
  }

  @Override
  public String toString() {
    return "Person{"
      + "name='" + name + '\''
      + ", age=" + age
      + ", sex='" + sex + '\''
      + '}';
  }
}
