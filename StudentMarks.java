import java.util.HashMap;

public class StudentMarks {
    public static void main(String[] args) {

        HashMap<String, Integer> students = new HashMap<>();

        // 1. Insert five students
        students.put("Ali", 80);
        students.put("Ahmed", 75);
        students.put("Sara", 90);
        students.put("Shamir", 85);
        students.put("Ayesha", 88);

        // 2. Display all students
        System.out.println("All student records:");

        for (String name : students.keySet()) {
            System.out.println(name + ": " + students.get(name));
        }

        // 3. Update Ali's marks
        students.put("Ali", 95);
        System.out.println("\nUpdated marks of Ali: " + students.get("Ali"));

        // 4. Retrieve one student's marks
        System.out.println("Marks of Shamir: " + students.get("Shamir"));

        // 5. Display total students
        System.out.println("Total students: " + students.size());
    }
}