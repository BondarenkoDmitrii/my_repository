package com.bondarenko.xml;

import com.bondarenko.Division;
import ru.vsu.lab.entities.IDivision;
import ru.vsu.lab.entities.enums.Gender;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@XmlRootElement(name = "Person")
@XmlAccessorType(XmlAccessType.FIELD)
public class Persons {
    private String firstName;
    private String lastName;
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate birthdate;
    private Integer age;
    private Gender gender;
    private Division division;
    private BigDecimal salary;
    @XmlAttribute
    private Integer id;

    public Persons() {
    }

    //    конструктор для создания
    public Persons(String firstName, LocalDate birthdate, Gender gender, Division division, BigDecimal salary, Integer id) {
        this.firstName = firstName;
        this.lastName = firstName;
        this.birthdate = birthdate;
        this.age = Math.toIntExact(ChronoUnit.YEARS.between(birthdate, LocalDate.now()));
        this.gender = gender;
        this.division = division;
        this.salary = salary;
        this.id = id;
    }

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

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division){
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
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdate, age, gender, division, salary);
    }
}
