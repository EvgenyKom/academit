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

    public MyArrayList(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be > 0.");
        }

        //noinspection unchecked
        items = (E[]) new Object[initialCapacity];
    }

    private void ensureCapacity(int minCapacity) {
        if (items.length < minCapacity) {
            items = Arrays.copyOf(items, minCapacity * 2);
        }
    }

    private void validateIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be >= 0 and < size.");
        }
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("The collection has changed during iterations.");
            }

            if (!hasNext()) {
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
        int index = indexOf(o);

        if (index > -1) {
            remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] array = c.toArray();
        int collectionLength = array.length;
        ensureCapacity(size + collectionLength);

        modCount++;
        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(array, 0, items, size, collectionLength);
        size += collectionLength;

        return c.size() != 0;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be <= size and >= 0");
        }

        int collectionSize = c.size();

        if (collectionSize == 0) {
            return false;
        }

        ensureCapacity(items.length + collectionSize);
        modCount++;

        int numMoved = size - index;

        if (numMoved > 0) {
            System.arraycopy(items, index, items, index + collectionSize, numMoved);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(c.toArray(), 0, items, index, collectionSize);
        size += collectionSize;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;

        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                modified = true;
                remove(i);
            }
        }

        return modified;
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
        validateIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);

        E tempItem = items[index];
        items[index] = element;
        return tempItem;
    }

    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index must be <= size and >= 0");
        }

        ensureCapacity(size + 10);
        modCount++;

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);

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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
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