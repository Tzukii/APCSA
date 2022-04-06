import java.lang.reflect.*;

/**
 * Represents a buy or sell order for trading a given number of shares of a
 * specified stock.
 * 
 * @author me
 * @version 1
 */
public class TradeOrder
{
    private Trader  trader;
    private String  symbol;
    private boolean buyOrder;
    private boolean marketOrder;
    private int     numShares;
    private double  price;

    /**
     * @param joe
     *            a
     * @param ticker
     *            a
     * @param bOdr
     *            a
     * @param mOdr
     *            a
     * @param shares
     *            a
     * @param cost
     *            a
     */
    public TradeOrder(
        Trader joe,
        String ticker,
        boolean bOdr,
        boolean mOdr,
        int shares,
        double cost)
    {
        trader = joe;
        symbol = ticker;
        buyOrder = bOdr;
        marketOrder = mOdr;
        numShares = shares;
        price = cost;
    }


    /**
     * @return a
     */
    public double getPrice()
    {
        return price;
    }


    /**
     * @return a
     */
    public int getShares()
    {
        return numShares;
    }


    /**
     * @return a
     */
    public String getSymbol()
    {
        return symbol;
    }


    /**
     * @return a
     */
    public Trader getTrader()
    {
        return trader;
    }


    /**
     * @return a
     */
    public boolean isBuy()
    {
        return buyOrder;
    }


    /**
     * @return a
     */
    public boolean isLimit()
    {
        return !marketOrder;
    }


    /**
     * @return a
     */
    public boolean isMarket()
    {
        return marketOrder;
    }


    /**
     * @return a
     */
    public boolean isSell()
    {
        return !buyOrder;
    }


    /**
     * @param shares
     *            a
     */
    public void subtractShares(int shares)
    {
        if (shares > numShares)
        {
            throw new IllegalArgumentException();
        }
        numShares = numShares - shares;
    }


    //
    // The following are for test purposes only
    //
    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this TradeOrder.
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
