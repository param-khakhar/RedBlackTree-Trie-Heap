package PriorityQueue;

public class Student implements Comparable<Student> {
    private String name;
    private Integer marks;

    public Student(String trim, int parseInt) {
    	name = trim;
    	marks = parseInt;
    }

    public int getMarks() {
    	return marks;
    }
    
    public void setMarks(int m) {
    	marks = m;
    }
    
    @Override
    public int compareTo(Student student) {
        if(marks == student.getMarks()) {
        	return 0;
        }
        else if(marks>student.getMarks()) {
        	return 1;
        }
        else {
        	return -1;
        }
    }

    public String getName() {
        return name;
    }
    
    public String toString() {
    	//Student{name='Bhavesh Kumar', marks=100}

    	return "Student{name='"+name+"', marks="+marks+"}";
    }
}
