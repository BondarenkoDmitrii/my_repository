package com.bondarenko;

import org.junit.Assert;
import org.junit.Test;
import ru.vsu.lab.entities.enums.Gender;

import java.math.BigDecimal;
import java.time.Month;

public class RepositoryTest {
    @Test
    public void testAdd() {
        Person person = new Person("Aahan", java.time.LocalDate.of(1999, Month.AUGUST, 20), Gender.FEMALE, new Division(12, "F"), new BigDecimal(8700), 1111 );
        Person person1 = new Person("Dima", java.time.LocalDate.of(1998, Month.FEBRUARY, 18), Gender.MALE, new Division(1, "A"), new BigDecimal(6000), 2222 );
        Person person2 = new Person("Olga", java.time.LocalDate.of(1989, Month.SEPTEMBER, 30), Gender.FEMALE, new Division(3, "C"), new BigDecimal(5700), 3333 );

        Repository<Person> repository = new Repository<Person>();
        repository.add(person);
        repository.add(person1);
        repository.add(person2);

        Person [] array =  new Person[10];

        array[0] = person;
        array[1] = person1;
        array[2] = person2;

        Assert.assertArrayEquals(repository.getArray(), array);
    }

    @Test
    public void delete() {
        Repository<Person> actual = new Repository<Person>();
        Person person = new Person("Aahan", java.time.LocalDate.of(1999, Month.AUGUST, 20), Gender.FEMALE, new Division(12, "F"), new BigDecimal(8700), 1111 );
        Person person1 = new Person("Dima", java.time.LocalDate.of(1998, Month.FEBRUARY, 18), Gender.MALE, new Division(1, "A"), new BigDecimal(6000), 2222 );
        Person person2 = new Person("Olga", java.time.LocalDate.of(1989, Month.SEPTEMBER, 30), Gender.FEMALE, new Division(3, "C"), new BigDecimal(5700), 3333 );
        Person person3 = new Person("Peter", java.time.LocalDate.of(1990, Month.APRIL, 1), Gender.MALE, new Division(3, "C"), new BigDecimal(7000), 4444 );

        actual.add(person);
        actual.add(person1);
        actual.add(person2);
        actual.add(person3);
        actual.delete(2);
        Repository<Person> expected = new Repository<Person>();
        expected.add(person);
        expected.add(person1);
        expected.add(person3);
        Assert.assertArrayEquals(actual.getArray(), expected.getArray());

    }

    @Test
    public void set() {
        Repository<Person> actual = new Repository<Person>();
        Person person = new Person("Aahan", java.time.LocalDate.of(1999, Month.AUGUST, 20), Gender.FEMALE, new Division(12, "F"), new BigDecimal(8700), 1111 );
        Person person1 = new Person("Dima", java.time.LocalDate.of(1998, Month.FEBRUARY, 18), Gender.MALE, new Division(1, "A"), new BigDecimal(6000), 2222 );
        Person person2 = new Person("Olga", java.time.LocalDate.of(1989, Month.SEPTEMBER, 30), Gender.FEMALE, new Division(3, "C"), new BigDecimal(5700), 3333 );
        actual.add(person);
        actual.add(person1);
        actual.set(1,person2);
        Repository<Person> expected = new Repository<Person>();
        expected.add(person);
        expected.add(person2);
        Assert.assertArrayEquals(actual.getArray(), expected.getArray());

    }

    @Test
    public void searchBy() {
        Person person = new Person("Aahan", java.time.LocalDate.of(1999, Month.AUGUST, 20), Gender.FEMALE, new Division(12, "F"), new BigDecimal(8700), 1111 );
        Person person1 = new Person("Dima", java.time.LocalDate.of(1998, Month.FEBRUARY, 18), Gender.MALE, new Division(1, "A"), new BigDecimal(6000), 2222 );
        Person person2 = new Person("Olga", java.time.LocalDate.of(1989, Month.SEPTEMBER, 30), Gender.FEMALE, new Division(3, "C"), new BigDecimal(5700), 3333 );
        Repository<Person> actual = new Repository<Person>();
        actual.add(person);
        actual.add(person1);
        actual.add(person2);
        Repository<Person> expected = new Repository<Person>();
        expected.add(person1);
        Assert.assertArrayEquals(actual.searchBy(x->x.getFirstName().equals("Dima")).getArray(), expected.getArray());
    }

    @Test
    public void sortBy() {
        Repository<Person> actual = new Repository<Person>();
        Person person = new Person("Aahan", java.time.LocalDate.of(1999, Month.AUGUST, 20), Gender.FEMALE, new Division(12, "F"), new BigDecimal(8700), 1111 );
        Person person1 = new Person("Dima", java.time.LocalDate.of(1998, Month.FEBRUARY, 18), Gender.MALE, new Division(1, "A"), new BigDecimal(6000), 2222 );
        Person person2 = new Person("Olga", java.time.LocalDate.of(1989, Month.SEPTEMBER, 30), Gender.FEMALE, new Division(3, "C"), new BigDecimal(5700), 3333 );
        Person person3 = new Person("Peter", java.time.LocalDate.of(1990, Month.APRIL, 1), Gender.MALE, new Division(3, "C"), new BigDecimal(7000), 4444 );
        actual.add(person);
        actual.add(person1);
        actual.add(person2);
        actual.add(person3);
        Repository<Person> expected = new Repository<Person>();
        expected.add(person2);
        expected.add(person3);
        expected.add(person1);
        expected.add(person);
        actual.sortBy(Person.BirthdateComparator);
        Assert.assertArrayEquals(actual.getArray(), expected.getArray());
    }
}
