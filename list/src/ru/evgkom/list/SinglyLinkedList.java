package ru.evgkom.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    private ListItem<T> getItem(int index) {
        ListItem<T> p = head;

        for (int i = 0; p != null; p = p.getNext(), i++) {
            if (i == index) {
                break;
            }
        }

        return p;
    }

    public int getSize() {
        return count;
    }

    public T getHead() {
        if (count == 0) {
            throw new NoSuchElementException("List is empty.");
        }

        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        ListItem<T> p = getItem(index);

        return p.getData();
    }

    public T set(int index, T data) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        ListItem<T> p = getItem(index);
        T temp = p.getData();
        p.setData(data);
        return temp;
    }

    public T delete(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        if (index == 0) {
            T temp = head.getData();
            head = head.getNext();
            count--;
            return temp;
        }

        ListItem<T> p = getItem(index - 1);
        T temp = p.getNext().getData();
        p.setNext(p.getNext().getNext());
        count--;

        return temp;
    }

    public void addToStart(T data) {
        count++;

        ListItem<T> temp = head;
        head = new ListItem<>(data);
        head.setNext(temp);
    }

    public void add(int index, T data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        if (index == 0) {
            addToStart(data);
        } else {
            count++;

            ListItem<T> temp = new ListItem<>(data);
            ListItem<T> p = getItem(index - 1);
            temp.setNext(p.getNext());
            p.setNext(temp);
        }
    }

    public boolean delete(T data) {
        ListItem<T> p = head;

        if (Objects.equals(p.getData(), data)) {
            head = p.getNext();
            count--;

            return true;
        }

        for (; p.getNext() != null; p = p.getNext()) {
            if (Objects.equals(p.getNext().getData(), data)) {
                p.setNext(p.getNext().getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    public T deleteFirst() {
        return delete(0);
    }

    public void revert() {
        for (ListItem<T> p = head, temp, tempNext = null; ; ) {
            if (p.getNext() == null) {
                p.setNext(tempNext);
                head = p;
                break;
            }

            temp = p.getNext();
            p.setNext(tempNext);
            tempNext = p;
            p = temp;
        }
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();

        if (head == null) {
            return list;
        }

        list.addToStart(head.getData());

        for (ListItem<T> p = head.getNext(), tempListItem = list.head; p != null; p = p.getNext()) {
            tempListItem.setNext(new ListItem<>(p.getData()));
            tempListItem = tempListItem.getNext();
        }

        list.count = count;

        return list;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("{ ");

        for (ListItem<T> p = head; ; p = p.getNext()) {
            string.append(p.getData());

            if (p.getNext() == null) {
                string.append(" }");
                break;
            }

            string.append(", ");
        }

        return string.toString();
    }
}