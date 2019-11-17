package ru.evgkom.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    private ListItem<T> getItem(int index) {
        int i = 0;
        ListItem<T> p = head;

        for (; p != null; p = p.getNext(), i++) {
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
        return head.getData();
    }

    public T get(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("index must be >= 0 and < count.");
        }

        ListItem<T> p = getItem(index);

        if (p == null) {
            throw new NullPointerException("List must not be null!");
        }

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
        int i = 0;

        if (data == null) {
            for (ListItem<T> p = head; p != null; p = p.getNext(), i++) {
                if (p.getData() == null) {
                    delete(i);
                    return true;
                }
            }

            return false;
        }

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
        ListItem<T> tempItem = getItem(count - 1);
        ListItem<T> p = list.head;

        for (int i = 0; i < list.count; i++, p = p.getNext()) {
            tempItem.setNext(new ListItem<>(p.getData()));
            tempItem = tempItem.getNext();
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