package com.bondarenko;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Scanner in = new Scanner(System.in);
        String flag;
        getMenu();
        do {
            System.out.println("Enter command:");
            flag = in.nextLine();
            switch (flag){
                case "add":
                    repository.add(createPerson(in));
                    break;
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
                case "search":
                    searchArray(in, repository, searchPerson(in));
                    break;
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
                "search menu - search\n" +
                "exit from the program - exit\n" +
                "get menu - menu");
    }

    private static void sortArray(Scanner in, Repository repository){
        String str;
        System.out.println("Sort menu:\n" +
                "Sort by fio - fio\n" +
                "Sort by birthday - birthday\n" +
                "Sort by age - age\n" +
                "Sort by sex - sex\n" +
                "Sort by id - id");
        str = in.nextLine();
        switch (str){
            case "fio":
                System.out.println("Sort by fio:");
                repository.outputArray(repository.sorting(repository.getArray(), Person.FioComparator));
                break;
            case "birthday":
                System.out.println("Sort by birthday:");
                repository.outputArray(repository.sorting(repository.getArray(), Person.BirthdayComparator));
                break;
            case "age":
                System.out.println("Sort by age:");
                repository.outputArray(repository.sorting(repository.getArray(), Person.AgeComparator));
                break;
            case "sex":
                System.out.println("Sort by sex:");
                repository.outputArray(repository.sorting(repository.getArray(), Person.SexComparator));
                break;
            case "id":
                System.out.println("Sort by id:");
                repository.outputArray(repository.sorting(repository.getArray(), Person.IdComparator));
                break;
            default:
                break;
        }
    }

    private static void searchArray(Scanner in, Repository repository, Person elementToSearch){
        ArrayList<Person> arrayList;
        Person [] array = repository.getArray();
        LocalDate date = LocalDate.parse("0000-01-01");
        if (!elementToSearch.getFio().equals("")){
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
        if (!elementToSearch.getSex().equals(Sex.NO)){
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
    }

    public static Person createPerson(Scanner in){

        String fio;
        String birthday;
        Sex sex = null;

        System.out.println("Enter fio:");
        fio = in.nextLine();

        System.out.println("Enter birthday in format yyyy-MM-dd:");
        birthday = in.nextLine();

        System.out.println("Enter sex:");
        do {
            String tempSex = in.nextLine();
            if (tempSex.equals("man") || tempSex.equals("male")) {
                sex = Sex.MALE;
            } else if (tempSex.equals("woman") || tempSex.equals("female")) {
                sex = Sex.FEMALE;
            } else {
                System.out.println("Enter sex one more time");
            }
        }
        while (sex == null);

        return new Person(fio, birthday, sex);
    }

    public static Person searchPerson(Scanner in){

        String fio;
        String birthday;
        int age;
        Sex sex = null;
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
                sex = Sex.MALE;
            } else if (tempSex.equals("woman") || tempSex.equals("female")) {
                sex = Sex.FEMALE;
            } else if (tempSex.equals("")){
                sex = Sex.NO;
            }
            else {
                System.out.println("Enter sex one more time");
            }
        }
        while (sex == null);

        System.out.println("Enter id:");
        tempStr = in.nextLine();
        if (tempStr.equals("")){
            id = -1;
        }
        else {
            id = Integer.parseInt (tempStr);
        }

        return new Person(fio, birthday, age, sex, id);
    }
}
