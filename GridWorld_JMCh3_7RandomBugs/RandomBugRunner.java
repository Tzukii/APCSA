import info.gridworld.actor.Bug;
import java.awt.Color;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;

public class RandomBugRunner extends RandomBug{

    public static void main( String[] args )
    {
        ActorWorld world = new ActorWorld();
        RandomBugRunner alice = new RandomBugRunner();
        RandomBugRunner bob = new RandomBugRunner();
        world.add( new Location( 7, 8 ), alice );
        world.add( new Location( 5, 5 ), bob );
        world.show();
    }


}
