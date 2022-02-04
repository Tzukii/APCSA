import java.util.*;

/**
 * Implements a Mailserver through a LinkedList that behaves like a
 * queue.
 * 
 * @author Shivam Maji
 * @version 1/12/2021
 * 
 * @author Period - 6
 * @author Assignment - JM Ch21.6 - Actors World
 * 
 * @author Sources - Me Myself and I
 */
public class MailServer extends LinkedList<Message> {
    private Set<Actor> actors;
    private LinkedList<Actor> server;

    /**
     * Constructor using a TreeSet
     */
    MailServer() {
        actors = new TreeSet<Actor>();
    }

    /**
     * Adds an actor to the actors set object
     * 
     * @param actor The actor you want to add to the set
     */
    public void signUp(Actor actor) {
        actors.add(actor);
    }

    /**
     * sends msg to the recipient indicated in msg (by calling the recipient’s
     * receive(msg)), or, if the recipient is null, to all registered subscribers
     * (except the sender).
     * 
     * @param msg The message you want to send
     */
    public void dispatch(Message msg) {
        if (msg.getRecipient() != null) {
            msg.getRecipient().receive(msg);
            return;
        }

        Actor newActor = msg.getSender();

        for (Actor actor : actors) {
            if (actor != newActor) {
                actor.receive(msg);
            }
        }
    }

    // for testing purposes only
    protected Set<Actor> getActors() {
        return actors;
    }

}
