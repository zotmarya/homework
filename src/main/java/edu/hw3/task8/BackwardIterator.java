package edu.hw3.task8;

import java.util.Collection;
import java.util.Iterator;

public class BackwardIterator<T> implements Iterator<T> {

    private T[] arrayOfElements;
    private int arraySize;
    private int currentIndex;

    @Override
    public boolean hasNext() {
        if (arrayOfElements == null) {
            return false;
        }

        if (currentIndex >= 0) {
            return true;
        }

        return false;
    }

    @Override
    public T next() {
        if (hasNext()) {
            return arrayOfElements[currentIndex--];
        }

        return null;
    }

    public BackwardIterator(Collection<T> collection) {
        if (collection != null) {
            arrayOfElements = (T[]) collection.toArray();
            arraySize = arrayOfElements.length;
            currentIndex = arraySize - 1;
        }
    }
}
