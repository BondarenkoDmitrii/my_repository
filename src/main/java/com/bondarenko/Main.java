package com.bondarenko;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Repository repository = new Repository();
        Scanner in = new Scanner(System.in);
        String flag;
        do {
        System.out.println("Would you like to add new person? y/n");
        flag = in.nextLine();
            if (flag.equals("y")) {
                repository.add(createPerson(in));
            }
        }
        while (flag.equals("y"));

        System.out.println("Would you like to see repository? y/n");
        if (in.nextLine().equals("y")) {
            repository.output();
        }

        System.out.println("Would you like to delete person? y/n");
        if (in.nextLine().equals("y")) {
            System.out.println("Enter number to delete person. y/n");
            repository.delete(in.nextInt());
            System.out.println("Would you like to see repository? y/n");
            if (in.next().equals("y")) {
                repository.output();
            }
        }
    }

    public static Person createPerson (Scanner in){

        String name;
        String birthday;
        Sex sex = null;

        System.out.println("Input name:");
        name = in.nextLine();

        System.out.println("Input birthday in format yyyy-MM-dd:");
        birthday = in.nextLine();

        System.out.println("Input sex:");
        do {
            String tempSex = in.nextLine();
            if (tempSex.equals("man") || tempSex.equals("male")) {
                sex = Sex.MALE;
            } else if (tempSex.equals("woman") || tempSex.equals("female")) {
                sex = Sex.FEMALE;
            } else {
                System.out.println("Input sex one more time");
            }
        }
        while (sex == null);

        return new Person(name, birthday, sex);

    }
}
