public class Ark {
    SpeciesQueue<Animal> queue;

    public Ark() {
        queue = new SpeciesQueue<>();
    }

    /**
     * Add an animal to the queue to enter the ark
     */
    public void add(Animal animal) {
        queue.add(animal);
    }

    /**
     * Enter the animal at the front of the queue to the ark
     */
    public void enterToArk() {
        Animal animal = queue.remove();
        System.out.println("A " + animal + " entered the ark");
    }

    /**
     * Enter all animals in the queue to the ark
     */
    public void enterAllToArk() {
        while (!queue.isEmpty()) {
            enterToArk();
        }
    }

    /**
     * Show the contents of the queue
     */
    public void showQueue() {
        System.out.println(queue);
    }
}
