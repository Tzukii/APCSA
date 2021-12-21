import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * Coins: This class accepts a certain amount of monetary change (in cents). The
 * output is a list of the number of quarters, dimes, nickels, and pennies that
 * will make that amount of change with the least number of coins possible.
 *
 * @author TODO enter your name
 * @version TODO enter the date
 * @author Period: TODO enter your period
 * 
 * @author Assignment: Lab Activity 3.2 - Coins
 * 
 * @author Sources: TODO: list collaborators
 */
public class Coins {
    private int myChange;
    private int quarters;
    private int pennies;
    private int nickels;
    private int dimes;

    public Coins(int change) {
        myChange = change;
    }

    public void calculate() {
        quarters = myChange / 25;

        myChange %= 25;

        dimes = myChange / 10;

        myChange %= 10;

        nickels = myChange / 5;

        myChange %= 5;

        pennies = myChange;

        System.out.println("Quarters: " + quarters);
        System.out.println("Dimes: " + dimes);
        System.out.println("Nickels: " + nickels);
        System.out.println("Pennies: " + pennies);


    }

    /**
     * Intended only for debugging.
     * 
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that superclass
     * fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Easter.
     */
    public String toString() {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                str += separator + field.getType().getName() + " " + field.getName() + ":" + field.get(this);
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }
            separator = ", ";
        }
        return str + "]";
    }

    /**
     * Tester for the Coins class.
     * 
     * @param args command line arguments - not used
     */
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Please enter the number of cents --> ");
        int cents = console.nextInt();

        Coins change = new Coins(cents);
        change.calculate();
    }
}
