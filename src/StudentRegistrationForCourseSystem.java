import java.util.Arrays;
import java.util.Scanner;


public class Main {
    private static Student[] students = new Student[100];
    private static Course[] courses = new Course[100];
    private static int studentCount = 0;
    private static int courseCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Arrays.toString(args));
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add a Student");
            System.out.println("2. Add a Course");
            System.out.println("3. Register a Student for a Course");
            System.out.println("4. List all courses a student is enrolled in");
            System.out.println("5. List all students in a course");
            System.out.println("6. Exit");
            System.out.println();
            System.out.println("Choose an option (1-6):");

            int opt = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (opt) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    addCourse(scanner);
                    break;
                case 3:
                    registerStudentForCourse(scanner);
                    break;
                case 4:
                    listCoursesForStudent(scanner);
                    break;
                case 5:
                    listStudentsInCourse(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void addStudent(Scanner scanner) {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter student ID:");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter major:");
        String major = scanner.nextLine();
        System.out.println("Enter year:");
        int year = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        students[studentCount] = new Student(firstName, lastName, studentID, major, year);
        studentCount++;
        System.out.println("Student added successfully.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.println("Enter professor name:");
        String professorName = scanner.nextLine();
        System.out.println("Enter course name:");
        String courseName = scanner.nextLine();
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine();
        System.out.println("Enter credits:");
        int credits = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        courses[courseCount] = new Course(professorName, courseName, courseId, credits);
        courseCount++;
        System.out.println("Course added successfully.");
    }

    private static void registerStudentForCourse(Scanner scanner) {
        System.out.println("Enter student ID:");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine();

        Student student = findStudentById(studentID);
        Course course = findCourseById(courseId);

        if (student != null && course != null) {
            if (student.getCurrentCredits() + course.getCredits() <= student.getMaxCredit() && student.getCourseCount() < student.getCoursesList().length) {
                if (course.getStudentCount() < course.getStudentList().length) {
                    student.addCourse(course);
                    course.addStudent(student);
                    System.out.println("Student registered for the course successfully.");
                } else {
                    System.out.println("Can't register a student for the course, because the course is full.");
                }
            } else {
                System.out.println("Can't register a student for a course, because student's credit limit exceeded or course list full.");
            }

        } else {
            System.out.println("Student or course not found.");
        }
    }

    private static void listCoursesForStudent(Scanner scanner) {
        System.out.println("Enter student ID:");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Student student = findStudentById(studentID);
        if (student != null) {
            student.showCourses();
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void listStudentsInCourse(Scanner scanner) {
        System.out.println("Enter course ID:");
        String courseId = scanner.nextLine();

        Course course = findCourseById(courseId);
        if (course != null) {
            course.showStudents();
        } else {
            System.out.println("Course not found.");
        }
    }

    private static Student findStudentById(int studentID) {
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getStudentID() == studentID) {
                return students[i];
            }
        }
        return null;
    }

    private static Course findCourseById(String courseId) {
        for (int i = 0; i < courseCount; i++) {
            if (courses[i].getCourseId().equals(courseId)) {
                return courses[i];
            }
        }
        return null;
    }

}

class Student {
    private String firstName;
    private String lastName;
    private int studentID;
    private String major;
    private int year;
    private Course[] coursesList = new Course[10]; // Assuming a student can take up to 10 courses
    private int maxCredit = 12;
    private int currentCredits = 0;
    private int courseCount = 0;

    public Student(String firstName, String lastName, int studentID, String major, int year) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.major = major;
        this.year = year;
    }

    public Course[] getCoursesList() {
        return coursesList;
    }

    public int getMaxCredit() {
        return maxCredit;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public int getStudentID() {
        return studentID;
    }

    public void addCourse(Course course) {
        coursesList[courseCount] = course;
        currentCredits += course.getCredits();
        courseCount++;
    }

    public void showCourses() {
        System.out.println(this + " is enrolled in the following courses:");
        for (int i = 0; i < courseCount; i++) {
            System.out.println(coursesList[i]);
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}

class Course {
    private String professorName;
    private String courseName;
    private String courseId;
    private int credits;
    private Student[] studentList = new Student[100]; // Assuming a course can have up to 100 students
    private int studentCount = 0;

    public Course(String professorName, String courseName, String courseId, int credits) {
        this.professorName = professorName;
        this.courseName = courseName;
        this.courseId = courseId;
        this.credits = credits;
    }

    public String getCourseId() {
        return courseId;
    }

    public int getCredits() {
        return credits;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public Student[] getStudentList() {
        return studentList;
    }

    public void addStudent(Student student) {
        studentList[studentCount] = student;
        studentCount++;
    }

    public void showStudents() {
        System.out.println("Students enrolled in " + this + ":");
        for (int i = 0; i < studentCount; i++) {
            System.out.println(studentList[i]);
        }
    }

    @Override
    public String toString() {
        return courseName;
    }
}