public class Ark {
    SpeciesQueue<Animal> queue;
    public Ark() {
        queue = new SpeciesQueue<>();
    }

    public void add(Animal animal) {
        queue.add(animal);
    }

    public void enterToArk() {
        Animal animal = queue.remove();
        System.out.println("A " + animal + " entered the ark");
    }


    public void enterAllToArk() {
        while (!queue.isEmpty()) {
            enterToArk();
        }
    }

    public void showQueue() {
        System.out.println(queue);
    }
}
