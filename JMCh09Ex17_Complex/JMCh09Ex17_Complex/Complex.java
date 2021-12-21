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

public class Complex {

    private double a;
    private double b;

    /**
     * Test for the complex number class.
     */
    public Complex() {
        a = 0.0;
        b = 0.0;
    }

    /**
     * @param a input
     */
    public Complex(double a) {
        this.a = a;
        b = 0.0;
    }

    /**
     * @param a input
     * @param b input
     */
    public Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Test for the complex number class.
     * @return bruh
     */
    public double abs() {
        return Math.sqrt(Math.pow(this.a, 2) + Math.pow(this.b, 2));
    }

    /**
     * @param t input
     * @return bruh
     */
    public Complex add(Complex t) {
        Complex s = new Complex();
        s.a = this.a + t.a;
        s.b = this.b + t.b;
        return s;
    }

    /**
     * Test for the complex number class.
     * @return bruh
     */
    public String toString() {
        String s = this.a + " + " + this.b + "i";
        return s;
    }

    /**
     * @param t input
     * @return bruh
     */
    public Complex multiply(Complex t) {
        // (a+bi)(c+di)
        // ac - bd + (bc + ad)i

        Complex s = new Complex();
        s.a = (this.a * t.a) - (this.b * t.b);
        s.b = (this.b * t.a) + (this.a * t.b);

        return s;
    }

    /**
     * @param input input
     * @return bruh
     */
    public Complex add(double input) {
        Complex s = new Complex();
        s.a = this.a + input;
        s.b = this.b;
        System.out.println(s.a);
        System.out.println(s.b);
        return s;
    }

    /**
     * @param input input
     * @return bruh
     */
    public Complex multiply(double input) {
        Complex s = new Complex();
        Complex s1 = new Complex(input);

        s = multiply(s1);
        return s;
    }

}
