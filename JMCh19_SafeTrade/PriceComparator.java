/**
 * A price comparator for trade orders.
 * 
 * @author Shivam Maji thats cap
 * @version 3/19/21
 */
public class PriceComparator
    implements java.util.Comparator<TradeOrder>
{
    private boolean isAscending;

    /**
     * Constructs a price comparator that compares two orders in ascending
     * order. Sets the private boolean ascending flag to true.
     */
    public PriceComparator()
    {
        isAscending = true;
    }


    /**
     * Constructs a price comparator that compares two orders in ascending or
     * descending order. The order of comparison depends on the value of a given
     * parameter. Sets the private boolean ascending flag to asc.
     * 
     * @param asc
     *            if true, make an ascending comparator; otherwise make a
     *            descending comparator.
     */
    public PriceComparator(boolean asc)
    {
        isAscending = asc;
    }


    /**
     * Compares two trade orders.
     * 
     * @param order1
     *            the first order
     * @param order2
     *            the second order
     * @return 0 if both orders are market orders; 
     * -1 if order1 is market and
     *         order2 is limit; 
     * 1 if order1 is limit and order2 is market;
     *         difference in prices, rounded to the nearest cent, 
     * if both order1
     *         and order2 are limit orders. 
     * In the latter case, the difference
     *         returned is cents1 - cents2 or cents2 - cents1, 
     * depending on
     *         whether this is an 
     * ascending or descending comparator 
     * (ascending is true or false).
     */
    public int compare(TradeOrder order1, TradeOrder order2)
    {
        int retVal = -9999;

        if (order1.isMarket() && order2.isMarket())
        {
            retVal = 0;
        }
        else if (order1.isMarket() && order2.isLimit())
        {
            retVal = -1;
        }
        else if (order1.isLimit() && order2.isMarket())
        {
            retVal = 1;
        }
        else // else if (order1.isLimit() && order2.isLimit())
        {
            if (isAscending)
            {

                int val = (int)(order1.getPrice() * 100);
                int val2 = (int)(order2.getPrice() * 100);
                retVal = val - val2;
            }
            else
            {
                int val = (int)(order1.getPrice() * 100);
                int val2 = (int)(order2.getPrice() * 100);
                retVal = val2 - val;
            }

        }

        return retVal;
    }

}
