import java.lang.reflect.*;
import java.util.*;

/**
 * Represents a stock exchange. A <code>StockExchange</code> keeps a
 * <code>HashMap</code> of stocks, keyed by a stock symbol. It has methods to
 * list a new stock, request a quote for a given stock symbol, and to place a
 * specified trade order.
 * 
 * @author me
 * @version 1
 */
public class StockExchange
{
    private Map<String, Stock> listedStocks;

    /**
     * a
     */
    public StockExchange()
    {
        listedStocks = new HashMap<String, Stock>();
    }


    /**
     * @param stockSymbol
     *            a
     * @return a
     */
    public String getQuote(String stockSymbol)
    {
        if (!listedStocks.containsKey(stockSymbol))
        {
            return stockSymbol + " not found";
        }
        else
        {
            Stock stock = listedStocks.get(stockSymbol);
            return stock.getQuote();
        }
    }


    /**
     * @param symbol
     *            a
     * @param company
     *            a
     * @param price
     *            a
     */
    public void listStock(String symbol, String company, double price)
    {
        listedStocks.put(symbol, new Stock(symbol, company, price));
    }


    /**
     * @param order
     *            a
     */
    public void placeOrder(TradeOrder order)
    {
        String symbol = order.getSymbol();
        if (!listedStocks.containsKey(symbol))
        {
            order.getTrader().receiveMessage(symbol + " not found");
        }
        else
        {
            Stock stock = listedStocks.get(symbol);
            stock.placeOrder(order);
        }
    }


    //
    // The following are for test purposes only
    //
    /**
     * @return a
     */
    protected Map<String, Stock> getListedStocks()
    {
        return listedStocks;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this StockExchange.
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
