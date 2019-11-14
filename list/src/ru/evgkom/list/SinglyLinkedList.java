package ru.evgkom.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getSize() {
        return count;
    }

    public T getHead() {
        if (head == null) {
            throw new NullPointerException("List must not be null!");
        }

        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index > count - 1) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        int i = 0;
        ListItem<T> p = head;

        for (; p != null; p = p.getNext(), i++) {
            if (i == index) {
                break;
            }
        }

        if (p == null) {
            throw new NullPointerException("List must not be null!");
        }

        return p.getData();
    }

    public T set(int index, T data) {
        if (index < 0 || index > count - 1) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        int i = 0;
        ListItem<T> p = head;

        for (; p != null; p = p.getNext(), i++) {
            if (i == index) {
                break;
            }
        }

        if (p == null) {
            throw new NullPointerException("List must not be null!");
        }

        T temp = p.getData();
        p.setData(data);
        return temp;
    }

    public T delete(int index) {
        if (index < 0 || index > count - 1) {
            throw new ArrayIndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        T temp = get(index);

        if (index == 0) {
            head = head.getNext();
            count--;
            return temp;
        }

        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext(), i++) {
            if (i == index - 1) {
                p.setNext(p.getNext().getNext());
                count--;
                break;
            }
        }

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
            throw new ArrayIndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        if (index == 0) {
            addToStart(data);
        } else {
            count++;

            int i = 0;
            ListItem<T> p = head;
            ListItem<T> temp = new ListItem<>(data);
            for (; p != null; p = p.getNext(), i++) {
                if (i == index - 1) {
                    temp.setNext(p.getNext());
                    p.setNext(temp);

                    break;
                }
            }
        }
    }

    public boolean delete(T data) {
        int i = 0;

        for (ListItem<T> p = head; p != null; p = p.getNext(), i++) {
            if (p.getData().equals(data)) {
                delete(i);
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

    public void copy(SinglyLinkedList<T> list) {
        for (ListItem<T> p = list.head; p != null; p = p.getNext()) {
            add(count, p.getData());
        }
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