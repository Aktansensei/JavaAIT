//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Step 1: Create a Student class that implements Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private double gpa;
    private int studentId;

    public Student(int studentId, String name, int age, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public String toString() {
        return "ID: " + studentId + " | " + name + " - Age: " + age + ", GPA: " + gpa;
    }
}

public class StudentFileIO {
    private static final String FILE_NAME = "students.bin";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 2: Get user input for student details
        List<Student> students = new ArrayList<>();
        System.out.println("Enter student details (or type 'exit' to stop):");

        while (true) {
            System.out.print("Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();

            System.out.print("GPA: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            students.add(new Student(id, name, age, gpa));

            System.out.print("Add another student? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("no")) break;
        }

        scanner.close();

        // Step 3: Compute metadata
        int totalStudents = students.size();
        double totalGPA = 0.0;
        for (Student s : students) {
            totalGPA += s.getGpa();
        }
        double avgGPA = (students.size() > 0) ? (totalGPA / students.size()) : 0.0;
        // Step 4: Write students and metadata to binary file
        writeToFile(totalStudents, avgGPA, students);

        // Step 5: Read and display the file contents
        readFromFile();
    }

    private static void writeToFile(int totalStudents, double avgGPA, List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeInt(totalStudents);
            oos.writeDouble(avgGPA);
            for (Student student : students) {
                oos.writeObject(student);
            }
            System.out.println("✅ Student data saved successfully!\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            int totalStudents = ois.readInt();
            double avgGPA = ois.readDouble();

            System.out.println("📂 Reading from file...");
            System.out.println("Total Students: " + totalStudents);
            System.out.printf("Average GPA: %.2f\n", avgGPA);
            System.out.println("Student List:");

            while (true) {
                try {
                    Student student = (Student) ois.readObject();
                    System.out.println(student);
                } catch (EOFException e) {
                    break; // End of file reached
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}
