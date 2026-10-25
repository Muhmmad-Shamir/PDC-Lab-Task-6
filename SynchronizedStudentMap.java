import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SynchronizedStudentMap {
    public static void main(String[] args) throws InterruptedException {

        // Create a normal HashMap
        HashMap<String, Integer> originalMap = new HashMap<>();

        // Create a synchronized wrapper around the HashMap
        Map<String, Integer> students =
                Collections.synchronizedMap(originalMap);

        // Create a pool with three worker threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Submit three tasks
        for (int t = 1; t <= 3; t++) {
            final int threadId = t;

            executor.execute(new Runnable() {
                @Override
                public void run() {

                    // Each task inserts 100 unique students
                    for (int i = 1; i <= 100; i++) {
                        String name = "Student_" + threadId + "_" + i;
                        students.put(name, 80);
                    }
                }
            });
        }

        // Stop accepting new tasks
        executor.shutdown();

        // Keep waiting until all tasks finish
        while (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Still waiting for tasks to finish...");
        }

        System.out.println("Expected entries: " + (3 * 100));
        System.out.println("Actual entries: " + students.size());
    }
}