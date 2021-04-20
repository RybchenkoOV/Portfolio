package L9_StreamAPI;

import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private List<Course> attendCourse;

    public Student(String name, List<Course> attendCourse) {
        this.name = name;
        this.attendCourse = attendCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getAttendCourse() {
        return attendCourse;
    }

    public void setAttendCourse(List<Course> attendCourse) {
        this.attendCourse = attendCourse;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", attendCourse=" + attendCourse +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name) && Objects.equals(attendCourse, student.attendCourse);
    }

}
