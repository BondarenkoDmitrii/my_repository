package com.bondarenko;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAPI {

    public void task_1(Person[] array){
        Map<String, Double> map = Arrays.stream(array)
                .collect(Collectors.groupingBy(x->x.getDivision().getName(),Collectors.summingDouble(x->x.getSalary().doubleValue())));
    }

    public void task_2(Person[] array){
        Arrays.stream(array)
                .filter(e->e.getSalary().compareTo(new BigDecimal(5000)) < 0)
                .filter(e->e.getAge()>30)
                .filter(e->e.getFirstName().toLowerCase().contains("a"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void task_3(Person[] array){
        Arrays.stream(array)
                .filter(e->e.getFirstName().toLowerCase().contains("aa"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public void task_4(Person[] array){
        Map<Integer, Long> map = Arrays.stream(array)
                .collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));

        for (Map.Entry<Integer, Long> pair : map.entrySet()) {
            Integer key = pair.getKey();
            Long value = pair.getValue();
            System.out.println(key+"  "+value);
        }
    }
}
