import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a brokerage.
 * 
 * @author me
 * @version 1
 */
public class Brokerage
    implements Login
{
    private Map<String, Trader> traders;
    private Set<Trader>         loggedTraders;
    private StockExchange       exchange;

    /**
     * @param exchange
     *            a
     */
    public Brokerage(StockExchange exchange)
    {
        this.exchange = exchange;
        traders = new TreeMap<String, Trader>();
        loggedTraders = new TreeSet<Trader>();
    }


    /**
     * @param name
     *            a
     * @param pswd
     *            a
     * @return a
     */
    public int addUser(String name, String pswd)
    {
        if (name.length() < 4 || name.length() > 10)
        {
            return -1;
        }
        else if (pswd.length() < 2 || pswd.length() > 10)
        {
            return -2;
        }
        else if (traders.containsKey(name))
        {
            return -3;
        }
        else
        {
            Trader bob = new Trader(this, name, pswd);
            traders.put(name, bob);
            return 0;
        }
    }


    /**
     * @param name
     *            a
     * @param pswd
     *            a
     * @return a
     */
    public int login(String name, String pswd)
    {
        Trader joe = new Trader(this, name, pswd);
        if (!traders.containsKey(name))
        {
            return -1;
        }
        else if (!traders.get(name).getPassword().equals(pswd))
        {
            return -2;
        }
        else if (loggedTraders.contains(joe))
        {
            return -3;
        }
        else
        {
            if (!joe.hasMessages())
            {
                System.out.println("Welcome to SafeTrade!");
            }
            joe.openWindow();
            loggedTraders.add(joe);
            return 0;
        }
    }


    /**
     * @param joe
     *            a
     */
    public void logout(Trader joe)
    {
        loggedTraders.remove(joe);
    }


    /**
     * @param symbol
     *            a
     * @param joe
     *            a
     */
    public void getQuote(String symbol, Trader joe)
    {
        String quote = exchange.getQuote(symbol);
        joe.receiveMessage(quote);
    }


    /**
     * @param order
     *            a
     */
    public void placeOrder(TradeOrder order)
    {
        exchange.placeOrder(order);
    }


    //
    // The following are for test purposes only
    //
    /**
     * @return a
     */
    protected Map<String, Trader> getTraders()
    {
        return traders;
    }


    /**
     * @return a
     */
    protected Set<Trader> getLoggedTraders()
    {
        return loggedTraders;
    }


    /**
     * @return a
     */
    protected StockExchange getExchange()
    {
        return exchange;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Brokerage.
     */
    public String toString()
    {
        String str = this.getClass().getName() + "[";
        String separator = "";

        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields)
        {
            try
            {
                str += separator + field.getType().getName()
                    + " " + field.getName() + ":" + field.get(this);
            }
            catch (IllegalAccessException ex)
            {
                System.out.println(ex);
            }

            separator = ", ";
        }

        return str + "]";
    }
}
