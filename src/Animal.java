public abstract class Animal implements Comparable<Animal>, Cloneable {
    private final int power;

    protected Animal(int power) {
        this.power = power;
    }

    @Override
    public int compareTo(Animal o) {
        return this.power - o.power;
    }

    @Override
    public Animal clone() {
        try {
            return (Animal) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return this.getClass().getName();
    }
}
