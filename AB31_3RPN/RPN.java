import java.util.*;

/**
 * RPN Calculator
 *
 * @author Shivam Maji
 * @version 1/4/2021
 * @author Period: 6
 * @author Assignment: AB31RPN Calculator
 *
 * @author Sources: Me Myself and I
 */
public class RPN {
    Scanner scan = new Scanner(System.in);

    private Stack<Integer> myStack;
    private Queue<String> myQ;

    /**
     * Constructs an RPN Calculator
     */
    public RPN() {
        myStack = new Stack<Integer>();
        myQ = new LinkedList<String>();
    }

    /**
     * **** Used for testing - Do Not Remove ***
     * 
     * Constructs an RPN Calculator and then redirects the Scanner input
     * to the supplied string.
     * 
     * @param console replaces console input
     */
    public RPN(String console) {
        this();
        scan = new Scanner(console);
    }

    /**
     * Calculates values for the RPN Output
     */
    public void calculate() {
        /**
         * Reads the expression using the helper method
         */
        readExpression();

        /**
         * Use an enhanced for loop to iterate through each String value in myQ
         */
        for (String token : myQ) {
            char c = token.charAt(0);

            /**
             * If the current character is c, then append it to myStack
             */
            if (Character.isDigit(c)) {
                myStack.add(c - '0');
                continue;
            }

            /**
             * Set val1 as the first item in myStack, val2 as the 2nd item in myStack.
             * Create an int result but do not initialize it.
             */
            int val1 = myStack.pop();
            int val2 = myStack.pop();
            int result;

            /**
             * Use a switch case statement to calculate values based on conditions. Save
             * this value to result
             */
            switch (c) {
                case '+':
                    result = val2 + val1;
                    break;
                case '-':
                    result = val2 - val1;
                    break;
                case '*':
                    result = val2 * val1;
                    break;
                case '/':
                    result = val2 / val1;
                    break;
                default:
                    result = Integer.MIN_VALUE;
                    break;
            }

            /**
             * Append result to myStack
             */

            myStack.add(result);
        }

        printProblemAndAnswer(myStack.pop());
    }

    /**
     * Reads the expression on the next line
     */
    public void readExpression() {
        String input = scan.nextLine();
        while (!input.equalsIgnoreCase("q")) {
            myQ.add(input);
            input = scan.nextLine();
        }
    }

    /**
     * Prints myQ and asks for an answer as input
     * 
     * @param answer Answer to be printed
     */
    public void printProblemAndAnswer(int answer) // answer = 4
    {
        for (String token : myQ) {
            System.out.print(token + " ");
        }
        System.out.print("= " + answer);
    }

    /**
     * Instantiates an RPN Calculator and invokes it calculate method
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        RPN rpn = new RPN();
        rpn.calculate();
    }
}