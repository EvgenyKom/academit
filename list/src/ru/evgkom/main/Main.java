package ru.evgkom.main;

import ru.evgkom.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addToStart(8);
        list.addToStart(3);
        list.addToStart(2);
        list.addToStart(-6);
        list.addToStart(0);

        System.out.println("Списко list: " + list);
        System.out.println("Размер списка: " + list.getSize());

        System.out.println("Элемент списка под индексом 2: " + list.get(2));

        list.deleteFirst();
        System.out.println("Списко list, после удаления первого элемента: " + list);

        System.out.println("Голова списка: " + list.head());

        list.revert();
        System.out.println("Списко list после разворота: " + list);

        list.delete(0);
        System.out.println("Списко list после удаление элемента с индексом 1: " + list);

        System.out.println("Голова списка: " + list.head());

        list.add(2, 9);
        System.out.println("Списко list после добавления элемента по индексу 2: " + list);

        System.out.println("Удалился ли элемент со значением 22 :" + list.delete(new Integer(22)));

        System.out.println("Удалился ли элемент со значением 3 :" + list.delete(new Integer(3)));
        System.out.println("Списко list после удаления элемента со значением 3: " + list);



    }
}
