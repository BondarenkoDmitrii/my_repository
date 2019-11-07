package com.bondarenko;

import java.util.Objects;
import java.util.Comparator;

import org.joda.time.LocalDate;
import org.joda.time.Years;

public class Person {

    private String fio;
    private LocalDate birthday;
    private int age;
    private Sex sex;
    private int id;

    public Person(String fio, String birthday, Sex sex) {
        this.fio = fio;
        this.birthday = LocalDate.parse(birthday);
        this.age = Years.yearsBetween(this.birthday, LocalDate.now()).getYears();
        this.sex = sex;
        this.id = Math.abs(hashCode());
    }

    public Person(String fio, String birthday, int age, Sex sex, int id) {
        this.fio = fio;
        this.birthday = LocalDate.parse(birthday);
        this.age = age;
        this.sex = sex;
        this.id = id;
    }

    public static Comparator<Person> FioComparator = Comparator.comparing(Person::getFio);

    public static Comparator<Person> BirthdayComparator = Comparator.comparing(Person::getBirthday);

    public static Comparator<Person> AgeComparator = Comparator.comparingInt(Person::getAge);

    public static Comparator<Person> SexComparator = Comparator.comparing(Person::getSex);

    public static Comparator<Person> IdComparator = Comparator.comparingInt(Person::getId);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(fio, person.fio) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(age, person.age) &&
                sex == person.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, birthday, age, sex);
    }

    @Override
    public String toString() {
        return "{" +
                "fio: " + fio +
                ", birthday: " + birthday +
                ", age: " + age +
                ", sex: " + sex +
                ", id: " + id +
                '}';
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}