package ru.evgkom.main;

import ru.evgkom.lambda.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();

        list.add(new Person("Иван", 20));
        list.add(new Person("Сергей", 10));
        list.add(new Person("Петр", 33));
        list.add(new Person("Николай", 17));
        list.add(new Person("Ирина", 50));
        list.add(new Person("Ксения", 42));
        list.add(new Person("Иван", 2));
        list.add(new Person("Ирина", 21));

        List<String> distinctNamesList = list.stream()
                .map(Person::getName).distinct()
                .collect(Collectors.toList());

        String distinctNames = distinctNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));
        System.out.println(distinctNames);

        List<Person> sortedList = list.stream()
                .filter(p -> p.getAge() < 18).collect(Collectors.toList());
        sortedList.stream()
                .mapToDouble(Person::getAge)
                .average()
                .ifPresent(avg -> System.out.println("Средний возраст людей младше 18 лет - " + avg));

        Map<String, Double> avgAgeByNames = list.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingDouble(Person::getAge)));
        System.out.println(avgAgeByNames.toString());

        list.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45).sorted((p2, p1) -> p1.getAge() - p2.getAge())
                .map(Person::getName)
                .forEach(s -> System.out.print(s + " "));
    }
}