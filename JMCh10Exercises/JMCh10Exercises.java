import java.util.*;

/**
 * Java Methods Chapter 10 Exercises 2, 6 - 11, 14 - 15, 20 & 22
 * 
 * @author Shivam Maji
 * @version 10/6/21
 * 
 * @author Period - 6
 * @author Assignment - Ch10Exercises
 * 
 * @author Sources - Me Myself and I
 */
public class JMCh10Exercises {
    Scanner scan;

    /**
     * Constructs a Scanner for input from the console.
     */
    public JMCh10Exercises() {
        scan = new Scanner(System.in);
    }

    /**
     * Constructs a Scanner to evaluate passed input (used for testing).
     * 
     * @param str input for the various methods
     */
    public JMCh10Exercises(String str) {
        scan = new Scanner(str);
    }

    // 10-2(a)
    public boolean endsWithStar(String s) {
        return (s.endsWith("*"));
    }

    // 10-2(b)
    public boolean endsWith2Stars(String s) {
        return (s.endsWith("**"));
    }

    // 10-6
    public String scroll(String s) {
        return s.substring(1) + s.substring(0, 1);
    }

    // 10-7
    public String convertName(String name) {
        int comma = name.indexOf(",");
        String last = "";
        String first = "";

        for (int i = 0; i < comma; i++) {
            last += name.charAt(i);
        }

        for (int i = comma + 1; i < name.length(); i++) {
            first += name.charAt(i);
        }

        first = first.trim();

        return first + " " + last;
    }

    // 10-8
    public String negate(String str) {
        String string1 = str.replace("0", "2");
        string1 = string1.replace("1", "0");
        string1 = string1.replace("2", "1");
        return string1;
    }

    // 10-9
    public boolean isConstant(String s) {
        if (scroll(s).equals(s)) {
            return true;
        }

        return false;
    }

    // 10-10
    public String removeComment(String str) {
        if(str.contains("/*") && str.contains("*/")){
            String str1 = str.substring(0, str.indexOf("/*")); 
            String str2 = str.substring(str.indexOf("*/") + 2);
            str = str1 + str2;
        }
        return str;
    }

    // 10-11
    public static String cutOut(String str, String s) {
        String str1 = "";
        // result is set to "Hi, hi-ho"
        int i = str.indexOf(s);
        int l = s.length();
        str1 = str.substring(0, i);
        str1 = str1 + str.substring(i + l);

        return str1; // FIXED!!!
    }

    // 10-14
    public String removeHtmlTags(String str) {
        String str1 = "";
        int oTag1 = str.indexOf("<");
        int oTag2 = str.indexOf(">");
        int cTag1 = str.lastIndexOf("<");
        int cTag2 = str.lastIndexOf(">");
        str1 = str.substring(0, oTag1);
        str1 = str1 + str.substring(oTag2 + 1, cTag1);
        str1 = str1 + str.substring(cTag2 + 1);
        return str1;
    }

    // 10-15
    public boolean onlyDigits(String s) {

        return s.matches("[0-9]+");

    }

    // 10-20
    public static boolean isValidISBN(String isbn) {

        int d, c = 10, s = 0;

        for (int i = 0; i < isbn.length(); i++) {
            if (isbn.charAt(i) == 'X')
                d = 10;
            else
                d = Integer.parseInt(String.valueOf(isbn.charAt(i)));
            s = s + d * c;
            c--;
        }
        if (s % 11 == 0)
            return true;
        else
            return false;
    } // FIXED!!!

    // 10-22
    public String shuffle(String s) {

        StringBuffer sb = new StringBuffer(s);
        int n = sb.length();
        Random random = new Random();
        while (n > 1) {
            int i = random.nextInt(n);

            char ch = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(n - 1));
            sb.setCharAt(n - 1, ch);
            n--;
        }
        s = sb.toString();
        return s; // FIXED!!!
    }

    /**
     * Testing method: instantiates a Ch18Ex1to5 object and then invokes each
     * method.
     * 
     * @param args command line parameters (not used)
     */
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        boolean done = false;

        JMCh10Exercises exercise = new JMCh10Exercises();

        do {
            System.out.println();
            System.out.println();
            System.out.println("Make a selection");
            System.out.println();
            System.out.println("   (1) 10-2(a) endsWithStar( String s )");
            System.out.println("   (2) 10-2(b) endsWith2Stars( String s )");
            System.out.println("   (3) 10-6 scroll( String s )");
            System.out.println("   (4) 10-7 convertName( String name )");
            System.out.println("   (5) 10-8 negate( String str )");
            System.out.println("   (6) 10-9 isConstant( String s )");
            System.out.println("   (7) 10-10 removeComment( String str )");
            System.out.println("   (8) 10-11 cutOut( String s, String s2 )");
            System.out.println("   (9) 10-14 removeHtmlTags( String str )");
            System.out.println("   (A) 10-15 onlyDigits( String s )");
            System.out.println("   (B) 10-20 isValidISBN( String isbn )");
            System.out.println("   (C) 10-22 shuffle( String s )");
            System.out.println("   (Q) Quit");
            System.out.println();
            System.out.print("Enter a choice:  ");
            String response = kbd.nextLine();

            if (response.length() > 0) {
                System.out.println();

                switch (response.charAt(0)) {
                    case '1':
                        String end1Star = "**endsWith**Star*";
                        System.out.println("endsWithStar(" + end1Star + ") = " + exercise.endsWithStar(end1Star));
                        String ends0Star = "**endsWith**Star*No";
                        System.out.println("endsWithStar(" + ends0Star + ") = " + exercise.endsWithStar(ends0Star));
                        break;
                    case '2':
                        String end2Str = "**endsWith**2Stars**";
                        System.out.println("endsWith2Stars(" + end2Str + ") = " + exercise.endsWith2Stars(end2Str));
                        String endsWith1Star = "**endsWith**2Stars*";
                        System.out.println(
                                "endsWith2Stars(" + endsWith1Star + ") = " + exercise.endsWith2Stars(endsWith1Star));
                        break;
                    case '3':
                        String scrollStr = "bdfhjlnprtvxz";
                        System.out.println(
                                "scroll(\"" + scrollStr + "\") --> " + "\"" + exercise.scroll(scrollStr) + "\"");
                        break;
                    case '4':
                        String convStr = "von Neumann, John";
                        System.out.println(
                                "convertName(\"" + convStr + "\") --> " + "\"" + exercise.convertName(convStr) + "\"");
                        break;
                    case '5':
                        String negStr = "1001110001010101110";
                        System.out.println("negate(\"" + negStr + "\") --> " + "\"" + exercise.negate(negStr) + "\"");
                        break;
                    case '6':
                        String constStr1 = "0000000000000000000";
                        String constStr2 = "1001110001010101110";
                        System.out.println("isConstant(\"" + constStr1 + "\") = " + exercise.isConstant(constStr1));
                        System.out.println("isConstant(\"" + constStr2 + "\") = " + exercise.isConstant(constStr2));
                        break;
                    case '7':
                        String comment = "/* this should be gone */ int a = 0;";
                        String notComment = "/* this should stay /* int a = 0;";
                        System.out.println("removeComment(\"" + comment + "\") --> " + "\""
                                + exercise.removeComment(comment) + "\"");
                        System.out.println("removeComment(\"" + notComment + "\") --> " + "\""
                                + exercise.removeComment(notComment) + "\"");
                        break;
                    case '8':
                        String cutstr = "Hi-ho, hi-ho";
                        String cutOutstr = "-ho";
                        System.out.println("cutOut(\"" + cutstr + "\", \"" + cutOutstr + "\") --> " + "\""
                                + exercise.cutOut(cutstr, cutOutstr) + "\"");
                        break;
                    case '9':
                        String htmlStr = "Strings are <b>immutable</b>";
                        System.out.println("removeHtmlTags(\"" + htmlStr + "\") --> " + "\""
                                + exercise.removeHtmlTags(htmlStr) + "\"");
                        break;
                    case 'A':
                    case 'a':
                        String digitStr = "123456789";
                        String dStr = "1234V5678";
                        System.out.println("onlyDigits(\"" + digitStr + "\") = " + exercise.onlyDigits(digitStr));
                        System.out.println("onlyDigits(\"" + dStr + "\") = " + exercise.onlyDigits(dStr));
                        break;
                    case 'B':
                    case 'b':
                        String validISBN = "096548534X";
                        String invalidISBN = "1234567890";
                        System.out.println("isValidISBN(\"" + validISBN + "\") = " + exercise.isValidISBN(validISBN));
                        System.out
                                .println("isValidISBN(\"" + invalidISBN + "\") = " + exercise.isValidISBN(invalidISBN));
                        break;
                    case 'C':
                    case 'c':
                        String str = "The quick brown fox";
                        System.out.println("shuffle(\"" + str + "\") --> " + "\"" + exercise.shuffle(str) + "\"");
                        System.out.println("shuffle(\"" + str + "\") --> " + "\"" + exercise.shuffle(str) + "\"");
                        break;
                    default:
                        if (response.toLowerCase().charAt(0) == 'q') {
                            done = true;
                        } else {
                            System.out.print("Invalid Choice");
                        }
                        break;
                }
            }
        } while (!done);
        System.out.println("Goodbye!");
    }
}
