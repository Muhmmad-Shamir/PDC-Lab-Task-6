import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Q3ConcurrentStudentMap {
    public static void main(String[] args) throws InterruptedException {

        ConcurrentHashMap<String, Integer> students =
                new ConcurrentHashMap<>();

        int numberOfThreads = 5;
        int recordsPerTask = 1000;

        // Start measuring time
        long startTime = System.nanoTime();

        ExecutorService executor =
                Executors.newFixedThreadPool(numberOfThreads);

        // Submit five tasks
        for (int t = 1; t <= numberOfThreads; t++) {
            final int threadId = t;

            executor.execute(new Runnable() {
                @Override
                public void run() {

                    // Insert 1,000 unique records
                    for (int i = 1; i <= recordsPerTask; i++) {
                        String name = "Student_" + threadId + "_" + i;
                        students.put(name, 80);
                    }

                    // Retrieve the marks for every inserted record
                    for (int i = 1; i <= recordsPerTask; i++) {
                        String name = "Student_" + threadId + "_" + i;
                        Integer marks = students.get(name);

                        // Display one retrieved record per task
                        if (i == 1) {
                            System.out.println(name + " marks: " + marks);
                        }
                    }
                }
            });
        }

        executor.shutdown();

        while (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
            System.out.println("Still waiting for tasks to finish...");
        }

        // Stop measuring time
        long endTime = System.nanoTime();

        double executionTime = (endTime - startTime) / 1_000_000.0;

        System.out.println("\nNumber of threads: " + numberOfThreads);
        System.out.println("Expected entries: "
                + (numberOfThreads * recordsPerTask));
        System.out.println("Actual entries: " + students.size());
        System.out.printf("Execution time: %.3f ms%n", executionTime);
    }
}