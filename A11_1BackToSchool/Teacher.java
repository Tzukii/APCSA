// TODO add comments
public class Teacher extends Person {
    private String mySubject;
    private double mySalary;

    public Teacher(String name, int age, String gender, String subject, double salary) {
        super(name, age, gender);
        mySubject = subject;
        mySalary = salary;
    }

    private String getSubject(Teacher t){
        return subject;jkjmkljkkjkkmopmoko
    }

    /**
     * Returns a String representation of this class.
     * 
     * @return private instance data as a String
     */
    public String toString() {
        return super.toString() + ", subject: " + mySubject + ", salary: " + mySalary;
    }
}
