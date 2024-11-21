package dominio;
import java.awt.Color;


public abstract class Artefact{

    public final static char UNKNOWN='u';
    public final static char ACTIVE='a';
    public final static char INACTIVE='d';
    protected char state;
    private int steps;

    /**Create a new Artefact
     *
     */
    public Artefact(){
        state=UNKNOWN;
        steps=0;
    }


    /**The Artefact makes one step
     *
     */
    protected void step(){
        steps++;
    }

    /**Returns the step
     @return
     */
    public final int getSteps(){
        return steps;
    }

    /**Returns if active
     @return true, if ACTIVE; false, otherwise
     */
    public final boolean isActive(){
        return (state == Artefact.ACTIVE) ;
    }

}
