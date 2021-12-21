import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/**
 * Test for the complex number class.
 * 
 * testConstructor1Param - test the 1 parameter constructor
 * testConstructor2Param - test the 2 parameter constructor
 * testAddRealAndComplexNum - addition of a complex number with a real number
 * testAdd2ComplexNums - addition of two complex numbers
 * testMultiply2ComplexNums - multiplication of two complex numbers
 * testMultiplyRealAndComplexNum - multiplication of a complex number with a
 * real number testAbsoluteValue - absolute value of a complex number
 *
 * @author TODO Your Name
 * @version TODO Date
 * @author Period: TODO Your Period
 * @author Assignment: JMCh09Ex17_Complex
 *
 * @author Sources: TODO List collaborators
 */
public class ComplexJUTest extends junit.framework.TestCase {
    /**
     * Test for the complex number class.
     */
    @Test
    public void testConstructor1Param() {
        Complex complex = new Complex();
    }

    /**
     * Test for the complex number class.
     */
    @Test
    public void testConstructor2Param() {
        Complex complex = new Complex(1.0, 2.0);
    }

    /**
     * Test for the complex number class.
     */
    @Test
    public void testAddRealAndComplexNum() {
        Complex complex = new Complex(1.0, 2.0);
        Complex c2 = complex.add(1.0);

        assertEquals(c2.toString(), "2.0 + 2.0i");
    }

    /**
     * Test for the complex number class.
     */
    @Test
    public void testAdd2ComplexNums() {
        Complex complex = new Complex(1.0, 2.0);
        Complex complex2 = new Complex(1.0, 2.0);

        Complex complex3 = complex.add(complex2);
    }

    /**
     * Test for the complex number class.
     */
    @Test
    public void testMultiply2ComplexNums() {
        Complex complex = new Complex(1.0, 2.0);
        Complex complex2 = new Complex(1.0, 2.0);

        Complex complex3 = complex.multiply(complex2);
    }

    /**
     * Test for the complex number class.
     */
    @Test
    public void testMultiplyRealAndComplexNum() {
        Complex complex = new Complex(1.0, 2.0);

        Complex complex2 = complex.multiply(2.0);
    }

    /**
     * Test for the complex number class.
     */
    @Test
    public void testAbsoluteValue() {
        Complex complex = new Complex(3.0, 4.0);
        double abs = complex.abs();
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(ComplexJUTest.class);
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("ComplexJUTest");
    }
}
