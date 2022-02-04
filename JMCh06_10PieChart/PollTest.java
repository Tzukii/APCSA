/**
 * @author Shivam Maji
 * @version 9/3/21
 * @author Period: 5
 * @author Assignment: JMCh06_10PieChartSoln
 *
 */
public class PollTest {
    public static void main(String[] args){ 
        PollDisplayPanel votingMachine = new PollDisplayPanel("Tami", "Brian", "Liz");
        votingMachine.vote1(); votingMachine.vote2(); votingMachine.vote2();
        System.out.println(votingMachine);


        
    }
}
