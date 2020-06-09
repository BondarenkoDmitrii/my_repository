package com.bondarenko;

import com.bondarenko.xml.JaxbWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.vsu.lab.entities.enums.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class WorkWithFile {

    private static final Logger logger = LoggerFactory.getLogger(
            JaxbWorker.class);

    public WorkWithFile() {
    }

    public Map<String, String> toMap() {
        String[] array = new String[2];
        Map<String, String> map = new HashMap<>();

        try {
            String c;
            BufferedReader reader = new BufferedReader(new FileReader("Annotation.txt"));
            while ((c = reader.readLine()) != null) {
                array = c.split(" = ");
                map.put(array[1], array[0]);
            }
            logger.info("File read successfully");
            reader.close();
        } catch (IOException e) {
            //reader.close();
            logger.error("Read operation failed", e);
        }
        return map;
    }

}
