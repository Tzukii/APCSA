/**
 * Represents a single digit with a given base
 * 
 * @author Shivam Maji
 * @version 4/5/2021
 * 
 * @author Period - 7
 * @author Assignment - Lab: A21.1 - Numbers
 * 
 * @author Sources - Me Myself and I
 */
public class Digit {
    private int base;
    private int value;

    /**
     * Makes a new Digit Object
     */
    public Digit() {
        base = 10;
        value = 0;
    }

    /**
     * Creates new Digit object
     * 
     * @param val the value
     * @param b   the base
     */
    public Digit(int value, int base) {
        this.base = base;
        this.value = value;
    }

    /**
     * If value = base, give true, else false.
     * 
     * @return boolean
     */
    public boolean increment() {
        if (base - 1 != value) {
            value++;
            return false;
        } else {
            value = 0;
            return true;
        }

    }

    /**
     * If value < 10, prints the value
     * Prints code if bringer than 10
     * 
     * @return str that has string designated to value.
     */
    public String toString() {
        if (value < 10) {
            return value + "";
        } else {
            return (char) (value - 10 + 'A') + "";
        }

    }
}
