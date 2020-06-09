package com.bondarenko;

import com.bondarenko.xml.JaxbWorker;
import com.bondarenko.xml.Persons;
import com.bondarenko.xml.PersonsList;
import org.jcp.xml.dsig.internal.SignerOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.lab.entities.enums.Gender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(
            JaxbWorker.class);
    public static void main(String[] args) throws FileNotFoundException, JavaException {

        Repository<Person> repository;

        Parser parser = new Parser();
        repository = parser.parser();

        String fileName = "C:/Users/bonda/IdeaProjects/my_repository/PERSONS.xml";
        Repository<Persons> repositoryXML = new Repository<>();
        for (int i = 0; i < repository.getSize(); i++) {
            repositoryXML.add(new Persons(
                    repository.get(i).getFirstName(),
                    repository.get(i).getBirthdate(),
                    repository.get(i).getGender(),
                    new Division(
                            repository.get(i).getDivision().getId(),
                            repository.get(i).getDivision().getName()
                    ),
                    repository.get(i).getSalary(),
                    repository.get(i).getId()
            ));
        }
        JaxbWorker jaxbWorker = new JaxbWorker();
        jaxbWorker.convertObjectToXml(repositoryXML, fileName);

        PersonsList unmarshPerson = jaxbWorker.fromXmlToObject(fileName);
        if (unmarshPerson != null) {
            for (Persons persons : unmarshPerson.getPersons()) {
                logger.info(persons.toString());
            }
        }

//        StreamAPI streamAPI = new StreamAPI();
//        streamAPI.task_1(repository.getArray());
//        streamAPI.task_2(repository.getArray());
//        streamAPI.task_3(repository.getArray());
//        streamAPI.task_4(repository.getArray());

        LabInjector inj = new LabInjector();
        inj.injector(repository);
        repository.sortBy(Person.FirstNameComparator);


        Scanner in = new Scanner(System.in);
        String flag;
        getMenu();
        do {
            System.out.println("Enter command:");
            flag = in.nextLine();
            switch (flag){
                /*case "add":
                    repository.add(createPerson(in));
                    break;*/
                case "delete":
                    repository.outputArray(repository.getArray());
                    System.out.println("Enter index:");
                    repository.delete(in.nextInt());
                    break;
                case "view":
                    repository.outputArray(repository.getArray());
                    break;
                case "menu":
                    getMenu();
                    break;
                case "sort":
                    sortArray(in, repository);
                    break;
                /*case "search":
                    searchArray(in, repository, searchPerson(in));
                    break;*/
                default:
                    System.out.println("Invalid command entered!");
                    break;
            }
        }
        while (!flag.equals("exit"));
    }

    private static void getMenu(){
        System.out.println("This program has the following functionality:\n" +
                "add person to repository - add\n" +
                "delete person to repository - delete\n" +
                "view repository - view\n" +
                "sort menu - sort\n" +
                "search menu - search (в разработке)\n" +
                "exit from the program - exit\n" +
                "get menu - menu");
    }

    private static void sortArray(Scanner in, Repository repository){
        String str;
        System.out.println("Sort menu:\n" +
                "Sort by first name - first name\n" +
                "Sort by last name - last name\n" +
                "Sort by birthdate - birthdate\n" +
                "Sort by age - age\n" +
                "Sort by gender - gender\n" +
                "Sort by division - division\n" +
                "Sort by salary - salary\n" +
                "Sort by id - id");
        str = in.nextLine();
        switch (str){
            case "first name":
                System.out.println("Sort by first name:");
                repository.sortBy(Person.FirstNameComparator);
                break;
            case "last name":
                System.out.println("Sort by last name:");
                repository.sortBy(Person.LastNameComparator);
                break;
            case "birthdate":
                System.out.println("Sort by birthdate:");
                repository.sortBy(Person.BirthdateComparator);
                break;
            case "age":
                System.out.println("Sort by age:");
                repository.sortBy(Person.AgeComparator);
                break;
            case "gender":
                System.out.println("Sort by gender:");
                repository.sortBy(Person.GenderComparator);
                break;
            case "division":
                System.out.println("Sort by division:");
                repository.sortBy(Person.DivisionComparator);
                break;
            case "salary":
                System.out.println("Sort by salary:");
                repository.sortBy(Person.SalaryComparator);
                break;
            case "id":
                System.out.println("Sort by id:");
                repository.sortBy(Person.IdComparator);
                break;
            default:
                break;
        }
    }

 /*   private static void searchArray(Scanner in, Repository repository, Person elementToSearch){
        ArrayList<Person> arrayList;
        Person [] array = repository.getArray();
        LocalDate date = LocalDate.parse("0000-01-01");
        if (!elementToSearch.getFirstName().equals("")){
            arrayList = repository.binarySearch(repository.sorting(array, Person.FioComparator), elementToSearch,Person.FioComparator);
            array = new Person[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++){
                array[i] = arrayList.get(i);
            }
        }
        if (!elementToSearch.getBirthday().equals(date)){
            arrayList = repository.binarySearch(repository.sorting(array, Person.BirthdayComparator), elementToSearch,Person.BirthdayComparator);
            array = new Person[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++){
                array[i] = arrayList.get(i);
            }
        }
        if (elementToSearch.getAge() >= 0){
            arrayList = repository.binarySearch(repository.sorting(array, Person.AgeComparator), elementToSearch,Person.AgeComparator);
            array = new Person[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++){
                array[i] = arrayList.get(i);
            }
        }
        if (elementToSearch.getGender() != null){
            arrayList = repository.binarySearch(repository.sorting(array, Person.SexComparator), elementToSearch,Person.SexComparator);
            array = new Person[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++){
                array[i] = arrayList.get(i);
            }
        }
        if (elementToSearch.getId() >= 0){
            arrayList = repository.binarySearch(repository.sorting(array, Person.IdComparator), elementToSearch,Person.IdComparator);
            array = new Person[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++){
                array[i] = arrayList.get(i);
            }
        }
        repository.outputArray(array);
    }*/

    /*public static Person createPerson(Scanner in){

        String firstName;
        String lastName;
        String birthdate;
        Gender gender = null;

        System.out.println("Enter firstName:");
        firstName = in.nextLine();

        System.out.println("Enter birthdate in format yyyy-MM-dd:");
        birthdate = in.nextLine();

        System.out.println("Enter sex:");
        do {
            String tempSex = in.nextLine();
            if (tempSex.equals("man") || tempSex.equals("male")) {
                gender = Gender.MALE;
            } else if (tempSex.equals("woman") || tempSex.equals("female")) {
                gender = Gender.FEMALE;
            } else {
                System.out.println("Enter sex one more time");
            }
        }
        while (gender == null);

        return new Person(firstName, birthdate, gender);
    }*/

    public static Person searchPerson(Scanner in){

        String fio;
        String birthday;
        int age;
        Gender gender = null;
        int id;

        System.out.println("Enter fio:");
        fio = in.nextLine();

        System.out.println("Enter birthday in format yyyy-MM-dd:");
        birthday = in.nextLine();
        if (birthday.equals("")){
            birthday = "0000-01-01";
        }

        System.out.println("Enter age:");
        String tempStr = in.nextLine();
        if (tempStr.equals("")){
            age = -1;
        }
        else {
            age = Integer.parseInt (tempStr);
        }

        System.out.println("Enter sex:");
        do {
            String tempSex = in.nextLine();
            if (tempSex.equals("man") || tempSex.equals("male")) {
                gender = Gender.MALE;
            } else if (tempSex.equals("woman") || tempSex.equals("female")) {
                gender = Gender.FEMALE;
            }
            else {
                System.out.println("Enter sex one more time");
            }
        }
        while (gender == null);

        System.out.println("Enter id:");
        tempStr = in.nextLine();
        if (tempStr.equals("")){
            id = -1;
        }
        else {
            id = Integer.parseInt (tempStr);
        }

        return new Person(fio, birthday, age, gender, id);
    }
}
