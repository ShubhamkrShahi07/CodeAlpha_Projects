import java.util.ArrayList;
import java.util.Scanner;

class Grade {
    private int id;
    private int grade;

    public Grade(int id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }
}

class GradeManager {
    private ArrayList<Grade> grades;

    public GradeManager() {
        grades = new ArrayList<>();
    }

    public void addGrade(int id, int grade) {
        grades.add(new Grade(id, grade));
    }

    public ArrayList<Grade> getGrades() {
        return grades;
    }
}

class GradeCalculator {
    public double calculateAverage(ArrayList<Grade> grades) {
        if (grades.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Grade grade : grades) {
            sum += grade.getGrade();
        }
        return (double) sum / grades.size();
    }

    public Grade findHighest(ArrayList<Grade> grades) {
        if (grades.isEmpty()) {
            return null;
        }
        Grade highest = grades.get(0);
        for (Grade grade : grades) {
            if (grade.getGrade() > highest.getGrade()) {
                highest = grade;
            }
        }
        return highest;
    }

    public Grade findLowest(ArrayList<Grade> grades) {
        if (grades.isEmpty()) {
            return null;
        }
        Grade lowest = grades.get(0);
        for (Grade grade : grades) {
            if (grade.getGrade() < lowest.getGrade()) {
                lowest = grade;
            }
        }
        return lowest;
    }
}

public class StudentGradeTracker {
    public static void main(String[] args) {
        GradeManager gradeManager = new GradeManager();
        GradeCalculator gradeCalculator = new GradeCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter student IDs and grades. Type '-1' to finish.");

        while (true) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            if (id == -1) {
                break;
            }

            System.out.print("Enter grade: ");
            int grade = scanner.nextInt();
            gradeManager.addGrade(id, grade);
        }

        ArrayList<Grade> grades = gradeManager.getGrades();
        if (grades.isEmpty()) {
            System.out.println("No grades entered.");
        } else {
            double average = gradeCalculator.calculateAverage(grades);
            Grade highestGrade = gradeCalculator.findHighest(grades);
            Grade lowestGrade = gradeCalculator.findLowest(grades);

            System.out.println("Average grade: " + average);
            if (highestGrade != null) {
                System.out.println("Highest grade: " + highestGrade.getGrade() + " (ID: " + highestGrade.getId() + ")");
            }
            if (lowestGrade != null) {
                System.out.println("Lowest grade: " + lowestGrade.getGrade() + " (ID: " + lowestGrade.getId() + ")");
            }
        }
        scanner.close();
    }
}
