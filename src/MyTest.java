public class MyTest {
    public static void main(String[] args) {
        test_2();
        System.out.println("\n-------------------------\n");
        test_3();
        System.out.println("\n-------------------------\n");
        test_4();
        System.out.println("\n-------------------------\n");
        test_5();
        System.out.println("\n-------------------------\n");
        test_6();
    }

    public static void test_2() {
        System.out.println("Running test_2...");

        // Create a new SpeciesQueue
        SpeciesQueue<Animal> queue = new SpeciesQueue<>();

        // Add some animals to the queue
        queue.add(new Lion());
        queue.add(new Tiger());
        queue.add(new Lion());
        queue.add(new Monkey());

        // Show the queue before removal
        System.out.println("Queue before removing animals:");
        showQueueState(queue);

        // Remove animals from the queue one by one and show the state of the queue after each removal
        System.out.println("\nRemoving animals...");
        queue.remove();
        showQueueState(queue);
        queue.remove();
        showQueueState(queue);
        queue.remove();
        showQueueState(queue);

        System.out.println("test_2 finished.");
    }

    public static void test_3() {
        System.out.println("Running test_3...");

        // Create a new SpeciesQueue
        SpeciesQueue<Animal> queue = new SpeciesQueue<>();

        // Add animals with the same dominance level but different classes
        queue.add(new Lion());
        queue.add(new Tiger());
        queue.add(new Lion());

        // Show the queue before any removal
        System.out.println("Queue before removing animals:");
        showQueueState(queue);

        // Remove animals from the queue one by one
        System.out.println("\nRemoving animals...");
        queue.remove();
        showQueueState(queue);
        queue.remove();
        showQueueState(queue);

        System.out.println("test_3 finished.");
    }

    // Test for exceptions in adding/removing elements
    public static void test_4() {
        System.out.println("Running test_4...");

        // Create a new SpeciesQueue
        SpeciesQueue<Animal> queue = new SpeciesQueue<>();

        try {
            // Try adding null element (this should throw an InvalidInputException)
            queue.add(null);
        } catch (InvalidInputException e) {
            System.out.println("Caught InvalidInputException while adding null");
        }

        try {
            // Try removing from an empty queue (this should throw an EmptyQueueException)
            queue.remove();
        } catch (EmptyQueueException e) {
            System.out.println("Caught EmptyQueueException while removing from empty queue");
        }

        System.out.println("test_4 finished.");
    }

    // Test for iterator functionality
    public static void test_5() {
        System.out.println("Running test_5...");

        // Create a new SpeciesQueue
        SpeciesQueue<Animal> queue = new SpeciesQueue<>();

        // Add some animals to the queue
        queue.add(new Lion());
        queue.add(new Tiger());
        queue.add(new Monkey());
        queue.add(new Zebra());

        // Test the iterator
        System.out.println("Iterating over the queue:");
        for (Animal animal : queue) {
            System.out.println(animal);
        }

        System.out.println("test_5 finished.");
    }

    // Test cloning functionality
    public static void test_6() {
        System.out.println("Running test_6...");

        // Create a new SpeciesQueue
        SpeciesQueue<Animal> queue = new SpeciesQueue<>();

        // Add some animals to the queue
        queue.add(new Lion());
        queue.add(new Tiger());
        queue.add(new Lion());

        // Clone the queue
        SpeciesQueue<Animal> clonedQueue = queue.clone();

        // Show the original and cloned queue
        System.out.println("Original queue:");
        showQueueState(queue);
        System.out.println("Cloned queue:");
        showQueueState(clonedQueue);

        // Remove an animal from the cloned queue and show the state again
        clonedQueue.remove();
        System.out.println("Cloned queue after removal:");
        showQueueState(clonedQueue);

        System.out.println("queue after removal from cloned:");
        showQueueState(queue);

        System.out.println("test_6 finished.");
    }

    // Utility method to print the state of the queue
    public static void showQueueState(SpeciesQueue<Animal> queue) {
        System.out.print("Queue: ");
        if (queue.isEmpty()) {
            System.out.println("empty");
        } else {
            for (Animal animal : queue) {
                System.out.print(animal + " ");
            }
            System.out.println();
        }
    }
}