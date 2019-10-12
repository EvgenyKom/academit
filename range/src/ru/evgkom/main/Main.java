package ru.evgkom.main;

import ru.evgkom.range.Range;

public class Main {
    public static void main(String[] args) {
        Range range = new Range(-5, 4);
        Range range1 = new Range(2, 3);
        Range range2 = new Range(-8, 0);
        Range range3 = new Range(-5, 2);
        Range range4 = new Range(-8, 4);

        System.out.println("Длина интервала range: " + range.getLength());
        System.out.println("Входит ли число 1 в интервал range: " + range.isInside(1));
        System.out.println("Входит ли число 4,13 в интервал range: " + range.isInside(4.13));
        System.out.println("Входит ли число -7.24 в интервал range: " + range.isInside(-7.24));
        System.out.println();

        System.out.println("Пересечение интервалов range и range2: " + range.getIntersection(range2));
        System.out.println("Пересечение интервалов range1 и range4: " + range1.getIntersection(range4));
        System.out.println("Пересечение интервалов range3 и range3: " + range3.getIntersection(range3));
        System.out.println("Пересечение интервалов range и range3: " + range.getIntersection(range3));
        System.out.println();

        System.out.println("Объединение интервалов range1 и range3:");
        for (Range r : range1.getUnion(range3)) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Объединение интервалов range2 и range1:");
        for (Range r : range2.getUnion(range1)) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Разность интервалов range и range2:");
        for (Range r : range.getDifference(range2)) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Разность интервалов range4 и range2:");
        for (Range r : range4.getDifference(range2)) {
            System.out.println(r);
        }
        System.out.println();

        System.out.println("Разность интервалов range и range4:");
        for (Range r : range.getDifference(range4)) {
            System.out.println(r);
        }
    }
}