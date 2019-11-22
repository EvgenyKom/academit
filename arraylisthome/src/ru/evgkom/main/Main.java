package ru.evgkom.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String fileName = "input.txt";
        try {
            System.out.println("Массив из файла input.txt, прочитанный построчно = " + readLinesFromFile(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка! Файл " + fileName + " не найден!");
        }

        ArrayList<Integer> numbers1 = new ArrayList<>();

        for (int i = -15; i < 15; i++) {
            numbers1.add(i);
        }

        System.out.println("Массив чисел = " + numbers1);
        deleteEvenNumbers(numbers1);
        System.out.println("Массив чисел после удаления четных чисел = " + numbers1);

        ArrayList<Integer> numbers2 = new ArrayList<>();
        int[] array = {1, 3, 4, -2, 0, 4, 8, 3, 23, 1, 7, 4, 0, -1, 1, -1, 1, 6};

        for (int value : array) {
            numbers2.add(value);
        }

        System.out.println("Массив чисел = " + numbers2);
        System.out.println("Массив чисел после удаления повторяющихся чисел = " + getListWithoutRepetitions(numbers2));
    }

    private static ArrayList<String> readLinesFromFile(String inputFileName) throws FileNotFoundException {
        ArrayList<String> stringsList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(inputFileName))) {
            while (scanner.hasNextLine()) {
                stringsList.add(scanner.nextLine());
            }
        }

        return stringsList;
    }

    private static void deleteEvenNumbers(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); ) {
            if (list.get(i) % 2 == 0) {
                list.remove(i);
                continue;
            }

            i++;
        }
    }

    private static ArrayList<Integer> getListWithoutRepetitions(ArrayList<Integer> list) {
        ArrayList<Integer> integerArrayList = new ArrayList<>(list.size());

        for (Integer n : list) {
            if (!integerArrayList.contains(n)) {
                integerArrayList.add(n);
            }
        }

        return integerArrayList;
    }
}