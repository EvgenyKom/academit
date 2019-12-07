package ru.evgkom.main;

import ru.evgkom.hashtable.HashTable;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable1 = new HashTable<>();
        HashTable<Integer> hashTable2 = new HashTable<>();
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();

        arrayList1.add(7);
        arrayList1.add(0);
        arrayList1.add(222);
        arrayList1.add(-1552);

        arrayList2.add(10);
        arrayList2.add(222);

        hashTable1.add(5);
        hashTable1.add(10);
        hashTable1.add(222);
        hashTable1.add(-1552);
        hashTable1.add(0);

        hashTable2.addAll(arrayList2);

        System.out.println("Пустая ли хеш-таблица hashtable1: " + hashTable1.isEmpty());

        System.out.println("Хеш-таблица hashtable1 = " + hashTable1);
        System.out.println("Хеш-таблица hashtable2 = " + hashTable2);

        System.out.println("Содержится ли число 10 в хеш-таблице hashtable1: " + hashTable1.contains(10));
        System.out.println("Содержится ли число 1552 в хеш-таблице hashtable1: " + hashTable1.contains(1552));

        hashTable1.remove(5);
        System.out.println("Хеш-таблица hashtable1 после удаления числа 5 = " + hashTable1);

        System.out.println("Содержит ли хеш-таблица hashtable1 все элементы arrayList1 = " + hashTable1.containsAll(arrayList1));
        System.out.println("Содержит ли хеш-таблица hashtable1 все элементы arrayList2 = " + hashTable1.containsAll(arrayList2));

        hashTable1.addAll(arrayList2);
        System.out.println("Хеш-таблица hashtable1 после добавления в неё arrayList2 = " + hashTable1);

        hashTable1.retainAll(hashTable2);
        System.out.println("Хеш-таблица hashtable1 после удаления элементов не содержащихся в hashtable2 = " + hashTable1);

        Object[] array = hashTable1.toArray();
        System.out.println("Массив array, полученный из hashTable1 = " + Arrays.toString(array));

        hashTable1.removeAll(arrayList1);
        System.out.println("Хеш-таблица hashtable1 после удаления в ней элементов arrayList1 = " + hashTable1);

        hashTable1.clear();
        System.out.println("Хеш-таблица hashtable1 после очистки = " + hashTable1);
    }
}