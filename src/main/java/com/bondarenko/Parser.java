package com.bondarenko;

import com.bondarenko.xml.JaxbWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.lab.entities.enums.Gender;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Parser {
    private HashMap<String, Division> divisionHash = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(
            JaxbWorker.class);

    public Repository<Person> parser() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("C:/Users/bonda/Downloads/persons.txt"));
        String [] splitted = new String[6];
        String firstName;
        LocalDate birthdate;
        Gender gender;
        BigDecimal salary;
        Integer id;

        Repository<Person> repository = new Repository<Person>();

        while(sc.hasNext()) {
            splitted = sc.nextLine().split(";");
            id = Integer.parseInt(splitted[0]);
            firstName = splitted[1];
            if (splitted[2].equals("Male")) {
                gender = Gender.MALE;
            } else if (splitted[2].equals("Female")) {
                gender = Gender.FEMALE;
            } else {
                gender = null;
            }
            DateTimeFormatter russianFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
            birthdate = LocalDate.parse(splitted[3], russianFormatter);

            if (!divisionHash.isEmpty()) {
                if (!divisionHash.containsKey(splitted[4])){
                    Division division = new Division();
                    division.setId(divisionHash.size());
                    division.setName(splitted[4]);
                    divisionHash.put(splitted[4], division);
                }
            } else {
                Division division = new Division();
                division.setId(0);
                division.setName(splitted[4]);
                divisionHash.put(splitted[4], division);
            }

            salary = new BigDecimal(splitted[5]);

            repository.add(new Person(firstName, birthdate, gender, divisionHash.get(splitted[4]), salary, id));
        }
        logger.info("File read successfully");
        return repository;
    }
}
