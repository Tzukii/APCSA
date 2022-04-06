import java.math.BigDecimal;

/**
 * Represents a number with a given base
 * 
 * @author Shivam Maji
 * @version 4/5/2021
 * 
 * @author Period - 7
 * @author Assignment - Lab: A21.1 - Numbers
 * 
 * @author Sources - Me Myself and I
 */
public class Number {
  private int base = 10;
  private double value = 0.0;

  /**
   * no args constructor
   */
  public Number() {
    // no args constructor
  }

  /**
   * 2 args constructor
   *
   * @param value value of number
   * @param base  base of the value
   */
  public Number(double value, int base) {
    this.value = value;
    this.base = base;
  }

  /**
   * increment the value
   */
  public void increment() {
    value++;
  }

  @Override
  public String toString() {
    return new BigDecimal(String.valueOf(value))
        .toBigInteger().toString(base);
  }
}