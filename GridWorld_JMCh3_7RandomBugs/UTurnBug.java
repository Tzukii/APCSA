import info.gridworld.actor.Bug;
import java.awt.Color;

public class UTurnBug extends Bug
{
    public UTurnBug()
    {
        setColor( Color.YELLOW );
    }

    public UTurnBug( Color bugColor )
    {
        setColor( bugColor );
    }

    public void turn(int angle)
    {
        angle = 45 * (int)(Math.random() * 8);
        setDirection(getDirection() + angle);
    }

    // Overrides Bug's act method
    public void act()
    {
        if ( canMove() )
            move();
        else
            turn();
    }
}
