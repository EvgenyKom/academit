package ru.evgkom.main;

import ru.evgkom.arraylist.MyArrayList;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(13);
        list1.add(1, 14);
        list1.add(0);
        list1.add(16);
        list1.add(null);
        list1.add(-22);
        list1.add(null);
        list1.add(-2);
        list1.add(0, 55);

        MyArrayList<Integer> list2 = new MyArrayList<>();
        list2.add(16);
        list2.add(-2);
        list2.add(14);

        System.out.println("Список list1: " + list1);
        System.out.println("Список list2: " + list2);

        System.out.println("Пуст ли list1: " + list1.isEmpty());

        System.out.println("Содержится ли число 13 в list1: " + list1.contains(13));
        System.out.println("Содержится ли число 20 в list1: " + list1.contains(20));

        list1.remove(null);
        System.out.println("Список list1, после удаления null элемента : " + list1);

        System.out.println("Содержатся ли все элементы list2 в list1: " + list1.containsAll(list2));
        System.out.println("Содержатся ли все элементы list1 в list2: " + list2.containsAll(list1));

        list1.addAll(list2);
        System.out.println("Список list1, после добавления в него list2: " + list1);

        list1.retainAll(list2);
        System.out.println("Список list1, после оставления в нем элементов как в list2: " + list1);

        list1.removeAll(list2);
        System.out.println("Список list1, после после удаления в нем элементов list2: " + list1);
    }
}