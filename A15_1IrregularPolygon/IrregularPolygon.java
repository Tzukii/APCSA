import java.awt.geom.*;
import java.util.*;
import gpdraw.*;

/**
 * Among Us Sus
 *
 * @author Shivam
 * @version 11/9/21
 *
 * @author Period - 6
 * @author Assignment - Lab: A15.1 - IrregularPolygon
 * 
 * @author Sources - Among us
 */
public class IrregularPolygon {

    /**
     * sus
     */
    private DrawingTool pen; // = new DrawingTool(new SketchPad(300, 300, 0));
    private ArrayList<Point2D.Double> myPolygon;

    /**
     * amogus
     *
     */

    public IrregularPolygon() {
        myPolygon = new ArrayList<Point2D.Double>();
    }

    /**
     * @param aPoint sus
     *
     */

    public void add(Point2D.Double aPoint) {
        myPolygon.add(aPoint);
    }

    /**
     * @param a sus
     * @param b sus
     * @return bruh
     */

    public double calculateDistance(int a, int b) {
        return Math.sqrt(Math.pow((myPolygon.get(a).getX() 
            - myPolygon.get(b).getX()), 2)
                + Math.pow((myPolygon.get(a).getY() 
                    - myPolygon.get(b).getY()), 2));
    }

    /**
     * @return sus
     */

    public double perimeter() {
        if (myPolygon.size() < 3) {
            return 0;
        } 
        else 
        {
            double perimeter = calculateDistance(0, myPolygon.size() - 1);
            for (int i = 1; i < myPolygon.size(); i++) {
                perimeter += calculateDistance(i, i - 1);
            }

            return perimeter;
        }
    }

    /**
     * 
     * @return sus
     */

    public double area() {
        if (myPolygon.size() < 3) {
            return 0;
        } 
        else {
            double a1 = (myPolygon.get(myPolygon.size() - 1).getX() * 
                myPolygon.get(0).getY());
            double a2 = (myPolygon.get(myPolygon.size() - 1).getY() * 
                myPolygon.get(0).getX());
            for (int i = 1; i < myPolygon.size(); i++) {
                a1 += (myPolygon.get(i - 1).getX() * 
                    myPolygon.get(i).getY());
                a2 += (myPolygon.get(i - 1).getY() * 
                    myPolygon.get(i).getX());
            }

            return Math.abs((a1 - a2) / 2);
        }
    }

    /**
     * sus
     */

    public void draw() {
        if (myPolygon.size() < 3) {
            return;
        } 
        else {
            pen.up();
            pen.move(myPolygon.get(0).getX(), myPolygon.get(0).getY());
            pen.down();

            for (int i = 1; i < myPolygon.size(); i++) {
                pen.move(myPolygon.get(i).getX(), myPolygon.get(i).getY());
            }
            pen.move(myPolygon.get(0).getX(), myPolygon.get(0).getY());
        }
    }
}
