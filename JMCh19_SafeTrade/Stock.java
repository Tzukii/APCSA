import java.util.*;
import java.lang.reflect.*;
import java.text.DecimalFormat;

/**
 * Represents a stock in the SafeTrade project
 * 
 * @author chris's mom
 * @version right now
 */
public class Stock
{   
    /**
     * a
     */
    public static final DecimalFormat MONEY = new DecimalFormat("0.00");

    private String                    stockSymbol;
    private String                    companyName;
    private double                    loPrice;
    private double                    hiPrice;
    private double                    lastPrice;

    private int                       volume;
    private PriorityQueue<TradeOrder> buyOrders;
    private PriorityQueue<TradeOrder> sellOrders;

    private int                       countOrders;

    /**
     * @param ticker
     *            - the stock symbol.
     * @param company
     *            - full company name.
     * @param price
     *            - opening price for this stock.
     */
    public Stock(String ticker, String company, double price)
    {
        stockSymbol = ticker;
        companyName = company;
        lastPrice = price;
        loPrice = price;
        hiPrice = price;
        volume = 0;

        buyOrders = new PriorityQueue<TradeOrder>(
        100, new PriceComparator(false));
        sellOrders = new PriorityQueue<TradeOrder>(
        100, new PriceComparator(true));

        countOrders = 0;

    }


    /**
     * @return the quote
     */
    public String getQuote()
    {
        TradeOrder ask = sellOrders.peek();
        TradeOrder bid = buyOrders.peek();
        String str = companyName + " (" + stockSymbol + ")";
        str += "\n Price: " + lastPrice;
        str += " hi: " + hiPrice + " lo: " + loPrice + " vol: " + volume;
        if (sellOrders.isEmpty()) 
        {
            str += "\n Ask: none";
        }
        else {
            str += "\n Ask: " + ask.getPrice() 
                + " size: " + ask.getShares();
        }
        if (buyOrders.isEmpty()) 
        {
            str += " Bid: none";
        }
        else 
        {
            str += " Bid: " + bid.getPrice()
                + " size: " + bid.getShares();
        }

        return str;
    }


    /**
     * @param order
     *            places order
     */
    public void placeOrder(TradeOrder order)
    {
        String buyOrSell = "";
        String limitOrMarket = "";

        if (order.isBuy())
        {
            buyOrders.add(order);
            buyOrSell = "Buy";
        }

        else
        {
            sellOrders.add(order);
            buyOrSell = "Sell";
        }

        if (order.isMarket())
        {
            limitOrMarket = "market";
        }

        else
        {
            limitOrMarket = "$" + MONEY.format(order.getPrice());
        }

        Trader bob = order.getTrader();

        String message = "New order: " + buyOrSell + " ";
        message += stockSymbol + "(" + companyName + ")";
        message += "\n " + order.getShares() + " shares at " + limitOrMarket;
        bob.receiveMessage(message);
        executedOrders();
        

    }


    /**
     * exectues it
     */
    protected void executedOrders()
    {
        Iterator<TradeOrder> iterBuy = buyOrders.iterator();
        Iterator<TradeOrder> iterSell = sellOrders.iterator();

        while (iterBuy.hasNext())
        {
            TradeOrder tradeBuy = iterBuy.next();

            while (iterSell.hasNext())
            {
                TradeOrder tradeSell = iterSell.next();

                if (tradeSell.getSymbol().equals(tradeBuy.getSymbol()))
                {
                    String symbol = tradeSell.getSymbol();

                    // i: both are limit and buy price >= sell price
                    if (tradeBuy.isLimit() && tradeSell.isLimit()
                        && tradeBuy.getPrice() >= tradeSell.getPrice())
                    {
                        if (tradeBuy.getShares() == tradeSell.getShares())
                        {
                            String toSeller = "You sold: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(tradeSell.getPrice()) 
                                + " amt "
                                + MONEY.format(tradeSell.getPrice() 
                                * tradeSell.getShares());
                            String toBuyer = "You bought: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(tradeSell.getPrice()) 
                                + " amt "
                                + MONEY.format(tradeSell.getPrice() 
                                * tradeSell.getShares());

                            tradeSell.getTrader().receiveMessage(toSeller);
                            tradeBuy.getTrader().receiveMessage(toBuyer);

                            volume += tradeSell.getShares();
                            lastPrice = tradeSell.getPrice();

                            if (countOrders == 0)
                            {
                                loPrice = tradeSell.getPrice();
                            }
                            else if (loPrice > tradeSell.getPrice())
                            {
                                loPrice = tradeSell.getPrice();
                            }

                            if (hiPrice < tradeSell.getPrice())
                            {
                                hiPrice = tradeSell.getPrice();
                            }

                            iterBuy.remove();
                            iterSell.remove();
                        }

                        else if (tradeBuy.getShares() > 
                            tradeSell.getShares())
                        {
                            String toSeller = "You sold: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(tradeSell.getPrice()) 
                                + " amt "
                                + MONEY.format(tradeSell.getPrice() 
                                * tradeSell.getShares());
                            String toBuyer = "You bought: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(tradeSell.getPrice()) 
                                + " amt "
                                + MONEY.format(tradeSell.getPrice() 
                                * tradeSell.getShares());

                            tradeSell.getTrader().receiveMessage(toSeller);
                            tradeBuy.getTrader().receiveMessage(toBuyer);

                            volume += tradeSell.getShares();
                            lastPrice = tradeSell.getPrice();

                            if (countOrders == 0)
                            {
                                loPrice = tradeSell.getPrice();
                            }
                            else if (loPrice > tradeSell.getPrice())
                            {
                                loPrice = tradeSell.getPrice();
                            }

                            if (hiPrice < tradeSell.getPrice())
                            {
                                hiPrice = tradeSell.getPrice();
                            }

                            tradeBuy.subtractShares(tradeSell.getShares());
                            iterSell.remove();
                        }

                        else
                        { // else if (tradeBuy.getShares() <
                          // tradeSell.getShares()) {
                            String toSeller = "You sold: " 
                                + tradeBuy.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(tradeSell.getPrice()) 
                                + " amt "
                                + MONEY.format(tradeSell.getPrice() 
                                * tradeBuy.getShares());
                            String toBuyer = "You bought: " 
                                + tradeBuy.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(tradeSell.getPrice()) 
                                + " amt "
                                + MONEY.format(tradeSell.getPrice() 
                                * tradeBuy.getShares());

                            tradeSell.getTrader().receiveMessage(toSeller);
                            tradeBuy.getTrader().receiveMessage(toBuyer);

                            volume += tradeBuy.getShares();
                            lastPrice = tradeSell.getPrice();

                            if (countOrders == 0)
                            {
                                loPrice = tradeSell.getPrice();
                            }
                            else if (loPrice > tradeSell.getPrice())
                            {
                                loPrice = tradeSell.getPrice();
                            }

                            if (hiPrice < tradeSell.getPrice())
                            {
                                hiPrice = tradeSell.getPrice();
                            }

                            iterBuy.remove();
                            tradeSell.subtractShares(tradeBuy.getShares());
                        }
                    }

                    // ii: either is limit
                    else if (tradeBuy.isLimit() || tradeSell.isLimit())
                    {
                        if (tradeBuy.isLimit())
                        {
                            if (tradeBuy.getShares() == tradeSell.getShares())
                            {
                                String toSeller = "You sold: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeBuy.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeBuy.getPrice() 
                                    * tradeSell.getShares());
                                String toBuyer = "You bought: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeBuy.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeBuy.getPrice() 
                                    * tradeSell.getShares());

                                tradeSell.getTrader().receiveMessage(toSeller);
                                tradeBuy.getTrader().receiveMessage(toBuyer);

                                volume += tradeSell.getShares();
                                lastPrice = tradeBuy.getPrice();

                                if (countOrders == 0)
                                {
                                    loPrice = tradeSell.getPrice();
                                }
                                else if (loPrice > tradeSell.getPrice())
                                {
                                    loPrice = tradeSell.getPrice();
                                }

                                if (hiPrice < tradeSell.getPrice())
                                {
                                    hiPrice = tradeSell.getPrice();
                                }

                                iterBuy.remove();
                                iterSell.remove();
                            }

                            else if (tradeBuy.getShares() > 
                                tradeSell.getShares())
                            {
                                String toSeller = "You sold: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeBuy.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeBuy.getPrice() 
                                    * tradeSell.getShares());
                                String toBuyer = "You bought: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeBuy.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeBuy.getPrice() 
                                    * tradeSell.getShares());

                                tradeSell.getTrader().receiveMessage(toSeller);
                                tradeBuy.getTrader().receiveMessage(toBuyer);

                                volume += tradeSell.getShares();
                                lastPrice = tradeBuy.getPrice();

                                if (countOrders == 0)
                                {
                                    loPrice = tradeSell.getPrice();
                                }
                                else if (loPrice > tradeSell.getPrice())
                                {
                                    loPrice = tradeSell.getPrice();
                                }

                                if (hiPrice < tradeSell.getPrice())
                                {
                                    hiPrice = tradeSell.getPrice();
                                }

                                tradeBuy.subtractShares(tradeBuy.getShares());
                                iterSell.remove();
                            }

                            else
                            { // else if (tradeBuy.getShares() <
                              // tradeSell.getShares()) {
                                String toSeller = "You sold: " 
                                    + tradeBuy.getShares() 
                                    + " " + symbol
                                    + " at $" 
                                    + MONEY.format(tradeBuy.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeBuy.getPrice() 
                                    * tradeBuy.getShares());
                                String toBuyer = "You bought: " 
                                    + tradeBuy.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeBuy.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeBuy.getPrice() 
                                    * tradeBuy.getShares());

                                tradeSell.getTrader().receiveMessage(toSeller);
                                tradeBuy.getTrader().receiveMessage(toBuyer);

                                volume += tradeBuy.getShares();
                                lastPrice = tradeBuy.getPrice();

                                if (countOrders == 0)
                                {
                                    loPrice = tradeSell.getPrice();
                                }
                                else if (loPrice > tradeSell.getPrice())
                                {
                                    loPrice = tradeSell.getPrice();
                                }

                                if (hiPrice < tradeSell.getPrice())
                                {
                                    hiPrice = tradeSell.getPrice();
                                }

                                iterBuy.remove();
                                tradeSell.subtractShares(tradeBuy.getShares());
                            }
                        }

                        else
                        { // else if (tradeSell.isLimit()) {
                            if (tradeBuy.getShares() == tradeSell.getShares())
                            {
                                String toSeller = "You sold: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeSell.getPrice())
                                    + " amt "
                                    + MONEY.format(tradeSell.getPrice() 
                                    * tradeSell.getShares());
                                String toBuyer = "You bought: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeSell.getPrice())
                                    + " amt "
                                    + MONEY.format(tradeSell.getPrice() 
                                    * tradeSell.getShares());

                                tradeSell.getTrader().receiveMessage(toSeller);
                                tradeBuy.getTrader().receiveMessage(toBuyer);

                                volume += tradeSell.getShares();
                                lastPrice = tradeSell.getPrice();

                                if (countOrders == 0)
                                {
                                    loPrice = tradeSell.getPrice();
                                }
                                else if (loPrice > tradeSell.getPrice())
                                {
                                    loPrice = tradeSell.getPrice();
                                }

                                if (hiPrice < tradeSell.getPrice())
                                {
                                    hiPrice = tradeSell.getPrice();
                                }

                                iterBuy.remove();
                                iterSell.remove();
                            }

                            else if (tradeBuy.getShares() > 
                                tradeSell.getShares())
                            {
                                String toSeller = "You sold: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeSell.getPrice())
                                    + " amt "
                                    + MONEY.format(tradeSell.getPrice() 
                                    * tradeSell.getShares());
                                String toBuyer = "You bought: " 
                                    + tradeSell.getShares() + " "
                                    + symbol + " at $" 
                                    + MONEY.format(tradeSell.getPrice())
                                    + " amt "
                                    + MONEY.format(tradeSell.getPrice() 
                                    * tradeSell.getShares());

                                tradeSell.getTrader().receiveMessage(toSeller);
                                tradeBuy.getTrader().receiveMessage(toBuyer);

                                volume += tradeSell.getShares();
                                lastPrice = tradeSell.getPrice();

                                if (countOrders == 0)
                                {
                                    loPrice = tradeSell.getPrice();
                                }
                                else if (loPrice > tradeSell.getPrice())
                                {
                                    loPrice = tradeSell.getPrice();
                                }

                                if (hiPrice < tradeSell.getPrice())
                                {
                                    hiPrice = tradeSell.getPrice();
                                }

                                tradeBuy.subtractShares(tradeSell.getShares());
                                iterSell.remove();
                            }

                            else
                            { // else if (tradeBuy.getShares() <
                              // tradeSell.getShares()) {
                                String toSeller = "You sold: " 
                                    + tradeBuy.getShares() + " " + symbol
                                    + " at $" 
                                    + MONEY.format(tradeSell.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeSell.getPrice() 
                                    * tradeBuy.getShares());
                                String toBuyer =
                                    "You bought: " + tradeBuy.getShares() 
                                    + " " + symbol + " at $"
                                    + MONEY.format(tradeSell.getPrice()) 
                                    + " amt "
                                    + MONEY.format(tradeSell.getPrice() 
                                    * tradeBuy.getShares());

                                tradeSell.getTrader().receiveMessage(toSeller);
                                tradeBuy.getTrader().receiveMessage(toBuyer);

                                volume += tradeBuy.getShares();
                                lastPrice = tradeSell.getPrice();

                                if (countOrders == 0)
                                {
                                    loPrice = tradeSell.getPrice();
                                }
                                else if (loPrice > tradeSell.getPrice())
                                {
                                    loPrice = tradeSell.getPrice();
                                }

                                if (hiPrice < tradeSell.getPrice())
                                {
                                    hiPrice = tradeSell.getPrice();
                                }

                                iterBuy.remove();
                                tradeSell.subtractShares(tradeBuy.getShares());
                            }
                        }
                    }

                    // iii: both are market
                    else
                    {
                        if (tradeBuy.getShares() == tradeSell.getShares())
                        {
                            String toSeller = "You sold: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(lastPrice) 
                                + " amt "
                                + MONEY.format(lastPrice 
                                * tradeSell.getShares());
                            String toBuyer = "You bought: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(lastPrice) 
                                + " amt "
                                + MONEY.format(lastPrice 
                                * tradeSell.getShares());

                            tradeSell.getTrader().receiveMessage(toSeller);
                            tradeBuy.getTrader().receiveMessage(toBuyer);

                            volume += tradeSell.getShares();

                            iterBuy.remove();
                            iterSell.remove();
                        }

                        else if (tradeBuy.getShares() > tradeSell.getShares())
                        {
                            String toSeller = "You sold: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(lastPrice) 
                                + " amt "
                                + MONEY.format(lastPrice 
                                * tradeSell.getShares());
                            String toBuyer = "You bought: " 
                                + tradeSell.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(lastPrice) 
                                + " amt "
                                + MONEY.format(lastPrice 
                                * tradeSell.getShares());

                            tradeSell.getTrader().receiveMessage(toSeller);
                            tradeBuy.getTrader().receiveMessage(toBuyer);

                            volume += tradeSell.getShares();

                            tradeBuy.subtractShares(tradeSell.getShares());
                            iterSell.remove();
                        }

                        else
                        { // else if (tradeBuy.getShares() <
                          // tradeSell.getShares()) {
                            String toSeller = "You sold: " 
                                + tradeBuy.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(lastPrice) 
                                + " amt "
                                + MONEY.format(lastPrice 
                                * tradeBuy.getShares());
                            String toBuyer = "You bought: " 
                                + tradeBuy.getShares() 
                                + " " + symbol
                                + " at $" 
                                + MONEY.format(lastPrice) 
                                + " amt "
                                + MONEY.format(lastPrice 
                                * tradeBuy.getShares());

                            tradeSell.getTrader().receiveMessage(toSeller);
                            tradeBuy.getTrader().receiveMessage(toBuyer);

                            volume += tradeBuy.getShares();

                            iterBuy.remove();
                            tradeSell.subtractShares(tradeBuy.getShares());
                        }
                    }
                    countOrders++;
                }
            }
        }
    }

    //
    // The following are for test purposes only
    //


    /**
     * @return a
     */
    protected String getStockSymbol()
    {
        return stockSymbol;
    }


    /**
     * @return a
     */
    protected String getCompanyName()
    {
        return companyName;
    }


    /**
     * @return a
     */
    protected double getLoPrice()
    {
        return loPrice;
    }


    /**
     * @return a
     */
    protected double getHiPrice()
    {
        return hiPrice;
    }


    /**
     * @return aa
     */
    protected double getLastPrice()
    {
        return lastPrice;
    }


    /**
     * @return a
     */
    protected int getVolume()
    {
        return volume;
    }


    /**
     * @return a
     */
    protected PriorityQueue<TradeOrder> getBuyOrders()
    {
        return buyOrders;
    }


    /**
     * @return a
     */
    protected PriorityQueue<TradeOrder> getSellOrders()
    {
        return sellOrders;
    }


    /**
     * <p>
     * A generic toString implementation that uses reflection to print names and
     * values of all fields <em>declared in this class</em>. Note that
     * superclass fields are left out of this implementation.
     * </p>
     * 
     * @return a string representation of this Stock.
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
                    + " " + field.getName() + ":"
                    + field.get(this);
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
