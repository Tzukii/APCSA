/**
 * Represents a complex number of the form a + bi. Provides methods for adding,
 * multiplying and calculating the absolute value.
 *
 * @author Shivam
 * @version Maji
 * @author Period: 6
 * @author Assignment: JMCh09Ex17_Complex
 *
 * @author Sources: Me Myself and I
 */
public class CollegeStudent extends Student {
    private int myYear;
    private String myMajor;

    /**
     * Returns a String representation of this class.
     * 
     * @param name   bruh
     * @param age    bruh
     * @param gender bruh
     * @param idNum  bruh
     * @param gpa    bruh
     * @param year   bruh
     * @param major  bruh
     */

    public CollegeStudent(String name, int age, String gender, String idNum, double gpa, int year, String major) {
        super(name, age, gender, idNum, gpa);
        myYear = year;
        myMajor = major;
    }

    /**
     * Returns a String representation of this class.
     * 
     * @return private instance data as a String
     */

    public int getYear() {
        return this.myYear;
    }

    /**
     * Returns a String representation of this class.
     * 
     * @param y bruh
     */

    public void setYear(int y) {
        myYear = y;
    }

    /**
     * Returns a String representation of this class.
     * 
     * @return private instance data as a String
     */

    public String getMajor() {
        return this.myMajor;
    }

    /**
     * Returns a String representation of this class.
     * 
     * @param m bruh
     */

    public void setMajor(String m) {
        myMajor = m;
    }

    /**
     * Returns a String representation of this class.
     * 
     * @return private instance data as a String
     */
    public String toString() {
        return super.toString() + ", year: " + myYear + ", major: " + myMajor;
    }
}
