import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a stock trader.
 * 
 * @author me
 * @version 1
 */
public class Trader
    implements Comparable<Trader>
{
    private Brokerage     brokerage;
    private String        screenName;
    private String        password;
    private TraderWindow  myWindow;
    private Queue<String> mailbox;

    /**
     * @param broker
     *            a
     * @param name
     *            a
     * @param pswd
     *            a
     */
    public Trader(Brokerage broker, String name, String pswd)
    {
        screenName = name;
        password = pswd;
        brokerage = broker;
        mailbox = new LinkedList<String>();

    }


    /**
     * @return a
     */
    public String getName()
    {
        return screenName;
    }


    /**
     * @return a
     */
    public String getPassword()
    {
        return password;
    }


    /**
     * @return a
     * @param joe
     *            a
     */
    public int compareTo(Trader joe)
    {
        return screenName.compareToIgnoreCase(joe.getName());
    }


    /**
     * @param obj
     *            a
     * @return a
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Trader))
        {
            throw new ClassCastException();
        }
        else
        {
            Trader joe = (Trader)obj;
            return screenName.equalsIgnoreCase(joe.getName());
        }
    }


    /**
     * a
     */
    public void openWindow()
    {
        myWindow = new TraderWindow(this);
        if (hasMessages())
        {
            for (String msg : mailbox)
            {
                mailbox.remove(msg);
                myWindow.showMessage(msg);
            }
        }

    }


    /**
     * @return a
     */
    public boolean hasMessages()
    {
        return !mailbox.isEmpty();
    }


    /**
     * @param msg
     *            a
     */
    public void receiveMessage(String msg)
    {
        mailbox.add(msg);
        if (myWindow != null && hasMessages())
        {
            for (String mesg : mailbox)
            {
                mailbox.remove(mesg);
                myWindow.showMessage(mesg);
            }
        }
        
    }


    /**
     * @param symbol
     *            a
     */
    public void getQuote(String symbol)
    {
        brokerage.getQuote(symbol, this);
    }


    /**
     * @param order
     *            a
     */
    public void placeOrder(TradeOrder order)
    {
        brokerage.placeOrder(order);
    }


    /**
     * a
     */
    public void quit()
    {
        brokerage.logout(this);
        myWindow = null;
    }


    //
    // The following are for test purposes only
    //
    /**
     * @return a
     */
    protected Queue<String> mailbox()
    {
        return mailbox;
    }


    /**
     * @return a
     */
    protected TraderWindow tradeWindow()
    {
        return myWindow;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Trader.
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
                if (field.getType().getName().equals("Brokerage"))
                {
                    str += separator + field.getType().getName()
                        + " " + field.getName();
                }

                else
                {
                    str += separator + field.getType().getName()
                        + " " + field.getName() + ":" + field.get(this);
                }

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
