import java.beans.Transient;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.regex.*;
import java.util.*;

import org.junit.*;

import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;

/**
 * SafeTrade tests: TradeOrder PriceComparator Trader Brokerage StockExchange
 * Stock
 *
 * @author Nishad
 * @author Chris
 * @author Shivam
 * @version 3.22.22
 * @author Assignment: JM Chapter 19 - SafeTrade
 * @author Sources:
 */
public class JUSafeTradeTest
{
    // --Test TradeOrder
    /**
     * TradeOrder tests: TradeOrderConstructor - constructs TradeOrder and then
     * compare toString TradeOrderGetTrader - compares value returned to
     * constructed value TradeOrderGetSymbol - compares value returned to
     * constructed value TradeOrderIsBuy - compares value returned to
     * constructed value TradeOrderIsSell - compares value returned to
     * constructed value TradeOrderIsMarket - compares value returned to
     * constructed value TradeOrderIsLimit - compares value returned to
     * constructed value TradeOrderGetShares - compares value returned to
     * constructed value TradeOrderGetPrice - compares value returned to
     * constructed value TradeOrderSubtractShares - subtracts known value &
     * compares result returned by getShares to expected value
     */
    private String  symbol        = "GGGL";
    private boolean buyOrder      = true;
    private boolean marketOrder   = true;
    private int     numShares     = 123;
    private int     numToSubtract = 24;
    private double  price         = 123.45;

    @Test
    public void tradeOrderConstructor()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        String toStr = to.toString();

        assertTrue(
            "<< Invalid TradeOrder Constructor >>",
            toStr.contains("TradeOrder[Trader trader:null")
                && toStr.contains("java.lang.String symbol:" + symbol)
                && toStr.contains("boolean buyOrder:" + buyOrder)
                && toStr.contains("boolean marketOrder:" + marketOrder)
                && toStr.contains("int numShares:" + numShares)
                && toStr.contains("double price:" + price));
    }


    @Test
    public void TradeOrderToString()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertNotNull(to.toString());
    }


    @Test
    public void tradeOrderGetTrader()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertNull("<< TradeOrder: " + to.getTrader() + " should be null >>", to.getTrader());
    }


    @Test
    public void tradeOrderGetSymbol()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertEquals(
            "<< TradeOrder: " + to.getTrader() + " should be " + symbol + " >>",
            symbol,
            to.getSymbol());
    }


    @Test
    public void tradeOrderIsBuy()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);

        assertTrue("<< TradeOrder: " + to.isBuy() + " should be " + buyOrder + " >>", to.isBuy());
    }


    @Test
    public void tradeOrderIsSell()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertFalse(
            "<< TradeOrder: " + to.isSell() + " should be " + !buyOrder + " >>",
            to.isSell());
    }


    @Test
    public void tradeOrderIsMarket()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertTrue(
            "<< TradeOrder: " + to.isMarket() + " should be " + marketOrder + " >>",
            to.isMarket());
    }


    @Test
    public void tradeOrderIsLimit()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);

        assertFalse(
            "<< TradeOrder: " + to.isLimit() + " should be " + !marketOrder + ">>",
            to.isLimit());
    }


    @Test
    public void tradeOrderGetShares()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertTrue(
            "<< TradeOrder: " + to.getShares() + " should be " + numShares + ">>",
            numShares == to.getShares() || (numShares - numToSubtract) == to.getShares());
    }


    @Test
    public void tradeOrderGetPrice()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        assertEquals(
            "<< TradeOrder: " + to.getPrice() + " should be " + price + ">>",
            price,
            to.getPrice(),
            0.0);
    }


    @Test
    public void tradeOrderSubtractShares()
    {
        TradeOrder to = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        to.subtractShares(numToSubtract);
        assertEquals(
            "<< TradeOrder: subtractShares(" + numToSubtract + ") should be "
                + (numShares - numToSubtract) + ">>",
            numShares - numToSubtract,
            to.getShares());
    }


    // --Test TraderWindow Stub
    @Test
    public void traderWindowConstructor()
    {
        Trader test = new Trader(brokerage, screenName, password);
        TraderWindow tw = new TraderWindow(test);
        assertNotNull(tw);
    }


    @Test
    public void traderWindowShowMessage()
    {
        Trader test = new Trader(brokerage, screenName, password);
        TraderWindow tw = new TraderWindow(test);
        assertNotNull(tw);
        tw.showMessage(null);
    }

    // --Test PriceComparator


    @Test
    public void priceComparatorCompare()
    {
        PriceComparator sus = new PriceComparator();
        TradeOrder order1 = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);
        TradeOrder order2 = new TradeOrder(null, symbol, buyOrder, marketOrder, numShares, price);

        sus.compare(order1, order2);
    }

    // --Test Trader

    private StockExchange exchange   = new StockExchange();
    private Brokerage     brokerage  = new Brokerage(exchange);
    private String        screenName = "test";
    private String        password   = "icicles";
    Trader                test       = new Trader(brokerage, "test", "icicles");
    private TraderWindow  myWindow;
    private Queue<String> mailbox;

    @Test
    public void traderConstructor()
    {
        Trader to = new Trader(brokerage, screenName, password);
        String toStr = to.toString();

        assertTrue(
            "<< Invalid TradeOrder Constructor >>",
            toStr.contains("Trader[") && toStr.contains("Brokerage brokerage")
                && toStr.contains("java.lang.String screenName:" + screenName)
                && toStr.contains("java.lang.String password:" + password));
    }


    @Test
    public void traderToString()
    {
        Trader test = new Trader(brokerage, screenName, password);
        assertNotNull(test.toString());
    }


    @Test
    public void traderGetName()
    {
        Trader test = new Trader(brokerage, screenName, password);
        assertEquals(test.getName(), "test");
    }


    @Test
    public void traderGetPassword()
    {
        Trader test = new Trader(brokerage, screenName, password);
        assertEquals(test.getPassword(), "icicles");
    }


    @Test
    public void traderCompareTo()
    {
        Trader test = new Trader(brokerage, screenName, password);
        Trader testE = new Trader(brokerage, "bobe", password);
        assertTrue("error", test.compareTo(testE) > 0); 
    }


    @Test
    public void traderEquals()
    {
        Trader test = new Trader(brokerage, screenName, password);
        Trader testE = new Trader(brokerage, screenName.toUpperCase(), password);
        assertEquals(test, testE);
    }


    @Test
    public void traderOpenWindow()
    {
        Trader test = new Trader(brokerage, screenName, password);
        test.openWindow();
        assertTrue("error", test.tradeWindow() != null);
    }


    @Test
    public void traderHasMessages()
    {
        Trader test = new Trader(brokerage, screenName, password);
        assertFalse("error", test.hasMessages()); 
    }

    @Test
    public void traderQuit()
    {
        Trader test = new Trader(brokerage, screenName, password);
        test.quit();
        assertNull(test.tradeWindow());
    }

    @Test
    public void traderReceiveMessage() {
        Trader test = new Trader(brokerage, screenName, password);
        brokerage.login(screenName, password);
        test.receiveMessage("mseag");
        assertTrue("error", test.hasMessages()); 
    }

    @Test
    public void traderGetQuote() {
        Trader test = new Trader(brokerage, screenName, password);
        StockExchange exchange = new StockExchange();
        brokerage.login(screenName, password);
        exchange.listStock("RANDOM", "piss", 1);
        test.getQuote("RANDOM");
        assertTrue("error", test.hasMessages()); 
    }

    @Test
    public void traderPlaceOrder() {
        Trader test = new Trader(brokerage, screenName, password);
        StockExchange exchange = new StockExchange();
        exchange.listStock("RANDOM", "piss", 1);
        test.placeOrder(new TradeOrder(test, "RANDOM", true, true, 1, 1));
        assertTrue("error", test.hasMessages()); 
    }

    // --Test Stock


    @Test
    public void stockQuote()
    {
        String ticker = "TSLA";
        String company = "Tesla";
        int num = 100;
        Stock stock = new Stock(ticker, company, num);
        String str = stock.getQuote();

        assertTrue(str.contains(ticker) && str.contains(company) && str.contains("" + num));
    }


    public void stockPlaceOrder()
    {
        StockExchange exchange = new StockExchange();
        Brokerage broke = new Brokerage(exchange);
        String name = "joe";
        String name1 = "bob";
        Trader joe = new Trader(broke, name, name);
        Trader bob = new Trader(broke, name1, name1);


        String ticker = "TSLA";
        String company = "Tesla";
        int num = 100;
        Stock stock = new Stock(ticker, company, num);

        //--------------(buy/sell, market/limit)----------------//

        // i >> true
        TradeOrder a = new TradeOrder(joe, ticker, true, false, num, num);
        TradeOrder b = new TradeOrder(bob, ticker, false, false, num + 1, num);
        stock.placeOrder(a);
        stock.placeOrder(b);
        assertTrue(stock.getBuyOrders().isEmpty() && stock.getSellOrders().isEmpty());

        // i >> false
        a = new TradeOrder(joe, ticker, true, false, num, num);
        b = new TradeOrder(bob, ticker, false, false, num - 1, num);
        stock.placeOrder(a);
        stock.placeOrder(b);
        assertFalse(stock.getBuyOrders().isEmpty() && stock.getSellOrders().isEmpty());

        // ii >> true
        a = new TradeOrder(joe, ticker, true, true, num, stock.getLastPrice());
        b = new TradeOrder(bob, ticker, false, false, num, num);
        stock.placeOrder(a);
        stock.placeOrder(b);
        assertTrue(stock.getBuyOrders().isEmpty() && stock.getSellOrders().isEmpty());

        // iii >> true
        a = new TradeOrder(joe, ticker, true, true, num, stock.getLastPrice());
        b = new TradeOrder(bob, ticker, false, false, num, stock.getLastPrice());
        stock.placeOrder(a);
        stock.placeOrder(b);
        assertTrue(stock.getBuyOrders().isEmpty() && stock.getSellOrders().isEmpty());

    }

    @Test
    public void stockConstructor()
    {
        Stock test = new Stock("RANDOM", "piss", 1);
        String testString = test.toString();
        assertTrue("no", testString.contains("Stock[") &&
                    testString.contains("java.text.DecimalFormat MONEY:") &&
                    testString.contains("java.lang.String stockSymbol:") &&
                    testString.contains("java.lang.String companyName:") &&
                    testString.contains("double loPrice:") &&
                    testString.contains("double hiPrice:") &&
                    testString.contains("double lastPrice") &&
                    testString.contains("int volume:") &&
                    testString.contains("java.util.PriorityQueue buyOrders:") &&
                    testString.contains("java.util.PriorityQueue sellOrders:") &&
                    testString.contains("int countOrders:"));
    }

    @Test
    public void stockToString()
    {
        Stock test = new Stock("RANDOM", "piss", 1);
        assertNotNull(test.toString());
    }
    // --Test Brokerage

    @Test
    public void brokerageConstructor()
    {
        StockExchange to = new StockExchange();
        Brokerage test = new Brokerage(to);
        String testString = test.toString();
        assertTrue("no bueno", testString.contains("Brokerage[") &&
                    testString.contains("java.util.Map traders:") &&
                    testString.contains("java.util.Set loggedTraders:") &&
                    testString.contains("StockExchange exchange:"));
    }

    @Test
    public void brokerageAddUser()
    {
        StockExchange to = new StockExchange();
        Brokerage test = new Brokerage(to);
        test.addUser(screenName, password);
        assertTrue("nah", test.getTraders().containsKey(screenName));
    }

    @Test
    public void brokerageLogin()
    {
        StockExchange to = new StockExchange();
        Brokerage test = new Brokerage(to);
        Trader joe = new Trader(test, screenName, password);
        test.addUser(screenName, password);
        test.login(screenName, password);
        assertTrue("nah", test.getLoggedTraders().contains(joe));
    }

    @Test
    public void brokerageLogout()
    {
        StockExchange to = new StockExchange();
        Brokerage test = new Brokerage(to);
        Trader joe = new Trader(test, screenName, password);
        test.login(screenName, password);
        test.logout(joe);
        assertFalse("nah", test.getLoggedTraders().contains(joe));
    }

    @Test
    public void brokerageGetQuote()
    {
        StockExchange exchange = new StockExchange();
        Brokerage test = new Brokerage(exchange);
        Trader joe = new Trader(test, screenName, password);
        test.login(screenName, password);
        exchange.listStock("RANDOM", "piss", 1);
        test.getQuote("RANDOM", joe);
        assertTrue("no work", joe.hasMessages());
    }

    @Test
    public void brokeragePlaceOrder()
    {
        Trader test = new Trader(brokerage, screenName, password);
        StockExchange exchange = new StockExchange();
        exchange.listStock("RANDOM", "piss", 1);
        brokerage.placeOrder(new TradeOrder(test, "RANDOM", true, true, 1, 1));
        assertTrue("error", test.hasMessages()); 
    }
    // --Test StockExchange

    private Map<String, Stock> listedStocks = new HashMap<String, Stock>();;

    @Test
    public void stockExchangeConstructor()
    {
        StockExchange to = new StockExchange();
        String toStr = to.toString();

        assertTrue(
            "<< Invalid StockExchange Constructor >>",
            toStr.contains("StockExchange[")
                && toStr.contains("java.util.Map listedStocks:" + listedStocks));
    }

    @Test
    public void stockExchangeGetQuote() 
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock("RANDOM", "piss", 1);
        String quote = exchange.getQuote("RANDOM");
        assertTrue("no work", quote.contains("piss") && 
                    quote.contains("RANDOM"));
    }

    @Test
    public void stockExchangeListStock()
    {
        StockExchange exchange = new StockExchange();
        exchange.listStock("RANDOM", "piss", 1);
        assertTrue("didn't put", exchange.getListedStocks().containsKey("RANDOM"));
    }

    @Test
    public void stockExchangePlaceOrder()
    {
        Trader test = new Trader(brokerage, screenName, password);
        brokerage.login(screenName, password);
        StockExchange exchange = new StockExchange();
        exchange.listStock("RANDOM", "piss", 1);
        exchange.placeOrder(new TradeOrder(test, "RANDOM", true, true, 1, 1));
        assertTrue("error", test.hasMessages()); 
    }

    @Test
    public void stockExchangeToString()
    {
        StockExchange to = new StockExchange();
        assertNotNull(to.toString());
    }
    // TODO your tests here

    // Remove block comment below to run JUnit test in console


    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter(JUSafeTradeTest.class);
    }


    public static void main(String args[])
    {
        org.junit.runner.JUnitCore.main("JUSafeTradeTest");
    }

}
