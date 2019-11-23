package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
    }


    @Override
    public int compareTo(Student student) {
        return 0;
    }

    public String getName() {
        return name;
    }
}
