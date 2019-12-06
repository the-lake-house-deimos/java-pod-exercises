package miguel.grades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private ArrayList<Integer> grades;
    private HashMap<String, String> attendance;

    public Student(String name) {
        this.name = name;
        grades = new ArrayList<>();
        attendance = new HashMap<>();
    }

    // returns the student's name
    public String getName() {
        return this.name;
    }

    // adds the given grade to the grades property
    public void addGrade(int grade) {
        grades.add(grade);
    }

    public int[] getAllGrades() {
        int[] newArray = new int[grades.size()];
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = grades.get(i);
        }
        return newArray;
    }

    // returns the average of the students grades
    public double getGradeAverage() {
        double average = 0;
        for (int grade : grades) {
            average += grade;
        }
        average = average / grades.size();
        return average;
    }

    public HashMap<String, String> getAttendance() {
        return attendance;
    }

    public void setAttendance(String date, String status) {
        attendance.put(date, status);
    }

    public HashMap<String, String> getAbsences() {
        HashMap<String, String> absences = new HashMap<>();
        for (Map.Entry<String, String> entry : attendance.entrySet()) {
            if (entry.getValue().equalsIgnoreCase("a")) {
                absences.put(entry.getKey(), entry.getValue());
            }
        }
        return absences;
    }

    public int getAttendancePercentage() {
        float totalDays = attendance.size();
        float totalAbsences = getAbsences().size();
        return (int) (((totalDays - totalAbsences) / totalDays) * 100);
    }

    public static void main(String[] args) {
        Student student1 = new Student("Bob");

    }
}
