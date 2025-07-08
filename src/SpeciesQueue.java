import java.util.Iterator;

public class SpeciesQueue<T extends Cloneable & Comparable<T>> implements Iterable<T>, Cloneable {
    MyArrayList<T> elements;

    public SpeciesQueue() {
        elements = new MyArrayList<>();
    }

    public void add(T element) {
        if (element == null) {
            throw new InvalidInputException();
        }

        int i;
        for(i = 0; i < elements.size(); i++) {
            int compareVal = element.compareTo(elements.get(i));
            if (compareVal > 0 ||
                    (compareVal == 0 &&
                            element.getClass() == elements.get(i).getClass())) {
                elements.insert(i, element);
                return;
            }
        }
        elements.insert(i, element);
    }

    public T remove() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }

        return elements.remove(0);
    }

    public T peek() {
        if(isEmpty()) {
            throw new EmptyQueueException();
        }

        return elements.get(0);
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

    @Override
    public String toString() {
        String result = "";
        for (T element: elements) {
            result += element + ", ";
        }
        return result;
    }
}
