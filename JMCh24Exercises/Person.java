import java.util.Objects;

/**
 * Question 24-20
 * 
 * Write a hashCode method for this class that agrees with the equals method
 * and returns different values for Persons of different ages.
 *
 * @author Shivam Maji
 * @version 2/13/21
 * @author Period: 6
 * @author Assignment: JMCh24Exercises Question 20
 *
 * @author Sources: Me Myself and I
 */
public class Person {
    private String name;
    private int age; // age <= 125

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }
        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name) && age == otherPerson.age;
    }

    public int hashCode() {
        return name.hashCode() + age;
    }

}
