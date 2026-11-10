package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class LessonFive1 {

    // Класс студента
    static class Student {
        private String name;
        private String group;
        private int course;
        private List<Integer> grades;

        public Student(String name, String group, int course, List<Integer> grades) {
            this.name = name;
            this.group = group;
            this.course = course;
            this.grades = new ArrayList<>(grades);
        }

        // Метод для подсчета среднего балла
        public double getAverageGrade() {
            if (grades.isEmpty()) return 0.0;
            return grades.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        }

        public int getCourse() { return course; }
        public void setCourse(int course) { this.course = course; }
        public String getName() { return name; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(name, student.name) && Objects.equals(group, student.group);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, group);
        }

        @Override
        public String toString() {
            return name + " (Группа: " + group + ", Курс: " + course + ", Средний балл: " +
                    String.format("%.2f", getAverageGrade()) + ")";
        }
    }

    // Удаляет студентов со средним баллом < 3
    public static void removeFailedStudents(Set<Student> students) {
        students.removeIf(student -> student.getAverageGrade() < 3.0);
    }

    // Переводит студентов на следующий курс, если средний балл >= 3
    public static void advanceToNextCourse(Set<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    // Печатает имена студентов указанного курса
    public static void printStudents(Set<Student> students, int course) {
        System.out.println("--- Студенты " + course + " курса ---");
        boolean found = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Студентов на данном курсе не найдено.");
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new LinkedHashSet<>();

        students.add(new Student("Иванов", "ИТ-1", 1, Arrays.asList(5, 4, 5, 4))); // Средний: 4.5
        students.add(new Student("Петров", "ИТ-1", 1, Arrays.asList(2, 3, 2, 2))); // Средний: 2.25
        students.add(new Student("Сидоров", "ИТ-2", 2, Arrays.asList(3, 3, 4, 3))); // Средний: 3.25
        students.add(new Student("Смирнов", "ИТ-1", 1, Arrays.asList(5, 5, 5, 5))); // Средний: 5.0

        System.out.println("Изначальный список:");
        students.forEach(System.out::println);

        // Удаляем троечников
        removeFailedStudents(students);
        System.out.println("\nПосле удаления студентов с баллом < 3:");
        students.forEach(System.out::println);

        // Переводим на следующий курс
        advanceToNextCourse(students);
        System.out.println("\nПосле перевода на следующий курс:");
        students.forEach(System.out::println);

        // Печатаем студентов 2-го курса
        printStudents(students, 2);
    }
}
