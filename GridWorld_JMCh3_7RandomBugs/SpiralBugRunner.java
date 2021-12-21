import info.gridworld.actor.Actor;
import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;


public class SpiralBugRunner
{
    public static void main( String[] args )
    {
        UnboundedGrid grid = new UnboundedGrid<Actor>();
        ActorWorld world = new ActorWorld( grid );

        SpiralBug alice = new SpiralBug( 6 );
        SpiralBug bob = new SpiralBug( 3 );
        world.add( new Location( 0, 6 ), alice );
        world.add( new Location( 5, 5 ), bob );
        world.show();
    }
}