package L9_StreamAPI;

public class Course {
    private String name;
    private int length;

    public Course(String name, int length) {
        this.name = name;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", length=" + length +
                '}';
    }
}
