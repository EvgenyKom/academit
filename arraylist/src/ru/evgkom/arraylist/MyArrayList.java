package ru.evgkom.arraylist;

import java.util.*;

public class MyArrayList<E> implements List<E> {
    private static final int INITIAL_CAPACITY = 5;
    private E[] items;
    private int size;
    private int modCount = 0;

    public MyArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[INITIAL_CAPACITY];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private void ensureCapacity(int minCapacity) {
        while (minCapacity >= items.length) {
            increaseCapacity();
        }
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("The collection has changed during iterations.");
            }

            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException("Collection has ended.");
            }

            currentIndex++;

            return items[currentIndex];
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E e) {
        modCount++;

        ensureCapacity(size + 1);

        items[size] = e;
        size++;

        return true;
    }

    @Override
    public boolean remove(Object o) {

        if (o == null) {
            for (int index = 0; index < size; index++) {
                if (items[index] == null) {
                    modCount++;

                    if (size - index - 1 > 0) {
                        System.arraycopy(items, index + 1, items, index, size - index - 1);
                        size--;
                        return true;
                    }
                }
            }
        } else {
            for (int index = 0; index < size; index++) {
                if (o.equals(items[index])) {
                    modCount++;

                    if (size - index - 1 > 0) {
                        System.arraycopy(items, index + 1, items, index, size - index - 1);
                        size--;
                        return true;
                    }

                    size--;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (indexOf(o) < 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int tempLength = a.length;

        ensureCapacity(size + tempLength);
        modCount++;

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(a, 0, items, size, tempLength);

        size += tempLength;

        return tempLength != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be <= size and >= 0");
        }

        Object[] a = c.toArray();
        int tempNumber = a.length;

        ensureCapacity(size + tempNumber);
        modCount++;

        int numMoved = size - index;

        if (numMoved > 0) {
            System.arraycopy(items, index, items, index + tempNumber, numMoved);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(a, 0, items, index, tempNumber);
        size += tempNumber;

        return tempNumber != 0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            while (true) {
                if (!remove(o)) {
                    break;
                }
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int i = 0, j = 0;

        for (; i < size; i++) {
            if (c.contains(items[i])) {
                items[j] = items[i];
                j++;
            }
        }

        if (i != size) {
            System.arraycopy(items, i, items, j, size - i);
            j += size - i;
        }

        if (j != size) {
            for (int k = j; k < size; k++) {
                items[k] = null;
            }

            modCount += size - j;
            size = j;
        }

        return true;
    }

    @Override
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    @Override
    public E get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be < size.");
        }

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be < size.");
        }

        E tempItem = items[index];
        items[index] = element;
        return tempItem;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be <= size and >= 0");
        }

        ensureCapacity(size + 1);
        modCount++;

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index must be < size.");
        }

        modCount++;

        E temp = get(index);

        if (index < size - 1) {
            System.arraycopy(items, index + 1, items, index, size - index - 1);
        }

        size--;

        return temp;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (items[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(items[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void trimToSize() {
        modCount++;

        if (size < items.length) {
            if (size == 0) {
                //noinspection unchecked
                items = (E[]) new Object[]{};
            } else {
                items = Arrays.copyOf(items, size);
            }
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{ ");

        for (int i = 0; i < size; i++) {
            s.append(items[i]);

            if (i + 1 < size) {
                s.append(", ");
            }
        }
        s.append(" }").append(" size = ").append(size);

        return s.toString();
    }
}