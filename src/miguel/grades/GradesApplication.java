package miguel.grades;

import miguel.util.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GradesApplication {

    private static Input input = new Input();
    private static boolean continueLoop = true;
    public static Scanner scan = new Scanner(System.in);

    public static HashMap<String, Student> students = new HashMap<>();

    public static void splash() {
        System.out.println("Here are the GitHub user names of our students:");
        students.forEach((key, value) -> System.out.printf("|%s| ", key));
        System.out.printf("%n%n");
    }

    public static void displayOptions() {
        System.out.printf("Select an option: (0-3)%n");
        System.out.printf("0 - exit%n");
        System.out.printf("1 - student search%n");
        System.out.printf("2 - student search (detailed)%n");
        System.out.printf("3 - log student attendance%n");
        System.out.printf("4 - view student attendance %%%n");
        System.out.printf("5 - view student report%n");
        System.out.printf("6 - view all students%n");
        System.out.printf("7 - view class average%n");
    }

    public static int selectOption() {
        return input.getInt(0, 7);
    }

    public static void userSelection(int option) {
        switch (option) {
            case 0:
                continueLoop = false;
                break;
            case 1:
                runStudentSearch();
                break;
            case 2:
                runStudentSearchDetailed();
                break;
            case 3:
                runStudentAddAttendance();
                break;
            case 4:
                runStudentAttendance();
                break;
            case 5:
                runStudentReport();
                break;
            case 6:
                runEntireClass();
                break;
            case 7:
                runClassAvg();
                break;
            default:
                System.out.println("Hi! Don't know how you got here :)");
                confirm();
                break;
        }
    }

    protected static String studentSearchInput() {
        System.out.println("What student would you like to see more information on?");
        String studentId = scan.next();
        if (students.get(studentId) == null) {
            System.out.printf("Sorry, no student found with the GitHub username of \"%s\".%n%n", studentId);
            return studentSearchInput(); // Talk about
        } else {
            return studentId;
        }
    }

    public static void displayStudent(String studentName) {
        System.out.printf("Name %s - GitHub User name: %s%nGrades:%n", students.get(studentName).getName(), studentName);
        displayGrades(studentName);
    }

    public static void displayGrades(String name) {
        for (int grade : students.get(name).getAllGrades()) {
            System.out.printf("- %d%n", grade);
        }
    }

    public static void displayAverage(String studentName) {
        System.out.printf("GPA: %.1f%n", students.get(studentName).getGradeAverage());
    }

    public static String[] getNames() { // get all student names
        String[] studentNamesArr = new String[students.size()];
        return students.keySet().toArray(studentNamesArr); // convert Set object -> array
    }

    public static void displayAllStudents(String[] studentNames) { // displays all students details
        for (String name : studentNames) { // iterates through array and prints each element
            System.out.printf("Student: %s%n", name);
            System.out.println("Grades:");
            displayGrades(name);
            displayAverage(name);
            System.out.println("---------------");
        }
    }

    // ===== display student w/grades =====
    public static void runStudentSearch() { // displays student w/all grades
        splash();
        String student = studentSearchInput();
        displayStudent(student);
        confirm();
    }

    // ===== display student w/grades & average
    public static void runStudentSearchDetailed() { // displays student w/all grades & avg.
        splash();
        String student = studentSearchInput();
        displayStudent(student);
        displayAverage(student);
        confirm();
    }

    // ===== log student attendance =====
    public static boolean recordAttendance(String date, String value, String studentName) {
        switch (value.toLowerCase()) {
            case "a":
            case "absent":
            case "p":
            case "present":
                students.get(studentName).setAttendance(date, value.toLowerCase());
                return true;
            default:
                System.out.println("Attendance indicator not valid!");
                return false;
        }
    }

    public static void addStudentAttendance() {
        String student = studentSearchInput();
        System.out.println("Log student's attendance:");
        String date = input.getString("Date: (YYYY/MM/DD)"); // check
        String attendance = input.getString("Attendance: (P)-present/ (A)-absent");
        while (!recordAttendance(date, attendance, student)) {
            attendance = input.getString("Attendance: (P) = present/ (A) = absent");
        }
        System.out.printf("Logged: %s : %s - %s%n", date, attendance.toUpperCase(), students.get(student).getName());
    }

    public static void runStudentAddAttendance() {
        splash();
        addStudentAttendance();
        confirm();
    }

    // ====== display student percentage ======
    public static void displayStudentAttendance() {
        String student = studentSearchInput();
        if (students.get(student).getAttendance().isEmpty()) {
            System.out.printf("No recorded attendances. Student - %s%n", students.get(student).getName());
        } else {
            System.out.printf("Attendance %%: %d - %s%n", students.get(student).getAttendancePercentage(), students.get(student).getName());
        }
    }

    public static void runStudentAttendance() {
        splash();
        displayStudentAttendance();
        confirm();
    }

    // ===== All student's grades =====
    public static void runEntireClass() {
        displayAllStudents(getNames());
        confirm();
    }

    // ===== class average =====
    public static ArrayList<Integer> classGrades() { // returns an arrayList w/entire class grades
        ArrayList<Integer> arr = new ArrayList<>();
        for (String name : getNames()) { // for each student
            for (int grade : students.get(name).getAllGrades()) { // add students grades to the ArrayList
                arr.add(grade);
            }
        }
        return arr;
    }

    public static void displayClassAvg(ArrayList<Integer> classGrades) {
        float sum = 0;
        for (int grade : classGrades) {
            sum += grade;
        }
        System.out.printf("Class Average: %.1f%n", (sum / classGrades.size()));
    }

    public static void runClassAvg() {
        displayClassAvg(classGrades());
        confirm();
    }

    // ===== csv report =====
    public static void studentReport() {
        for (String name : getNames()) {
            System.out.printf("%s,%s,%.1f%n", name, students.get(name).getName(), students.get(name).getGradeAverage());
        }
        System.out.println();
    }

    public static void runStudentReport() {
        System.out.printf("%nname,github_username,average%n");
        studentReport();
        confirm();
    }

    // ===== continuation confirmation =====
    public static void confirm() { // confirmation to continue
        System.out.println("Would you like to perform another search?(yes/no)");
        String yesNo = scan.next();
        if (yesNo.equalsIgnoreCase("y") || yesNo.equalsIgnoreCase("yes")) {
            run();
        } else {
            continueLoop = false;
            System.out.println("Goodbye, and have a wonderful day!");
        }
    }

    public static void run() { // single method to run program
        splash();
        displayOptions();
        userSelection(selectOption());
    }

    public static void main(String[] args) {

        Student bob = new Student("Bob"); // create new Student objects
        Student jack = new Student("Jack");
        Student phil = new Student("Phil");
        Student bill = new Student("Bill");

        bob.addGrade(89); // add grades
        bob.addGrade(97);
        bob.addGrade(69);

        bob.setAttendance("2019/10/01", "a");
        bob.setAttendance("2019/10/02", "a");
        bob.setAttendance("2019/10/03", "a");
        bob.setAttendance("2019/10/04", "a");

        jack.addGrade(99);
        jack.addGrade(96);
        jack.addGrade(89);

        jack.setAttendance("2019/10/05", "p");
        jack.setAttendance("2019/10/06", "p");
        jack.setAttendance("2019/10/07", "a");

        phil.addGrade(79);
        phil.addGrade(83);
        phil.addGrade(78);

        bob.setAttendance("2019/10/01", "p");
        bob.setAttendance("2019/10/02", "p");
        bob.setAttendance("2019/10/03", "p");
        bob.setAttendance("2019/10/04", "p");

        bill.addGrade(100);
        bill.addGrade(89);
        bill.addGrade(94);

        bob.setAttendance("2019/10/01", "p");
        bob.setAttendance("2019/10/02", "p");
        bob.setAttendance("2019/10/03", "p");
        bob.setAttendance("2019/10/04", "a");

        students.put("bigBob", bob); // add to HashMap with ('user name','Student')
        students.put("bigJack", jack);
        students.put("bigPhil", phil);
        students.put("littleBill", bill);

        System.out.println("Welcome!");

        while (continueLoop) {
            run();
        }
    }
}
