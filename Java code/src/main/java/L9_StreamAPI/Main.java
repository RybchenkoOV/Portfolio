package L9_StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        /** Создаем список курсов */
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Java", 30));
        courseList.add(new Course("Python", 20));
        courseList.add(new Course("QA", 20));
        courseList.add(new Course("SQL", 10));

        /** Создаем список студентов */
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Anna", Arrays.asList(courseList.get(0), courseList.get(1))));
        studentList.add(new Student("Mike", Arrays.asList(courseList.get(0))));
        studentList.add(new Student("Linda", courseList));
        studentList.add(new Student("Kate", Arrays.asList(courseList.get(0), courseList.get(1))));
        studentList.add(new Student("Peter", Arrays.asList(courseList.get(0), courseList.get(1), courseList.get(2))));
        studentList.add(new Student("Pablo", courseList)); // Павел посещает все, поэтому перечислять не стал

        System.out.println(getUniqCourses(studentList)); // Вызываем метод для получения уникальных курсов, на которые подписаны студенты
        System.out.println(getCurious(studentList)); // Вызываем метод для определения любознательных студентов
        System.out.println("Метод для определения студентов, посещающих конкретный курс");
        System.out.println("Для курса Java:");
        System.out.println(checkCourse(studentList, courseList.get(0))); // Вызываем метод для определения студентов, посещающих курс Java
        System.out.println("Для курса SQL:");
        System.out.println(checkCourse(studentList, courseList.get(3))); // Вызываем метод для определения студентов, посещающих курс SQL
    }

    /**
     * Метод для получения уникальных курсов, на которые подписаны студенты
     */
    public static List<Course> getUniqCourses(List<Student> students) {
        System.out.println();
        System.out.println("****************************");
        System.out.println("Метод для получения уникальных курсов, на которые подписаны студенты");
        List<Course> uniqCourses = students.stream()
                .map(f -> f.getAttendCourse())
                .flatMap(f -> f.stream())
                .distinct()
                .collect(Collectors.toList());
        return uniqCourses;
    }

    /**
     * Метод для определения любознательных студентов
     */
    public static List<Student> getCurious(List<Student> students) {
        System.out.println();
        System.out.println("****************************");
        System.out.println("Метод для определения любознательных студентов");
        List<Student> curious = students.stream()
                .sorted((f1, f2) -> f2.getAttendCourse().size() - f1.getAttendCourse().size())
                .limit(3)
                .collect(Collectors.toList());
        return curious;
    }

    /**
     * Метод для определения студентов, посещающих конкретный курс
     */
    public static List<Student> checkCourse(List<Student> students, Course course) {
        System.out.println("****************************");
        List<Student> checkExactCourse = students.stream()
                .filter(f -> f.getAttendCourse().contains(course))
                .collect(Collectors.toList());
        return checkExactCourse;
    }
}
