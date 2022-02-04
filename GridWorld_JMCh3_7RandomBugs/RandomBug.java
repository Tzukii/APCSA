import info.gridworld.actor.Bug;
import java.awt.Color;

public class RandomBug extends Bug
{
    public RandomBug()
    {
        setColor( Color.YELLOW );
    }

    public RandomBug( Color bugColor )
    {
        setColor( bugColor );
    }

    public void turn(int angle)
    {
        // angle = 45 * (int)(Math.random() * 8);
        setDirection(getDirection() + angle);
    }

    // Overrides Bug's act method
    public void act()
    {
        if ( canMove() ){
            move();
        }
        turn(45 * (int)(Math.random() * 8));

            
    }
}
