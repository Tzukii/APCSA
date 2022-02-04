import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * bruh
 *
 * @author Shivam
 * @version Maji
 * @author Period: 5
 * @author Assignment: BlusterCritter - GridWorld Part4 Exercise 4
 *
 * @author Sources: Me Myself and I
 */
public class BlusterCritter extends Critter {
    private int courage;
    private static final double DARKENING_FACTOR = 0.05;

    /**
     * bruh
     *
     * @author Shivam
     * @version Maji
     * @author Period: 5
     * @author Assignment: BlusterCritter - GridWorld Part4 Exercise 4
     *
     * @author Sources: Me Myself and I
     * @param c bruh
     */

    public BlusterCritter(int c) {
        courage = c;
    }

    /**
     * javadoc Postcondition: The state of all actors is unchanged.
     * 
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<Actor>(24);
        Grid<Actor> gr = getGrid();

        Location currLoc = getLocation();

        for (int r = -2; r <= 2; r++) {
            for (int c = -2; c <= 2; c++) {
                Location checkLoc = new Location(currLoc.getRow() + r, 
                    currLoc.getCol() + c);

                if (gr.isValid(checkLoc)) {
                    Actor a = gr.get(checkLoc);

                    if (a != null && a != this) {
                        actors.add(gr.get(checkLoc));
                    }
                }
            }
        }
        return actors;
    }

    /**
     * davajoc Postcondition: (1) The state of all actors in the grid other than
     * this critter and the elements of <code>actors</code> 
     * is unchanged. (2) The
     * location of this critter is unchanged.
     * 
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors) {
        super.processActors(actors);
        if (getActors().size() < courage) {
            lighten();
        } 
        else {
            darken();
        }
    }

    /**
     * javadoc
     */
    private void darken() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
    }

    /**
     * javadoc
     */
    private void lighten() {
        Color c = getColor();
        int red = (int) ((c.getRed() + 2) * (1 + DARKENING_FACTOR));
        int green = (int) ((c.getGreen() + 2) * (1 + DARKENING_FACTOR));
        int blue = (int) ((c.getBlue() + 2) * (1 + DARKENING_FACTOR));
        if (red > 255) {
            red = 255;
        }
        if (green > 255) {
            green = 255;
        }
        if (blue > 255) {
            blue = 255;
        }
        setColor(new Color(red, green, blue));
    }
}
