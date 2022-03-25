package week2.assignment4;

import java.util.ArrayList;
import java.util.List;

/**
 * 10 kişilik bir sınıf var.
 * Sınıftaki kişilerin numaraları ve cinsiyetlerini biliyorum.
 * bu sınıftaki kızların ve erkeklerin numalarını ayrı ayrı ekrana yazdıran program.
 *
 */

public class Main {

    public static void main(String[] args) {
        List<Student> studentList = generateStudentList();
        printMaleStudentIdNumbers(studentList);
        printFeMaleStudentIdNumbers(studentList);
    }

    private static List<Student> generateStudentList() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, Gender.FEMALE));
        studentList.add(new Student(2, Gender.FEMALE));
        studentList.add(new Student(3, Gender.MALE));
        studentList.add(new Student(4, Gender.FEMALE));
        studentList.add(new Student(5, Gender.MALE));
        studentList.add(new Student(6, Gender.FEMALE));
        studentList.add(new Student(7, Gender.MALE));
        studentList.add(new Student(8, Gender.MALE));
        studentList.add(new Student(9, Gender.FEMALE));
        studentList.add(new Student(10, Gender.MALE));

        return studentList;
    }

    private static void printMaleStudentIdNumbers(List<Student> list) {
        System.out.println("Student numbers of male students:");
        for (Student s : list) {
            if (s.getGender() == Gender.MALE) {
                System.out.println(s.getId());
            }
        }
    }

    private static void printFeMaleStudentIdNumbers(List<Student> list) {
        System.out.println("Student numbers of female students:");
        for (Student s : list) {
            if (s.getGender() == Gender.FEMALE) {
                System.out.println(s.getId());
            }
        }
    }
}
