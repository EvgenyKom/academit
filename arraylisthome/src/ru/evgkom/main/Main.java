package ru.evgkom.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt";
        System.out.println("Массив из файла input.txt, прочитанный построчно = " + readLinesFromFile(fileName));

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = -15; i < 15; i++) {
            arrayList.add(i);
        }

        System.out.println("Массив чисел = " + arrayList);
        deleteEvenNumbers(arrayList);
        System.out.println("Массив чисел после удаления четных чисел = " + arrayList);

        ArrayList<Integer> arrayList1 = new ArrayList<>();
        int[] array = {1, 3, 4, -2, 0, 4, 8, 3, 23, 1, 7, 4, 0, -1, 1, -1, 1, 6};

        for (int value : array) {
            arrayList1.add(value);
        }

        System.out.println("Массив чисел = " + arrayList1);
        System.out.println("Массив чисел после удаления повторяющихся чисел = " + getListWithoutRepetition(arrayList1));
    }

    private static ArrayList<String> readLinesFromFile(String inputFileName) {
        ArrayList<String> stringArrayList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            while (scanner.hasNextLine()) {
                stringArrayList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found");
        }

        return stringArrayList;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); ) {
            if (arrayList.get(i) % 2 == 0) {
                arrayList.remove(i);
            }

            i++;
        }
    }

    private static ArrayList<Integer> getListWithoutRepetition(ArrayList<Integer> arrayList) {
        ArrayList<Integer> integerArrayList = new ArrayList<>(arrayList.size());

        for (Integer n : arrayList) {
            if (!integerArrayList.contains(n)) {
                integerArrayList.add(n);
            }
        }

        return integerArrayList;
    }
}