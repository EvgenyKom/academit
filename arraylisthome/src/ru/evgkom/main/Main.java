package ru.evgkom.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String fileName = "input.txt";
        System.out.println("Массив из файдла input.txt, прочитанный построчно = " + readLinesFromFile(fileName));

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = -15; i < 15; i++) {
            arrayList.add(i);
        }

        System.out.println("Массив чичел : " + arrayList.toString());
        deleteEvenNumbers(arrayList);
        System.out.println("Массив чичел после удаления четных чисел: " + arrayList.toString());



        ArrayList<Integer> arrayList1 = new ArrayList<>();
        int[] array = {1, 3, 4, -2, 0, 4, 8, 3, 23, 1, 7, 4, 0, -1, 1, -1, 1, 6};

        for (int value : array) {
            arrayList1.add(value);
        }

        System.out.println("Массив чичел : " + arrayList1.toString());
        System.out.println("Массив чичел после удаления повторяющихся чисел: " + removeRepetition(arrayList1).toString());

    }

    private static ArrayList<String> readLinesFromFile(String inputFileName) throws FileNotFoundException {
        ArrayList<String> arrayList = new ArrayList<>();
        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            while (scanner.hasNextLine()) {
                arrayList.add(scanner.nextLine());
            }
        }

        return arrayList;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i) % 2 == 0 && arrayList.get(i) != 0) {
                arrayList.remove(arrayList.get(i));
            }
        }
    }

    private static ArrayList<Integer> removeRepetition(ArrayList<Integer> arrayList) {
        ArrayList<Integer> integerArrayList = new ArrayList<>(arrayList.size());

        for (Integer n : arrayList) {
            if (!integerArrayList.contains(n)) {
                integerArrayList.add(n);
            }
        }

        return integerArrayList;
    }
}