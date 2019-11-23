package ru.evgkom.main;

import ru.evgkom.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.addToStart(8);
        list1.addToStart(3);
        list1.addToStart(2);
        list1.addToStart(-6);
        list1.addToStart(0);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<>();
        list2.addToStart(7);
        list2.addToStart(7);
        list2.addToStart(7);

        System.out.println("Списко list1: " + list1);
        System.out.println("Размер списка list1: " + list1.getSize());

        System.out.println("Списко list2: " + list2);
        System.out.println("Размер списка list2: " + list2.getSize());

        System.out.println("Голова списка list1: " + list1.getHead());

        System.out.println("Элемент списка list1 под индексом 2: " + list1.get(2));

        list1.deleteFirst();
        System.out.println("Списко list1, после удаления первого элемента: " + list1);

        list1.revert();
        System.out.println("Списко list1 после разворота: " + list1);

        System.out.println("Замененный элемент с индексом 2 списка list1: " + list1.set(2, 11));
        System.out.println("Списко list1, после замены элемента с индексом 2: " + list1);

        list1.delete(0);
        System.out.println("Списко list после удаление элемента с индексом 0: " + list1);
        System.out.println("Размер списка list1: " + list1.getSize());
        System.out.println("Голова списка list1: " + list1.getHead());

        list1.add(2, 9);
        System.out.println("Списко list1 после добавления элемента по индексу 2: " + list1);

        System.out.println("Удалился ли элемент со значением 22 в списке list1:" + list1.delete(new Integer(22)));
        System.out.println("Размер списка list1: " + list1.getSize());

        System.out.println("Удалился ли элемент со значением 3 в списке list1:" + list1.delete(new Integer(3)));
        System.out.println("Размер списка list1: " + list1.getSize());
        System.out.println("Список list1 после удаления элемента со значением 3: " + list1);

        SinglyLinkedList<Integer> list3 = list1.copy();
        System.out.println("Списко list3, скопированный со списка list1: " + list3);
        System.out.println("Размер списка list3: " + list3.getSize());

        list1.delete(2);
        System.out.println("Список list1, после удаление элемента с индексом 2: " + list1);

        list2.deleteFirst();
        System.out.println("Список list3, после удаления первого элемента: " + list3);

        System.out.println("Удален первый элемент из list1 со значением " + list1.deleteFirst());
    }
}