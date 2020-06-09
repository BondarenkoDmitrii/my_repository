package com.bondarenko;

import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.IPerson;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.Objects;

public class Person implements IPerson {

    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Integer age;
    private Gender gender;
    private IDivision division;


    private BigDecimal salary;
    private Integer id;

    public Person() {
    }

    //    конструктор для создания
    public Person(String firstName, LocalDate birthdate, Gender gender, Division division, BigDecimal salary, Integer id) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.birthdate = birthdate;
        this.age = Math.toIntExact(ChronoUnit.YEARS.between(birthdate, LocalDate.now()));
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.id = id;
    }

//    конструктор для сравнения
    public Person(String firstName, String birthdate, int age, Gender gender, int id) {
        this.firstName = firstName;
        this.birthdate = LocalDate.parse(birthdate);
        this.age = age;
        this.gender = gender;
        this.id = id;
    }

    public static Comparator<Person> FirstNameComparator = Comparator.comparing(Person::getFirstName);

    public static Comparator<Person> LastNameComparator = Comparator.comparing(Person::getFirstName);

    public static Comparator<Person> BirthdateComparator = Comparator.comparing(Person::getBirthdate);

    public static Comparator<Person> AgeComparator = Comparator.comparingInt(Person::getAge);

    public static Comparator<Person> GenderComparator = Comparator.comparing(Person::getGender);

    public static Comparator<Person> DivisionComparator = Comparator.comparing(Person::getGender);

    public static Comparator<Person> SalaryComparator = Comparator.comparing(Person::getGender);

    public static Comparator<Person> IdComparator = Comparator.comparingInt(Person::getId);

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public IDivision getDivision() {
        return division;
    }

    public void setDivision(IDivision division){
        this.division = division;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", age=" + age +
                ", gender=" + gender +
                ", division=" + division.getName() +
                ", salary=" + salary +
                ", id=" + id +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(birthdate, person.birthdate) &&
                Objects.equals(age, person.age) &&
                gender == person.gender &&
                Objects.equals(division, person.division) &&
                Objects.equals(salary, person.salary) &&
                Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdate, age, gender, division, salary);
    }
}