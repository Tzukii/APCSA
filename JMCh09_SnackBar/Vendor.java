import java.lang.reflect.*;

/**
 * This class implements a vendor that sells one kind of item. A vendor carries
 * out sales transactions.
 * 
 * @author Shivam Maji
 * @version 9/29/21
 * 
 * @author Period - T6
 * @author Assignment - Java Methods Ch09 - SnackBar
 * 
 * @author Sources - Me Myself and I
 */
public class Vendor {
    // Fields:
    private int price;
    private int stock;
    private int deposit;
    private int change;
    private static double totalSales = 0;

    // Constructor
    // Parameters:
    // int price of a single item in cents
    // int number of items to place in stock
    public Vendor(int price, int stock) {
        this.price = price;
        this.stock = stock;
    }

    // Sets the quantity of items in stock.
    // Parameters:
    // int number of items to place in stock
    // Return:
    // None
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Returns the number of items currently in stock.
    // Parameters:
    // None
    // Return:
    // int number of items currently in stock
    public int getStock() {
        return stock;
    }

    // Adds a specified amount (in cents) to the deposited amount.
    // Parameters:
    // int number of cents to add to the deposit
    // Return:
    // None
    public void addMoney(int d) {
        if (stock > 0) {
            deposit += d;
        }
    }

    // Returns the currently deposited amount (in cents).
    // Parameters:
    // None
    // Return:
    // int number of cents in the current deposit
    public int getDeposit() {
        return deposit;
    }

    // Implements a sale. If there are items in stock and
    // the deposited amount is greater than or equal to
    // the single item price, then adjusts the stock
    // and calculates and sets change and returns true;
    // otherwise refunds the whole deposit (moves it into change)
    // and returns false.
    // Parameters:
    // None
    // Return:
    // boolean successful sale (true) or failure (false)
    public boolean makeSale() {
        if (stock > 0 && deposit >= price) {
            stock--;
            totalSales += ((price) / 100.0);
            change = deposit - price;
            deposit = 0;
            return true;
        } 
        change += deposit;
        deposit = 0;
        return false;
    }

    // Returns and zeroes out the amount of change (from the last
    // sale or refund).
    // Parameters:
    // None
    // Return:
    // int number of cents in the current change
    public int getChange() {
        int c = change;
        change = 0;
        return c;
    }

    public static double getTotalSales() {
        double t = totalSales;
        totalSales = 0;
        return t;
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
     * @return a string representation of this Vendor.
     */
    public String toString() {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                str += separator + field.getName() + ":" + field.get(this);
            } catch (IllegalAccessException ex) {
                System.out.println(ex);
            }

            separator = ", ";
        }

        return str + "]";
    }
}
