package ru.evgkom.hashtable;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] lists;
    private int modCount;
    private int size;
    private static final int HASHTABLE_ARRAY_SIZE = 10;

    private int getIndex(Object o) {
        return Math.abs(o.hashCode() % lists.length);
    }

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[HASHTABLE_ARRAY_SIZE];
    }

    private class HashTableIterator implements Iterator<T> {
        private int currentList = 0;
        private int currentListIndex = 0;
        private int currentCollectionIndex = 0;
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentCollectionIndex < size();
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("The collection has changed during iterations.");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("Collection has ended.");
            }

            while (lists[currentList] == null || lists[currentList].size() == 0 || currentListIndex >= lists[currentList].size()) {
                currentList++;
                currentListIndex = 0;
            }

            T nextData = (T) lists[currentList].get(currentListIndex);
            currentListIndex++;
            currentCollectionIndex++;

            return nextData;
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
        if (lists[getIndex(o)] == null) {
            return false;
        }

        return lists[getIndex(o)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Iterator<T> iterator = iterator();
        //noinspection unchecked
        T[] array = (T[]) new Object[size()];

        for (int i = 0; i < size(); i++) {
            array[i] = iterator.next();
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size()) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(toArray(), size);
        }

        Iterator<T> iterator = iterator();

        for (int i = 0; i < size(); i++) {
            //noinspection unchecked
            a[i] = (T1) iterator.next();
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        modCount++;
        size++;

        if (lists[getIndex(t)] == null) {
            //noinspection unchecked
            lists[getIndex(t)] = new ArrayList();
        }

        lists[getIndex(t)].add(t);

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (lists[getIndex(o)] == null || lists[getIndex(o)].size() == 0) {
            return false;
        }

        if (lists[getIndex(o)].remove(o)) {
            modCount++;
            size--;

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
    public boolean addAll(Collection<? extends T> c) {
        modCount++;

        for (Object o : c) {
            //noinspection unchecked
            add((T) o);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            while (remove(o)) {
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        ArrayList<T> tempList = new ArrayList<>();
        HashTableIterator iterator = new HashTableIterator();

        while (iterator.hasNext()) {
            T item = iterator.next();
            boolean contained = false;

            for (Object o : c) {
                if (Objects.equals(o, item)) {
                    contained = true;

                    break;
                }
            }

            if (!contained) {
                tempList.add(item);
            }
        }

        if (tempList.size() != 0) {
            modified = true;
        }

        removeAll(tempList);

        return modified;
    }

    @Override
    public void clear() {
        //noinspection unchecked
        lists = new ArrayList[HASHTABLE_ARRAY_SIZE];
        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("{ ");
        HashTableIterator iterator = new HashTableIterator();

        if (!iterator.hasNext()) {
            s.append(" }").append(" size = ").append(size);
        }

        while (iterator.hasNext()) {
            s.append(iterator.next());

            if (iterator.hasNext()) {
                s.append(", ");
            } else {
                s.append(" }").append(" size = ").append(size);
            }
        }

        return s.toString();
    }
}