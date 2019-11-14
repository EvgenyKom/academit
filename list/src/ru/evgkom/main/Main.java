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

        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.addToStart(7);
        list1.addToStart(7);
        list1.addToStart(7);

        System.out.println("Списко list: " + list);
        System.out.println("Размер списка list: " + list.getSize());

        System.out.println("Списко list1: " + list1);
        System.out.println("Размер списка list1: " + list1.getSize());

        System.out.println("Голова списка: " + list.getHead());

        System.out.println("Элемент списка под индексом 2: " + list.get(2));

        list.deleteFirst();
        System.out.println("Списко list, после удаления первого элемента: " + list);

        list.revert();
        System.out.println("Списко list после разворота: " + list);

        System.out.println("Замененный элемент с индексом 2: " + list.set(2,11));
        System.out.println("Списко list, после замены элемента с индексом 2: " + list);

        list.delete(0);
        System.out.println("Списко list после удаление элемента с индексом 0: " + list);
        System.out.println("Размер списка list: " + list.getSize());
        System.out.println("Голова списка list: " + list.getHead());

        list.add(2, 9);
        System.out.println("Списко list после добавления элемента по индексу 2: " + list);

        System.out.println("Удалился ли элемент со значением 22 :" + list.delete(new Integer(22)));
        System.out.println("Размер списка list: " + list.getSize());

        System.out.println("Удалился ли элемент со значением 3 :" + list.delete(new Integer(3)));
        System.out.println("Размер списка list: " + list.getSize());
        System.out.println("Список list после удаления элемента со значением 3: " + list);

        list.copy(list1);
        System.out.println("Список list после копирования в него списка list1: " + list);
        System.out.println("Размер списка list: " + list.getSize());

        System.out.println("Удален первый элемент из list со значением " + list.deleteFirst());
    }
}
