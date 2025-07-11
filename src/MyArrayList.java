import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class MyArrayList<T extends Cloneable> implements Iterable<T>, Cloneable {
    public static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int capacity;
    private int size;

    public MyArrayList() {
        size = 0;
        capacity = INITIAL_CAPACITY;
        elements = new Object[capacity];
    }

    /**
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return the size of the array
     */
    public int size() {
        return size;
    }

    /**
     * @return the element at index i
     */
    public T get(int i) {
        return (T) elements[i];
    }

    /**
     * Insert an element at index i. The element is allowed to be null.
     */
    public void insert(int i, T element) {
        if (size == capacity) {
            resize();
        }

        for (int j = size - 1; j >= i; j--) {
            elements[j + 1] = elements[j];
        }
        elements[i] = element;
        size++;
    }

    /**
     * append an element to the end of the list. The element is allowed to be null.
     */
    public void append(T element) {
        insert(size, element);
    }

    /**
     * Remove an element at index i and return it.
     */
    public T remove(int i) {
        T result = get(i);

        for (int j = i; j < size - 1; j++) {
            elements[j] = elements[j + 1];
        }
        size--;

        return result;
    }

    /**
     * Reallocate the array to increase capacity.
     */
    private void resize() {
        Object[] newElements = new Object[capacity * 2];

        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        capacity *= 2;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public MyArrayList<T> clone() {
        try {
            MyArrayList<T> clone = (MyArrayList<T>) super.clone();
            clone.elements = new Object[capacity];
            for (int i = 0; i < capacity; i++) {
                if (elements[i] != null) {
                    Method cloneMethod = elements[i].getClass().getMethod("clone");
                    cloneMethod.setAccessible(true);
                    clone.elements[i] = cloneMethod.invoke(elements[i]);
                }
            }
            return clone;
        } catch (CloneNotSupportedException | NoSuchMethodException
                 | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += elements[i];
            if (i < size - 1)
                result += Util.COMMA;
        }
        return result;
    }

    private class Itr implements Iterator<T> {
        private int i;

        public Itr() {
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public T next() {
            return get(i++);
        }
    }
}
