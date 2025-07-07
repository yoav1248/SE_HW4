import java.util.Iterator;

public class SpeciesQueue<T extends Cloneable & Comparable<T>> implements Iterable<T>, Cloneable {
    CircularArray<T> elements;

    public SpeciesQueue() {
        elements = new CircularArray<>();
    }

    public void add(T element) {
        if (element == null) {
            throw new InvalidInputException();
        }

        elements.add(element);

        reorder();
    }


    private void reorder() {
        for (int i = elements.size() - 1; i > 0; i--) {
            if (elements.get(i).compareTo(elements.get(i - 1)) < 0) {
                elements.swap(i, i-1);
            }
        }
    }


    public T remove() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }

        return elements.poll();
    }

    public T peek() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }

        return elements.peek();
    }


    @Override
    public SpeciesQueue<T> clone() {
        try {
            SpeciesQueue<T> clone = (SpeciesQueue<T>) super.clone();
            clone.elements = this.elements.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
