import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class CircularArray<T extends Cloneable> implements Iterable<T>, Cloneable {
    public static final int INITIAL_CAPACITY = 10;

    private Object[] elements;
    private int capacity;
    private int size;
    private int front, back;


    public CircularArray() {
        size = 0;
        front = 0;
        back = 0;
        capacity = INITIAL_CAPACITY;
        elements = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retrieve an item that is the (i+1)th in the order of the queue.
     */
    public T get(int i) {
        return (T) elements[(front + i) % capacity];
    }


    /**
     * Sets the item that is the (i+1)th in the order of the queue to value x.
     */
    public void set(int i, T x) {
        elements[(front + i) % capacity] = x;
    }

    public int size() {
        return size;
    }


    /**
     * Swaps element at index i with
     */
    public void swap(int i, int j) {
        T temp = get(i);
        set(i, get(j));
        set(j, temp);
    }

    /**
     * Insert an element at index i. The element is allowed to be null.
     */
    public void insert(int i, T element) {
        if (size == capacity) {
            resize();
        }

        for (int j = i; j < size; j++) {
            set(j + 1, get(j));
        }
        set(i, element);
    }

    /**
     * Returns the item at the front of the queue.
     * if queue is empty, return null
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return get(0);
    }

    /**
     * Retrieve an item from front of the queue, and remove it.
     * if queue is empty, return null
     */
    public T poll() {
        if (isEmpty())
            return null;

        T result = get(0);
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /**
     * Add an item to the back of queue. The item is allowed to be null.
     */
    public void add(T element) {
        if (size == capacity) {
            resize();
        }

        elements[back] = element;
        back = (back + 1) % capacity;
        size++;
    }

    private void resize() {
        Object[] newElements = new Object[capacity * 2];

        int i = 0;
        for (T element: this) {
            newElements[i++] = element;
        }

        elements = newElements;
        front = 0;
        back = size;
        capacity *= 2;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    public CircularArray<T> clone() {
        try {
            CircularArray<T> clone = (CircularArray<T>) super.clone();
            clone.elements = new Object[capacity];
            for (int i = 0; i < capacity; i++) {
                if (elements[i] != null) {
                    clone.elements[i] = elements[i].getClass().getMethod("clone").invoke(elements[i]);
                }
            }
            return clone;
        } catch (CloneNotSupportedException | NoSuchMethodException
                 | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
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
