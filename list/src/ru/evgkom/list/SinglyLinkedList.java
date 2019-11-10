package ru.evgkom.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T HeadData() {
        return head.getData();
    }

    public T getData(int index) {
        int i = 0;
        ListItem<T> p = head;
        for (; p != null; p = p.getNext(), i++) {
            if (i == index) {
                break;
            }
        }
        return p.getData();
    }

    public T setData(int index, T data) {
        int i = 0;

        ListItem<T> p = head;
        for (; p != null; p = p.getNext(), i++) {
            if (i == index) {
                break;
            }
        }
        T temp = p.getData();
        p.setData(data);
        return temp;
    }

}